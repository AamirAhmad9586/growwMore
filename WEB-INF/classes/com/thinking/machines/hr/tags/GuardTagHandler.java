package com.thinking.machines.hr.tags;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
public class GuardTagHandler extends TagSupport
{
public GuardTagHandler()
{
reset();
}
private void reset()
{
// do nothing
}
public int doStartTag()
{
String username=(String)pageContext.getAttribute("username",PageContext.SESSION_SCOPE);
if(username==null) return super.EVAL_BODY_INCLUDE;
return super.SKIP_BODY;
}
public int doEndTag()
{
reset();
return super.EVAL_PAGE;
}
}