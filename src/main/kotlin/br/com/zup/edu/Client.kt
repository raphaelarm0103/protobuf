package br.com.zup.edu

import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder

fun main() {

    val channel = ManagedChannelBuilder
        .forAddress("localhost", 50051)
        .usePlaintext()
        .build()


    val request = FuncionarioRequest.newBuilder()
        .setNome("Yuri Matheus")
        .setCpf("000.000.000-00")
        .setIdade(23)
        .setSalario(2000.20)
        .setAtivo(true)
        .setCargo(Cargo.QA)
        .addEnderecos(FuncionarioRequest.Endereco.newBuilder()
            .setLogradouro("Rua da Tabajaras")
            .setCep("12345-123")
            .setComplemento("Casa 20")
            .build())
        .build()

    val client = FuncionarioServiceGrpc.newBlockingStub(channel)

    val response = client.cadastrar(request)

    println(response)

}