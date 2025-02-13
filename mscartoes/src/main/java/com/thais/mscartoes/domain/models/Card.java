package com.thais.mscartoes.domain.models;

import com.thais.mscartoes.domain.FlagCard;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private FlagCard bandeira;
    private BigDecimal income; //renda
    private BigDecimal basicLimit;

    public Card(String name, FlagCard bandeira, BigDecimal income, BigDecimal basicLimit) {
        this.name = name;
        this.bandeira = bandeira;
        this.income = income;
        this.basicLimit = basicLimit;
    }
}
