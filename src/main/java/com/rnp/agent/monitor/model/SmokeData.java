package com.rnp.agent.monitor.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SmokeData {

    @JsonProperty("loss")
    private double loss;

    @JsonProperty("avg_val")
    private double avgLatency;

    @JsonProperty("max_val")
    private double maxLatency;

    @JsonProperty("avg_loss")
    private double avgLoss;

    @JsonProperty("max_loss")
    private double maxLoss;
}
