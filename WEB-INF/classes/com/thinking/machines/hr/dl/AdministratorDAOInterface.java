package com.thinking.machines.hr.dl;
public interface AdministratorDAOInterface
{
public void add(AdministratorDTOInterface administratorDTO) throws DAOException;
public AdministratorDTOInterface getByUsername(String vUsername) throws DAOException;
}