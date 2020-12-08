package com.tylerj.javaorders.services;

import com.tylerj.javaorders.models.Agent;

public interface AgentServices {
    Agent save(Agent agent);

    Agent findAgentById(long id);
}
