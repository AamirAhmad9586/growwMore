package com.thinking.machines.hr.dl;
public interface EmployeeDAOInterface
{
public EmployeeDTOInterface add(EmployeeDTOInterface employeeDTO) throws DAOException;
public EmployeeDTOInterface update(EmployeeDTOInterface employeeDTO) throws DAOException;
public void delete(String vEmployeeId) throws DAOException;
public java.util.List<EmployeeDTOInterface> getEmployees() throws DAOException;
public EmployeeDTOInterface getByEmployeeId(String vEmployeeId) throws DAOException;
public EmployeeDTOInterface getByPanNumber(String vPanNumber) throws DAOException;
public EmployeeDTOInterface getByAadharCardNumber(String vAadharCardNumber) throws DAOException;
public boolean employeeIdExists(String vEmployeeId) throws DAOException;
public boolean panNumberExists(String vPanNumber) throws DAOException;
public boolean aadharCardNumberExists(String vAadharCardNumber) throws DAOException;
public int getCount() throws DAOException;
public boolean panNumberExists(String vPanNumber,String vEmployeeId) throws DAOException;
public boolean aadharCardNumberExists(String vAadharCardNumber,String vEmployeeId) throws DAOException;
}