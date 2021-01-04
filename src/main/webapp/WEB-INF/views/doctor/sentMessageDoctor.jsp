<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Doctor Dashboard </title>
	<%@include file="dheader.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">


  <!-- Left side column. contains the logo and sidebar -->
 
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
   
	<!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Sent Messages
        
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li><a href="#"><i class="fa fa-bell"></i> Message Manager</a></li>
        <li class="active">Sent Messages</li>
      </ol>
    </section>
    
    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          

          <div class="box">
            <div class="box-header">
              <h3 class="box-title"><a href="message_for_doctor_d" class="btn btn-primary btn-sm"
					title="Add Message"><i class="fa fa-plus-circle"></i>Doctor
					</a></h3>
					
			
			<h3 class="box-title"><a href="message_for_patient_d" class="btn btn-primary btn-sm"
					title="Add Message"><i class="fa fa-plus-circle"></i>Patient
					</a></h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table id="example1" class="table table-bordered table-striped">
               <thead>
								<tr>
									<th>SN</th>
									<th>ID</th>
									<th>Sender</th>
									<th>Message</th>
									<th>Receiver</th>
									<th>Status</th>
									<th>Actions</th>

								</tr>
							</thead>
                <tbody>
								<c:forEach items="${message}" var="m" varStatus="c">
									<tr>
										<td>${c.count }</td>
										<td>${m.id}</td>
										<td>${m.sender}</td>
										<td>${m.message}</td>
										<td>${m.receiver}</td>
										<td>${m.status}</td>

										<td><a class="btn btn-secondary " title="view"
											href="#viewModal${m.id}" role="button" data-toggle="modal"><i class="fa fa-eye"></i></a>
										
											<a class="btn btn-danger" title="delete"
											href="#myModal${m.id}" role="button" data-toggle="modal"><i
												class="fa fa-trash"></i></a></td>
										<div id="viewModal${m.id}" class="modal fade">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
														
														<h4 class="modal-title centre">Message</h4>
													</div>

													<div class="modal-body">
														
														<b>Sender: </b>${m.sender}<br>
														<b>Message: </b>${m.message}<br>
														
														
														
													</div>
													<div class="modal-footer">

														<button type="button" class="btn btn-default"
															data-dismiss="modal">Close</button>
														
													</div>
												</div>
											</div>
										</div>
										<div id="myModal${m.id}" class="modal fade">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal"
															aria-hidden="true">&times;</button>
														<h4 class="modal-title">Delete Confirmation</h4>
													</div>

													<div class="modal-body">
														<p>Are you sure you want to delete this message?</p>
													</div>
													<div class="modal-footer">

														<button type="button" class="btn btn-default"
															data-dismiss="modal">Close</button>
														<a href="message_delete_d?id=${m.id}" title="Delete"><i
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
