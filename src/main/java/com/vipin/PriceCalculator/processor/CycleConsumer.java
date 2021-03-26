package com.vipin.PriceCalculator.processor;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import com.vipin.PriceCalculator.Cycle;
import com.vipin.PriceCalculator.PriceInfo;
import com.vipin.PriceCalculator.entities.BicycleComponent;
import com.vipin.PriceCalculator.repository.BicycleComponentRepository;

public class CycleConsumer implements Runnable {

    private BicycleComponentRepository repository;

    private BlockingQueue<Cycle> cyclesQueue;

    private Long dateOfPricing;

    public CycleConsumer(BlockingQueue<Cycle> cyclesQueue, BicycleComponentRepository repositoryBean, Long dateOfPricing) {
        super();
        this.dateOfPricing = dateOfPricing;
        this.cyclesQueue = cyclesQueue;
        this.repository = repositoryBean;
    }

    @Override
    public void run() {
        while (true) {
            Cycle cycle;
            try {
                cycle = cyclesQueue.take();
                PriceInfo priceInfo = new PriceInfo();
                priceInfo.setCycleId(cycle.getCycleId());
                priceInfo.setFramePrice(calculatePrice(cycle.getFrameComponents()));
                priceInfo.setHandleBarAndBrakesPrice(calculatePrice(cycle.getHandleBarComponents()));
                priceInfo.setSeatingPrice(calculatePrice(cycle.getSeating()));
                priceInfo.setWheelsPrice(calculatePrice(cycle.getWheelComponents()));
                priceInfo.setChainAssemblyPrice(calculatePrice(cycle.getChainAssembly()));
                System.out.println("-----\n" + priceInfo.toString() + "-----------\n");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public BigDecimal calculatePrice(List<Integer> componentIds) {
        BigDecimal price = BigDecimal.ZERO;
        try {
            List<BicycleComponent> components = repository.findByIdIn(componentIds);
            for (BicycleComponent bicycleComponent : components) {
                price = price.add(bicycleComponent.getPrice(dateOfPricing));
            }
        } catch (Exception e) {
            throw new RuntimeException("No pricing Info found");
        }
        return price;
    }

}
