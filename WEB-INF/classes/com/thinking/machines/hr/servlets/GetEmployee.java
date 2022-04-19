package com.thinking.machines.hr.servlets;
import javax.servlet.*;
import javax.servlet.http.*;
import com.thinking.machines.hr.dl.*;
import com.thinking.machines.hr.beans.*;
import com.thinking.machines.common.*;
import com.google.gson.*;
import java.io.*;
import java.text.*;
public class GetEmployee extends HttpServlet
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
SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
String employeeId=request.getParameter("employeeId");
pw=response.getWriter();
response.setContentType("appliation/json");
response.setCharacterEncoding("utf-8");
EmployeeDTOInterface employeeDTO=new EmployeeDAO().getByEmployeeId(employeeId);
DesignationDTOInterface designationDTO=new DesignationDAO().getByCode(employeeDTO.getDesignationCode());
EmployeeBean employeeBean=new EmployeeBean();
POJOCopier.copy(employeeBean,employeeDTO);
employeeBean.setDateOfBirth(simpleDateFormat.format(employeeDTO.getDateOfBirth()));
employeeBean.setBasicSalary(employeeDTO.getBasicSalary().toPlainString());
employeeBean.setDesignation(designationDTO.getTitle());
ResponseData responseData=new ResponseData();
responseData.setException(false);
responseData.setResult(employeeBean);
Gson gson=new Gson();
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