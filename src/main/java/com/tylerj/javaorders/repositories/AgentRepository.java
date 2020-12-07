package com.tylerj.javaorders.repositories;

import com.tylerj.javaorders.models.Agent;
import org.springframework.data.repository.CrudRepository;

public interface AgentRepository extends CrudRepository<Agent, Long> {
}
