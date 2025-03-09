package com.rnp.agent.monitor.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "viaipe_stats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ViaIpeStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "nome", nullable = false)
    private String name;

    @Column(name = "latitude", nullable = false)
    private double latitude;

    @Column(name = "longitude", nullable = false)
    private double longitude;

    @Column(name = "latencia_media", nullable = false)
    private Double avgLatency;

    @Column(name = "perda_media", nullable = false)
    private Double avgLoss;

    @Column( name = "latencia_maxima", nullable = false)
    private Double maxLatency;

    @Column( name = "latencia_minima", nullable = false)
    private Double maxLoss;

    @Column( name = "data_hora", nullable = false, updatable = false)
    private LocalDateTime timestamp = LocalDateTime.now();
}
