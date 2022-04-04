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
<%@ page import="main.CreateCharacterMission"%>

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
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js" type="text/javascript"></script>
    

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
                    <div class="custom-select">
                        <select>
					    <option value="0">No Theme</option>
					    <option value="1">Default</option>
					  </select>
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
                        <a class="collapse-item" href="#">Annoucements</a>
                        <a class="collapse-item" href="ladder.jsp">Rankings</a>
                        <a class="collapse-item" href="missions.jsp">Missions</a>
                        <a class="collapse-item" href="help.jsp">Help</a>

                        <div class="collapse-divider"></div>
                    </div>
                </div>
            </li>

            <li class="nav-item active">
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
        <div id="content-wrapper" class="d-flex flex-column" >

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
                                <a class="dropdown-item" href="profile.jsp">
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
                
                <form class="modal-content animate" method="post"
					enctype="multipart/form-data"
					action="${pageContext.request.contextPath}/CreateCharacterMission"
				>
		
					<div class="container">
		
						<div class="container">
							<label for="characterName">
								<b>Character Name</b>
							</label>
							<input type="text" placeholder="Character Name" name="characterName"
								required pattern="[\w ()-]{3,100}"
							/>
						</div>
						<div class="container">
							<label for="characterDescription">
								<b>Character Description</b>
							</label>
							<textarea placeholder="Write Character Description (max 5000chars)" name="characterDescription"
								style="min-height: 200px; width: 100%;" required
							></textarea>
						</div>
		
						<div class="container">
							<label for="charPic">Character Picture</label>
							<input type="file" accept="image/*" name="charPic" required />
						</div>
						<br>
						<br>
						<div class="container">
							<label for="abilityName1">
								<b>Ability1 Name</b>
							</label>
							<input type="text" placeholder="Ability Name" name="ability1"
								required pattern="[\w ()-]{3,100}"
							/>
						</div>
						
						<div class="container">
							<label for="ability1Description">
								<b>Ability1 Description</b>
							</label>
							<textarea placeholder="Write Ability 1 Description (max 5000chars)" name="ability1Description"
								style="min-height: 200px; width: 100%;" required
							></textarea>
						</div>
						<div class="container">
							<label for="ability1cd">Cooldown (minimum 0):</label>
							<input type="number" name="ability1cd" min="0" max="100">
						</div>	
						<div class="container">
							<label for="ability1image">Ability1 Picture</label>
							<input type="file" accept="image/*" name="ability1image" required />
						</div>
						<br><br>
						<div class="container">
							<label for="abilityName2">
								<b>Ability2 Name</b>
							</label>
							<input type="text" placeholder="Ability Name" name="ability2"
								required pattern="[\w ()-]{3,100}"
							/>
						</div>
						
						<div class="container">
							<label for="ability2Description">
								<b>Ability2 Description</b>
							</label>
							<textarea placeholder="Write Ability 2 Description (max 5000chars)" name="ability2Description"
								style="min-height: 200px; width: 100%;" required
							></textarea>
						</div>
						<div class="container">
							<label for="ability2cd">Cooldown (minimum 0):</label>
							<input type="number" name="ability2cd" min="0" max="100">
						</div>	
						<div class="container">
							<label for="ability2image">Ability2 Picture</label>
							<input type="file" accept="image/*" name="ability2image" required />
						</div>
						<br><br>
						<div class="container">
							<label for="abilityName3">
								<b>Ability3 Name</b>
							</label>
							<input type="text" placeholder="Ability Name" name="ability3"
								required pattern="[\w ()-]{3,100}"
							/>
						</div>
						<div class="container">
							<label for="ability3Description">
								<b>Ability3 Description</b>
							</label>
							<textarea placeholder="Write Ability 3 Description (max 5000chars)" name="ability3Description"
								style="min-height: 200px; width: 100%;" required
							></textarea>
						</div>
						<div class="container">
							<label for="ability3cd">Cooldown (minimum 0):</label>
							<input type="number" name="ability3cd" min="0" max="100">
						</div>	
						<div class="container">
							<label for="ability3image">Ability3 Picture</label>
							<input type="file" accept="image/*" name="ability3image" required />
						</div>
						<br><br>
						<div class="container">
							<label for="abilityName4">
								<b>Ability4 Name</b>
							</label>
							<input type="text" placeholder="Ability Name" name="ability4"
								required pattern="[\w ()-]{3,100}"
							/>
						</div>
						<div class="container">
							<label for="ability4Description">
								<b>Ability4 Description</b>
							</label>
							<textarea placeholder="Write Ability 4 Description (max 5000chars)" name="ability4Description"
								style="min-height: 200px; width: 100%;" required
							></textarea>
						</div>
						<div class="container">
							<label for="ability4cd">Cooldown (minimum 0):</label>
							<input type="number" name="ability4cd" min="0" max="100">
						</div>	
						<div class="container">
							<label for="ability4image">Ability4 Picture</label>
							<input type="file" accept="image/*" name="ability4image" required />
						</div>
						<br><br>
		
						<div class="container">
							<label for="charAnime">Character Anime</label>
							<select name="charAnime" id="characterAnime">
								<option value="Bleach">Bleach</option>
								<option value="Naruto">Naruto</option>
							</select>
						</div>
						
						<div class="container">
							<label for="defaultmission">Default or Mission Character ?</label>
							<select name="defaultmission" id="defaultORmission" onchange="isMission(this)">
								<option value="Default">Default</option>
								<option value="Mission">Mission</option>
							</select>
						</div>
						
						<div id="showMission" style="display:none">
							<div class="container">
								<label for="missionName">
									<b>Mission Name</b>
								</label>
								<input type="text" placeholder="Mission Name" name="missionName"
									required pattern="[\w ()-]{3,100}"
								/>
							</div>
							<div class="container">
								<label for="missionDescription">
									<b>Mission Description</b>
								</label>
								<textarea placeholder="Write Mission Description (max 5000chars)" name="missionDescription"
									style="min-height: 200px; width: 100%;" required
								></textarea>
							</div>
							
							<div class="container">
								<label for="minLevel">Required Level (minimum 1):</label>
								<input type="number" name="minLevel" min="1" max="1000">
							</div>	
							
							<div class="container">
								<label for="missionImage">Mission Picture</label>
								<input type="file" accept="image/*" name="missionImage" required />
							</div>
						</div>
						<br>
		
						<button type="submit">Submit</button>
					</div>
				</form>
                
                </div>
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

<script>
function isMission(m) {
	console.log(m.value);
    if (m.value=="Mission") {
        document.getElementById("showMission").style.display="block";
    }
    else if (m.value=="Default") {
        document.getElementById("showMission").style.display="none";
    }
}
</script>




    <!-- Bootstrap core JavaScript-->
    <script src="js/jquery.js"></script>
    <script src="extras/bootstrap/js/bootstrap.bundle.min.js"></script>

    <script src="js/jquery.easing.js"></script>

    <script src="js/interface.js"></script>

  



</body>

</html>