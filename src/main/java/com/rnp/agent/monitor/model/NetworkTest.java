package com.rnp.agent.monitor.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "NETWORK_RESULT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class NetworkTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( name = "host", nullable = false)
    private String host;

    @Column( name = "pacotes_enviados", nullable = false)
    private int packetsSent;

    @Column( name = "pacotes_recebidos", nullable = false)
    private int packetsReceived;

    @Column( name = "pacotes_perdidos", nullable = false)
    private String packetLoss;

    @Column( name = "latencia_media", nullable = false)
    private double avgRtt;

    @Column( name = "data", nullable = false)
    private LocalDateTime timestamp;

    @PrePersist
    public void prePersist() {
        this.timestamp = LocalDateTime.now();
    }
}
