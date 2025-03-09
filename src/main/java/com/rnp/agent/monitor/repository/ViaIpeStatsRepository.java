package com.rnp.agent.monitor.repository;

import com.rnp.agent.monitor.model.ViaIpeStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViaIpeStatsRepository extends JpaRepository<ViaIpeStats, Long> {
    List<ViaIpeStats>findAllByClientId(String clientId);
}
