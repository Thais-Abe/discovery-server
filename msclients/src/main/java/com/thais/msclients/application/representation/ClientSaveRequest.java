package com.thais.msclients.application.representation;

import com.thais.msclients.domain.Client;
import lombok.Data;


public class ClientSaveRequest {
    private String cpf;
    private String name;
    private Integer age;

    public Client toModel(){
        return new Client(this.name, this.cpf, this.age);
    }

    public ClientSaveRequest() {
    }

    public ClientSaveRequest(String cpf, String name, Integer age) {
        this.cpf = cpf;
        this.name = name;
        this.age = age;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
