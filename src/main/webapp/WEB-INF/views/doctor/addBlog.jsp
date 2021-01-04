<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Doctor Dashboard</title>
	<%@include file="dheader.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

	


  <!-- Left side column. contains the logo and sidebar -->
 
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
  
  	<section class="content-header">
      <h1>
        Add Blog
        
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li><a href="#"><i class="fa fa-building"></i> Blog</a></li>
        <li class="active">Add Blog</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">

      <!-- SELECT2 EXAMPLE -->
      <div class="box box-primary">
        
        <!-- /.box-header -->
        <div class="box-body">
          <form role="form" action="add_blog_d"  method="POST" enctype="multipart/form-data">
            <div class="form-group">
             	 <label><b>Title</b></label>
             	 <input type="text" class="form-control" name="title" placeholder="Title" required>
             </div>
             
             <div class="form-group">
             	 <label><b>Sub Title</b></label>
             	 <input type="text" class="form-control" name="subTitle" placeholder="sub title" required>
             </div>
             
             	
             <div class="form-group">
             	 <label><b>Blog</b></label>
             	<textarea name="blog" class="form-control" placeholder="Description about Blog ..." required></textarea>
             </div>

			<div class="form-group">
             	 <label><b>Image</b></label>
             	 <input type="file" name="image">
             	</div>
             	
             	
             	
             	<button type="submit" class="btn btn-secondary">Add Blog</button>
            
          </form>
        </div>
        <!-- /.box-body -->
       
      </div>
      <!-- /.box -->

 
 

    </section>
    <!-- /section -->
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
<!-- FastClick -->
<script src="resources/bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="resources/dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="resources/dist/js/demo.js"></script>
</body>
</html>
