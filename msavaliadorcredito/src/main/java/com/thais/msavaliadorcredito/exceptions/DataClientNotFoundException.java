package com.thais.msavaliadorcredito.exceptions;

public class DataClientNotFoundException extends Exception{

    public DataClientNotFoundException() {
        super("Dados do cliente não encontrados para o CPF informado");
    }
}
