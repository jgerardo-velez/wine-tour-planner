package edu.cetys.springlabs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.cetys.springlabs.model.Region;


public interface RegionRepository extends JpaRepository<Region, Integer> {

}
