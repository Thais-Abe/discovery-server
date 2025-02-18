package com.thais.msavaliadorcredito.exceptions;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

public class ErrorCominucationMicroservicesException extends Exception {
    @Getter
    private Integer status;
    public ErrorCominucationMicroservicesException(String msg, Integer status){
        super(msg);
        this.status = status;
    }
}
