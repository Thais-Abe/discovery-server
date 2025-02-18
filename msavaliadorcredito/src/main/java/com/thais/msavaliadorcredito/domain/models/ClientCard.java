package com.thais.msavaliadorcredito.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientCard {
    private String name;
    private String flag;
    private BigDecimal availableLimit;
}
