package com.thinking.machines.hr.servlets;
import javax.servlet.*;
import javax.servlet.http.*;
import com.thinking.machines.hr.dl.*;
import com.thinking.machines.hr.beans.*;
import java.io.*;
public class Login extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
AdministratorBean administratorBean=(AdministratorBean)request.getAttribute("administratorBean");
AdministratorDTOInterface administratorDTO=null;
try
{
administratorDTO=new AdministratorDAO().getByUsername(administratorBean.getUsername());
}catch(DAOException daoException)
{
ErrorBean errorBean=new ErrorBean();
errorBean.setError(daoException.getMessage());
request.setAttribute("errorBean",errorBean);
RequestDispatcher requestDispatcher=request.getRequestDispatcher("/LoginForm.jsp");
try
{
requestDispatcher.forward(request,response);
}catch(IOException ioException)
{
System.out.println(ioException.getMessage());
}
catch(ServletException servletException)
{
System.out.println(servletException.getMessage());
}
}
if(!administratorDTO.getPassword().equals(administratorBean.getPassword()))
{
ErrorBean errorBean=new ErrorBean();
errorBean.setError("Invalid Username or Password");
request.setAttribute("errorBean",errorBean);
RequestDispatcher requestDispatcher=request.getRequestDispatcher("/LoginForm.jsp");
try
{
requestDispatcher.forward(request,response);
}catch(IOException ioException)
{
System.out.println(ioException.getMessage());
}
catch(ServletException servletException)
{
System.out.println(servletException.getMessage());
}
}
HttpSession ss=request.getSession();
ss.setAttribute("username",administratorDTO.getUsername());
RequestDispatcher requestDispatcher=request.getRequestDispatcher("/index.jsp");
try
{
requestDispatcher.forward(request,response);
}catch(IOException ioException)
{
System.out.println(ioException.getMessage());
}
catch(ServletException servletException)
{
System.out.println(servletException.getMessage());
}
}
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
doGet(request,response);
}
}