package br.com.zup.edu

import java.io.FileInputStream
import java.io.FileOutputStream

fun main(){

    val request = FuncionarioRequest.newBuilder()
        .setNome("Yuri Matheus")
        .setCpf("000.000.000-00")
        .setSalario(2000.20)
        .setAtivo(true)
        .setCargo(Cargo.QA)
        .addEnderecos(FuncionarioRequest.Endereco.newBuilder()
            .setLogradouro("Rua da Tabajaras")
            .setCep("12345-123")
            .setComplemento("Casa 20")
            .build())
        .build()

    println(request)

    // escrevemos o objeto
    request.writeTo(FileOutputStream("funcionario-request.bin"))

    // lemos o objeto
    val request2 = FuncionarioRequest.newBuilder()
        .mergeFrom(FileInputStream("funcionario-request.bin"))

    request2.setCargo(Cargo.GERENTE).build()

    println(request2)
}