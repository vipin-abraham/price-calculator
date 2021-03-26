package com.vipin.PriceCalculator.processor;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vipin.PriceCalculator.Cycle;

public class CycleProducer implements Runnable {

    private BlockingQueue<Cycle> cyclesQueue;

    private String cycleConfiguration;

    public CycleProducer(BlockingQueue<Cycle> cyclesQueue, String cycleConfiguration) {
        super();
        this.cyclesQueue = cyclesQueue;
        this.cycleConfiguration = cycleConfiguration;
    }

    @Override
    public void run() {
        try {
            Cycle cycle = new ObjectMapper().readValue(cycleConfiguration, Cycle.class);
            cyclesQueue.put(cycle);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
