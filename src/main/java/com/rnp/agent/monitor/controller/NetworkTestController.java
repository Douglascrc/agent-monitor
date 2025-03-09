package com.rnp.agent.monitor.controller;

import com.rnp.agent.monitor.service.NetworkTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/network")
@RequiredArgsConstructor
public class NetworkTestController {

    private final NetworkTestService networkTestService;

    @GetMapping("/ping")
    public ResponseEntity ping(@RequestParam String host) {

        return ResponseEntity.ok(networkTestService.ping(host));
    }
}
