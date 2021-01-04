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
   
	<!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Enquiry Lists
        
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li><a href="#"><i class="fa fa-building"></i> Enquiry</a></li>
        <li class="active">Enquiry List</li>
      </ol>
    </section>
    
    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          

          <div class="box">
            <div class="box-header">
              <h3 class="box-title"><a>Enquiry List</a></h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table id="example1" class="table table-bordered table-striped">
               <thead>
								<tr>
									<th>SN</th>
									<th>ID</th>
									<th>Email</th>
									<th>Enquiry</th>
									<th>Enquiry Date</th>
									<th>Status</th>
									<th>Actions</th>

								</tr>
							</thead>
                <tbody>
								<c:forEach items="${enquiry}" var="e" varStatus="c">
									<tr>
										<td>${c.count }</td>
										<td>${e.id}</td>
										<td>${e.email}</td>
										<td>${e.enquiry}</td>
										<td>${e.enquiryDate}</td>
										<td>${e.status}</td>

										<td><a class="btn btn-secondary " title="view"
											href="#viewModal${e.id}" role="button" data-toggle="modal"><i class="fa fa-eye"></i></a>
										
											<a class="btn btn-danger" title="delete"
											href="#myModal${e.id}" role="button" data-toggle="modal"><i
												class="fa fa-trash"></i></a></td>
										<div id="viewModal${e.id}" class="modal fade">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
														
														<h4 class="modal-title centre">Enquiry</h4>
													</div>

													<div class="modal-body">
														<b>Id: </b>${e.id}<br>
														<b>Email: </b>${e.email}<br>
														<b>Enquiry: </b>${e.enquiry}<br>
														<b>Enquiry date: </b>${e.enquiryDate}<br>
														
														
													</div>
													<div class="modal-footer">

														<a href="enquiry_update?id=${e.id}" >Close</a>
														
													</div>
												</div>
											</div>
										</div>
										<div id="myModal${e.id}" class="modal fade">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal"
															aria-hidden="true">&times;</button>
														<h4 class="modal-title">Delete Confirmation</h4>
													</div>

													<div class="modal-body">
														<p>Are you sure you want to delete this enquiry?</p>
													</div>
													<div class="modal-footer">

														<button type="button" class="btn btn-default"
															data-dismiss="modal">Close</button>
														<a href="enquiry_delete?id=${e.id}" title="Delete"><i
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
