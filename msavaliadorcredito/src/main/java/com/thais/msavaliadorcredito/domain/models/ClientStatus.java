package com.thais.msavaliadorcredito.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientStatus {
    private ClientData client;
    private List<ClientCard> cards;

}
