package org.sultan.Sam.markets.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MarketEntityRepository extends JpaRepository<Market, Long> {
    Optional<Market> findByName(String name);
}
