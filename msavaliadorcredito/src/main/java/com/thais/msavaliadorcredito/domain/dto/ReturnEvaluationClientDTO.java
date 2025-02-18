package com.thais.msavaliadorcredito.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ReturnEvaluationClientDTO {

    private List<CardApprovedDTO> cards;
}
