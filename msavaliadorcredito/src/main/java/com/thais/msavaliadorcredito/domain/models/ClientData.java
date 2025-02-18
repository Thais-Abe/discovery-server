package com.thais.msavaliadorcredito.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientData {
    private Long id;
    private String name;
    private Integer age;
}
