package com.thais.msavaliadorcredito.domain.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardApprovedDTO {

    private String card;
    private String flag;
    private BigDecimal availableLimit;
}
