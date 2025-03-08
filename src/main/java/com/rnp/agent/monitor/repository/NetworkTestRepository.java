package com.rnp.agent.monitor.repository;

import com.rnp.agent.monitor.model.NetworkTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NetworkTestRepository extends JpaRepository<NetworkTest, Long>{
    List<NetworkTest> findByHost(String host);
}
