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

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Add Schedule
        
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li><a href="#"><i class="fa fa-calendar"></i> Schedule</a></li>
        <li class="active">Add Schedule</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">

      <!-- SELECT2 EXAMPLE -->
      <div class="box box-primary">
        
        <div class="box-body">
          <form role="form" action="add_schedule"  method="POST"  enctype="multipart/form-data">

      <div class="form-group">
             	 <label><b>Doctor</b></label>
             	 <select class="form-control" name="doctorId" required>
											<c:forEach items="${doctor}" var="d">
												<option value="${d.id}">${d.firstName} ${d.lastName}</option>
											</c:forEach>

				</select>
											
										
             	</div>
            <div class="form-group">
             	 <label><b>Date</b></label>
             	 <input type="date" name="scheduleDate" class="form-control" required>
             </div> 	
             
			<div class="bootstrap-timepicker">
                <div class="form-group">
                  <label>Start Time</label>

                  <div class="input-group">
                    <input type="text" name="time1" class="form-control timepicker" required>

                    <div class="input-group-addon">
                      <i class="fa fa-clock-o"></i>
                    </div>
                  </div>
                  <!-- /.input group -->
                </div>
                <!-- /.form group -->
                </div>
                
               <div class="bootstrap-timepicker">
                <div class="form-group">
                  <label>End Time</label>

                  <div class="input-group">
                    <input type="text" name="time2" class="form-control timepicker" required>

                    <div class="input-group-addon">
                      <i class="fa fa-clock-o"></i>
                    </div>
                  </div>
                  <!-- /.input group -->
                </div>
                <!-- /.form group -->
                </div>


   
 
            
       
       			 <div class="form-group">
             	 <label><b>Capacity</b></label>
             	 <input type="number" name="capacity" class="form-control" required>
             	</div> 
             	
             	<div class="form-group">
	             	 <label><b>Status</b></label>
	             	 <select class="form-control" name="status">
									<option value="Active">Active</option>
									<option value="Inactive">Inactive</option>
									
					</select>
             	</div>
             	
             	
             	
             	<button type="submit" class="btn btn-secondary ">Submit</button>
             	</form>
             	</div>
             	</div>

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

<!-- ./wrapper -->

<!-- jQuery 3 -->
<script src="resources/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- Select2 -->
<script src="resources/bower_components/select2/dist/js/select2.full.min.js"></script>
<!-- InputMask -->
<script src="resources/plugins/input-mask/jquery.inputmask.js"></script>
<script src="resources/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script src="resources/plugins/input-mask/jquery.inputmask.extensions.js"></script>
<!-- date-range-picker -->
<script src="resources/bower_components/moment/min/moment.min.js"></script>
<script src="resources/bower_components/bootstrap-daterangepicker/daterangepicker.js"></script>
<!-- bootstrap datepicker -->
<script src="resources/bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
<!-- bootstrap color picker -->
<script src="resources/bower_components/bootstrap-colorpicker/dist/js/bootstrap-colorpicker.min.js"></script>
<!-- bootstrap time picker -->
<script src="resources/plugins/timepicker/bootstrap-timepicker.min.js"></script>
<!-- SlimScroll -->
<script src="resources/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- iCheck 1.0.1 -->
<script src="resources/plugins/iCheck/icheck.min.js"></script>
<!-- FastClick -->
<script src="resources/bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="resources/dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="resources/dist/js/demo.js"></script>
<!-- Page script -->
<script>
  $(function () {
    

    //Timepicker
    $('.timepicker').timepicker({
      showInputs: false
    })
  })
</script>
</body>
</html>
