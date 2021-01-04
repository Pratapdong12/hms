<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Admin Dashboard</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

	<%@include file="aheader.jsp"%>
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Dashboard
        <small>Admin Profile</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Profile</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
     <div class="row">
        <div class="col-xs-12">
        
        <div class="box-header">
              <h3 class="box-title"><a href="edit_admin" class="btn btn-primary btn-sm"
					title="Edit Admin"><i class="fa fa-pencil"></i> Edit
					Admin</a></h3>
					
				<h3 class="box-title"><a href="#viewModal" data-toggle="modal" class="btn btn-primary btn-sm"
					title="Upload Photo"><i class="fa fa-pencil"></i> Upload
					Photo</a></h3>
					
					<div id="viewModal${p.id}" class="modal fade">
											<div class="modal-dialog">
												<div class="modal-content">
												 <form role="form" action="upload_aphoto"  method="POST"  enctype="multipart/form-data">
												 	<div class="form-group">
										             	 <label><b>Image</b></label>
										             	 <input type="file" name="image">
										             	</div>
										             	<button type="submit" class="btn btn-secondary">Upload</button>
												 
												 </form>

													
											</div>
										</div>
				</div>
            </div>
            
            <div class="box-body">
    
    	<form role="form" >
		
			<div class="form-group">

				<h4 class="form-title centre">
					<img alt="pic" src="ImageDisplayAdmin?adminId=${Admin.id}" width="70px"
						height="80px">
				</h4>
				
				
            <div class="form-group">
             	<b>First Name: </b>${Admin.firstName}<br>
             	</div>
             	
             	<div class="form-group">
             	<b>Last Name: </b>${Admin.lastName}<br> 
             	</div>
             	
             	<div class="form-group">
             	 <b>Address: </b>${Admin.address}<br>
             	</div>
             	
             	<div class="form-group">
             	 <b>Email: </b>${Admin.email}<br> 
				
             	</div>
             	
             	<div class="form-group">
             	 <b>Contact Number: </b>${Admin.contactNumber}<br>
				
             	</div>
             	
             	<div class="form-group">
             	 <b>Sex: </b>${Admin.sex}<br> 
				
             	</div>
             	
             	<div class="form-group">
             	 <b>DOB: </b>${Admin.dob}<br>
				
             	</div>
             	
             	
             	<div class="form-group">
             	<b>Blood Group: </b>${Admin.bloodGroup}<br>
             	</div>
             	
             	
             </form>
             </div>
             </div>
             </div>
    </section>
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
<!-- DataTables -->
<script src="resources/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="resources/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<!-- SlimScroll -->
<script src="resources/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="resources/bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="resources/dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="resources/dist/js/demo.js"></script>
<!-- page script -->
</body>
</html>
