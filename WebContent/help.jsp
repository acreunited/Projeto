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
    <link href="css/characterInfo.css" rel="stylesheet">
    <link href="css/responsive.css" rel="stylesheet">

    <script src="js/help.js"></script>

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

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <div class="text-center">
                        <div class="buttonsHelp">
                            <button onclick="buttonBasics()">Basics</button>
                            <button onclick="buttonCharsSkills()">Characters and Skills</button>
                            <button onclick="buttonRanking()">Ranking System</button>
                        </div>
                    </div>

                    <div id="contentBasics" style="display: block;">
                        <p><br>We explain here how the game is played. Its better to screens of the actual game imo, so I´ll just leave like this for now</p>
                    </div>

                    <div id="contentCharSkills" style="display: none;">
                        
                       <%
					Class.forName(Connector.drv);
					try (Connection conn = Connector.getConnection();) {
						Statement stmt = conn.createStatement();
		
						ResultSet rs = stmt.executeQuery("select * from THEME_CHARACTER where themeID=1");
						while (rs.next()) {
							String characterID = rs.getString("characterID");
							String nome = rs.getString("nome");
							
							if (characterID == null || nome==null ) {
								break;
							}
					%>
	                        <article>
	                            <img src="ViewCharacter?id=<%=characterID %>" onclick="document.getElementById('characterID<%=characterID%>').style.display='block'">
	
	                            <article class="content">
	                                <b><%=nome %></b><br>
	                            </article>
	                        </article>
                        
	                        <div id="characterID<%=characterID%>" class="modal" style="display: none;">
	                        	<div class="container" style="background-color: #f1f1f1">
	                        		<%
	                        		ResultSet character = conn.createStatement().executeQuery(
											"select * from BLEACH INNER JOIN THEME_CHARACTER where BLEACH.bleachID=THEME_CHARACTER.characterID and THEME_CHARACTER.themeID=1 and BLEACH.bleachID=" + characterID);
									while (character.next()) {
									%>
										<div style="margin: auto; width: 50%; padding: 10px">
											<p><%=character.getString("nome")%></p>
											<img src="ViewCharacter?id=<%=characterID %>"/>
											<p><%=character.getString("descricao")%></p>
										</div>
										<%
									}
									character.close();
									
									ResultSet abilities = conn.createStatement().executeQuery(
											"select * from ABILITY where characterID="+characterID+";");
									
									while (abilities.next()) {
										
										String abilityID = abilities.getString("abilityID");
										if (abilityID==null) {
											break;
										}
										
										ResultSet abilit = conn.createStatement().executeQuery(
												"select * from THEME_ABILITY where themeID=1 and abilityID="+abilityID+";");
										
											if (abilit.next()) {
												String name = abilit.getString("nome");
												String descricao = abilit.getString("descricao");
												if (name==null) {
													break;
												}
											
												%>
												<div style="margin: auto; width: 50%; padding: 10px">
													<p><b>Name:</b> <%=name%></p>
													<img src="ViewAbility?id=<%=abilityID %>"/>
													<p><b>Description:</b> <%=descricao%></p>
													<p><b>Cooldown:</b> <%=abilities.getString("cooldown")%></p>
												</div>
												<%
												
											}
											abilit.close();
										}
									
									abilities.close();
									%>	
									
	                        		<div class="container" style="background-color: #f1f1f1">
										<button type="button" onclick="document.getElementById('characterID<%=characterID%>').style.display='none'"
										class="cancelbtn"
										>Exit</button>
									</div>
								</div>
	                        </div>

                        <%
                        }
						rs.close();
						} catch (SQLException | IOException e) {
						System.out.println(e.getMessage());
						}
						%>
                        
                        
                    </div>

                    <div id="contentRankins" style="display: none;">
                        <p><br>We explain here all the ranks, levels, experience etc. Will do it once we have them defined and all that</p>
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

    <script src="js/jquery.easing.js"></script>
    <script src="js/interface.js"></script>


</body>

</html>