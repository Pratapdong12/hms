<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Doctor Dashboard</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <%@include file="dheader.jsp"%>
  <script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

 
 

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Add Message
        
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li><a href="#"><i class="fa fa-envelope"></i> Message Manager</a></li>
        <li class="active">Add Message</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
     
        <div class="col-md-12">
          <div class="box box-info">
            
            <div class="box-body pad">
            <form role="form" action="add_message_doctor"  method="POST">
            
             	
             	 <div class="form-group">
	             	 <label><b>Send To</b></label>
	             	  <select class="form-control" name="receiver" required>
											<c:forEach items="${doc}" var="d">
												<option value="${d.firstName} ${d.lastName}">Dr.${d.firstName} ${d.lastName}</option>
											</c:forEach>

				</select>
             	</div>
             	
             	<div class="form-group">
              		<label><b>Message</b></label>
                    <textarea id="editor1" name="message" rows="10" cols="80" placeholder="Message ..." required>
                                            
                    </textarea>
                    
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
<script src="resources/bower_components/jquery/dist/jquery.min.js"></script>
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
<script>
  $(function () {
    // Replace the <textarea id="editor1"> with a CKEditor
    // instance, using default configuration.
    CKEDITOR.replace('editor1')
    //bootstrap WYSIHTML5 - text editor
    $('.textarea').wysihtml5()
  })
</script>

<script type="text/javascript">
    function getUser(user)
    {
   
    var user=$('#user').val();
    alert(user);
       $.ajax({ 
           type: "POST",     
           url: "/find",
           data: "user="+user,
           success: function(response){
          // we have the response
          if(response.status == "SUCCESS"){
              options = '';
              options += '<option> Please select ... </option>';
              for(i =0 ; i < response.length ; i++){
                  options += '<option value="' + responce[i].id + '">' + response[i].firstName response[i].lastName + '</option>';
                 
              }
              selectOption=options;
             $('#user').html(options);
             
          }else{
              errorInfo = "";
              for(i =0 ; i < response.result.length ; i++){
                  errorInfo += "<br>" + (i + 1) +". " + response.result[i];
              }
              $('#error').html("Please correct following errors: " + errorInfo);
              $('#info').hide('slow');
              $('#error').show('slow');
          }         
        }, 
        error: function(e){ 
          alert('Error: ' + e); 
          
        
        } 
       }); 
     }
   </script>
</body>
</html>
