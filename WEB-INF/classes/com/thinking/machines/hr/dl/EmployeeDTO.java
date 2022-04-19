package com.thinking.machines.hr.dl;
import java.util.*;
import java.math.*;
public class EmployeeDTO implements EmployeeDTOInterface
{
private String employeeId;
private String name;
private String gender;
private String aadharCardNumber;
private String panNumber;
private boolean isIndian;
private int designationCode;
private BigDecimal basicSalary;
private Date dateOfBirth;
public EmployeeDTO()
{
this.employeeId="";
this.name="";
this.gender="";
this.aadharCardNumber="";
this.panNumber="";
this.isIndian=false;
this.designationCode=0;
this.basicSalary=null;
this.dateOfBirth=null;
}
public void setEmployeeId(String employeeId)
{
this.employeeId=employeeId;
}
public String getEmployeeId()
{
return this.employeeId;
}
public void setName(String name)
{
this.name=name;
}
public String getName()
{
return this.name;
}
public void setGender(String gender)
{
this.gender=gender;
}
public String getGender()
{
return this.gender;
}
public void setAadharCardNumber(String aadharCardNumber)
{
this.aadharCardNumber=aadharCardNumber;
}
public String getAadharCardNumber()
{
return this.aadharCardNumber;
}
public void setPANNumber(String panNumber)
{
this.panNumber=panNumber;
}
public String getPANNumber()
{
return this.panNumber;
}
public void setBasicSalary(BigDecimal basicSalary)
{
this.basicSalary=basicSalary;
}
public BigDecimal getBasicSalary()
{
return this.basicSalary;
}
public void setDateOfBirth(Date dateOfBirth)
{
this.dateOfBirth=dateOfBirth;
}
public Date getDateOfBirth()
{
return this.dateOfBirth;
}
public void setIsIndian(boolean isIndian)
{
this.isIndian=isIndian;
}
public boolean getIsIndian()
{
return this.isIndian;
}
public void setDesignationCode(int designationCode)
{
this.designationCode=designationCode;
}
public int getDesignationCode()
{
return this.designationCode;
}
}