package com.tylerj.javaorders.services;

import com.tylerj.javaorders.models.Agent;
import com.tylerj.javaorders.repositories.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service(value = "agentServices")
public class AgentServicesImpl implements AgentServices {
    @Autowired
    AgentRepository agentrepos;

    @Override
    public Agent findAgentById(long id) {
        return agentrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agent " + id + " Not Found"));
    }

    @Override
    public Agent save(Agent agent) {
        return agentrepos.save(agent);
    }
}
