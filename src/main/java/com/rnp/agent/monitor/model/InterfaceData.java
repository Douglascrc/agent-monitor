package com.rnp.agent.monitor.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class InterfaceData {

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("max_out")
    private double maxOut;

    @JsonProperty("avg_out")
    private double avgOut;

    @JsonProperty("avg_in")
    private double avgIn;

    @JsonProperty("traffic_out")
    private double trafficOut;

    @JsonProperty("traffic_in")
    private double trafficIn;
}
