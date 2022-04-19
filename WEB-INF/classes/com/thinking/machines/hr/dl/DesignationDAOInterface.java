package com.thinking.machines.hr.dl;
public interface DesignationDAOInterface
{
public DesignationDTOInterface add(DesignationDTOInterface designationDTO) throws DAOException;
public DesignationDTOInterface update(DesignationDTOInterface designationDTO) throws DAOException;
public void delete(int vCode) throws DAOException;
public DesignationDTOInterface getByCode(int vCode) throws DAOException;
public DesignationDTOInterface getByTitle(String vTitle) throws DAOException;
public boolean codeExists(int vCode) throws DAOException;
public boolean titleExists(String vTitle) throws DAOException;
public java.util.List<DesignationDTOInterface> getDesignations() throws DAOException;
public int getCount() throws DAOException;
}