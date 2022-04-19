package com.thinking.machines.hr.dl;
import java.util.*;
import java.math.*;
public interface EmployeeDTOInterface
{
public void setEmployeeId(String employeeId);
public String getEmployeeId();
public void setName(String name);
public String getName();
public void setDesignationCode(int designationCode);
public int getDesignationCode();
public void setIsIndian(boolean isIndian);
public boolean getIsIndian();
public void setBasicSalary(BigDecimal basicSalary);
public BigDecimal getBasicSalary();
public void setGender(String gender);
public String getGender();
public void setAadharCardNumber(String aadharCardNumber);
public String getAadharCardNumber();
public void setPANNumber(String panNumber);
public String getPANNumber();
public void setDateOfBirth(Date dateOfBirth);
public Date getDateOfBirth();
}