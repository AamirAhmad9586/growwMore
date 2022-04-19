<%@ taglib uri='/WEB-INF/mytags/tmtags.tld' prefix='tm' %>
<tm:Install>
<jsp:forward page='/Installer.jsp' />
</tm:Install>
<tm:Guard>
<jsp:forward page='/LoginForm.jsp' />
</tm:Guard>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>Grow More</title>
<!-- Custom fonts for this template-->
<link href="/growwMore/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<!-- Custom styles for this page -->
<link href="/growwMore/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
<!-- Custom styles for this template-->
<link href="/growwMore/css/sb-admin-2.min.css" rel="stylesheet">
<link rel='stylesheet' type='text/css' href='/growwMore/css/main.css'>
<!-- Bootstrap core JavaScript-->
<script src="/growwMore/vendor/jquery/jquery.min.js"></script>
<script src="/growwMore/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- Core plugin JavaScript-->
<script src="/growwMore/vendor/jquery-easing/jquery.easing.min.js"></script>
<!-- Custom scripts for all pages-->
<script src="/growwMore/js/sb-admin-2.min.js"></script>
<!-- Page level plugins -->
<script src="/growwMore/vendor/datatables/jquery.dataTables.min.js"></script>
<script src="/growwMore/vendor/datatables/dataTables.bootstrap4.min.js"></script>
<script src="/growwMore/js/demo/datatables-demo.js"></script>
<script src='/growwMore/js/main.js'></script>
</head>
<body id="page-top">
<!-- Page Wrapper -->
<div id="wrapper">
<!-- Sidebar -->
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">
<!-- Sidebar - Brand -->
<a class="sidebar-brand d-flex align-items-center justify-content-center" href="#">
<div class="sidebar-brand-icon rotate-n-15">
<i class="fas fa-laugh-wink"></i>
</div>
<div class="sidebar-brand-text mx-3" style="font-size:15pt;font-family:Verdana">Groww More</div>
</a>
<!-- Divider -->
<hr class="sidebar-divider my-0">
<!-- Nav Item - Dashboard -->
<li class="nav-item active">
<a class="nav-link" href="#">
<i class="fas fa-fw fa-tachometer-alt"></i>
<span>Dashboard</span></a>
</li>
<!-- Divider -->
<hr class="sidebar-divider">
<!-- Nav Item - Designations -->
<li class="nav-item">
<a class="nav-link" href="javascript:getDesignations()">
<i class="fas fa-fw fa-table"></i>
<span>Designations</span></a>
</li>
<!-- Nav Item - Employees -->
<li class="nav-item">
<a class="nav-link" href='javascript:getEmployees()'>
<i class="fas fa-fw fa-table"></i>
<span>Employees</span></a>
</li>
</ul>
<!-- Content Wrapper -->
<div id="content-wrapper" class="d-flex flex-column">
<!-- Main Content -->
<div id="content">
<!-- Topbar -->
<nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
<!-- Sidebar Toggle (Topbar) -->
<button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
<i class="fa fa-bars"></i>
</button>
<!-- Topbar Search -->
<form
class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
<div class="input-group">
<input type="text" class="form-control bg-light border-0 small" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
<div class="input-group-append">
<button class="btn btn-primary" type="button">
<i class="fas fa-search fa-sm"></i>
</button>
</div>
</div>
</form>
<!-- Topbar Navbar -->
<ul class="navbar-nav ml-auto">
<!-- Nav Item - Search Dropdown (Visible Only XS) -->
<li class="nav-item dropdown no-arrow d-sm-none">
<a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
<i class="fas fa-search fa-fw"></i>
</a>
<!-- Dropdown - Messages -->
<div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
aria-labelledby="searchDropdown">
<form class="form-inline mr-auto w-100 navbar-search">
<div class="input-group">
<input type="text" class="form-control bg-light border-0 small"
placeholder="Search for..." aria-label="Search"
aria-describedby="basic-addon2">
<div class="input-group-append">
<button class="btn btn-primary" type="button">
<i class="fas fa-search fa-sm"></i>
</button>
</div>
</div>
</form>
</div>
</li>
<!-- Nav Item - Alerts -->
<li class="nav-item dropdown no-arrow mx-1">
<a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button"
data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
<i class="fas fa-bell fa-fw"></i>
<!-- Counter - Alerts -->
<span class="badge badge-danger badge-counter">3+</span>
</a>
<!-- Dropdown - Alerts -->
<div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
aria-labelledby="alertsDropdown">
<h6 class="dropdown-header">
Alerts Center
</h6>
<a class="dropdown-item d-flex align-items-center" href="#">
<div class="mr-3">
<div class="icon-circle bg-primary">
<i class="fas fa-file-alt text-white"></i>
</div>
</div>
<div>
<div class="small text-gray-500">December 12, 2019</div>
<span class="font-weight-bold">A new monthly report is ready to download!</span>
</div>
</a>
<a class="dropdown-item d-flex align-items-center" href="#">
<div class="mr-3">
<div class="icon-circle bg-success">
<i class="fas fa-donate text-white"></i>
</div>
</div>
<div>
<div class="small text-gray-500">December 7, 2019</div>
$290.29 has been deposited into your account!
</div>
</a>
<a class="dropdown-item d-flex align-items-center" href="#">
<div class="mr-3">
<div class="icon-circle bg-warning">
<i class="fas fa-exclamation-triangle text-white"></i>
</div>
</div>
<div>
<div class="small text-gray-500">December 2, 2019</div>
Spending Alert: We've noticed unusually high spending for your account.
</div>
</a>
<a class="dropdown-item text-center small text-gray-500" href="#">Show All Alerts</a>
</div>
</li>
<!-- Nav Item - Messages -->
<li class="nav-item dropdown no-arrow mx-1">
<a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" role="button"
data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
<i class="fas fa-envelope fa-fw"></i>
<!-- Counter - Messages -->
<span class="badge badge-danger badge-counter">7</span>
</a>
<!-- Dropdown - Messages -->
<div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="messagesDropdown">
<h6 class="dropdown-header">
Message Center
</h6>
<a class="dropdown-item d-flex align-items-center" href="#">
<div class="dropdown-list-image mr-3">
<img class="rounded-circle" src="img/undraw_profile_1.svg" alt="...">
<div class="status-indicator bg-success"></div>
</div>
<div class="font-weight-bold">
<div class="text-truncate">Hi there! I am wondering if you can help me with a
problem I've been having.</div>
<div class="small text-gray-500">Emily Fowler · 58m</div>
</div>
</a>
<a class="dropdown-item d-flex align-items-center" href="#">
<div class="dropdown-list-image mr-3">
<img class="rounded-circle" src="img/undraw_profile_2.svg" alt="...">
<div class="status-indicator"></div>
</div>
<div>
<div class="text-truncate">I have the photos that you ordered last month, how
would you like them sent to you?</div>
<div class="small text-gray-500">Jae Chun · 1d</div>
</div>
</a>
<a class="dropdown-item d-flex align-items-center" href="#">
<div class="dropdown-list-image mr-3">
<img class="rounded-circle" src="img/undraw_profile_3.svg" alt="...">
<div class="status-indicator bg-warning"></div>
</div>
<div>
<div class="text-truncate">Last month's report looks great, I am very happy with
the progress so far, keep up the good work!</div>
<div class="small text-gray-500">Morgan Alvarez · 2d</div>
</div>
</a>
<a class="dropdown-item d-flex align-items-center" href="#">
<div class="dropdown-list-image mr-3">
<img class="rounded-circle" src="img/undraw_profile_1.svg" alt="...">
<div class="status-indicator bg-success"></div>
</div>
<div>
<div class="text-truncate">Am I a good boy? The reason I ask is because someone
told me that people say this to all dogs, even if they aren't good...</div>
<div class="small text-gray-500">Chicken the Dog · 2w</div>
</div>
</a>
<a class="dropdown-item text-center small text-gray-500" href="#">Read More Messages</a>
</div>
</li>
<div class="topbar-divider d-none d-sm-block"></div>
<!-- Nav Item - User Information -->
<li class="nav-item dropdown no-arrow">
<a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
<span class="mr-2 d-none d-lg-inline text-gray-600 small">Admin</span>
<img class="img-profile rounded-circle"
src="img/undraw_profile.svg">
</a>
<!-- Dropdown - User Information -->
<div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
aria-labelledby="userDropdown">
<a class="dropdown-item" href="#">
<i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
Profile
</a>
<div class="dropdown-divider"></div>
<a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
<i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
Logout
</a>
</div>
</li>
</ul>
</nav>
<!-- End of Topbar -->



<!-- Begin Page Content -->
<div id="containerFluidContentDivision" class="container-fluid">
<!-- Page Heading -->
<div class="d-sm-flex align-items-center justify-content-between mb-4">
<h1 class="h3 mb-0 text-gray-800">Dashboard</h1>
</div>
<center><h1>Welcome</h1></center>
</div>










<!-- Footer -->
<footer class="sticky-footer bg-white">
<div class="container my-auto">
<div class="copyright text-center my-auto">
<span>Copyright &copy; <a href='https://thinkingmachines.in'>thinkingmachines.in</a> 2021</span>
</div>
</div>
</footer>
<!-- End of Footer -->
</div>
<!-- End of Content Wrapper -->
</div>
<!-- End of Page Wrapper -->
<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
<i class="fas fa-angle-up"></i>
</a>




<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
<div class="modal-dialog" role="document">
<div class="modal-content">
<div class="modal-header">
<h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
<button class="close" type="button" data-dismiss="modal" aria-label="Close">
<span aria-hidden="true">×</span>
</button>
</div>
<div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
<div class="modal-footer">
<button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
<a class="btn btn-primary" href="/growwMore/logout">Logout</a>
</div>
</div>
</div>
</div>


<style>
.addFormTd
{
padding-top:10px;
padding-bottom:10px;
padding-left:20px;
padding-right:30px;
font-size:15pt;
}
.textBox
{
line-height: 31px;
}
</style>


<!-- Add Designation Modal-->
<div class="modal fade" id="addDesignationModal" tabindex="-1" role="dialog" aria-labelledby="addDesignationLabel" aria-hidden="true">
<div class="modal-dialog" role="document">
<div class="modal-content">
<div class="modal-header">
<h5 class="modal-title" id="addDesignationLabel">Designation Add (Module)</h5>
<button class="close" type="button" data-dismiss="modal" aria-label="Close">
<span aria-hidden="true">×</span>
</button>
</div>
<form method='post' action='/growwMore/addDesignation'>
<div class="modal-body">
Please fill the following information-<br><br>
<table>
<tr>
<td class='addFormTd'>Designation Title : </td>
<td><input type="text" id='title' name='title' class="form-control bg-light border-0 small" placeholder="Enter Designation Title" aria-describedby="basic-addon2"></td>
</tr>
</table>
</div>
<div class="modal-footer">
<button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
<a class="btn btn-primary" href='javascript:addDesignation(this)'>Add</a>
</div>
</form>
</div>
</div>
</div>







<!-- Add Employee Modal-->
<div class="modal fade" id="addEmployeeModal" tabindex="-1" role="dialog" aria-labelledby="addEmployeeLabel" aria-hidden="true">
<div class="modal-dialog" role="document">
<div class="modal-content">
<div class="modal-header">
<h5 class="modal-title" id="addEmployeeLabel">Employee Add (Module)</h5>
<button class="close" type="button" data-dismiss="modal" aria-label="Close">
<span aria-hidden="true">×</span>
</button>
</div>
<form method='post' action='/growwMore/addEmployee'>
<div class="modal-body" id='modalBodyDivision'>
Please fill the following information-<br><br>
<table>
<tr>
<td class='addFormTd'>Employee Name : </td>
<td><input type="text" id='employeeName' name='employeeName' class="form-control bg-light border-0 small" placeholder="Enter Employee Name" aria-describedby="basic-addon2"></td>
</tr>
<tr>
<td class='addFormTd'>Designation : </td>
<td><select id='designationCode' name='designationCode' class="form-control bg-light border-0 small">
<option value='-1'>< select ></option>
</select></td>
</tr>
<tr>
<td class='addFormTd'>Date of Birth : </td>
<td><input type='date' class='form-control' id='dateOfBirth' name='dateOfBirth' placeholder='DD/MM/YYYY' /></td>
</tr>

<tr>
<td class='addFormTd'>Gender : </td>
<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" id='male' name='gender' class="form-check-input" value='Male'>Male&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="radio" id='female' name='gender' class="form-check-input" value='Female'>Female</td>
</tr>



<tr>
<td class='addFormTd'>Indian : </td>
<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" id='isIndian' name='isIndian' class="form-check-input"></td>
</tr>


<tr>
<td class='addFormTd'>Basic Salary : </td>
<td><input type="number" id='basicSalary' name='basicSalary' class="form-control bg-light border-0 small" placeholder="Enter Basic Salary" aria-describedby="basic-addon2"></td>
</tr>



<tr>
<td class='addFormTd'>PAN Number : </td>
<td><input type="text" id='panCardNumber' name='panCardNumber' class="form-control bg-light border-0 small" placeholder="PAN Card Number" aria-describedby="basic-addon2"></td>
</tr>



<tr>
<td class='addFormTd'>Aadhar Card Number : </td>
<td><input type="number" id='aadharCardNumber' name='aadharCardNumber' class="form-control bg-light border-0 small" placeholder="Aadhar Card Number" aria-describedby="basic-addon2" maxsize='12'></td>
</tr>


</table>
</div>
<div class="modal-footer">
<button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
<a class="btn btn-primary" href='javascript:addEmployee(this)'>Add</a>
</div>
</form>
</div>
</div>
</div>



<!-- Employee Detail Modal-->
<div class="modal fade" id="employeeDetailModal" tabindex="-1" role="dialog" aria-labelledby="employeeDetailLabel" aria-hidden="true">
<div class="modal-dialog" role="document">
<div class="modal-content">
<div class="modal-header">
<h5 class="modal-title" id="employeeDetailLabel">Employee Details - </h5>
<button class="close" type="button" data-dismiss="modal" aria-label="Close">
<span aria-hidden="true">×</span>
</button>
</div>
<div class="modal-body" id='employeeDetailDivision'></div>
<div class="modal-footer">
<button class="btn btn-secondary" type="button" data-dismiss="modal" id='employeeDetailCloseButton'>Back</button>
<a class="btn btn-primary" style='display:none' id='deanchor'>Logout</a>
</div>
</div>
</div>
</div>




<!-- Designation Detail Modal-->
<div class="modal fade" id="designationDetailModal" tabindex="-1" role="dialog" aria-labelledby="designationDetailLabel" aria-hidden="true">
<div class="modal-dialog" role="document">
<div class="modal-content">
<div class="modal-header">
<h5 class="modal-title" id="designationDetailLabel">Designation Details - </h5>
<button class="close" type="button" data-dismiss="modal" aria-label="Close">
<span aria-hidden="true">×</span>
</button>
</div>
<div class="modal-body" id='designationDetailDivision'></div>
<div class="modal-footer">
<button class="btn btn-secondary" type="button" data-dismiss="modal" id='designationDetailCloseButton'>Back</button>
<a class="btn btn-primary" style='display:none' id='ddeanchor'>Logout</a>
</div>
</div>
</div>
</div>


</body>
</html>