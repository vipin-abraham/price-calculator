package com.vipin.PriceCalculator;

import java.math.BigDecimal;

public class PriceRevision implements Comparable<PriceRevision> {

    private Long timeStamp;

    private BigDecimal price;

    public PriceRevision() {
    }

    public PriceRevision(long revisionTime, BigDecimal price) {
        this.timeStamp = revisionTime;
        this.price = price;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public int compareTo(PriceRevision o) {
        return this.timeStamp > o.timeStamp ? 1 : this.timeStamp < o.timeStamp ? -1 : 0;
    }

}
