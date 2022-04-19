package com.thinking.machines.hr.beans;
public class MessageBean
{
private String heading;
private String message;
private boolean generateButton;
private boolean generateTwoButton;
private String buttonOneText;
private String buttonTwoText;
private String buttonOneAction;
private String buttonTwoAction;
public MessageBean()
{
this.heading="";
this.message="";
this.generateButton=false;
this.generateTwoButton=false;
this.buttonOneText="";
this.buttonTwoText="";
this.buttonOneAction="";
this.buttonTwoAction="";
}
public void setHeading(java.lang.String heading)
{
this.heading=heading;
}
public java.lang.String getHeading()
{
return this.heading;
}
public void setMessage(java.lang.String message)
{
this.message=message;
}
public java.lang.String getMessage()
{
return this.message;
}
public void setGenerateButton(boolean generateButton)
{
this.generateButton=generateButton;
}
public boolean getGenerateButton()
{
return this.generateButton;
}
public void setGenerateTwoButton(boolean generateTwoButton)
{
this.generateTwoButton=generateTwoButton;
}
public boolean getGenerateTwoButton()
{
return this.generateTwoButton;
}
public void setButtonOneText(java.lang.String buttonOneText)
{
this.buttonOneText=buttonOneText;
}
public java.lang.String getButtonOneText()
{
return this.buttonOneText;
}
public void setButtonTwoText(java.lang.String buttonTwoText)
{
this.buttonTwoText=buttonTwoText;
}
public java.lang.String getButtonTwoText()
{
return this.buttonTwoText;
}
public void setButtonOneAction(java.lang.String buttonOneAction)
{
this.buttonOneAction=buttonOneAction;
}
public java.lang.String getButtonOneAction()
{
return this.buttonOneAction;
}
public void setButtonTwoAction(java.lang.String buttonTwoAction)
{
this.buttonTwoAction=buttonTwoAction;
}
public java.lang.String getButtonTwoAction()
{
return this.buttonTwoAction;
}
}