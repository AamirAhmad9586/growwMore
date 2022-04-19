package com.thinking.machines.hr.utils;
import java.sql.*;
import com.thinking.machines.hr.dl.*;
public class DatabaseUtility
{
private DatabaseUtility(){}
public static void createTables(String driver,String connectionString,String username,String password) throws DAOException
{
try
{
Class.forName(driver);
Connection connection=DriverManager.getConnection(connectionString,username,password);
Statement statement=connection.createStatement();
String sqlStatement;
StringBuffer stringBuffer=new StringBuffer();
stringBuffer.append("create table administrator");
stringBuffer.append("(");
stringBuffer.append("username char(15) primary key,");
stringBuffer.append("password char(100) not null");
stringBuffer.append(");");
sqlStatement=stringBuffer.toString();
statement.executeUpdate(sqlStatement);
statement.close();

stringBuffer=new StringBuffer();
stringBuffer.append("create table designation");
stringBuffer.append("(");
stringBuffer.append("code int primary key auto_increment,");
stringBuffer.append("title char (35) not null unique");
stringBuffer.append(");");
sqlStatement=stringBuffer.toString();
statement=connection.createStatement();
statement.executeUpdate(sqlStatement);
statement.close();



stringBuffer=new StringBuffer();
stringBuffer.append("create table employee");
stringBuffer.append("(");
stringBuffer.append("employee_id int primary key auto_increment,");
stringBuffer.append("employee_name char (50) not null,");
stringBuffer.append("designation_code int not null,");
stringBuffer.append("dateOfBirth date not null,");
stringBuffer.append("basicSalary decimal(10,2) not null,");
stringBuffer.append("aadhar_card_number char(13) not null unique,");
stringBuffer.append("pan_card_number char(13) not null unique,");
stringBuffer.append("isIndian boolean not null,");
stringBuffer.append("gender char(6) not null,");
stringBuffer.append("foreign key (designation_code) references designation(code)");
stringBuffer.append(");");
sqlStatement=stringBuffer.toString();
statement=connection.createStatement();
statement.executeUpdate(sqlStatement);
statement.close();

statement=connection.createStatement();
statement.executeUpdate("alter table employee auto_increment=181000;");
statement.close();

}catch(SQLException sqlException)
{
System.out.println(sqlException.getMessage());
throw new DAOException(sqlException.getMessage());
}
catch(ClassNotFoundException cnfe)
{
System.out.println(cnfe.getMessage());
throw new DAOException(cnfe.getMessage());
}
}
}