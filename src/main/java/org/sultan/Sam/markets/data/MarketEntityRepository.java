package org.sultan.Sam.markets.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketEntityRepository extends JpaRepository<Market, Long> {
    Market findByName(String name);
}
