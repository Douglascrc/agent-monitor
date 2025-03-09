package com.rnp.agent.monitor.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ViaIpeResponse {

    private String id;
    private String name;

    @JsonProperty("lat")
    private double latitude;

    @JsonProperty("lng")
    private double longitude;

    @JsonProperty("data")
    private DataInfo data;

    @Data
    public static class DataInfo {
        @JsonProperty("smoke")
        private SmokeData smoke;

        @JsonProperty("interfaces")
        private List<InterfaceData> interfaces;
    }
}
