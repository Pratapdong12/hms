<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Patient Dashboard </title>
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
        Doctors Lists
        
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li><a href="#"><i class="fa fa-user-md"></i>Doctors</a></li>
        <li class="active">Doctors List</li>
      </ol>
    </section>
    
    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          

          <div class="box">
            
            <!-- /.box-header -->
            <div class="box-body">
              <c:if test="${!empty doctor }">
						<table id="example1" class="table table-bordered table-striped">
							<thead>
								<tr>
									<th>SN</th>
									<th>ID</th>
									<th>Image</th>
									<th>First Name</th>
									<th>Last Name</th>
									<th>email</th>
									<th>sex</th>
									<th>Department</th>
									<th>Status</th>
									<th>Actions</th>

								</tr>
							</thead>

							<tbody>
								<c:forEach items="${doctor}" var="v" varStatus="c">
									<tr>
										<td>${c.count }</td>
										<td>${v.id}</td>
										<td><img alt="pic" src="ImageDisplay?doctorId=${v.id}"
											width="50px" height="50px"></td>
										<td>Dr.${v.firstName}</td>
										<td>${v.lastName}</td>
										<td>${v.email}</td>
										<td>${v.sex}</td>
										<td>${v.department.department}</td>
										<td>${v.status}</td>

										<td><a class="btn btn-success " title="view"
											href="view_doctor_p?id=${v.id}" role="button" data-toggle="modal"><i class="fa fa-eye"></i></a>
											</td>
										
										
									</tr>
								</c:forEach>
							</tbody>
                
              </table>
              </c:if>
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
