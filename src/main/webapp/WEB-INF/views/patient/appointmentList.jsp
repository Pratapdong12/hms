<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Patient Dashboard</title>
	<%@include file="pheader.jsp"%>
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
										

										<td><%-- <a class="btn btn-primary" title="Cancel Booking"
											href="#myModalCancel${a.id}" role="button" data-toggle="modal">Cancel</a> --%>
										
											<a class="btn btn-danger" title="delete"
											href="#myModal${a.id}" role="button" data-toggle="modal"><i
												class="fa fa-trash"></i></a></td>
											<div id="myModalCancel${a.id}" class="modal fade">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal"
															aria-hidden="true">&times;</button>
														<h4 class="modal-title">Cancel Confirmation</h4>
													</div>

													<div class="modal-body">
														<div class="box-body pad">
            <form role="form" action="cancel_appointment"  method="POST">
            
             	<div class="form-group">
             	 <label><b>Id</b></label>
             	 <input type="number" class="form-control" value="${a.id}" readonly="readonly">
             	</div>
             	<div class="form-group">
             	 	<label><b>Department</b></label>
                     <select class="form-control" name="departmentId" >
											
												<option value=${a.department.id}>
												${a.department.department}</option>
											

										</select>
                    
                   </div>
             	 <div class="form-group">
             	 	<label><b>Date</b></label>
                     <select class="form-control" name="scheduleId">
											
												<option value=${a.schedule.id}>
												${a.schedule.scheduleDate}</option>
											

										</select>
                    
                   </div>
                   
                   
                   
                                    
                  
	             	
	             	<div class="form-group">
		             	 <label><b>Problem</b></label>
		             	<textarea name="problem" class="form-control" placeholder="Descrition about problem ..." readonly="readonly">${a.problem}</textarea>
		             </div>
                   
                   
                   <button type="submit" class="btn btn-primary">Cancel </button>
             
              </form>
          
													</div>
													</div>
													<div class="modal-footer">

														<button type="button" class="btn btn-default"
															data-dismiss="modal">Close</button>
														
													</div>
												</div>
											</div>
										</div>
										
										<div id="myModal${a.id}" class="modal fade">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal"
															aria-hidden="true">&times;</button>
														<h4 class="modal-title">Delete Confirmation</h4>
													</div>

													<div class="modal-body">
														
													<div class="modal-footer">

														<button type="button" class="btn btn-default"
															data-dismiss="modal">Close</button>
														<a href="appointment_delete_p?id=${a.id}" title="Delete"><i
															class="fa fa-trash-o"></i>Delete</a>
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
