var ds={
"designations" : [],
"employees": []
};
$(()=>{
$('#addDesignationModal').on('hidden.bs.modal',function(){
$('#title').val("");
});

$('#addEmployeeModal').on('hidden.bs.modal',function(){
$('#employeeName').val("");
$('#designationCode').val(-1);
$('#dateOfBirth').val("");
$('#male').prop('checked',false);
$('#female').prop('checked',false);
$('#isIndian').prop('checked',false);
$('#basicSalary').val("");
$('#panCardNumber').val("");
$('#aadharCardNumber').val("");
});

$('#designationCode').change(function(ev){
if($(this).val()=='addDesignation')
{
$('#addEmployeeModal').modal('hide');
$('#addDesignationModal').modal('show');
}
});

$('#addEmployeeModal').on('shown.bs.modal',function(ev){
doSomething();
});
});
function doSomething()
{
var xmlHttpRequest=new XMLHttpRequest();
xmlHttpRequest.onreadystatechange=function(){
if(this.readyState==4)
{
if(this.status==200)
{
var responseData=JSON.parse(this.responseText);
if(responseData.exception==true)
{
alert(responseData.result);
return;
}
responseData=responseData.result;
var selectTag=$('#designationCode');
$('#designationCode > option').each(function(rowIndex,row){
if(row.value!=-1) row.remove();
});
for(var i=0;i<responseData.length;i++) selectTag.append(`<option value="${responseData[i].code}">${responseData[i].title}</option>`);
}
else
{
alert('Some Problem');
}
}
};
xmlHttpRequest.open("POST","getDesignations",true);
xmlHttpRequest.setRequestHeader("Content-Type","application/json");
xmlHttpRequest.send();
}
function getDesignationDetail(code,title)
{
var designationDetail=$(`<div>
<table id='designationDetailTable'>
<tr>
<td>Designation Code&nbsp;&nbsp;:&nbsp;&nbsp;</td>
<td><b>${code}</b></td>
<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td style='padding-left:70px' id='edit${code}'><a href='javascript:editDesignation("${code}")'>Edit&nbsp;</a></td>
<td style='padding-left:10px' id='delete${code}'><a href='javascript:deleteDesignation("${code}")'>Delete</a></td>
</tr>
<tr>
<td>Designation Title&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;</td>
<td><b>${title}</b></td>
</tr>
</table>
</div>`);
$('#designationDetailDivision').empty();
$('#designationDetailDivision').append(designationDetail);
$('#designationDetailLabel').text('Designation Details - ');
$('#designationDetailCloseButton').html('Back');
document.getElementById('ddeanchor').style.display='none';
$('#designationDetailModal').modal('show');
}
function deleteDesignation(code)
{
document.getElementById('delete'+code).style.display='none';
document.getElementById('edit'+code).style.display='none';
$('#designationDetailLabel').text('Designation Delete (Module) - ');
$('#designationDetailCloseButton').html('Close');
var ddeanchor=document.getElementById('ddeanchor');
ddeanchor.style.display='block';
ddeanchor.innerHTML='Delete';
ddeanchor.setAttribute("href","javascript:delDesignation('"+code+"')");
}
function delDesignation(code)
{
var queryString="code="+code;
var xmlHttpRequest=new XMLHttpRequest();
xmlHttpRequest.onreadystatechange=function(){
if(this.readyState==4)
{
if(this.status==200)
{
var responseData=JSON.parse(this.responseText);
if(responseData.exception==true)
{
alert(responseData.result);
return;
}
alert("Designation Deleted Successfully");
$('#designationDetailModal').modal('hide');
getDesignations();
}
else
{
alert('Some Problem');
}
}
};
xmlHttpRequest.open("POST","deleteDesignation",true);
xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
xmlHttpRequest.send(queryString);
}
function editEmployee(employeeId)
{
var queryString="employeeId="+employeeId;
var xmlHttpRequest=new XMLHttpRequest();
xmlHttpRequest.onreadystatechange=function(){
if(this.readyState==4)
{
if(this.status==200)
{
var responseData=JSON.parse(this.responseText);
if(responseData.exception==true)
{
alert(responseData.result);
return;
}
responseData=responseData.result;
var updateForm=$(`<form id='updateEmployeeForm'>
</form>`);
var tr=$(`<tr>
<td class='addFormTd'>Employee ID : </td>
<td><b aria-describedby="basic-addon2" >${employeeId}</b></td>
</tr>`);
var table=$(`<table></table>`);
table.append(tr);
tr=$(`<tr>
<td class='addFormTd'>Employee Name : </td>
<td><input type="text" id='employeeName' name='employeeName' class="form-control bg-light border-0 small" placeholder="Enter Employee Name" aria-describedby="basic-addon2" value='${responseData.name}'></td>
</tr>`);
table.append(tr);
tr=$(`<tr>
<td class='addFormTd'>Designation : </td>
</tr>`);
var td=$(`<td></td>`);
var select=$(`<select id='designationCode' name=designationCode' class='form-control bg-light border-0 small'></select>`);
var option;
for(var i=0;i<ds.designations.length;i++)
{
if(ds.designations[i].code==responseData.designationCode)
{
option=$(`<option selected value='${ds.designations[i].code}'>${ds.designations[i].title}</option>`);
}
else
{
option=$(`<option value='${ds.designations[i].code}'>${ds.designations[i].title}</option>`);
}
select.append(option);
}
td.append(select);
tr.append(td);
table.append(tr);
tr=$(`<tr>
<td class='addFormTd'>Date of Birth : </td>
<td><input type='date' class='form-control' id='dateOfBirth' name='dateOfBirth' placeholder='DD/MM/YYYY' value='${responseData.dateOfBirth}'></td>
</tr>`);
table.append(tr);
tr=$(`<tr>
<td class='addFormTd'>Gender : </td>
</tr>`);
if(responseData.gender=='Male')
{
td=$(`<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" id='male' name='gender' class="form-check-input" checked value='Male'>Male</input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="radio" id='female' name='gender' class="form-check-input" value='Female'>Female</input>
</td>`);
}
else
{
td=$(`<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" id='male' name='gender' class="form-check-input" value='Male'>Male</input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="radio" id='female' name='gender' class="form-check-input" checked value='Female' checked>Female</input>
</td>`);
}
tr.append(td);
table.append(tr);
if(responseData.isIndian==true)
{
tr=$(`<tr>
<td class='addFormTd'>Indian : </td>
<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" id='isIndian' name='isIndian' checked class="form-check-input"></td>
</tr>`);
}
else
{
tr=$(`<tr>
<td class='addFormTd'>Indian : </td>
<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" id='isIndian' name='isIndian' class="form-check-input"></td>
</tr>`);
}
table.append(tr);
tr=$(`<tr>
<td class='addFormTd'>Basic Salary : </td>
<td><input type="number" id='basicSalary' name='basicSalary' class="form-control bg-light border-0 small" placeholder="Enter Basic Salary" aria-describedby="basic-addon2" value='${responseData.basicSalary}'></td>
</tr>
<tr>
<td class='addFormTd'>PAN Number : </td>
<td><input type="text" id='panCardNumber' name='panCardNumber' class="form-control bg-light border-0 small" placeholder="PAN Card Number" aria-describedby="basic-addon2" value='${responseData.panNumber}'></td>
</tr>
<tr>
<td class='addFormTd'>Aadhar Card Number : </td>
<td><input type="number" id='aadharCardNumber' name='aadharCardNumber' class="form-control bg-light border-0 small" placeholder="Aadhar Card Number" aria-describedby="basic-addon2" maxsize='12' value='${responseData.aadharCardNumber}'></td>
</tr>`);
table.append(tr);
updateForm.prepend(table);

$('#employeeDetailLabel').text('Employee Edit (Module) - ');
$('#employeeDetailCloseButton').html('Close');
var deanchor=document.getElementById('deanchor');
deanchor.style.display='block';
deanchor.innerHTML='Update';
deanchor.onclick=function(){
var isIndian;
if(updateEmployeeForm.isIndian.checked) isIndian=true;
else isIndian=false;
var queryString="employeeId="+employeeId;
queryString+="&name="+updateEmployeeForm.employeeName.value;
queryString+="&designationCode="+updateEmployeeForm.designationCode.value;
queryString+="&dateOfBirth="+updateEmployeeForm.dateOfBirth.value;
queryString+="&gender="+updateEmployeeForm.gender.value;
queryString+="&isIndian="+isIndian;
queryString+="&basicSalary="+updateEmployeeForm.basicSalary.value;
queryString+="&panNumber="+updateEmployeeForm.panCardNumber.value;
queryString+="&aadharCardNumber="+updateEmployeeForm.aadharCardNumber.value;
ediEmployee(queryString);
}
$('#employeeDetailDivision').empty();
$('#employeeDetailDivision').append(updateForm);
}
else
{
alert('Some Problem');
}
}
};
xmlHttpRequest.open("POST","getEmployee",true);
xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
xmlHttpRequest.send(queryString);
}
function ediEmployee(queryString)
{
var xmlHttpRequest=new XMLHttpRequest();
xmlHttpRequest.onreadystatechange=function(){
if(this.readyState==4)
{
if(this.status==200)
{
var responseData=JSON.parse(this.responseText);
if(responseData.exception==true)
{
alert(responseData.result);
return;
}
responseData=responseData.result;
alert(responseData);
$('#employeeDetailModal').modal('hide');
getEmployees();
}
else
{
alert('Some Problem');
}
}
};
xmlHttpRequest.open("POST","updateEmployee",true);
xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
xmlHttpRequest.send(queryString);
}
function deleteEmployee(employeeId)
{
document.getElementById('delete'+employeeId).style.display='none';
document.getElementById('edit'+employeeId).style.display='none';
$('#employeeDetailLabel').text('Employee Delete (Module) - ');
$('#employeeDetailCloseButton').html('Close');
var deanchor=document.getElementById('deanchor');
deanchor.style.display='block';
deanchor.innerHTML='Delete';
deanchor.setAttribute("href","javascript:delEmployee('"+employeeId+"')");
}
function delEmployee(employeeId)
{
var queryString="employeeId="+employeeId;
var xmlHttpRequest=new XMLHttpRequest();
xmlHttpRequest.onreadystatechange=function(){
if(this.readyState==4)
{
if(this.status==200)
{
var responseData=JSON.parse(this.responseText);
if(responseData.exception==true)
{
alert(responseData.result);
return;
}
responseData=responseData.result;
alert(responseData);
$('#employeeDetailModal').modal('hide');
getEmployees();
}
else
{
alert('Some Problem');
}
}
};
xmlHttpRequest.open("POST","deleteEmployee",true);
xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
xmlHttpRequest.send(queryString);
}
function getEmployeeDetail(employeeId)
{
var queryString="employeeId="+employeeId;
var xmlHttpRequest=new XMLHttpRequest();
xmlHttpRequest.onreadystatechange=function(){
if(this.readyState==4)
{
if(this.status==200)
{
var responseData=JSON.parse(this.responseText);
if(responseData.exception==true)
{
alert(responseData.result);
return;
}
responseData=responseData.result;
var nationality;
if(responseData.isIndian==true) nationality="Indian";
else nationality="Not an Indian";
var employeeDetail=$(`<div>
<table id='employeeDetailTable'>
<tr>
<td>Employee ID : </td>
<td><b>${responseData.employeeId}</b></td>
<td style='padding-left:70px' id='edit${responseData.employeeId}'><a href='javascript:editEmployee("${responseData.employeeId}")'>Edit</a></td>
<td style='padding-left:10px' id='delete${responseData.employeeId}'><a href='javascript:deleteEmployee("${responseData.employeeId}")'>Delete</a></td>
</tr>
<tr>
<td>Employee Name : </td>
<td><b>${responseData.name}</b></td>
</tr>
<tr>
<td>Designation : </td>
<td><b>${responseData.designation}</b></td>
</tr>
<tr>
<td>Date Of Birth : </td>
<td><b>${responseData.dateOfBirth}</b></td>
</tr>
<tr>
<td>Gender : </td>
<td><b>${responseData.gender}</b></td>
<tr>
<td>Nationality : </td>
<td><b>${nationality}</b></td>
</tr>
<tr>
<td>Basic Salary : </td>
<td><b>${responseData.basicSalary}</b></td>
</tr>
<tr>
<td>PAN Card Number : </td>
<td><b>${responseData.panNumber}</b></td>
</tr>
<tr>
<td>Aadhar Card Number : &nbsp;&nbsp;</td>
<td><b>${responseData.aadharCardNumber}</b></td>
</tr>
</table>
</div>`);
$('#employeeDetailDivision').empty();
$('#employeeDetailDivision').append(employeeDetail);
$('#employeeDetailLabel').text('Employee Details - ');
$('#employeeDetailCloseButton').html('Back');
document.getElementById('deanchor').style.display='none';
$('#employeeDetailModal').modal('show');
}
else
{
alert('Some Problem');
}
}
};
xmlHttpRequest.open("POST","getEmployee",true);
xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
xmlHttpRequest.send(queryString);
}
function getEmployees()
{
var xmlHttpRequest=new XMLHttpRequest();
xmlHttpRequest.onreadystatechange=function(){
if(this.readyState==4)
{
if(this.status==200)
{
var responseData=JSON.parse(this.responseText);
if(responseData.exception==true)
{
alert(responseData.result);
return;
}
responseData=responseData.result;
$('#employeesDataTable > tbody >tr').each(function(rowIndex,row){
row.remove();
});




$('#containerFluidContentDivision').remove();
var containerFluidDiv=$(`<div class='container-fluid' id='containerFluidContentDivision' >
<h1 class='h3 mb-2 text-gray-800'></h1>
<p class='mb-4'>Employees</p>
<div class='card shadow mb-4'>
<div class='card-header py-3'>

 

<h6 class='m-0 font-weight-bold text-primary'>Registered Employees</h6>
<div class='myAddEmployeeDiv' data-target='#addEmployeeModal' data-toggle='modal'>
<h4 class='m-0 font-weight-bold text-primary'>Add Employee</h4>
<button id='plusButton' class='btn btn-outline-primary myPlusButton'>+</button>
</div>

<div class='card-body'>
<div class='table-responsive'>
<table id='employeesDataTable' class='table table-bordered' width='100%' cellspacing='0' style='cursor:pointer'>
<thead>
<tr>
<th>S.No.</th>
<th>Employee ID</th>
<th>Employee Name</th>
<th>Designation</th>
</tr>
</thead>
<tfoot>
<tr>
<th>S.No.</th>
<th>Employee ID</th>
<th>Employee Name</th>
<th>Designation</th>
</tr>
</tfoot>
<tbody>
</tbody>
</table>
</div>
</div>
</div>
</div>
</div>`);
var contentDiv=document.getElementById('content');
var footer=document.getElementsByTagName("footer")[0];
contentDiv.insertBefore(containerFluidDiv.get(0),footer);
$(document).ready(function(){
$('#employeesDataTable').DataTable({
"responsive" : true
});
$(".dataTables_empty").remove();
});
var designations=[];
var rd;
xmlHttpRequest.onreadystatechange=function(){
if(this.readyState==4)
{
if(this.status==200)
{
rd=JSON.parse(this.responseText);
if(rd.exception)
{
alert(rd.result);
return;
}
designations=rd.result;
for(var k=0;k<responseData.length;k++)
{
for(var j=0;j<designations.length;j++)
{
if(designations[j].code==responseData[k].designationCode) break;
}
$('#employeesDataTable > tbody').append(`<tr class='ignoreHover' onclick='getEmployeeDetail("${responseData[k].employeeId}")'>
<td>${k+1}</td>
<td>${responseData[k].employeeId}</td>
<td>${responseData[k].name}</td>
<td>${designations[j].title}</td>
</tr>`);
}
}
else
{
alert('Some Problem');
}
}
};
xmlHttpRequest.open("POST","getDesignations",true);
xmlHttpRequest.send();
}
else
{
alert('Some Problem');
}
}
};
xmlHttpRequest.open("POST","getEmployees",true);
xmlHttpRequest.send();
}
function addDesignation(frm)
{
var title=frm.title.value.trim();
if(title.length==0)
{
frm.title.focus();
alert('please, enter designation title');
return false;
}
var queryString="title="+title;
var xmlHttpRequest=new XMLHttpRequest();
xmlHttpRequest.onreadystatechange=function()
{
if(this.readyState==4)
{
if(this.status==200)
{
responseData=JSON.parse(this.responseText);
if(responseData.exception==true)
{
alert(responseData.result);
return;
}
alert('Designation added successfully.');
$('#addDesignationModal').modal('hide');
responseData=responseData.result;
$('#designationsDataTable > tbody > tr').each(function(rowIndex,row){
row.remove();
});
var tbody=$('#designationsDataTable > tbody ');
ds.designations=[];
for(var i=0;i<responseData.length;i++)
{
ds.designations[ds.designations.length]=responseData[i];
tbody.append(`
<tr class='ignoreHover' style='cursor:pointer' onClick='getDesignationDetail(${responseData[i].code},"${responseData[i].title}")'>
<td>${responseData[i].code}</td>
<td>${responseData[i].title}</td>
</tr>
`);
}
}
else
{
alert('Some Problem');
}
}
};
xmlHttpRequest.open("POST","addDesignation",true);
xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
xmlHttpRequest.send(queryString);
}
function getDesignations()
{
var xmlHttpRequest=new XMLHttpRequest();
xmlHttpRequest.onreadystatechange=function(){
if(this.readyState==4)
{
if(this.status==200)
{
var responseData=JSON.parse(this.responseText);
if(responseData.exception==true)
{
alert(responseData.result);
return;
}
responseData=responseData.result;
$('#designationsDataTable > tbody >tr').each(function(rowIndex,row){
row.remove();
});
$('#containerFluidContentDivision').remove();
var containerFluidDiv=$(`<div class='container-fluid' id='containerFluidContentDivision' >
<h1 class='h3 mb-2 text-gray-800'></h1>
<p class='mb-4'>Designations</p>
<div class='card shadow mb-4'>
<div class='card-header py-3'>

<h6 class='m-0 font-weight-bold text-primary'>Registered Designations</h6>
<div class='myAddDesignationDiv' data-target='#addDesignationModal' data-toggle='modal'>
<h4 class='m-0 font-weight-bold text-primary'>Add Designation</h4>
<button id='plusButton' class='btn btn-outline-primary myPlusButton'>+</button>
</div>


<div class='card-body'>
<div class='table-responsive'>
<table id='designationsDataTable' class='table table-bordered' width='100%' cellspacing='0'>
<thead>
<tr>
<th>Code</th>
<th>Designation</th>
</tr>
</thead>
<tfoot>
<tr>
<th>Code</th>
<th>Designation</th>
</tr>
</tfoot>
<tbody>
</tbody>
</table>
</div>
</div>
</div>
</div>
</div>`);
var contentDiv=document.getElementById('content');
var footer=document.getElementsByTagName("footer")[0];
contentDiv.insertBefore(containerFluidDiv.get(0),footer);
$(document).ready(function(){
$('#designationsDataTable').DataTable({
"responsive" : true
});
});
for(var i=0;i<responseData.length;i++)
{
ds.designations[ds.designations.length]=responseData[i];
$('#designationsDataTable > tbody').append(`<tr class='ignoreHover' style='cursor:pointer' onClick='getDesignationDetail(${responseData[i].code},"${responseData[i].title}")'>
<td>${responseData[i].code}</td>
<td>${responseData[i].title}</td>
</tr>`);
}
}
else
{
alert('Some Problem');
}
}
};
xmlHttpRequest.open("POST","getDesignations",true);
xmlHttpRequest.setRequestHeader("Content-Type","application/json");
xmlHttpRequest.send();
}
function addEmployee(frm)
{
var focusComponent=false;
var isValid=true;
var employeeName=frm.employeeName.value.trim();
if(employeeName.length==0)
{
focusComponent=frm.employeeName;
isValid=false;
alert('please,enter employee name');
}
else
{
var characters='abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ. ';
for(var e=0;e<employeeName.length;e++)
{
if(characters.indexOf(employeeName.charAt(e))==-1)
{
focusComponent=frm.employeeName;
isValid=false;
alert('invalid employee name');
break;
}
}
}
var designationCode=frm.designationCode.value.trim();
if(designationCode==-1)
{
if(!focusComponent) focusComponent=frm.designationCode;
isValid=false;
alert('please, select designation');
}
var dateOfBirth=frm.dateOfBirth.value.trim();
if(dateOfBirth.length==0)
{
if(!focusComponent) focusComponent=frm.dateOfBirth;
isValid=false;
alert('please, enter date of birth');
}
if(frm.male.checked==false && frm.female.checked==false)
{
isValid=false;
alert('please, select gender');
}
var gender;
if(frm.male.checked) gender=frm.male.value;
if(frm.female.checked) gender=frm.female.value;
var isIndian=frm.isIndian.checked;
var basicSalary=frm.basicSalary.value.trim();
if(basicSalary.length==0)
{
if(!focusComponent) focusComponent=frm.basicSalary;
isValid=false;
alert('please, enter basic salary');
}
var panCardNumber=frm.panCardNumber.value.trim();
if(panCardNumber.length==0)
{
if(!focusComponent) focusComponent=frm.panCardNumber;
isValid=false;
alert('please, enter PAN Number');
}
var aadharCardNumber=frm.aadharCardNumber.value.trim();
if(aadharCardNumber.length==0)
{
if(!focusComponent) focusComponent=frm.aadharCardNumber;
isValid=false;
alert('please, enter aadhar card number');
}
if(focusComponent)
{
focusComponent.focus();
return isValid;
}

var queryString="name="+employeeName;
queryString+="&designationCode="+designationCode;
queryString+="&dateOfBirth="+dateOfBirth;
queryString+="&gender="+gender;
if(frm.isIndian.checked==true) queryString+="&isIndian="+true;
queryString+="&basicSalary="+basicSalary;
queryString+="&panCardNumber="+panCardNumber;
queryString+="&aadharCardNumber="+aadharCardNumber;

var xmlHttpRequest=new XMLHttpRequest();
xmlHttpRequest.onreadystatechange=function()
{
if(this.readyState==4)
{
if(this.status==200)
{
responseData=JSON.parse(this.responseText);
if(responseData.exception==true)
{
alert(responseData.result);
return;
}
alert('Employee added successfully.');
$('#addEmployeeModal').modal('hide');
responseData=responseData.result;
$('#employeesDataTable > tbody > tr').each(function(rowIndex,row){
row.remove();
});
var designations=[];
var rd;
xmlHttpRequest.onreadystatechange=function(){
if(this.readyState==4)
{
if(this.status==200)
{
rd=JSON.parse(this.responseText);
if(rd.exception)
{
alert(rd.result);
return;
}
designations=rd.result;
for(var i=0;i<responseData.length;i++)
{
for(var j=0;j<designations.length;j++)
{
if(designations[j].code==responseData[i].designationCode) break;
}
$('#employeesDataTable > tbody').append(`<tr class='ignoreHover' onclick='getEmployeeDetail("${responseData[i].employeeId}")'>
<td>${i+1}</td>
<td>${responseData[i].employeeId}</td>
<td>${responseData[i].name}</td>
<td>${designations[j].title}</td>
</tr>`);
}
}
else
{
alert('Some Problem');
}
}
};
xmlHttpRequest.open("POST","getDesignations",true);
xmlHttpRequest.send();
}
else
{
alert('Some Problem');
}
}
};
xmlHttpRequest.open("POST","addEmployee",true);
xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
xmlHttpRequest.send(queryString);
}