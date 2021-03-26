package com.vipin.PriceCalculator;

import java.util.List;

public class Cycle {

    private Integer cycleId;

    private List<Integer> frameComponents;

    private List<Integer> handleBarComponents;

    private List<Integer> seating;

    private List<Integer> wheelComponents;

    private List<Integer> chainAssembly;

    public List<Integer> getFrameComponents() {
        return frameComponents;
    }

    public void setFrameComponents(List<Integer> frameComponents) {
        this.frameComponents = frameComponents;
    }

    public List<Integer> getHandleBarComponents() {
        return handleBarComponents;
    }

    public void setHandleBarComponents(List<Integer> handleBarComponents) {
        this.handleBarComponents = handleBarComponents;
    }

    public List<Integer> getSeating() {
        return seating;
    }

    public void setSeating(List<Integer> seating) {
        this.seating = seating;
    }

    public List<Integer> getWheelComponents() {
        return wheelComponents;
    }

    public void setWheelComponents(List<Integer> wheelComponents) {
        this.wheelComponents = wheelComponents;
    }

    public List<Integer> getChainAssembly() {
        return chainAssembly;
    }

    public void setChainAssembly(List<Integer> chainAssembly) {
        this.chainAssembly = chainAssembly;
    }

    public Integer getCycleId() {
        return cycleId;
    }

    public void setCycleId(Integer cycleId) {
        this.cycleId = cycleId;
    }

}
