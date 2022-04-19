package com.thinking.machines.hr.servlets;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
public class Logout extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
HttpSession session=request.getSession();
session.invalidate();
RequestDispatcher requestDispatcher=request.getRequestDispatcher("/LoginForm.jsp");
try
{
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
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
doGet(request,response);
}
}