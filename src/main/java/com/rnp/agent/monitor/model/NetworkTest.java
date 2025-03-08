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
@Builder()

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
    private int packetLoss;

    @Column( name = "latencia_media", nullable = false)
    private double avgRtt;

    @Column( name = "data", nullable = false)
    private LocalDateTime timestamp;

    public NetworkTest(String host, int packetsSent, int packetsReceived, int packetLoss, double avgRtt, LocalDateTime timestamp) {
        this.host = host;
        this.packetsSent = packetsSent;
        this.packetsReceived = packetsReceived;
        this.packetLoss = packetLoss;
        this.avgRtt = avgRtt;
        this.timestamp = timestamp;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPacketsSent() {
        return packetsSent;
    }

    public void setPacketsSent(int packetsSent) {
        this.packetsSent = packetsSent;
    }

    public int getPacketsReceived() {
        return packetsReceived;
    }

    public void setPacketsReceived(int packetsReceived) {
        this.packetsReceived = packetsReceived;
    }

    public int getPacketLoss() {
        return packetLoss;
    }

    public void setPacketLoss(int packetLoss) {
        this.packetLoss = packetLoss;
    }

    public double getAvgRtt() {
        return avgRtt;
    }

    public void setAvgRtt(double avgRtt) {
        this.avgRtt = avgRtt;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

}
