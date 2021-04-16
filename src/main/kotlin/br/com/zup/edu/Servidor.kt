package br.com.zup.edu

import io.grpc.ServerBuilder
import io.grpc.stub.StreamObserver
import java.time.LocalDateTime
import java.time.ZoneId

fun main() {

    val server = ServerBuilder
        .forPort(50051)
        .addService(FuncionarioEndPoint())
        .build()

    server.start()
    server.awaitTermination()
}

class FuncionarioEndPoint: FuncionarioServiceGrpc.FuncionarioServiceImplBase(){
    override fun cadastrar(request: FuncionarioRequest?, responseObserver: StreamObserver<FuncionarioResponse>?) {

        println(request!!)

        var nome: String? = request.nome
        if(request.hasField(FuncionarioRequest.getDescriptor().findFieldByName("nome"))){
            nome = "[???]"
        }


        val instant = LocalDateTime.now().atZone(ZoneId.of("UTC"))
        val criadoEm = com.google.protobuf.Timestamp.newBuilder()
            .setSeconds(instant.toEpochSecond())
            .setNanos(instant.nano)
            .build()

        val response = FuncionarioResponse.newBuilder()
            .setNome(nome)
            .setCriadoEm(criadoEm)
            .build()

        responseObserver?.onNext(response)
        responseObserver?.onCompleted()
    }
}