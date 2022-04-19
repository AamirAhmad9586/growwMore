package com.thinking.machines.hr.dl;
import java.sql.*;
public class AdministratorDAO implements AdministratorDAOInterface
{
public void add(AdministratorDTOInterface administratorDTO) throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("insert into administrator (username,password) values (?,?)");
preparedStatement.setString(1,administratorDTO.getUsername());
preparedStatement.setString(2,administratorDTO.getPassword());
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}
public AdministratorDTOInterface getByUsername(String vUsername) throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select * from administrator where username=?");
preparedStatement.setString(1,vUsername);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid Username : "+vUsername);
}
String password=resultSet.getString("password");
resultSet.close();
preparedStatement.close();
connection.close();
AdministratorDTOInterface administratorDTO=new AdministratorDTO();
administratorDTO.setUsername(vUsername);
administratorDTO.setPassword(password);
return administratorDTO;
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}
}