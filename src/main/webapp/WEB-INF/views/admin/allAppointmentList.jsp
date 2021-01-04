<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Doctor Dashboard</title>
	<%@include file="aheader.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">


  <!-- Left side column. contains the logo and sidebar -->
 
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
   
	<!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Appointment List
        
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li><a href="#"><i class="fa fa-bell"></i> Appointment Manager</a></li>
        <li class="active">Appointment Lists</li>
      </ol>
    </section>
    
    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          

          <div class="box">
            
            <!-- /.box-header -->
            <div class="box-body">
              <table id="example1" class="table table-bordered table-striped">
               <thead>
								<tr>
									<th>SN</th>
									<th>ID</th>
									<th>Patient Name</th>
									<th>Date</th>
									<th>Problem</th>
									<th>Status</th>									
									<th>Actions</th>

								</tr>
							</thead>
                 <tbody>
						<c:forEach items="${appointment}" var="a" varStatus="c">
									<tr>
										<td>${c.count }</td>
										<td>${a.id}</td>
										<td>${a.patient.firstName} ${a.patient.lastName}</td>
										<td>${a.schedule.scheduleDate}</td>
										<td>${a.problem}</td>
										<td>${a.status}</td>
										
										

										<td>
											<a class="btn btn-success" title="View Detail"
											href="#myModalView${a.id}" role="button" data-toggle="modal">View Detail</a>
										
											</td>
											<div id="myModalView${a.id}" class="modal fade">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
														
														<h4 class="modal-title centre"><img alt="pic" src="ImageDisplayPatient?patientId=${a.patient.id}"
											width="70px" height="80px"></h4>
													</div>

													<div class="modal-body">
														<b>Id: </b>${a.patient.id}<br>
														<b>First Name: </b>${a.patient.firstName}<br>
														<b>Last Name: </b>${a.patient.lastName}<br>
														<b>Address: </b>${a.patient.address}<br>
														<b>Contact Number: </b>${a.patient.contactNumber}<br>
														<b>Sex: </b>${a.patient.sex}<br>
														<b>Blood Group: </b>${a.patient.bloodGroup}<br>
														<b>Appointment Date: </b>${a.schedule.scheduleDate}<br>
														<b>Time: </b>${a.schedule.time1}-${a.schedule.time1}<br>
														<b>Problem: </b>${a.problem}<br>
														<b>Appointment for:</b>${a.doctor.firstName} ${a.doctor.lastName}<br>
													</div>
													<div class="modal-footer">

														<button type="button" class="btn btn-default"
															data-dismiss="modal">Close</button>
														
													</div>
												</div>
											</div>
										</div>
											
										
										
										
									</tr>
								</c:forEach>
							</tbody>
                
              </table>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->
    </section>
    <!-- /.content -->
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
<script>
  $(function () {
    $('#example1').DataTable()
    $('#example2').DataTable({
      'paging'      : true,
      'lengthChange': false,
      'searching'   : false,
      'ordering'    : true,
      'info'        : true,
      'autoWidth'   : false
    })
  })
</script>
</body>
</html>
