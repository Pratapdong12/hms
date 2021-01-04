  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.7 -->
  <link rel="stylesheet" href="resources/bower_components/bootstrap/dist/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="resources/bower_components/font-awesome/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="resources/bower_components/Ionicons/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="resources/dist/css/AdminLTE.min.css">
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="resources/dist/css/skins/_all-skins.min.css">
  <!-- Morris chart -->
  <link rel="stylesheet" href="resources/bower_components/morris.js/morris.css">
  <!-- jvectormap -->
  <link rel="stylesheet" href="resources/bower_components/jvectormap/jquery-jvectormap.css">
  <!-- Date Picker -->
  <link rel="stylesheet" href="resources/bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css">
  <!-- Daterange picker -->
  <link rel="stylesheet" href="resources/bower_components/bootstrap-daterangepicker/daterangepicker.css">
  <!-- bootstrap wysihtml5 - text editor -->
  <link rel="stylesheet" href="resources/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
   <!-- Bootstrap time Picker -->
  <link rel="stylesheet" href="resources/plugins/timepicker/bootstrap-timepicker.min.css">
  <!-- Select2 -->
  <link rel="stylesheet" href="resources/bower_components/select2/dist/css/select2.min.css">
  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
  
  <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.18/datatables.min.css"/>
<link rel="icon" type="image/png" sizes="16x16" href="resources/front/favicons/favicon-16x16.png">
  <!-- Google Font -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

  <header class="main-header">
    <!-- Logo -->
    <a href="admin" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>L</b>C</span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>LIFE</b>Clinics</span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>

      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          
          
          
          <!-- User Account: style can be found in dropdown.less -->
          <li class="dropdown user user-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <img src="ImageDisplayAdmin?adminId=${Admin.id}"  class="user-image" alt="User Image">
              <span class="hidden-xs">${Admin.firstName} ${Admin.lastName} </span>
            </a>
            <ul class="dropdown-menu">
              <!-- User image -->
              <li class="user-header">
                
				<img src="ImageDisplayAdmin?adminId=${Admin.id}" class="img-circle" alt="User Image">
                <p>
                  ${Admin.firstName} ${Admin.lastName}
                  <small>Administrator</small>
                </p>
              </li>
             
              <!-- Menu Footer-->
              <li class="user-footer">
                <div class="pull-left">
                  <a href="info" class="btn btn-default btn-flat">Profile</a>
                </div>
                <div class="pull-right">
                  <a href="logout" class="btn btn-default btn-flat">Sign out</a>
                </div>
              </li>
            </ul>
          </li>
         
        </ul>
      </div>
    </nav>
  </header>
  <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel -->
      <div class="user-panel">
        <div class="pull-left image">
        
          <img src="ImageDisplayAdmin?adminId=${Admin.id}" class="img-circle" alt="User Image"  width="150px" height="150px">
         
        </div>
        <div class="pull-left info">
          <p>${Admin.firstName} ${Admin.lastName}</p>
          <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
        </div>
      </div>
      
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu" data-widget="tree">
        <li class="header">MAIN NAVIGATION</li>
        <li class="treeview">
          <a href="adminDash">
            <i class="fa fa-dashboard"></i> <span>Dashboard</span>
         
          </a>
          
        </li>
        
        
        <li class="treeview">
          <a href="#">
            <i class="fa fa-building"></i>
            <span>Department</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="department_form"><i class="fa fa-circle-o"></i> Add Department</a></li>
            <li><a href="department_list"><i class="fa fa-circle-o"></i> Department List</a></li>
            
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-user-md"></i>
            <span>Doctors</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="doctor_form"><i class="fa fa-circle-o"></i> Add Doctors</a></li>
            <li><a href="doctor_list"><i class="fa fa-circle-o"></i> Doctors List</a></li>
            
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-calendar"></i>
            <span>Schedule</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="schedule_form"><i class="fa fa-circle-o"></i> Add Schedule</a></li>
            <li><a href="schedule_list"><i class="fa fa-circle-o"></i> Schedule List</a></li>
            
          </ul>
        </li>
        
        <li class="treeview">
          <a href="#">
            <i class="fa fa-wheelchair"></i>
            <span>Patient</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="patient_form"><i class="fa fa-circle-o"></i> Add Patient</a></li>
            <li><a href="patient_list"><i class="fa fa-circle-o"></i> Patient List</a></li>
            <li><a href="patient_list_pending"><i class="fa fa-circle-o"></i> Pending Patient List</a></li>
            
          </ul>
        </li>
        
        
        
        
         <li class="treeview">
          <a href="#">
            <i class="fa fa-bell"></i>
            <span>Notice Manager</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li ${action eq 'bed_form' ? 'class="active"' : ''}><a href="notice_form"><i class="fa fa-circle-o"></i> Add Notice</a></li>
            <li><a href="notice_list"><i class="fa fa-circle-o"></i> Notice List</a></li>            
          </ul>
        </li>
        
        <li class="treeview">
          <a href="#">
            <i class="fa fa-envelope"></i>
            <span>Message Manager</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            
            <li><a href="sent_message_admin"><i class="fa fa-circle-o"></i> Sent Message</a></li>
            <li><a href="inbox_message_admin"><i class="fa fa-circle-o"></i>Inbox</a></li>            
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-calendar-check-o"></i>
            <span>Appointment Manager</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
           
            <li><a href="appointment_list_a"><i class="fa fa-circle-o"></i>Assign Appointment</a></li>
            <li><a href="allappointment_list_a"><i class="fa fa-circle-o"></i> Appointment List</a></li>
                       
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-file-text-o"></i>
            <span>Bill Manager</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="bill_form"><i class="fa fa-circle-o"></i> Add Bill</a></li>
            <li><a href="bill_list_a"><i class="fa fa-circle-o"></i> Bill List</a></li>
                       
          </ul>
        </li>
        
        
       
        <li class="header">For Website</li>
        
         <li class="treeview">
          <a href="#">
            <i class="fa  fa-sticky-note-o"></i>
            <span>Enquiry Manager</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
           
            <li><a href="enquiry_list"><i class="fa fa-circle-o"></i>Enquiry Lists</a></li>
                        
          </ul>
        </li>
        
        <li class="treeview">
          <a href="#">
            <i class="fa fa-feed "></i>
            <span>Blog Manager</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
           
            <li><a href="blog_form"><i class="fa fa-circle-o"></i>Add Blog</a></li>
            <li><a href="blog_list_a"><i class="fa fa-circle-o"></i>Blog List</a></li>
                        
          </ul>
        </li>
       
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>