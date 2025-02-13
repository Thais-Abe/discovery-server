package com.thais.mscartoes.domain.repositories;

import com.thais.mscartoes.domain.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card,Long> {
    List<Card> findByIncomeLessThanEqual(BigDecimal incomeBigDecimal);
}
