package com.rnp.agent.monitor.controller;

import com.rnp.agent.monitor.model.ViaIpeStats;
import com.rnp.agent.monitor.service.ViaIpeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/viaipe")
@RequiredArgsConstructor
public class ViaIpeController {

    private final ViaIpeService viaIpeService;

    @GetMapping("/buscar")
    public List<ViaIpeStats> buscarDados() {
        return viaIpeService.buscarDadosViaIpe();
    }

    @GetMapping("/listar")
    public List<ViaIpeStats> listarTodos() {
        return viaIpeService.listarTodos();
    }
}
