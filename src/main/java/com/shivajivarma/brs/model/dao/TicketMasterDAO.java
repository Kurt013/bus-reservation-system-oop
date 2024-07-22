package com.shivajivarma.brs.model.dao;

import org.springframework.dao.EmptyResultDataAccessException;

import com.shivajivarma.brs.model.entity.TicketMaster;

/**
 * CRUD operations for ticket master table.
 */
public interface TicketMasterDAO {
   
  //Create
  public void save(TicketMaster employee);
  
  //Read
  public TicketMaster findById(int id) throws EmptyResultDataAccessException;
  public TicketMaster findByUsername(String username) throws EmptyResultDataAccessException;

}