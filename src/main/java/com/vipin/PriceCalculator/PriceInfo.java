package com.vipin.PriceCalculator;

import java.math.BigDecimal;

public class PriceInfo {

    private Integer cycleId;

    private BigDecimal framePrice;

    private BigDecimal handleBarAndBrakesPrice;

    private BigDecimal seatingPrice;

    private BigDecimal wheelsPrice;

    private BigDecimal chainAssemblyPrice;

    public Integer getCycleId() {
        return cycleId;
    }

    public void setCycleId(Integer cycleId) {
        this.cycleId = cycleId;
    }

    public BigDecimal getFramePrice() {
        return framePrice;
    }

    public void setFramePrice(BigDecimal framePrice) {
        this.framePrice = framePrice;
    }

    public BigDecimal getHandleBarAndBrakesPrice() {
        return handleBarAndBrakesPrice;
    }

    public void setHandleBarAndBrakesPrice(BigDecimal handleBarAndBrakesPrice) {
        this.handleBarAndBrakesPrice = handleBarAndBrakesPrice;
    }

    public BigDecimal getSeatingPrice() {
        return seatingPrice;
    }

    public void setSeatingPrice(BigDecimal seatingPrice) {
        this.seatingPrice = seatingPrice;
    }

    public BigDecimal getWheelsPrice() {
        return wheelsPrice;
    }

    public void setWheelsPrice(BigDecimal wheelsPrice) {
        this.wheelsPrice = wheelsPrice;
    }

    public BigDecimal getChainAssemblyPrice() {
        return chainAssemblyPrice;
    }

    public void setChainAssemblyPrice(BigDecimal chainAssemblyPrice) {
        this.chainAssemblyPrice = chainAssemblyPrice;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PriceInfo [Cycle Id=");
        builder.append(cycleId);
        builder.append(", Frame =");
        builder.append(framePrice);
        builder.append(", Handle Bar and Brakes=");
        builder.append(handleBarAndBrakesPrice);
        builder.append(", Seating =");
        builder.append(seatingPrice);
        builder.append(", Wheels=");
        builder.append(wheelsPrice);
        builder.append(", ChainAssembly=");
        builder.append(chainAssemblyPrice);
        builder.append("]");
        return builder.toString();
    }

}
