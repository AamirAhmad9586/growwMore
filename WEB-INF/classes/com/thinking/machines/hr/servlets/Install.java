package com.thinking.machines.hr.servlets;
import javax.servlet.http.*;
import javax.servlet.*;
import com.thinking.machines.hr.beans.*;
import com.thinking.machines.hr.dl.*;
import com.thinking.machines.hr.utils.*;
import java.sql.*;
import java.io.*;
import com.google.gson.*;
public class Install extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
try
{
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
}catch(IOException ioException)
{
System.out.println(ioException.getMessage());
}
}
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
try
{
String driver=request.getParameter("driver");
String connectionString=request.getParameter("connectionString");
String username=request.getParameter("username");
String password=request.getParameter("password");
String administratorUsername=request.getParameter("administratorUsername");
String administratorPassword=request.getParameter("administratorPassword");
DatabaseBean databaseBean=new DatabaseBean();
databaseBean.setDriver(driver);
databaseBean.setConnectionString(connectionString);
databaseBean.setUsername(username);
databaseBean.setPassword(password);
DAOConnection.driver=driver;
DAOConnection.connectionString=connectionString;
DAOConnection.username=username;
DAOConnection.password=password;
Connection connection=DAOConnection.getConnection();
DatabaseUtility.createTables(driver,connectionString,username,password);
AdministratorDTOInterface administratorDTO=new AdministratorDTO();
administratorDTO.setUsername(administratorUsername);
administratorDTO.setPassword(administratorPassword);
new AdministratorDAO().add(administratorDTO);
ServletContext servletContext=request.getServletContext();
String filePath=servletContext.getRealPath("")+File.separator+"conf"+File.separator+"config.json";
File file=new File(filePath);
if(!file.exists())
{
file.getParentFile().mkdirs();
file.createNewFile();
}
Gson gson=new Gson();
String str=gson.toJson(databaseBean);
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
randomAccessFile.writeBytes(str);
randomAccessFile.close();
RequestDispatcher requestDispatcher=request.getRequestDispatcher("/InstallationSuccessful.jsp");
requestDispatcher.forward(request,response);
}catch(ServletException servletException)
{
System.out.println(servletException.getMessage());
}
catch(IOException ioException)
{
System.out.println(ioException.getMessage());
}
catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
try
{
RequestDispatcher requestDispatcher=request.getRequestDispatcher("/InstallationFailed.jsp");
requestDispatcher.forward(request,response);
}catch(ServletException servletException)
{
System.out.println(servletException.getMessage());
}
catch(IOException ioException)
{
System.out.println(ioException.getMessage());
}
}
}
}