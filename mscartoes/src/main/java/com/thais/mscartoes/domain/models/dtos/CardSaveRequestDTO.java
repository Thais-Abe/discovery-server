package com.thais.mscartoes.domain.models.dtos;

import com.thais.mscartoes.domain.FlagCard;
import com.thais.mscartoes.domain.models.Card;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardSaveRequestDTO {
    private String name;
    private FlagCard flag;
    private BigDecimal income;
    private BigDecimal limit;

    public Card cardDTOToCard(){
        return new Card(name, flag, income, limit);
    }
}
