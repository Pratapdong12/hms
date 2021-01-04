<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Patient Dashboard</title>
  <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.18/datatables.min.css"/>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <%@include file="pheader.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

 
 

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Add Appointment
        
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li><a href="#"><i class="fa fa-envelope"></i> Appointment Manager</a></li>
        <li class="active">Add Appointment</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
     
        <div class="col-md-12">
          <div class="box box-info">
            
            <div class="box-body pad">
            <form role="form" action="add_appointment"  method="POST">
            
             	
             	 <div class="form-group">
             	 <label><b>Department</b></label>
             	 <select class="form-control" name="departmentId" id="selectDepartment" required>
             	 							<option value="-1">Select Department</option>
											<c:forEach items="${department}" var="d">
												<option value="${d.id}">${d.department}</option>
											</c:forEach>

										</select>
											
										
             	</div>
             	
             	 <div class="form-group">
             	 <label><b>Doctor</b></label>
                    <select class="form-control" id="cDoctor" name="doctorId" required>
                    	<option value="-1">Select Doctor</option>
                                            
                    </select>
                    
                   </div>
                   
                   <div class="form-group">
             	 	<label><b>Date</b></label>
                    <select class="form-control" id="cDate" name="scheduleId" required>
                    	<option value="-1">Select Date</option>
                                            
                    </select>
                    
                   </div>
                   
                   <div class="form-group">
             	 	<label><b>Schedule</b></label>
                    <select class="form-control" id="cSchedule" name="scheduleId" required>
                    	<option value="-1">Select Schedule</option>
                                            
                    </select>
                    
                   </div>
                   
                  
	             	
	             	<div class="form-group">
		             	 <label><b>Problem</b></label>
		             	<textarea name="problem" class="form-control" placeholder="Descrition about problem ..."></textarea>
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
                   <button type="submit" class="btn btn-secondary">Send</button>
             
              </form>
            </div>
          </div>
          <!-- /.box -->

          
      </div>
      <!-- ./row -->
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

  <!-- Control Sidebar -->
 
  <!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
  <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

<!-- jQuery 3 -->
<script src="resources/bower_components/jquery/dist/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.18/datatables.min.js"></script>


<!-- Bootstrap 3.3.7 -->
<script src="resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- FastClick -->
<script src="resources/bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="resources/dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="resources/dist/js/demo.js"></script>
<!-- CK Editor -->
<script src="resources/bower_components/ckeditor/ckeditor.js"></script>
<!-- Bootstrap WYSIHTML5 -->
<script src="resources/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>


<script type="text/javascript">
    $(document).ready(function(){
    	var check = $('#selectDepartment').val();
    	
    	$('#selectDepartment').change(function(){
    		var departmentId = $('#selectDepartment').val();
    		$.ajax({
    			type: 'GET',
    			url: '/hms/loadDoctor/'+departmentId,
    			success:function(data){
    				var s='<option value="">Select Doctor</option>';
    				
    				for(var i=0; i < data.length; i++){
    					s += '<option value="' + data[i].id + '">'
     		           + data[i].firstName +" "+ data[i].lastName
     		           + '</option>';
    				}
    				s += '</option>';
    				$('#cDoctor').html(s);
    				console.log(data[0].address);
    				
    			},
    			 error:function(){
    		            alert("error");
    		        }
    		
    			
    		 
    		});
 		   		
 
    });
    	$('#cDoctor').change(function(){
    		var doctorId = $('#cDoctor').val();
    		var c;
    		$.ajax({
    			type: 'GET',
    			url: '/hms/loadSchedule/'+doctorId,
    			success:function(data){
					var s='<option value="">Select Schedule</option>';
					var d='<option value="">Select Date</option>';
    				for(var i=0; i < data.length; i++){
    					s += '<option value="' + data[i].id + '">'
     		           + data[i].time1 +"-"+ data[i].time2
     		           + '</option>';
    					
    				}
    				if(c===0){
    					var n='<option value="">Not available</option>';
    					$('#cSchedule').html(n);
    					$('#cDate').html(n);
    					alert(c)
    				}
    				else{
    				s += '</option>';
    				$('#cSchedule').html(s);
    				console.log(data[0].time1);
    				}
    				for(var i=0; i < data.length; i++){
    					d += '<option value="' + data[i].id + '">'
     		           + data[i].scheduleDate
     		           + '</option>';
    					c=data[i].Capacity;
    				}
    				
    				if(c===0){
    					var n='<option value="">Not available</option>';
    					$('#cSchedule').html(n);
    					$('#cDate').html(n);
    					alert("capacity is full ")
    				}	
    					
    				
    				else{
    				d += '</option>';
    				$('#cDate').html(d);
    				console.log(data[0].scheduleDate);
    				
    				}
    				
    			},
    			 error:function(){
    		            alert("error");
    		        }
    		
    			
    		 
    		});
 		   		
 
    });
    });
   </script>
</body>
</html>
