package com.vipin.PriceCalculator.entities;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.vipin.PriceCalculator.PriceRevision;
import com.vipin.PriceCalculator.converter.PriceRevisionConverter;

import java.math.BigDecimal;

@Entity
public class BicycleComponent {

    @Convert(converter = PriceRevisionConverter.class)
    private List<PriceRevision> pricingRevisions;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    public List<PriceRevision> getPricingRevisions() {
        return pricingRevisions;
    }

    public void setPricingRevisions(List<PriceRevision> pricingRevisions) {
        this.pricingRevisions = pricingRevisions;
    }

    public BigDecimal getPrice(Long timeOfPricing) {
        BigDecimal price = null;
        List<PriceRevision> sortedRevisions = pricingRevisions.stream().sorted().collect(Collectors.toList());
        for (PriceRevision priceRevision : sortedRevisions) {
            if (priceRevision.getTimeStamp() < timeOfPricing) {
                price = priceRevision.getPrice();
            }
        }
        return price;
    }

    public void revisePrice(BigDecimal price) {
        pricingRevisions.add(new PriceRevision(System.currentTimeMillis(), price));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
