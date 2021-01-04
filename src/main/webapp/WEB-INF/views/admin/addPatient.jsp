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
        Add Patient
        
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li><a href="#"><i class="fa fa-wheelchair"></i>Patient</a></li>
        <li class="active">Add Patient</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">

      <!-- SELECT2 EXAMPLE -->
      <div class="box box-primary">
        
        <div class="box-body">
          <form role="form" action="add_patient"  method="POST"  enctype="multipart/form-data">
            <div class="form-group">
             	 <label><b>Firstname</b></label>
             	 <input type="text" class="form-control" name="firstName" placeholder="Firstname" required>
             	</div>
             	
             	<div class="form-group">
             	 <label><b>Lastname</b></label>
             	 <input type="text" class="form-control" name="lastName" placeholder="Lastname" required>
             	</div>
             	
             	<div class="form-group">
             	 <label><b>Address</b></label>
             	 <input type="text" class="form-control" name="address" placeholder="Address" required>
             	</div>
             	
             	<div class="form-group">
             	 <label><b>Contact</b></label>
             	 <input type="text" class="form-control" name="contactNumber" placeholder="Contact" required>
             	</div>
             	
             	<div class="form-group">
             	 <label><b>Email</b></label>
             	 <input type="email" class="form-control" name="email" placeholder="Email" required>
             	</div>
             	
             	<div class="form-group">
             	 <label><b>Password</b></label>
             	 <input type="password" class="form-control" name="password" placeholder="Password" required>
             	</div>
             	
             	<div class="form-group">
             	 <label><b>DOB</b></label>
             	 <input type="date" name="dob" class="form-control">
             	</div>
             	
             	
             	<div class="form-group">
             	 <label><b>Image</b></label>
             	 <input type="file" name="image">
             	</div>
             	
             	<div class="form-group">
             	 <label><b>Sex</b></label>
             	 <input type="radio" name="sex" value="Male" placeholder="Male"> Male 
				<input type="radio" name="sex" value="Female" placeholder="Female"> Female
				<input type="radio" name="sex" value="Others" placeholder="Others"> Others
             	</div>
             	
             	<div class="form-group">
             	 <label><b>Blood Group</b></label>
             	 <input type="text" class="form-control" name="bloodGroup" placeholder="Blood Group" required>
             	</div>
             	
             	
             	<div class="form-group">
             	<label><b>Captcha</b></label><br>
				<td>
					<img src="${pageContext.request.contextPath }/captcha">
					<br>
					<input type="text" name="captcha" required="required" style="margin-top: 5px;" required>
					<br>
					${error}
				</td>
				</div>
             	
             	
             
             	
             	<button type="submit" class="btn btn-secondary">Submit</button>
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
