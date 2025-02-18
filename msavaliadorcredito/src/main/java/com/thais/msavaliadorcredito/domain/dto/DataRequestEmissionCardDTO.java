package com.thais.msavaliadorcredito.domain.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DataRequestEmissionCardDTO {
    private Long idCard;
    private String cpf;
    private String address;
    private BigDecimal availableLimit;
}
