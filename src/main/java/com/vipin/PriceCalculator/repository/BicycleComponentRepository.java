package com.vipin.PriceCalculator.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.vipin.PriceCalculator.entities.BicycleComponent;

public interface BicycleComponentRepository extends JpaRepository<BicycleComponent, Integer> {

    List<BicycleComponent> findByIdIn(@Param("ids") List<Integer> ids);
}
