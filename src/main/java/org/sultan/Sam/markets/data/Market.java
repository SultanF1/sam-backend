package org.sultan.Sam.markets.data;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "markets")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Market {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "prompt", nullable = false)
    private String prompt;

    @Column(name = "temperature", nullable = false)
    private String temperature;

    public Market(String name, String prompt, String temperature) {
        this.name = name;
        this.prompt = prompt;
        this.temperature = temperature;
    }
}
