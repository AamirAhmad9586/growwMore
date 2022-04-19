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
<table id='employeesDataTable' class='table table-bordered' width='100%' cellspacing='0'>
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
$('#employeesDataTable > tbody').append(`<tr>
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