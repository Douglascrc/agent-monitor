package com.rnp.agent.monitor.service;

import com.rnp.agent.monitor.model.SmokeData;
import com.rnp.agent.monitor.model.ViaIpeResponse;
import com.rnp.agent.monitor.model.ViaIpeStats;
import com.rnp.agent.monitor.repository.ViaIpeStatsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ViaIpeService {

    private final ViaIpeStatsRepository viaIpeStatsRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    private static final String API_URL = "https://viaipe.rnp.br/api/norte";

    @Transactional
    public List<ViaIpeStats> buscarDadosViaIpe() {
        try {
            // Consome a API e converte para um array de ViaIpeResponse
            ViaIpeResponse[] response = restTemplate.getForObject(API_URL, ViaIpeResponse[].class);

            if (response != null) {
                List<ViaIpeStats> statsList = new ArrayList<>();
                for (ViaIpeResponse data : response) {

                    if (data.getData() == null || data.getData().getSmoke() == null) {
                        System.out.println("Dados incompletos para o cliente: " + data.getId());
                        continue; // Pula este registro e vai para o próximo
                    }

                    SmokeData smoke = data.getData().getSmoke();
                    ViaIpeStats stats = ViaIpeStats.builder()
                            .clientId(data.getId())
                            .name(data.getName())
                            .latitude(data.getLatitude())
                            .longitude(data.getLongitude())
                            .avgLatency(smoke.getAvgLatency()) // Uso correto da variável
                            .maxLatency(smoke.getMaxLatency())
                            .avgLoss(smoke.getAvgLoss())
                            .maxLoss(smoke.getMaxLoss())
                            .build();

                    statsList.add(stats);
                }

                viaIpeStatsRepository.saveAll(statsList);
                return statsList;
            }
        } catch (Exception e) {
            System.err.println("Erro ao consumir API ViaIpe: " + e.getMessage());
        }
        return List.of();
    }

    public List<ViaIpeStats> listarTodos() {
        return viaIpeStatsRepository.findAll();
    }
}
