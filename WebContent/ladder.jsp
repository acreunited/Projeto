<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="DragonBall-Arena">
    <meta name="author" content="Pedro Dias">

    <title>Anime-Arena</title>

    <!-- Custom fonts for this template-->
    <link href="extras/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/main.css" rel="stylesheet">
    <link href="css/responsive.css" rel="stylesheet">


</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.jsp">
                <!--<div class="sidebar-brand-icon rotate-n-15">
                    <i class="fas fa-laugh-wink"></i>
                </div>-->
                <div class="sidebar-brand-text mx-3">Welcome!</div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            <!-- Nav Item - Dashboard -->
            <li class="nav-item active">
                <a class="nav-link" href="#">
                    <i class="fab fa-discord"></i>
                    <span>Discord</span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <div class="sidebar-heading">
                Interface
            </div>

      

            <!-- Nav Item - Utilities Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseMode"
                    aria-expanded="true" aria-controls="collapseMode">
                    <i class="fas fa-fw fa-user"></i>
                    <span>Language</span>
                </a>
                <div id="collapseMode" class="collapse" aria-labelledby="headingMode"
                    data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <a class="collapse-item" href="#">English</a>
                        <a class="collapse-item" href="#">Portuguese</a>
                    </div>
                </div>
            </li>

            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
                    aria-expanded="true" aria-controls="collapseTwo">
                    <i class="fas fa-fw fa-cog"></i>
                    <span>Themes</span>
                </a>
                <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <a class="collapse-item" href="#">Default</a>
                        <a class="collapse-item" href="#">Anime</a>
                    </div>
                </div>
            </li>

            

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <div class="sidebar-heading">
                Game
            </div>

            <li class="nav-item active">
                <a class="nav-link" href="#">
                    <i class="fas fa-fw fa-circle"></i>
                    <span>Start Playing</span></a>
            </li>

            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages"
                    aria-expanded="true" aria-controls="collapsePages">
                    <i class="fas fa-fw fa-folder"></i>
                    <span>Pages</span>
                </a>
                <div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <a class="collapse-item" href="index.jsp">Annoucements</a>
                        <a class="collapse-item" href="ladder.jsp">Rankings</a>
                        <a class="collapse-item" href="missions.jsp">Missions</a>
                        <a class="collapse-item" href="help.jsp">Help</a>

                        <div class="collapse-divider"></div>
                    </div>
                </div>
            </li>

            <!-- Nav Item - Charts -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseAccount"
                    aria-expanded="true" aria-controls="collapseAccount">
                    <i class="fas fa-fw fa-ellipsis-v"></i>
                    <span>Account</span>
                </a>
                <div id="collapseAccount" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <a class="collapse-item" href="login.jsp">Login</a>
                        <a class="collapse-item" href="register.jsp">Register</a>

                        <div class="collapse-divider"></div>
                    </div>
                </div>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider d-none d-md-block">

            <!-- Sidebar Toggler (Sidebar) -->
            <div class="text-center d-none d-md-inline">
                <button class="rounded-circle border-0" id="sidebarToggle"></button>
            </div>

            

        </ul>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                 

        
                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">Ranking</h1>
                    <p class="mb-4">Maybe some description here, not sure if its necessary. But basically its to show the rank of every player.
                        Will do for clans too for sure later on, but until July I doubt I can implement it.<br>
                        I copy pasted 30 examples so you guys can see the search thingy working :Praydge:
                    </p>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <!--<div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">DataTables Example</h6>
                        </div>-->
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th>Rank</th>
                                            <th>Username</th>
                                            <th>Level</th>
                                            <th>Experience</th>
                                            <th>Wins</th>
                                            <th>Losses</th>
                                            <th>Streak</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <th>Rank</th>
                                            <th>Username</th>
                                            <th>Level</th>
                                            <th>Experience</th>
                                            <th>Wins</th>
                                            <th>Losses</th>
                                            <th>Streak</th>
                                        </tr>
                                    </tfoot>
                                    <tbody>
                                        <tr>
                                            <td>#01</td>
                                            <td>Betrayer</td>
                                            <td>66</td>
                                            <td>54561561</td>
                                            <td>56465</td>
                                            <td>4125</td>
                                            <td>+16</td>
                                        </tr>
                                        <tr>
                                            <td>#02</td>
                                            <td>Clover</td>
                                            <td>66</td>
                                            <td>54561561</td>
                                            <td>56465</td>
                                            <td>4125</td>
                                            <td>+16</td>
                                        </tr>
                                        <tr>
                                            <td>#03</td>
                                            <td>Aby</td>
                                            <td>66</td>
                                            <td>54561561</td>
                                            <td>56465</td>
                                            <td>4125</td>
                                            <td>+16</td>
                                        </tr>
                                        <tr>
                                            <td>#04</td>
                                            <td>Sad</td>
                                            <td>66</td>
                                            <td>54561561</td>
                                            <td>56465</td>
                                            <td>4125</td>
                                            <td>+16</td>
                                        </tr>
                                        <tr>
                                            <td>#05</td>
                                            <td>Axe</td>
                                            <td>66</td>
                                            <td>54561561</td>
                                            <td>56465</td>
                                            <td>4125</td>
                                            <td>+16</td>
                                        </tr>
                                        <tr>
                                            <td>#06</td>
                                            <td>Shuna</td>
                                            <td>66</td>
                                            <td>54561561</td>
                                            <td>56465</td>
                                            <td>4125</td>
                                            <td>+16</td>
                                        </tr>
                                        <tr>
                                            <td>#07</td>
                                            <td>Betrayer</td>
                                            <td>66</td>
                                            <td>54561561</td>
                                            <td>56465</td>
                                            <td>4125</td>
                                            <td>+16</td>
                                        </tr>
                                        <tr>
                                            <td>#08</td>
                                            <td>Shuna</td>
                                            <td>66</td>
                                            <td>54561561</td>
                                            <td>56465</td>
                                            <td>4125</td>
                                            <td>+16</td>
                                        </tr>
                                        <tr>
                                            <td>#09</td>
                                            <td>Sad</td>
                                            <td>66</td>
                                            <td>54561561</td>
                                            <td>56465</td>
                                            <td>4125</td>
                                            <td>+16</td>
                                        </tr>
                                        <tr>
                                            <td>#10</td>
                                            <td>Shuna</td>
                                            <td>66</td>
                                            <td>54561561</td>
                                            <td>56465</td>
                                            <td>4125</td>
                                            <td>+16</td>
                                        </tr>
                                        <tr>
                                            <td>#11</td>
                                            <td>Shuna</td>
                                            <td>66</td>
                                            <td>54561561</td>
                                            <td>56465</td>
                                            <td>4125</td>
                                            <td>+16</td>
                                        </tr>
                                        <tr>
                                            <td>#12</td>
                                            <td>Betrayer</td>
                                            <td>66</td>
                                            <td>54561561</td>
                                            <td>56465</td>
                                            <td>4125</td>
                                            <td>+16</td>
                                        </tr>
                                        <tr>
                                            <td>#13</td>
                                            <td>Clover</td>
                                            <td>66</td>
                                            <td>54561561</td>
                                            <td>56465</td>
                                            <td>4125</td>
                                            <td>+16</td>
                                        </tr>
                                        <tr>
                                            <td>#14</td>
                                            <td>Shuna</td>
                                            <td>66</td>
                                            <td>54561561</td>
                                            <td>56465</td>
                                            <td>4125</td>
                                            <td>+16</td>
                                        </tr>
                                        <tr>
                                            <td>#15</td>
                                            <td>Aby</td>
                                            <td>66</td>
                                            <td>54561561</td>
                                            <td>56465</td>
                                            <td>4125</td>
                                            <td>+16</td>
                                        </tr>
                                        <tr>
                                            <td>#16</td>
                                            <td>Axe</td>
                                            <td>66</td>
                                            <td>54561561</td>
                                            <td>56465</td>
                                            <td>4125</td>
                                            <td>+16</td>
                                        </tr>
                                        <tr>
                                            <td>#17</td>
                                            <td>Shuna</td>
                                            <td>66</td>
                                            <td>54561561</td>
                                            <td>56465</td>
                                            <td>4125</td>
                                            <td>+16</td>
                                        </tr>
                                        <tr>
                                            <td>#18</td>
                                            <td>Clover</td>
                                            <td>66</td>
                                            <td>54561561</td>
                                            <td>56465</td>
                                            <td>4125</td>
                                            <td>+16</td>
                                        </tr>
                                        <tr>
                                            <td>#19</td>
                                            <td>Sad</td>
                                            <td>66</td>
                                            <td>54561561</td>
                                            <td>56465</td>
                                            <td>4125</td>
                                            <td>+16</td>
                                        </tr>
                                        <tr>
                                            <td>#20</td>
                                            <td>Aby</td>
                                            <td>66</td>
                                            <td>54561561</td>
                                            <td>56465</td>
                                            <td>4125</td>
                                            <td>+16</td>
                                        </tr>
                                        <tr>
                                            <td>#21</td>
                                            <td>Axe</td>
                                            <td>66</td>
                                            <td>54561561</td>
                                            <td>56465</td>
                                            <td>4125</td>
                                            <td>+16</td>
                                        </tr>
                                        <tr>
                                            <td>#22</td>
                                            <td>Betrayer</td>
                                            <td>66</td>
                                            <td>54561561</td>
                                            <td>56465</td>
                                            <td>4125</td>
                                            <td>+16</td>
                                        </tr>
                                        <tr>
                                            <td>#23</td>
                                            <td>Clover</td>
                                            <td>66</td>
                                            <td>54561561</td>
                                            <td>56465</td>
                                            <td>4125</td>
                                            <td>+16</td>
                                        </tr>
                                        <tr>
                                            <td>#24</td>
                                            <td>Sad</td>
                                            <td>66</td>
                                            <td>54561561</td>
                                            <td>56465</td>
                                            <td>4125</td>
                                            <td>+16</td>
                                        </tr>
                                        <tr>
                                            <td>#25</td>
                                            <td>Aby</td>
                                            <td>66</td>
                                            <td>54561561</td>
                                            <td>56465</td>
                                            <td>4125</td>
                                            <td>+16</td>
                                        </tr>
                                        <tr>
                                            <td>#26</td>
                                            <td>Axe</td>
                                            <td>66</td>
                                            <td>54561561</td>
                                            <td>56465</td>
                                            <td>4125</td>
                                            <td>+16</td>
                                        </tr>
                                        <tr>
                                            <td>#27</td>
                                            <td>Betrayer</td>
                                            <td>66</td>
                                            <td>54561561</td>
                                            <td>56465</td>
                                            <td>4125</td>
                                            <td>+16</td>
                                        </tr>
                                        <tr>
                                            <td>#28</td>
                                            <td>Clover</td>
                                            <td>66</td>
                                            <td>54561561</td>
                                            <td>56465</td>
                                            <td>4125</td>
                                            <td>+16</td>
                                        </tr>
                                        <tr>
                                            <td>#29</td>
                                            <td>Sad</td>
                                            <td>66</td>
                                            <td>54561561</td>
                                            <td>56465</td>
                                            <td>4125</td>
                                            <td>+16</td>
                                        </tr>
                                        <tr>
                                            <td>#30</td>
                                            <td>Aby</td>
                                            <td>66</td>
                                            <td>54561561</td>
                                            <td>56465</td>
                                            <td>4125</td>
                                            <td>+16</td>
                                        </tr>
                                        
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

      

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    

    <!-- Bootstrap core JavaScript-->
    <script src="js/jquery.js"></script>
    <script src="extras/bootstrap/js/bootstrap.bundle.min.js"></script>

    <script src="js/jquery.easing.js"></script>
    <script src="js/interface.js"></script>

    <script src="tables/table.js"></script>
    <script src="tables/jquery.dataTables.js"></script>
    <script src="tables/dataTables.js"></script>
 
</body>

</html>