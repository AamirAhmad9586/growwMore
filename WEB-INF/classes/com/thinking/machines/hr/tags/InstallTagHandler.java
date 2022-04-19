package com.thinking.machines.hr.tags;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.io.*;
public class InstallTagHandler extends TagSupport
{
public InstallTagHandler()
{
reset();
}
private void reset()
{
// do nothing
}
public int doStartTag()
{
File file=new File("C://tomcat9//webapps//growwMore//conf//config.json");
if(!file.exists()) return super.EVAL_BODY_INCLUDE;
return super.SKIP_BODY;
}
public int doEndTag()
{
reset();
return super.EVAL_PAGE;
}
}