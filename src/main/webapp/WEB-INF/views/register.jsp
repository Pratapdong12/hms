<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title> Patient | Log in</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.7 -->
	<%@include file="header.jsp"%>  
</head>
<body class="hold-transition login-page">
<div class="login-box">
  <div class="login-logo">
    <a href="front"><b>Life</b>Clinics</a>
  </div>
  <!-- /.login-logo -->
  <div class="login-box-body">
  	 ${error}
    <p class="login-box-msg">Register Account</p>

    <form action="add_patient_v" method="POST"  enctype="multipart/form-data">
      <div class="form-group has-feedback" >
             	 <label><b>Firstname</b></label> <input type="text" class="form-control" name="firstName" placeholder="Firstname" required>
             	</div>
             	
             	<div class="form-group">
             	 <label><b>Lastname</b></label>
             	 <input type="text" class="form-control" name="lastName" placeholder="Lastname" required>
             	</div>
             	
             	<div class="form-group">
             	 <label><b>Address</b></label>
             	 <input type="text" class="form-control" name="address" placeholder="Address" required>
             	</div>
             	
             	<div class="form-group">
             	 <label><b>Contact</b></label>
             	 <input type="text" class="form-control" name="contactNumber" placeholder="Contact" required>
             	</div>
             	
             	<div class="form-group">
             	 <label><b>Email</b></label>
             	 <input type="email" class="form-control" name="email" placeholder="Email" required>
             	</div>
             	
             	<div class="form-group">
             	 <label><b>Password</b></label>
             	 <input type="password" class="form-control" name="password" placeholder="Password" required>
             	</div>
             	
             	<div class="form-group">
             	 <label><b>DOB</b></label>
             	 <input type="date" name="dob" class="form-control">
             	</div>
             	
             	
             	<div class="form-group">
             	 <label><b>Image</b></label>
             	 <input type="file" name="image">
             	</div>
             	
             	<div class="form-group">
             	 <label><b>Sex</b></label>
             	 <input type="radio" name="sex" value="Male" placeholder="Male"> Male 
				<input type="radio" name="sex" value="Female" placeholder="Female"> Female
				<input type="radio" name="sex" value="Others" placeholder="Others"> Others
             	</div>
             	
             	<div class="form-group">
             	 <label><b>Blood Group</b></label>
             	 <input type="text" class="form-control" name="bloodGroup" placeholder="Blood Group" required>
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
        <!-- /.col -->
        
          <button type="submit" class="btn btn-primary btn-block btn-flat">Register</button>
        
        <!-- /.col -->
     
    </form>

   

    
    

  </div>
  <!-- /.login-box-body -->
  </div>
<!-- /.login-box -->

<!-- jQuery 3 -->
<script src="resources/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- iCheck -->
<script src="resources/plugins/iCheck/icheck.min.js"></script>
<script>
  $(function () {
    $('input').iCheck({
      checkboxClass: 'icheckbox_square-blue',
      radioClass: 'iradio_square-blue',
      increaseArea: '20%' /* optional */
    });
  });
</script>
</body>
</html>
