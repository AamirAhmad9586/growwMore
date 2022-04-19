package com.thinking.machines.hr.dl;
import java.sql.*;
import java.util.*;
public class DesignationDAO implements DesignationDAOInterface
{
public DesignationDTOInterface add(DesignationDTOInterface designationDTO) throws DAOException
{
int code=0;
String title=null;
try
{
code=designationDTO.getCode();
title=designationDTO.getTitle();
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select Designation.code from Designation where title=?");
preparedStatement.setString(1,title);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Designation Title : "+title+" exists.");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("insert into Designation (title) values (?)",Statement.RETURN_GENERATED_KEYS);
preparedStatement.setString(1,title);
preparedStatement.executeUpdate();
resultSet=preparedStatement.getGeneratedKeys();
resultSet.next();
code=resultSet.getInt(1);
resultSet.close();
preparedStatement.close();
connection.close();
designationDTO.setCode(code);
return designationDTO;
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}
public DesignationDTOInterface update(DesignationDTOInterface designationDTO) throws DAOException
{
int code=0;
String title=null;
try
{
code=designationDTO.getCode();
title=designationDTO.getTitle();
boolean codeExist=codeExists(code);
if(codeExist==false) throw new DAOException("Invalid Code : "+code);
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select Designation.code from Designation where title=? and code!=?");
preparedStatement.setString(1,title);
preparedStatement.setInt(2,code);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Designation Title : "+title+" exists.");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("update Designation set title=? where code=?");
preparedStatement.setString(1,title);
preparedStatement.setInt(2,code);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
return designationDTO;
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}
public void delete(int vCode) throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select Designation.title from Designation where code=?");
preparedStatement.setInt(1,vCode);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid Designation Code : "+vCode);
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("select Employee.gender from Employee where designation_code=?");
preparedStatement.setInt(1,vCode);
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Some Employee is atteched to this Designation");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("delete from Designation where code=?");
preparedStatement.setInt(1,vCode);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}
public DesignationDTOInterface getByCode(int vCode) throws DAOException
{
DesignationDTOInterface designationDTO=null;
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select Designation.title from Designation where code=?");
preparedStatement.setInt(1,vCode);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid Designation Code : "+vCode);
}
designationDTO=new DesignationDTO();
designationDTO.setCode(vCode);
designationDTO.setTitle(resultSet.getString("title"));
preparedStatement.close();
connection.close();
return designationDTO;
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}
public DesignationDTOInterface getByTitle(String vTitle) throws DAOException
{
DesignationDTOInterface designationDTO=null;
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select Designation.title from Designation where title=?");
preparedStatement.setString(1,vTitle);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid Designation Title : "+vTitle);
}
designationDTO=new DesignationDTO();
designationDTO.setCode(resultSet.getInt("code"));
designationDTO.setTitle(resultSet.getString("title"));
preparedStatement.close();
connection.close();
return designationDTO;
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}
public boolean codeExists(int vCode) throws DAOException
{
boolean valid=false;
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select Designation.title from Designation where code=?");
preparedStatement.setInt(1,vCode);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
valid=true;
}
else
{
valid=false;
}
resultSet.close();
preparedStatement.close();
connection.close();
return valid;
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}
public boolean titleExists(String vTitle) throws DAOException
{
boolean valid=false;
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select Designation.title from Designation where title=?");
preparedStatement.setString(1,vTitle);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
valid=true;
}
else
{
valid=false;
}
resultSet.close();
preparedStatement.close();
connection.close();
return valid;
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
Statement statement=connection.createStatement();
ResultSet resultSet=statement.executeQuery("select Designation.code from Designation");
while(resultSet.next())
{
count++;
}
if(count==0) throw new DAOException("You have not yet added Designations.");
resultSet.close();
statement.close();
connection.close();
return count;
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage()); //remove after testing
}
}
public java.util.List<DesignationDTOInterface> getDesignations() throws DAOException
{
java.util.List<DesignationDTOInterface> designations=new java.util.LinkedList<>();
DesignationDTOInterface designationDTO=null;
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select * from Designation");
ResultSet resultSet=preparedStatement.executeQuery();
int code=0;
String title=null;
while(resultSet.next())
{
code=resultSet.getInt("code");
title=resultSet.getString("title");
designationDTO=new DesignationDTO();
designationDTO.setCode(code);
designationDTO.setTitle(title);
designations.add(designationDTO);
}
resultSet.close();
preparedStatement.close();
connection.close();
return designations;
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}
}