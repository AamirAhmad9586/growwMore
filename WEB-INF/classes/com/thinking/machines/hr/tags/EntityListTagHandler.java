package com.thinking.machines.hr.tags;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.lang.reflect.*;
import java.util.*;
import java.io.*;
public class EntityListTagHandler extends BodyTagSupport
{
private String populatorClass;
private String populatorMethod;
private String name;
private int index;
private java.util.List list;
public EntityListTagHandler()
{
reset();
}
private void reset()
{
this.populatorClass="";
this.populatorMethod="";
this.name="";
this.index=0;
if(list!=null)
{
list.clear();
list=null;
}
}
public void setPopulatorClass(String populatorClass)
{
this.populatorClass=populatorClass;
}
public String getPopulatorClass()
{
return this.populatorClass;
}
public void setPopulatorMethod(String populatorMethod)
{
this.populatorMethod=populatorMethod;
}
public String getPopulatorMethod()
{
return this.populatorMethod;
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
if(name==null || name.trim().length()==0) return super.SKIP_BODY;
try
{
Class c;
c=Class.forName(populatorClass);
Method m=c.getMethod(populatorMethod,new Class[0]);
Object obj=c.newInstance();
list=(java.util.List)m.invoke(obj);
}catch(Throwable t)
{
return super.SKIP_BODY;
}
if(list==null || list.size()==0) return super.SKIP_BODY;
index=0;
pageContext.setAttribute(name,list.get(index),PageContext.REQUEST_SCOPE);
pageContext.setAttribute("serialNumber",new Integer(index+1),PageContext.REQUEST_SCOPE);
return super.EVAL_BODY_INCLUDE;
}
public int doAfterBody()
{
index++;
if(index==list.size()) return super.SKIP_BODY;
pageContext.setAttribute(name,list.get(index),PageContext.REQUEST_SCOPE);
pageContext.setAttribute("serialNumber",new Integer(index+1),PageContext.REQUEST_SCOPE);
return super.EVAL_BODY_AGAIN;
}
public int doEndTag()
{
reset();
return super.EVAL_PAGE;
}
}