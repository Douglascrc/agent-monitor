package com.rnp.agent.monitor.service;

import com.rnp.agent.monitor.model.NetworkTest;
import com.rnp.agent.monitor.repository.NetworkTestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;


@Service
public  class NetworkTestService {

    private final NetworkTestRepository networkTestRepository;

    public NetworkTestService(NetworkTestRepository networkTestRepository) {
        this.networkTestRepository = networkTestRepository;
    }

    public void ping(String host) {

        try {

            String os = System.getProperty("os.name").toLowerCase();
            String command = os.contains("win") ? "ping -n 4 " + host : "ping -c 4 " + host;

            ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            int packetsSent = 0, packetsReceived = 0;
            double totalTime = 0;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);

                if (line.contains("time=")) { // Captura RTT (latência)
                    int index = line.indexOf("time=");
                    String timeStr = line.substring(index + 5).split(" ")[0].replaceAll("[^0-9.]", ""); // Remove "ms"
                    totalTime += Double.parseDouble(timeStr);
                    packetsReceived++;
                }
                packetsSent++;
            }

            int packetLoss = ((packetsSent - packetsReceived) * 100) / packetsSent;
            double avgRtt = packetsReceived > 0 ? totalTime / packetsReceived : 0;

            System.out.println("\n🔹 Resultados do Ping:");
            System.out.println("📌 Pacotes enviados: " + packetsSent);
            System.out.println("✅ Pacotes recebidos: " + packetsReceived);
            System.out.println("❌ Perda de pacotes: " + packetLoss + "%");
            System.out.println("⏳ Latência média (RTT): " + avgRtt + " ms");

            NetworkTest result = new NetworkTest(host, packetsSent, packetsReceived, packetLoss, avgRtt, LocalDateTime.now());


            networkTestRepository.save(result);
            System.out.println("✅ Resultado do teste de rede salvo no banco de dados.");


        } catch (Exception e) {
            System.err.println("Erro ao executar ping: " + e.getMessage());
        }
    }
}
