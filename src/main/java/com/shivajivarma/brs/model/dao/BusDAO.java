package com.shivajivarma.brs.model.dao;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import com.shivajivarma.brs.model.entity.Bus;
import com.shivajivarma.brs.model.entity.Route;

/**
 * CRUD operations for bus table.
 */
public interface BusDAO {
  
  //Read
  public List<Bus> findByRouteAndDate(Route route, String date) throws EmptyResultDataAccessException;

}