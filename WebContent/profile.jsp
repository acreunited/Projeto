<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="java.io.*"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="main.Connector"%>
<%@page import="users.Login"%>
<%@page import="resources.ViewProfile"%>
<%@page import="users.UserInfo"%>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Anime-Arena">
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

            <li class="nav-item active" id="players" style="display:none">
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
            <li class="nav-item" id="isLog" style="display: block">
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

			<li class="nav-item active" id="admin" style="display:none">
                <a class="nav-link" href="create.jsp">
                    <span>Create Character</span></a>
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

					<!-- Topbar Navbar -->
                 <ul class="navbar-nav ml-auto" id="players">
                    
                     <%
					Class.forName(Connector.drv);
					try (Connection conn = Connector.getConnection();) {
						Statement stmt = conn.createStatement();
						
						ResultSet rs = stmt.executeQuery("select * from USERS where userID="+session.getAttribute("userID"));
						if (rs.next()) {
							String username = rs.getString("username");
							String userID = rs.getString("userID");
					%>
                    
                    <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small"><%=username %></span>
                                <img class="img-profile rounded-circle"
                                    src="ViewAvatar?id=<%=userID %>">
                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">
                                <a class="dropdown-item" href="ViewProfile?username=<%=username %>">
                                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Profile
                                </a>
                                <a class="dropdown-item" href="settings.jsp">
                                    <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Settings
                                </a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="logout.jsp">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Logout
                                </a>
                            </div>
                        </li>
                        
                         <%
                        }
						rs.close();
						} catch (SQLException | IOException e) {
						System.out.println(e.getMessage());
						}
						%>
                    </ul>
                    

                </nav>
                <!-- End of Topbar -->
                <div class="container-fluid">
                <!-- <p class="mb-4">I feel like it could/should be the same as the one from N-A. Open to suggestions tho</p><br>-->
                 
                 </div>

                <!-- Outer Row -->
                <div class="row justify-content-center">

                    <img style="width: 75px;height: 75px; margin-right: 20px;margin-top: 20px;" alt="Avatar" src="ViewAvatar?id=<%=session.getAttribute("profile_id") %>">
                    <table class="ml-8 w-full content:w-[70%] content:mr-4 table-auto border-separate text-[12px]">
                        <thead>
                            <tr>
                                <th class="text-hallo-top dark:text-dark-top">Account information</th>
                            </tr>
                        </thead>
                        <tbody>
                        
                            <tr class="bg-hallo-nav dark:bg-dark-nav">
                                <td class="w-2/6">Username:</td>
                                <td class="font-bold text-hallo-title dark:text-dark-title"><%=session.getAttribute("profile_username") %></td>
                            </tr>
                            <tr class="bg-hallo-navm dark:bg-dark-navm">
                                <td>Site rank:</td>
                                <td class="flex items-center"><img src="" class="mr-2">TODO</td>
                            </tr>
                            
                            <tr class="bg-hallo-navm dark:bg-dark-navm">
                                <td>Registered on:</td>
                                <td><%=session.getAttribute("profile_registerDate") %></td>
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
                                <span class="relative inset-0 top-[-15px] left-[45%]"><%=session.getAttribute("profile_level") %></span>
                                </div>
                    
                            </td>
                        </tr>
                        <tr class="bg-hallo-navm dark:bg-dark-navm">
                            <td>Rank:</td>
                                <td class="flex items-center"><img src="https://naruto-arena.net/images/ladderranks/Kage.png" class="mr-2">TODO</td>
                        </tr>
                        <tr class="bg-hallo-nav dark:bg-dark-nav">
                            <td>Experience Points:</td>
                            <td><%=session.getAttribute("profile_xp") %><span class="text-gray-400">xp</span></td>
                        </tr>
                        <tr class="bg-hallo-navm dark:bg-dark-navm">
                     
                            <td>Ladder Rank:</td>
                            <td>#<%=session.getAttribute("profile_rank")%></td>
                            
                        </tr>
                       
                        <tr class="bg-hallo-nav dark:bg-dark-nav">
                            <td>Wins:</td>
                            <td><%=session.getAttribute("profile_wins") %></td>
                        </tr>
                        <tr class="bg-hallo-navm dark:bg-dark-navm">
                            <td>Losses:</td>
                            <td><%=session.getAttribute("profile_losses") %></td>
                        </tr>
                        <tr class="bg-hallo-nav dark:bg-dark-nav">
                            <td>Win percentage:</td>
                            <td>TODO</td>
                        </tr>
                        <tr class="bg-hallo-navm dark:bg-dark-navm">
                            <td>Streak:</td>
                            <td><%=session.getAttribute("profile_streak") %></td>
                        </tr>
                        <tr class="bg-hallo-nav dark:bg-dark-nav">
                            <td>Highest Streak:</td>
                            <td><%=session.getAttribute("profile_hStreak") %></td>
                        </tr>
                        <tr class="bg-hallo-navm dark:bg-dark-navm">
                            <td>Highest Level:</td>
                            <td><%=session.getAttribute("profile_hLevel") %></td>
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

<script type="text/javascript">
function displayUsers(tipoUser) {
    if (tipoUser=="administrador") {
        document.getElementById("players").style.display="block";
        document.getElementById("admin").style.display="block";
    }
    else if (tipoUser=="player") {
        document.getElementById("players").style.display="block";
        document.getElementById("admin").style.display="none";
    }
    else {
        document.getElementById("players").style.display="none";
        document.getElementById("admin").style.display="none";
    }
}
	
function displayLogged(isLog) {
	console.log(isLog);
	
	if (isLog=="null" || isLog=="false") {
        document.getElementById("isLog").style.display="block";
    }
    else {
    	document.getElementById("isLog").style.display="none";
    }
	
    
}
</script>

<script>
	var tipo = "<%=(String) session.getAttribute("tipoUser")%>";
	
	if (tipo!=null) {
		displayUsers( tipo );
	}
	
	var isLogin = "<%=session.getAttribute("loggedIn")%>";

	displayLogged(isLogin);
	
</script>

    <!-- Bootstrap core JavaScript-->
    <script src="js/jquery.js"></script>
    <script src="extras/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="js/jquery.easing.js"></script>
    <script src="js/interface.js"></script>


</body>

</html>