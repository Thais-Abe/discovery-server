package com.thais.mscartoes.domain.models.dtos;

import com.thais.mscartoes.domain.models.ClientCard;
import jakarta.ws.rs.client.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardsByClientResponseDTO {
    private String name;
    private String flag;
    private BigDecimal limitAccepted;

    public static CardsByClientResponseDTO clientCardToCardsByClientResponseDTO(ClientCard clientCard){
        return new CardsByClientResponseDTO(
                clientCard.getCard().getName(),
                clientCard.getCard().getBandeira().toString(),
                clientCard.getLimit()
        );
    }
}
