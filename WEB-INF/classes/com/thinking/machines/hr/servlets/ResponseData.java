package com.thinking.machines.hr.servlets;
public class ResponseData implements java.io.Serializable
{
private boolean exception;
private Object result;
public ResponseData()
{
this.exception=false;
this.result=null;
}
public void setException(boolean exception)
{
this.exception=exception;
}
public boolean getException()
{
return this.exception;
}
public void setResult(Object result)
{
this.result=result;
}
public Object getResult()
{
return this.result;
}
}