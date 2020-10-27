package edu.cetys.springlabs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.cetys.springlabs.model.Winery;


public interface WineryRepository  extends JpaRepository<Winery, Integer>{

}
