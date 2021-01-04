<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Admin Dashboard</title>
  <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.18/datatables.min.css"/>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <%@include file="aheader.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

 
 

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Add Bill
        
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li><a href="#"><i class="fa fa-envelope"></i> Bill Manager</a></li>
        <li class="active">Add Bill</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
     
        <div class="col-md-12">
          <div class="box box-info">
            
            <div class="box-body pad">
            <form role="form" action="add_bill"  method="POST">
            
             	
             	 <div class="form-group">
             	 <label><b>Patient</b></label>
             	 <select class="form-control" name="prescriptionId" id="select" required>
             	 							<option value="-1">Select Patient</option>
											<c:forEach items="${prescription}" var="a">
												<option value="${a.id}">${a.appointment.patient.firstName} ${a.appointment.patient.lastName} ${a.appointment.schedule.scheduleDate}</option>
											</c:forEach>

										</select>
											
										
             	</div>
             	
             	 
                    <div class="form-group">
             	 	<label><b>Patient Id</b></label>
                    <select class="form-control" name="patientId" id="pId" required>
                    
                    </select>
                    	
                    
                   </div> 
                 
                   
                   
                   
                   <div class="form-group">
             	 	<label><b>Doctor Charge</b></label>
                    <input type="number" class="form-control" id="cPrescription" name="doctorCharge" placeholder="Charge" required>
                    	
                    
                   </div>
                   
                   
                   
                   <div class="form-group">
             	 	<label><b>Medicine Charge</b></label>
                    <input type="number" class="form-control"  name="medicineCharge" placeholder="Charge" required>
                    	
                    
                   </div>
                   <div class="form-group">
	             	 <label><b>Status</b></label>
	             	 <select class="form-control" name="paymentStatus" required>
									<option value="Paid">Paid</option>
									<option value="Unpaid">Unpaid</option>
									
					</select>
                   </div>
                   
                   <button type="submit" class="btn btn-primary">Add Bill</button>
             
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
    	var check = $('#select').val();
    	
    	$('#select').change(function(){
    		var prescriptionId = $('#select').val();
    		
    		
    		
    		$.ajax({
    			type: 'GET',
    			url: '/hms/loadPatientByPrescription/'+prescriptionId,
    			 async: true,
    			success:function(data){
					var s='';
					var a='';
    				var p='';
    				for(var i=0; i < data.length; i++){
    					s += 
     		           data[i].doctorCharge;
     		         
    				}
    				for(var i=0; i < data.length; i++){
    					p += '<option value="' + data[i].patient.id + '">'
     		           + data[i].pat.id 
     		           + '</option>';
    				}
    				for(var i=0; i < data.length; i++){
    					a += data[i].prescription;
    				}
    				$('#pId').html(p);
    				$('#cMed').html(a);
    				$('#cPrescription').val(s).change();
    				console.log(data[0].doctorCharge);
    				console.log(data[0].prescription);
    				
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
