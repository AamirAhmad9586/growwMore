package com.thinking.machines.hr.dl;
import com.thinking.machines.hr.beans.*;
import java.sql.*;
import java.io.*;
import com.google.gson.*;
public class DAOConnection
{
public static String driver=null;
public static String connectionString=null;
public static String username=null;
public static String password=null;
private DAOConnection(){}
public static Connection getConnection() throws DAOException
{
String filePath="C://tomcat9//webapps//growwMore//conf//config.json";
File file=new File(filePath);
if(file.exists())
{
try
{
JsonParser jsonParser=new JsonParser();
JsonObject jsonObject=(JsonObject)jsonParser.parse(new FileReader(filePath));
if(jsonObject.has("driver")) driver=(jsonObject.get("driver").getAsString());
if(jsonObject.has("connectionString")) connectionString=(jsonObject.get("connectionString").getAsString());
if(jsonObject.has("username")) username=(jsonObject.get("username").getAsString());
if(jsonObject.has("password")) password=(jsonObject.get("password").getAsString());
}catch(FileNotFoundException fnfe)
{
System.out.println(fnfe.getMessage());
System.exit(0);
}
}
Connection connection=null;
try
{
Class.forName(driver);
connection=DriverManager.getConnection(connectionString,username,password);
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
catch(ClassNotFoundException cnfe)
{
throw new DAOException(cnfe.getMessage());
}
return connection;
}
}