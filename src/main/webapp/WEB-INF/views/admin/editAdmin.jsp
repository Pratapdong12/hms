<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Admin Dashboard</title>
	<%@include file="aheader.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

	


  <!-- Left side column. contains the logo and sidebar -->
 
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
  
  	<section class="content-header">
      <h1>
        Edit Admin
        
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li><a href="#"><i class="active fa fa-user-md"></i>Edit Admin</a></li>
        
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">

      <!-- SELECT2 EXAMPLE -->
      <div class="box box-primary">
        
        <div class="box-body">
          <form role="form" action="admin_update"  method="POST"  >
            <div class="form-group">
             	 <label><b>Id:</b></label>
             	 <input type="text" class="form-control"  name="id" value="${Admin.id}"
								class="form-control" readonly="readonly">
             	</div>
             
             	<div class="form-group">
             	 <label><b>Firstname</b></label>
             	 <input type="text" class="form-control" name="firstName" value="${Admin.firstName}" placeholder="Firstname">
             	</div>
             	
             	<div class="form-group">
             	 <label><b>Lastname</b></label>
             	 <input type="text" class="form-control" name="lastName" value="${Admin.lastName}"  placeholder="Lastname">
             	</div>
             	
             	<div class="form-group">
             	 <label><b>Address</b></label>
             	 <input type="text" class="form-control" name="address" value="${Admin.address}"  placeholder="Address">
             	</div>
             	
             	<div class="form-group">
             	 <label><b>Contact</b></label>
             	 <input type="text" class="form-control" name="contactNumber" value="${Admin.contactNumber}" placeholder="Contact">
             	</div>
             	
             	<div class="form-group">
             	 <label><b>Email</b></label>
             	 <input type="text" class="form-control" name="email" value="${Admin.email}" placeholder="Email">
             	</div>
             	
             	<div class="form-group">
             	 <label><b>Password</b></label>
             	 <input type="password" class="form-control" name="password" value="${Admin.password}"  placeholder="Password"  >
             	</div>
             	
             	<div class="form-group">
             	 <label><b>DOB</b></label>
             	 <input type="date" name="dob" value="${Admin.dob}" class="form-control">
             	</div>
             	
             	
             	
             	<div class="form-group">
             	 <label><b>Sex</b></label>
             	 
             	 <input type="radio" name="sex" value="Male" ${Admin.sex=='Male'?'checked':''} placeholder="Male"> Male 
				<input type="radio" name="sex" value="Female" ${Admin.sex=='Female'?'checked':''} placeholder="Female"> Female
				<input type="radio" name="sex" value="Others" ${Admin.sex=='Others'?'checked':''} placeholder="Others"> Others
             	</div>
             	
             	<div class="form-group">
             	 <label><b>Blood Group</b></label>
             	 <input type="text" class="form-control" name="bloodGroup" value="${Admin.bloodGroup}"  placeholder="Blood Group">
             	</div>
             	
             	
             	
             	
             	<button type="submit" class="btn btn-secondary">Update</button>
             </form>
        </div>
        <!-- /.box-body -->
       
      </div>
      <!-- /.box -->

 
 

    </section>
    <!-- /section -->
  </div>
  <!-- /.content-wrapper -->
  <footer class="main-footer">
    <div class="pull-right hidden-xs">
      <b>Version</b> 2.4.0
    </div>
    <strong>Copyright &copy; 2014-2016 <a href="https://adminlte.io">Almsaeed Studio</a>.</strong> All rights
    reserved.
  </footer>


</div>
<!-- ./wrapper -->

<!-- jQuery 3 -->
<script src="resources/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- FastClick -->
<script src="resources/bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="resources/dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="resources/dist/js/demo.js"></script>
</body>
</html>
