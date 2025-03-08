package com.rnp.agent.monitor.controller;

import com.rnp.agent.monitor.service.NetworkTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/network")
public class NetworkTestController {

    private final NetworkTestService networkTestService;

    public NetworkTestController(NetworkTestService networkTestService) {
        this.networkTestService = networkTestService;
    }

    @GetMapping("/ping")
    public String ping(@RequestParam String host) {
        networkTestService.ping(host);
        return "Ping test realizado para: " + host;
    }
}
