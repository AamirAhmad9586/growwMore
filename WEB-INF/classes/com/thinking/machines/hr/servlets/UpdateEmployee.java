package com.thinking.machines.hr.servlets;
import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.thinking.machines.hr.dl.*;
import java.math.*;
import java.text.*;
import com.google.gson.*;
public class UpdateEmployee extends HttpServlet
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
SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
String employeeId=null;
String name=null;
String panNumber=null;
String aadharCardNumber=null;
String gender=null;
java.util.Date dateOfBirth=null;
BigDecimal basicSalary=null;
boolean isIndian=false;
int designationCode=0;
PrintWriter pw=null;
try
{
response.setContentType("application/json");
response.setCharacterEncoding("utf-8");
pw=response.getWriter();
employeeId=request.getParameter("employeeId");
name=request.getParameter("name");
panNumber=request.getParameter("panNumber");
aadharCardNumber=request.getParameter("aadharCardNumber");
gender=request.getParameter("gender");
isIndian=Boolean.parseBoolean(request.getParameter("isIndian"));
try
{
dateOfBirth=simpleDateFormat.parse(request.getParameter("dateOfBirth"));
}catch(ParseException parseException)
{
throw new DAOException(parseException.getMessage()); //remove after testing
}
basicSalary=new BigDecimal(request.getParameter("basicSalary"));
designationCode=Integer.parseInt(request.getParameter("designationCode"));
EmployeeDAOInterface employeeDAO=new EmployeeDAO();
EmployeeDTOInterface employeeDTO=new EmployeeDTO();
employeeDTO.setName(name);
employeeDTO.setGender(gender);
if(isIndian==false) employeeDTO.setIsIndian(false);
else employeeDTO.setIsIndian(true);
employeeDTO.setEmployeeId(employeeId);
employeeDTO.setPANNumber(panNumber);
employeeDTO.setAadharCardNumber(aadharCardNumber);
employeeDTO.setDesignationCode(designationCode);
employeeDTO.setDateOfBirth(dateOfBirth);
employeeDTO.setBasicSalary(basicSalary);
(new EmployeeDAO()).update(employeeDTO);
ResponseData responseData=new ResponseData();
responseData.setException(false);
responseData.setResult("Employee Updated Successfully.");
Gson gson=new Gson();
String jsonString=gson.toJson(responseData);
System.out.println(jsonString);
pw.print(jsonString);
pw.flush();
return;
}catch(DAOException daoException)
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
catch(Exception exception)
{
System.out.println(exception.getMessage()); //remove after testing.
}
}
}