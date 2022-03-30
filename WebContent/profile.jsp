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
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
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
                        <a class="collapse-item" href="#">Theme1</a>
                        <a class="collapse-item" href="#">Theme2</a>
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
                        <a class="collapse-item" href="index.html">Annoucements</a>
                        <a class="collapse-item" href="ladder.html">Rankings</a>
                        <a class="collapse-item" href="missions.html">Missions</a>
                        <a class="collapse-item" href="help.html">Help</a>

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
                        <a class="collapse-item" href="login.html">Login</a>
                        <a class="collapse-item" href="register.html">Register</a>

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

                    <!-- Sidebar Toggle (Topbar) -->
                    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>

        

                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">

                        <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                        <li class="nav-item dropdown no-arrow d-sm-none">
                            <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-search fa-fw"></i>
                            </a>
                            <!-- Dropdown - Messages -->
                            <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                                aria-labelledby="searchDropdown">
                                <form class="form-inline mr-auto w-100 navbar-search">
                                    <div class="input-group">
                                        <input type="text" class="form-control bg-light border-0 small"
                                            placeholder="Search for..." aria-label="Search"
                                            aria-describedby="basic-addon2">
                                        <div class="input-group-append">
                                            <button class="btn btn-primary" type="button">
                                                <i class="fas fa-search fa-sm"></i>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </li>

                        <!-- Nav Item - Alerts -->
                        <li class="nav-item dropdown no-arrow mx-1">
                            <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-bell fa-fw"></i>
                                <!-- Counter - Alerts -->
                                <span class="badge badge-danger badge-counter">2</span>
                            </a>
                            <!-- Dropdown - Alerts -->
                            <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="alertsDropdown">
                                <h6 class="dropdown-header">
                                    Annoucements
                                </h6>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                         
                                    <div>
                                        <div class="small text-gray-500">March 17, 2022</div>
                                        <span class="font-weight-bold">Notify users of annoucements, like balance update, new chars, etc</span>
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                         
                                    <div>
                                        <div class="small text-gray-500">March 17, 2022</div>
                                        <span class="font-weight-bold">PS: Just an idea, will implement this later, one thing at a time </span>
                                    </div>
                                </a>
                                
                                <a class="dropdown-item text-center small text-gray-500" href="#">Show All Annoucements</a>
                            </div>
                        </li>

                        <!-- Nav Item - Messages -->
                        <li class="nav-item dropdown no-arrow mx-1">
                            <a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-envelope fa-fw"></i>
                                <!-- Counter - Messages -->
                                <span class="badge badge-danger badge-counter">1</span>
                            </a>
                            <!-- Dropdown - Messages -->
                            <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="messagesDropdown">
                                <h6 class="dropdown-header">
                                    Private Messages
                                </h6>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="dropdown-list-image mr-3">
                                        <img class="rounded-circle" src="img/goku.png"
                                            alt="...">
                                        <div class="status-indicator bg-success"></div>
                                    </div>
                                    <div class="font-weight-bold">
                                        <div class="text-truncate">Krillin is dead again, we need to revive him!</div>
                                        <div class="small text-gray-500">Goku</div>
                                    </div>
                                </a>
                               
                                <a class="dropdown-item text-center small text-gray-500" href="#">Read More Messages</a>
                            </div>
                        </li>

                        <div class="topbar-divider d-none d-sm-block"></div>

                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">Username</span>
                                <img class="img-profile rounded-circle"
                                    src="img/buu.png">
                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">
                                <a class="dropdown-item" href="profile.html">
                                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Profile
                                </a>
                                <a class="dropdown-item" href="settings.html">
                                    <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Settings
                                </a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Logout
                                </a>
                            </div>
                        </li>

                    </ul>

                </nav>
                <!-- End of Topbar -->
                <div class="container-fluid">
                <p class="mb-4">I feel like it could/should be the same as the one from N-A. Open to suggestions tho<br>
                 </p>
                 </div>

                <!-- Outer Row -->
                <div class="row justify-content-center">

                    <img style="width: 75px;height: 75px; margin-right: 20px;margin-top: 20px;" alt="Avatar" src="https://acreunited.github.io/avys/Misc/71.png">
                    <table class="ml-8 w-full content:w-[70%] content:mr-4 table-auto border-separate text-[12px]">
                        <thead>
                            <tr>
                                <th class="text-hallo-top dark:text-dark-top">Account information</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr class="bg-hallo-nav dark:bg-dark-nav">
                                <td class="w-2/6">Username:</td>
                                <td class="font-bold text-hallo-title dark:text-dark-title">Kitana</td>
                            </tr>
                            <tr class="bg-hallo-navm dark:bg-dark-navm">
                                <td>Site rank:</td>
                                <td class="flex items-center"><img src="https://naruto-arena.net/images/ranks/Member.gif" class="mr-2"> Member</td>
                            </tr>
                            <tr class="bg-hallo-nav dark:bg-dark-nav">
                                <td>Posts:</td>
                                <td>101</td>
                            </tr>
                            <tr class="bg-hallo-navm dark:bg-dark-navm">
                                <td>Registered on:</td>
                                <td>August 12, 2019 16:34</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="row justify-content-center" style="margin-top: 20px;">
                    <table class="w-[100%] table-auto border-separate text-[12px] mb-10 mt-10">
                        <thead>
                        <tr>
                            <th class="text-hallo-top dark:text-dark-top"> NARUTOARENA Ladder</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr class="bg-hallo-nav dark:bg-dark-nav">
                            <td class="w-[30%]">Level:</td>
                            <td class="w-[70%]">
                            <div class="w-[100px] h-[18px] border border-black bg-hallo-cont dark:bg-dark-cont">
                                <div class="bg-hallo-top dark:bg-dark-top h-[16px]" style="width: 33.211488250653px;"></div>
                                <span class="relative inset-0 top-[-15px] left-[45%]">52</span>
                                </div>
                    
                            </td>
                        </tr>
                        <tr class="bg-hallo-navm dark:bg-dark-navm">
                            <td>Rank:</td>
                                <td class="flex items-center"><img src="https://naruto-arena.net/images/ladderranks/Kage.png" class="mr-2">Kage</td>
                        </tr>
                        <tr class="bg-hallo-nav dark:bg-dark-nav">
                            <td>Experience Points:</td>
                                <td>53576 <span class="text-gray-400">xp</span></td>
                        </tr>
                        <tr class="bg-hallo-navm dark:bg-dark-navm">
                            <td>Ladder Rank:</td>
                                        <td>#29</td>
                            
                        </tr>
                        <tr class="bg-hallo-nav dark:bg-dark-nav">
                            <td>Wins:</td>
                            <td>2345</td>
                        </tr>
                        <tr class="bg-hallo-navm dark:bg-dark-navm">
                            <td>Losses:</td>
                            <td>614</td>
                        </tr>
                        <tr class="bg-hallo-nav dark:bg-dark-nav">
                            <td>Win percentage:</td>
                            <td>79 %</td>
                        </tr>
                        <tr class="bg-hallo-navm dark:bg-dark-navm">
                            <td>Streak:</td>
                            <td>+1</td>
                        </tr>
                        <tr class="bg-hallo-nav dark:bg-dark-nav">
                            <td>Highest Streak:</td>
                            <td>+49</td>
                        </tr>
                        <tr class="bg-hallo-navm dark:bg-dark-navm">
                            <td>Highest Level:</td>
                            <td>Level 56</td>
                        </tr>
                        </tbody>
                    </table>
                </div>

                <div class="row justify-content-center" style="margin-top: 20px;">
                        <!--Games-->
                    <table class="w-[100%] table-auto text-[12px] mb-10 mt-10">
                        <thead>
                        <tr>
                            <th class="text-hallo-top dark:text-dark-top">Private games (in the last 5 days)</th>
                        </tr>
                        </thead>
                        <tbody>
                            <tr class="bg-hallo-nav dark:bg-dark-nav">
                                <td class="w-[25%]">March 16, 2022 17:25</td>
                                <td class="w-[30%]"><a href="#">Kitana</a> vs
                                <a href="#">AusBee</a></td>
                                <td class="w-[30%]"><a href="#">Winner <b class="text-hallo-title dark:text-dark-title">Kitana</b></a></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                             

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

    <!-- Core plugin JavaScript-->
    <script src="js/jquery.easing.js"></script>
    <script src="js/interface.js"></script>


</body>

</html>