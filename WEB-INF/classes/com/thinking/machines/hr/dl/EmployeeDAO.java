package com.thinking.machines.hr.dl;
import java.util.*;
import java.sql.*;
import java.math.*;
import java.text.*;
public class EmployeeDAO implements EmployeeDAOInterface
{
public EmployeeDTOInterface add(EmployeeDTOInterface employeeDTO) throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select Employee.employee_name from Employee where pan_card_number=?");
preparedStatement.setString(1,employeeDTO.getPANNumber());
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("PAN Number : "+employeeDTO.getPANNumber()+" Exists.");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("select Employee.employee_name from Employee where aadhar_card_number=?");
preparedStatement.setString(1,employeeDTO.getAadharCardNumber());
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Aadhar Card Number : "+employeeDTO.getAadharCardNumber()+" Exists.");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("select * from Designation where code=?");
preparedStatement.setInt(1,employeeDTO.getDesignationCode());
resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid Designation Code : "+employeeDTO.getDesignationCode());
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("insert into Employee (employee_name,designation_code,dateOfBirth,basicSalary,aadhar_card_number,pan_card_number,isIndian,gender) values(?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
preparedStatement.setString(1,employeeDTO.getName());
preparedStatement.setInt(2,employeeDTO.getDesignationCode());
java.util.Date dateOfBirth=employeeDTO.getDateOfBirth();
java.sql.Date dob=new java.sql.Date(dateOfBirth.getYear(),dateOfBirth.getMonth(),dateOfBirth.getDate());
preparedStatement.setDate(3,dob);
preparedStatement.setBigDecimal(4,employeeDTO.getBasicSalary());
preparedStatement.setString(5,employeeDTO.getAadharCardNumber());
preparedStatement.setString(6,employeeDTO.getPANNumber());
preparedStatement.setBoolean(7,employeeDTO.getIsIndian());
preparedStatement.setString(8,employeeDTO.getGender());
preparedStatement.executeUpdate();
resultSet=preparedStatement.getGeneratedKeys();
resultSet.next();
int employee_id=resultSet.getInt(1);
String employeeId="0704CS"+employee_id;
resultSet.close();
preparedStatement.close();
connection.close();
employeeDTO.setEmployeeId(employeeId);
return employeeDTO;
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}
public EmployeeDTOInterface update(EmployeeDTOInterface employeeDTO) throws DAOException
{
String employeeId=employeeDTO.getEmployeeId();
String panNumber=employeeDTO.getPANNumber();
String aadharCardNumber=employeeDTO.getAadharCardNumber();
String gender=employeeDTO.getGender();
boolean isIndian=employeeDTO.getIsIndian();
int designationCode=employeeDTO.getDesignationCode();
BigDecimal basicSalary=employeeDTO.getBasicSalary();
java.util.Date dateOfBirth=employeeDTO.getDateOfBirth();
String name=employeeDTO.getName();
int employee_id=0;
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select Employee.employee_id from Employee where pan_card_number=?");
preparedStatement.setString(1,panNumber);
ResultSet resultSet=preparedStatement.executeQuery();
String tempEID="";
if(resultSet.next())
{
employee_id=resultSet.getInt("employee_id");
tempEID="0704CS"+employee_id;
if(tempEID.equals(employeeId)==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("PAN Number : "+panNumber+" Exists.");
}
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("select Employee.employee_id from Employee where aadhar_card_number=?");
preparedStatement.setString(1,aadharCardNumber);
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
employee_id=resultSet.getInt("employee_id");
tempEID="0704CS"+employee_id;
if(tempEID.equals(employeeId)==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Aadhar Card Number : "+aadharCardNumber+" Exists.");
}
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("select * from Designation where code=?");
preparedStatement.setInt(1,designationCode);
resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid Designation Code : "+designationCode);
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("update Employee set employee_name=?,pan_card_number=?,aadhar_card_number=?,gender=?,isIndian=?,dateOfBirth=?,basicSalary=?,designation_code=? where Employee.employee_id=?");
preparedStatement.setString(1,name);
preparedStatement.setString(2,panNumber);
preparedStatement.setString(3,aadharCardNumber);
preparedStatement.setString(4,gender);
preparedStatement.setBoolean(5,isIndian);
java.sql.Date dob=new java.sql.Date(dateOfBirth.getYear(),dateOfBirth.getMonth(),dateOfBirth.getDate());
preparedStatement.setDate(6,dob);
preparedStatement.setBigDecimal(7,basicSalary);
preparedStatement.setInt(8,designationCode);
preparedStatement.setInt(9,Integer.parseInt(employeeId.substring(6)));
preparedStatement.executeUpdate();
resultSet.close();
preparedStatement.close();
connection.close();
return employeeDTO;
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}
public void delete(String vEmployeeId) throws DAOException
{
int employee_id=Integer.parseInt(vEmployeeId.substring(6));
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select Employee.employee_name from Employee where employee_id=?");
preparedStatement.setInt(1,employee_id);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid Employee ID : "+vEmployeeId);
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("delete from Employee where employee_id=?");
preparedStatement.setInt(1,employee_id);
preparedStatement.executeUpdate();
resultSet.close();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}
public java.util.List<EmployeeDTOInterface> getEmployees() throws DAOException
{
java.util.List<EmployeeDTOInterface> employees=new java.util.LinkedList<>();
EmployeeDTOInterface employeeDTO=null;
try
{
Connection connection=DAOConnection.getConnection();
Statement statement=connection.createStatement();
ResultSet resultSet=statement.executeQuery("select * from Employee");
while(resultSet.next())
{
employeeDTO=new EmployeeDTO();
employeeDTO.setEmployeeId("0704CS"+resultSet.getInt("employee_id"));
employeeDTO.setName(resultSet.getString("employee_name"));
employeeDTO.setGender(resultSet.getString("gender"));
employeeDTO.setPANNumber(resultSet.getString("pan_card_number"));
employeeDTO.setAadharCardNumber(resultSet.getString("aadhar_card_number"));
employeeDTO.setIsIndian(resultSet.getBoolean("isIndian"));
employeeDTO.setBasicSalary(resultSet.getBigDecimal("basicSalary"));
employeeDTO.setDateOfBirth(resultSet.getDate("dateOfBirth"));
employeeDTO.setDesignationCode(resultSet.getInt("designation_code"));
employees.add(employeeDTO);
}
resultSet.close();
statement.close();
connection.close();
return employees;
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage()); //remove after testing
}
}
public EmployeeDTOInterface getByEmployeeId(String vEmployeeId) throws DAOException
{
EmployeeDTOInterface employeeDTO=null;
try
{
int employee_id=Integer.parseInt(vEmployeeId.substring(6));
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select * from Employee where employee_id=?");
preparedStatement.setInt(1,employee_id);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid Employee ID : "+vEmployeeId);
}
employeeDTO=new EmployeeDTO();
employeeDTO.setEmployeeId("0704CS"+resultSet.getInt("employee_id"));
employeeDTO.setName(resultSet.getString("employee_name"));
employeeDTO.setGender(resultSet.getString("gender"));
employeeDTO.setPANNumber(resultSet.getString("pan_card_number"));
employeeDTO.setAadharCardNumber(resultSet.getString("aadhar_card_number"));
employeeDTO.setIsIndian(resultSet.getBoolean("isIndian"));
employeeDTO.setBasicSalary(resultSet.getBigDecimal("basicSalary"));
employeeDTO.setDateOfBirth(resultSet.getDate("dateOfBirth"));
employeeDTO.setDesignationCode(resultSet.getInt("designation_code"));
resultSet.close();
preparedStatement.close();
connection.close();
return employeeDTO;
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}
public EmployeeDTOInterface getByPanNumber(String vPanNumber) throws DAOException
{
EmployeeDTOInterface employeeDTO=null;
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select * from Employee where pan_card_number=?");
preparedStatement.setString(1,vPanNumber);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid PAN Number : "+vPanNumber);
}
employeeDTO=new EmployeeDTO();
employeeDTO.setEmployeeId("0704CS"+resultSet.getInt("employee_id"));
employeeDTO.setName(resultSet.getString("employee_name"));
employeeDTO.setGender(resultSet.getString("gender"));
employeeDTO.setPANNumber(resultSet.getString("pan_card_number"));
employeeDTO.setAadharCardNumber(resultSet.getString("aadhar_card_number"));
employeeDTO.setIsIndian(resultSet.getBoolean("isIndian"));
employeeDTO.setBasicSalary(resultSet.getBigDecimal("basicSalary"));
employeeDTO.setDateOfBirth(resultSet.getDate("dateOfBirth"));
employeeDTO.setDesignationCode(resultSet.getInt("designation_code"));
resultSet.close();
preparedStatement.close();
connection.close();
return employeeDTO;
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}
public EmployeeDTOInterface getByAadharCardNumber(String vAadharCardNumber) throws DAOException
{
EmployeeDTOInterface employeeDTO=null;
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select * from Employee where aadhar_card_number=?");
preparedStatement.setString(1,vAadharCardNumber);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid Aadhar Card Number : "+vAadharCardNumber);
}
employeeDTO=new EmployeeDTO();
employeeDTO.setEmployeeId("0704CS"+resultSet.getInt("employee_id"));
employeeDTO.setName(resultSet.getString("employee_name"));
employeeDTO.setGender(resultSet.getString("gender"));
employeeDTO.setPANNumber(resultSet.getString("pan_card_number"));
employeeDTO.setAadharCardNumber(resultSet.getString("aadhar_card_number"));
employeeDTO.setIsIndian(resultSet.getBoolean("isIndian"));
employeeDTO.setBasicSalary(resultSet.getBigDecimal("basicSalary"));
employeeDTO.setDateOfBirth(resultSet.getDate("dateOfBirth"));
employeeDTO.setDesignationCode(resultSet.getInt("designation_code"));
resultSet.close();
preparedStatement.close();
connection.close();
return employeeDTO;
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}
public boolean employeeIdExists(String vEmployeeId) throws DAOException
{
try
{
int employee_id=Integer.parseInt(vEmployeeId.substring(6));
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select * from Employee where employee_id=?");
preparedStatement.setInt(1,employee_id);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
return true;
}
resultSet.close();
preparedStatement.close();
connection.close();
return false;
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}
public boolean panNumberExists(String vPanNumber) throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select * from Employee where pan_card_number=?");
preparedStatement.setString(1,vPanNumber);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
return true;
}
resultSet.close();
preparedStatement.close();
connection.close();
return false;
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}
public boolean aadharCardNumberExists(String vAadharCardNumber) throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select * from Employee where aadhar_card_number=?");
preparedStatement.setString(1,vAadharCardNumber);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
return true;
}
resultSet.close();
preparedStatement.close();
connection.close();
return false;
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}
public int getCount() throws DAOException
{
try
{
int count=0;
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select Employee.gender from Employee");
ResultSet resultSet=preparedStatement.executeQuery();
while(resultSet.next())
{
count++;
}
resultSet.close();
preparedStatement.close();
connection.close();
if(count==0) throw new DAOException("You have not yet added Employees data.");
return count;
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}
public boolean panNumberExists(String vPanNumber,String vEmployeeId) throws DAOException
{
try
{
boolean valid=false;
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select Employee.employee_id from Employee where pan_card_number=?");
preparedStatement.setString(1,vPanNumber);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
String employeeId="0704CS"+resultSet.getInt("employee_id");
if(employeeId.equals(vEmployeeId)) valid=false;
else valid=true;
}
preparedStatement.close();
connection.close();
return valid;
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}
public boolean aadharCardNumberExists(String vAadharCardNumber,String vEmployeeId) throws DAOException
{
try
{
boolean valid=false;
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select Employee.employee_id from Employee where aadhar_card_number=?");
preparedStatement.setString(1,vAadharCardNumber);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
String employeeId="0704CS"+resultSet.getInt("employee_id");
if(employeeId.equals(vEmployeeId)) valid=false;
else valid=true;
}
preparedStatement.close();
connection.close();
return valid;
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}
}