package com.thinking.machines.hr.servlets;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import com.thinking.machines.hr.dl.*;
import com.google.gson.*;
public class GetEmployees extends HttpServlet
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
PrintWriter pw=null;
try
{
pw=response.getWriter();
response.setContentType("application/json");
response.setCharacterEncoding("utf-8");
java.util.List<EmployeeDTOInterface> employees=new EmployeeDAO().getEmployees();
Gson gson=new Gson();
ResponseData responseData=new ResponseData();
responseData.setException(false);
responseData.setResult(employees);
String jsonString=gson.toJson(responseData);
pw.print(jsonString);
pw.flush();
}catch(IOException ioException)
{
System.out.println(ioException.getMessage());
}
catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
ResponseData responseData=new ResponseData();
responseData.setException(true);
responseData.setResult(daoException.getMessage());
Gson gson=new Gson();
String jsonString=gson.toJson(responseData);
pw.print(jsonString);
pw.flush();
}
}
}