package com.thinking.machines.hr.tags;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
public class ModuleTagHandler extends TagSupport
{
private String name;
public ModuleTagHandler()
{
reset();
}
private void reset()
{
this.name="";
}
public void setName(String name)
{
this.name=name;
}
public String getName()
{
return this.name;
}
public int doStartTag()
{
pageContext.setAttribute("HOME",0,PageContext.REQUEST_SCOPE);
pageContext.setAttribute("DESIGNATION",1,PageContext.REQUEST_SCOPE);
pageContext.setAttribute("EMPLOYEE",2,PageContext.REQUEST_SCOPE);
if(name.equalsIgnoreCase("HOME"))
{
pageContext.setAttribute("Module",0,PageContext.REQUEST_SCOPE);
}
if(name.equalsIgnoreCase("DESIGNATION"))
{
pageContext.setAttribute("Module",1,PageContext.REQUEST_SCOPE);
}
if(name.equalsIgnoreCase("EMPLOYEE"))
{
pageContext.setAttribute("Module",2,PageContext.REQUEST_SCOPE);
}
return super.SKIP_BODY;
}
public int doEndTag()
{
reset();
return super.EVAL_PAGE;
}
}