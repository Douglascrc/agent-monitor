package com.rnp.agent.monitor.service;

import com.rnp.agent.monitor.model.NetworkTest;
import com.rnp.agent.monitor.repository.NetworkTestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class NetworkTestService {

    private final NetworkTestRepository networkTestRepository;

    public NetworkTest ping(String host) {
        host = host.trim();

        if (host.isEmpty()) {
            return null;
        }

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
            int packetLossPercentage = 0;

            Pattern windowsPattern = Pattern.compile("Packets: Sent = (\\d+), Received = (\\d+), Lost = (\\d+)");
            Pattern linuxPattern = Pattern.compile("(\\d+) packets transmitted, (\\d+) received, (\\d+)% packet loss");

            while ((line = reader.readLine()) != null) {
                Matcher winMatcher = windowsPattern.matcher(line);
                if (winMatcher.find()) {
                    packetsSent = Integer.parseInt(winMatcher.group(1));
                    packetsReceived = Integer.parseInt(winMatcher.group(2));
                    packetLossPercentage = Integer.parseInt(winMatcher.group(3));
                    continue;
                }

                Matcher linuxMatcher = linuxPattern.matcher(line);
                if (linuxMatcher.find()) {
                    packetsSent = Integer.parseInt(linuxMatcher.group(1));
                    packetsReceived = Integer.parseInt(linuxMatcher.group(2));
                    packetLossPercentage = Integer.parseInt(linuxMatcher.group(3));
                    continue;
                }

                if (line.contains("time=")) {
                    int index = line.indexOf("time=");
                    String timeStr = line.substring(index + 5).split(" ")[0].replaceAll("[^0-9.]", "");
                    totalTime += Double.parseDouble(timeStr);
                }
            }

            if (packetsSent > 0 && packetLossPercentage == 0) {
                packetLossPercentage = ((packetsSent - packetsReceived) * 100) / packetsSent;
            }

            double avgRtt = packetsReceived > 0 ? totalTime / packetsReceived : 0;
            String packetLossFormatted = packetLossPercentage + "%"; // Formata como string

            NetworkTest result = NetworkTest.builder()
                    .host(host)
                    .packetLoss(packetLossFormatted)
                    .packetsReceived(packetsReceived)
                    .packetsSent(packetsSent)
                    .avgRtt(avgRtt)
                    .timestamp(LocalDateTime.now())
                    .build();

            networkTestRepository.save(result);
            return result;

        } catch (Exception e) {
            return null;
        }
    }
}
