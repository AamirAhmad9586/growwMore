package com.thinking.machines.hr.tags;
import javax.servlet.jsp.tagext.*;
import java.util.*;
import javax.servlet.jsp.*;
import java.io.*;
public class FormIDTagHandler extends TagSupport
{
public FormIDTagHandler()
{
reset();
}
private void reset()
{
// do nothing
}
public int doStartTag()
{
String formId=UUID.randomUUID().toString();
pageContext.setAttribute(formId,formId,PageContext.SESSION_SCOPE);
JspWriter jw=pageContext.getOut();
try
{
jw.println("<input type='hidden' id='formId' name='formId' value='"+formId+"'>");
}catch(IOException ioException)
{
return super.SKIP_BODY;
}
return super.SKIP_BODY;
}
public int doEndTag()
{
reset();
return super.EVAL_PAGE;
}
}