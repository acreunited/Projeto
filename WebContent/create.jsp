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
								pattern="[A-Za-z]{1,16}" required
							/>
						</div>
						<div class="container">
							<label for="characterDescription">
								<b>Character Description</b>
							</label>
							<textarea placeholder="Write Character Description (max 5000chars)" name="characterDescription"
								style="min-height: 200px; width: 100%;" maxlength="5000" required
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
								pattern="[A-Za-z]{1,16}" required
							/>
						</div>
						
						<div class="container">
							<label for="ability1Description">
								<b>Ability1 Description</b>
							</label>
							<textarea placeholder="Write Ability 1 Description (max 5000chars)" name="ability1Description"
								style="min-height: 200px; width: 100%;" maxlength="5000" required
							></textarea>
						</div>
						
						<div class="container">
							<label for="ability1target">Who is the target of this ability?</label>
							<select name="ability1target" id="ability1target">
								<option value="self">Self</option>
								<option value="enemy">Enemy</option>
								<option value="ally">Ally</option>
							</select>
						</div>
						<div class="container">
							<label for="ability1damage">Does this ability do damage?</label>
							<select name="ability1damage" id="ability1damage" onchange="ability1DoesDamage(this)">
								<option value="no">No</option>
								<option value="yes">Yes</option>
							</select>
	
							<div id="ability1DoesDamage" style="display: none;">
								<label for="ability1damageNumber">Damage Value:</label>
								<input type="number" name="ability1damageNumber" min="0" max="1000">
								<label for="ability1damageDuration">Turn duration:</label>
								<input type="number" name="ability1damageDuration" min="0" max="1000">
							</div>	
							<br>
						</div>
						
						<div class="container">
							<label for="ability1increaseAbilityDamage">Ability damage increase per use (0 if none)</label>
							<input type="number" name="ability1taijutsu" min="0" max="1000">
							<br>
							<label for="ability1increasePermanentDamage">Permanent Character increase damage (0 if none)</label>
							<input type="number" name="ability1taijutsu" min="0" max="1000">
							<br>
							<label for="ability1stun">Stun Duration (0 if none)</label>
							<input type="number" name="ability1stun" min="0" max="1000">
							<br>
							<label for="ability1beInvul">How many turns this ability makes character invulnerable (0 if none)</label>
							<input type="number" name="ability1beInvul" min="0" max="1000">
							<br>
							<label for="ability1ignoreInvul">Does this ability ignore invulnerability?</label>
							<select name="ability1ignoreInvul" id="ability1ignoreInvul">
								<option value="no" >No</option>
								<option value="yes">Yes</option>
							</select>
							<br>
						</div>
				
						<div class="container">
							<label for="ability1removeNature">Does this ability remove Natures?</label>
							<select name="ability1removeNature" id="ability1removeNature" onchange="ability1removesNature(this)">
								<option value="no" >No</option>
								<option value="yes">Yes</option>
							</select>
	
							<div id="ability1removesNature" style="display: none;">
								<label for="ability1removesNatureNumber">How Many?:</label>
								<input type="number" name="ability1removesNatureNumber" min="0" max="1000">
								<label for="ability1removesNatureDuration">Turn duration:</label>
								<input type="number" name="ability1removesNatureDuration" min="0" max="1000">
							</div>	
							<br>
						</div>
						
						<div class="container">
							<label for="ability1gainNature">Does this ability gain Natures?</label>
							<select name="ability1gainNature" id="ability1gainNature" onchange="ability1gainsNature(this)">
								<option value="no" >No</option>
								<option value="yes">Yes</option>
							</select>
	
							<div id="ability1gainsNature" style="display: none;">
								<label for="ability1gainNatureNumber">How Many?:</label>
								<input type="number" name="ability1gainNatureNumber" min="0" max="1000">
								<label for="ability1gainNatureDuration">Turn duration:</label>
								<input type="number" name="ability1gainNatureDuration" min="0" max="1000">
							</div>	
							<br>
						</div>
						
						<div class="container">
							<label for="ability1gainHP">Does this ability gain Health?</label>
							<select name="ability1gainHP" id="ability1gainHP" onchange="ability1gainsHP(this)">
								<option value="no" >No</option>
								<option value="yes">Yes</option>
							</select>
	
							<div id="ability1gainsHP" style="display: none;">
								<label for="ability1gainHPNumber">How Many?:</label>
								<input type="number" name="ability1gainHPNumber" min="0" max="1000">
								<label for="ability1gainHPDuration">Turn duration:</label>
								<input type="number" name="ability1gainHPDuration" min="0" max="1000">
							</div>	
							<br>
						</div>
						
						<div class="container">
							<label for="ability1gainDD">Does this ability gain Destructible Defense?</label>
							<select name="ability1gainDD" id="ability1gainDD" onchange="ability1gainsDD(this)">
								<option value="no" >No</option>
								<option value="yes">Yes</option>
							</select>
	
							<div id="ability1gainsDD" style="display: none;">
								<label for="ability1gainDDNumber">How Many?:</label>
								<input type="number" name="ability1gainDDNumber" min="0" max="1000">
								<label for="ability1gainDDDuration">Turn duration:</label>
								<input type="number" name="ability1gainDDDuration" min="0" max="1000">
							</div>	
							<br>
						</div>
						
						<div class="container">
							<label for="ability1gainDR">Does this ability gain Damage Reduction?</label>
							<select name="ability1gainDR" id="ability1gainDR" onchange="ability1gainsDR(this)">
								<option value="no" >No</option>
								<option value="yes">Yes</option>
							</select>
	
							<div id="ability1gainsDR" style="display: none;">
								<label for="ability1gainDRNumber">How Many?:</label>
								<input type="number" name="ability1gainDRNumber" min="0" max="1000">
								<label for="ability1gainDRDuration">Turn duration:</label>
								<input type="number" name="ability1gainDRDuration" min="0" max="1000">
							</div>	
							<br>
						</div>
						
						
						<div class="container">
							<label for="ability1taijutsu">Taijutsu (minimum 0):</label>
							<input type="number" name="ability1taijutsu" min="0" max="100" required><br>
							<label for="ability1heart">Heart (minimum 0):</label>
							<input type="number" name="ability1heart" min="0" max="100" required><br>
							<label for="ability1energy">Energy (minimum 0):</label>
							<input type="number" name="ability1energy" min="0" max="100" required><br>
							<label for="ability1spirit">Spirit (minimum 0):</label>
							<input type="number" name="ability1spirit" min="0" max="100" required><br>
							<label for="ability1random">Random (minimum 0):</label>
							<input type="number" name="ability1random" min="0" max="100" required><br>
						</div>
						<div class="container">
							<label for="ability1cd">Cooldown (minimum 0):</label>
							<input type="number" name="ability1cd" min="0" max="100" required>
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
							<input type="text" placeholder="Ability Name (max 32chars)" name="ability2"
								pattern="[A-Za-z]{1,16}" required
							/>
						</div>
						
						<div class="container">
							<label for="ability2Description">
								<b>Ability2 Description</b>
							</label>
							<textarea placeholder="Write Ability 2 Description (max 5000chars)" name="ability2Description"
								style="min-height: 200px; width: 100%;" maxlength="5000" required
							></textarea>
						</div>
						
						<div class="container">
							<label for="ability2target">Who is the target of this ability?</label>
							<select name="ability2target" id="ability2target">
								<option value="self">Self</option>
								<option value="enemy">Enemy</option>
								<option value="ally">Ally</option>
							</select>
						</div>
						<div class="container">
							<label for="ability2damage">Does this ability do damage?</label>
							<select name="ability2damage" id="ability2damage" onchange="ability2DoesDamage(this)">
								<option value="no">No</option>
								<option value="yes">Yes</option>
							</select>
	
							<div id="ability2DoesDamage" style="display: none;">
								<label for="ability2damageNumber">Damage Value:</label>
								<input type="number" name="ability2damageNumber" min="0" max="1000">
								<label for="ability2damageDuration">Turn duration:</label>
								<input type="number" name="ability2damageDuration" min="0" max="1000">
							</div>	
							<br>
						</div>
						
						<div class="container">
							<label for="ability2increaseAbilityDamage">Ability damage increase per use (0 if none)</label>
							<input type="number" name="ability2increaseAbilityDamage" min="0" max="1000" required>
							<br>
							<label for="ability2increasePermanentDamage">Permanent Character increase damage (0 if none)</label>
							<input type="number" name="ability2increasePermanentDamage" min="0" max="1000" required>
							<br>
							<label for="ability2stun">Stun Duration (0 if none)</label>
							<input type="number" name="ability2stun" min="0" max="1000" required>
							<br>
							<label for="ability2beInvul">How many turns this ability makes character invulnerable (0 if none)</label>
							<input type="number" name="ability2beInvul" min="0" max="1000" required>
							<br>
							<label for="ability2ignoreInvul">Does this ability ignore invulnerability?</label>
							<select name="ability2ignoreInvul" id="ability2ignoreInvul">
								<option value="no" >No</option>
								<option value="yes">Yes</option>
							</select>
							<br>
						</div>
				
						<div class="container">
							<label for="ability2removeNature">Does this ability remove Natures?</label>
							<select name="ability2removeNature" id="ability2removeNature" onchange="ability2removesNature(this)">
								<option value="no" >No</option>
								<option value="yes">Yes</option>
							</select>
	
							<div id="ability2removesNature" style="display: none;">
								<label for="ability2removesNatureNumber">How Many?:</label>
								<input type="number" name="ability2removesNatureNumber" min="0" max="1000">
								<label for="ability2removesNatureDuration">Turn duration:</label>
								<input type="number" name="ability2removesNatureDuration" min="0" max="1000">
							</div>	
							<br>
						</div>
						
						<div class="container">
							<label for="ability2gainNature">Does this ability gain Natures?</label>
							<select name="ability2gainNature" id="ability2gainNature" onchange="ability2gainsNature(this)">
								<option value="no" >No</option>
								<option value="yes">Yes</option>
							</select>
	
							<div id="ability2gainsNature" style="display: none;">
								<label for="ability2gainNatureNumber">How Many?:</label>
								<input type="number" name="ability2gainNatureNumber" min="0" max="1000">
								<label for="ability2gainNatureDuration">Turn duration:</label>
								<input type="number" name="ability2gainNatureDuration" min="0" max="1000">
							</div>	
							<br>
						</div>
						
						<div class="container">
							<label for="ability2gainHP">Does this ability gain Health?</label>
							<select name="ability2gainHP" id="ability2gainHP" onchange="ability2gainsHP(this)">
								<option value="no" >No</option>
								<option value="yes">Yes</option>
							</select>
	
							<div id="ability2gainsHP" style="display: none;">
								<label for="ability2gainHPNumber">How Many?:</label>
								<input type="number" name="ability2gainHPNumber" min="0" max="1000" required>
								<label for="ability2gainHPDuration">Turn duration:</label>
								<input type="number" name="ability2gainHPDuration" min="0" max="1000" required>
							</div>	
							<br>
						</div>
						
						<div class="container">
							<label for="ability2gainDD">Does this ability gain Destructible Defense?</label>
							<select name="ability2gainDD" id="ability2gainDD" onchange="ability2gainsDD(this)">
								<option value="no" >No</option>
								<option value="yes">Yes</option>
							</select>
	
							<div id="ability2gainsDD" style="display: none;">
								<label for="ability2gainDDNumber">How Many?:</label>
								<input type="number" name="ability2gainDDNumber" min="0" max="1000">
								<label for="ability2gainDDDuration">Turn duration:</label>
								<input type="number" name="ability2gainDDDuration" min="0" max="1000">
							</div>	
							<br>
						</div>
						
						<div class="container">
							<label for="ability2gainDR">Does this ability gain Damage Reduction?</label>
							<select name="ability2gainDR" id="ability2gainDR" onchange="ability2gainsDR(this)">
								<option value="no" >No</option>
								<option value="yes">Yes</option>
							</select>
	
							<div id="ability2gainsDR" style="display: none;">
								<label for="ability2gainDRNumber">How Many?:</label>
								<input type="number" name="ability2gainDRNumber" min="0" max="1000">
								<label for="ability2gainDRDuration">Turn duration:</label>
								<input type="number" name="ability2gainDRDuration" min="0" max="1000">
							</div>	
							<br>
						</div>
						
						<div class="container">
							<label for="ability2taijutsu">Taijutsu (minimum 0):</label>
							<input type="number" name="ability2taijutsu" min="0" max="100"><br>
							<label for="ability2heart">Heart (minimum 0):</label>
							<input type="number" name="ability2heart" min="0" max="100"><br>
							<label for="ability2energy">Energy (minimum 0):</label>
							<input type="number" name="ability2energy" min="0" max="100"><br>
							<label for="ability2spirit">Spirit (minimum 0):</label>
							<input type="number" name="ability2spirit" min="0" max="100"><br>
							<label for="ability2random">Random (minimum 0):</label>
							<input type="number" name="ability2random" min="0" max="100"><br>
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
								pattern="[A-Za-z]{1,16}" required 
							/>
						</div>
						<div class="container">
							<label for="ability3Description">
								<b>Ability3 Description</b>
							</label>
							<textarea placeholder="Write Ability 3 Description (max 5000chars)" name="ability3Description"
								style="min-height: 200px; width: 100%;" maxlength="5000" required
							></textarea>
						</div>
						
						<div class="container">
							<label for="ability3target">Who is the target of this ability?</label>
							<select name="ability3target" id="ability3target">
								<option value="self">Self</option>
								<option value="enemy">Enemy</option>
								<option value="ally">Ally</option>
							</select>
						</div>
						<div class="container">
							<label for="ability3damage">Does this ability do damage?</label>
							<select name="ability3damage" id="ability3damage" onchange="ability3DoesDamage(this)">
								<option value="no">No</option>
								<option value="yes">Yes</option>
							</select>
	
							<div id="ability3DoesDamage" style="display: none;">
								<label for="ability3damageNumber">Damage Value:</label>
								<input type="number" name="ability3damageNumber" min="0" max="1000">
								<label for="ability3damageDuration">Turn duration:</label>
								<input type="number" name="ability3damageDuration" min="0" max="1000">
							</div>	
							<br>
						</div>
						
						<div class="container">
							<label for="ability3increaseAbilityDamage">Ability damage increase per use (0 if none)</label>
							<input type="number" name="ability3taijutsu" min="0" max="1000">
							<br>
							<label for="ability3increasePermanentDamage">Permanent Character increase damage (0 if none)</label>
							<input type="number" name="ability3taijutsu" min="0" max="1000">
							<br>
							<label for="ability3stun">Stun Duration (0 if none)</label>
							<input type="number" name="ability3stun" min="0" max="1000">
							<br>
							<label for="ability3beInvul">How many turns this ability makes character invulnerable (0 if none)</label>
							<input type="number" name="ability3beInvul" min="0" max="1000">
							<br>
							<label for="ability3ignoreInvul">Does this ability ignore invulnerability?</label>
							<select name="ability3ignoreInvul" id="ability3ignoreInvul">
								<option value="no" >No</option>
								<option value="yes">Yes</option>
							</select>
							<br>
						</div>
				
						<div class="container">
							<label for="ability3removeNature">Does this ability remove Natures?</label>
							<select name="ability3removeNature" id="ability3removeNature" onchange="ability3removesNature(this)">
								<option value="no" >No</option>
								<option value="yes">Yes</option>
							</select>
	
							<div id="ability3removesNature" style="display: none;">
								<label for="ability3removesNatureNumber">How Many?:</label>
								<input type="number" name="ability3removesNatureNumber" min="0" max="1000">
								<label for="ability3removesNatureDuration">Turn duration:</label>
								<input type="number" name="ability3removesNatureDuration" min="0" max="1000">
							</div>	
							<br>
						</div>
						
						<div class="container">
							<label for="ability3gainNature">Does this ability gain Natures?</label>
							<select name="ability3gainNature" id="ability3gainNature" onchange="ability3gainsNature(this)">
								<option value="no" >No</option>
								<option value="yes">Yes</option>
							</select>
	
							<div id="ability3gainsNature" style="display: none;">
								<label for="ability3gainNatureNumber">How Many?:</label>
								<input type="number" name="ability3gainNatureNumber" min="0" max="1000">
								<label for="ability3gainNatureDuration">Turn duration:</label>
								<input type="number" name="ability3gainNatureDuration" min="0" max="1000">
							</div>	
							<br>
						</div>
						
						<div class="container">
							<label for="ability3gainHP">Does this ability gain Health?</label>
							<select name="ability3gainHP" id="ability3gainHP" onchange="ability3gainsHP(this)">
								<option value="no" >No</option>
								<option value="yes">Yes</option>
							</select>
	
							<div id="ability3gainsHP" style="display: none;">
								<label for="ability3gainHPNumber">How Many?:</label>
								<input type="number" name="ability3gainHPNumber" min="0" max="1000">
								<label for="ability3gainHPDuration">Turn duration:</label>
								<input type="number" name="ability3gainHPDuration" min="0" max="1000">
							</div>	
							<br>
						</div>
						
						<div class="container">
							<label for="ability3gainDD">Does this ability gain Destructible Defense?</label>
							<select name="ability3gainDD" id="ability3gainDD" onchange="ability3gainsDD(this)">
								<option value="no" >No</option>
								<option value="yes">Yes</option>
							</select>
	
							<div id="ability3gainsDD" style="display: none;">
								<label for="ability3gainDDNumber">How Many?:</label>
								<input type="number" name="ability3gainDDNumber" min="0" max="1000">
								<label for="ability3gainDDDuration">Turn duration:</label>
								<input type="number" name="ability3gainDDDuration" min="0" max="1000">
							</div>	
							<br>
						</div>
						
						<div class="container">
							<label for="ability3gainDR">Does this ability gain Damage Reduction?</label>
							<select name="ability3gainDR" id="ability3gainDR" onchange="ability3gainsDR(this)">
								<option value="no" >No</option>
								<option value="yes">Yes</option>
							</select>
	
							<div id="ability3gainsDR" style="display: none;">
								<label for="ability3gainDRNumber">How Many?:</label>
								<input type="number" name="ability3gainDRNumber" min="0" max="1000">
								<label for="ability3gainDRDuration">Turn duration:</label>
								<input type="number" name="ability3gainDRDuration" min="0" max="1000">
							</div>	
							<br>
						</div>
						
						
						<div class="container">
							<label for="ability3taijutsu">Taijutsu (minimum 0):</label>
							<input type="number" name="ability3taijutsu" min="0" max="100"><br>
							<label for="ability3heart">Heart (minimum 0):</label>
							<input type="number" name="ability3heart" min="0" max="100"><br>
							<label for="ability3energy">Energy (minimum 0):</label>
							<input type="number" name="ability3energy" min="0" max="100"><br>
							<label for="ability3spirit">Spirit (minimum 0):</label>
							<input type="number" name="ability3spirit" min="0" max="100"><br>
							<label for="ability3random">Random (minimum 0):</label>
							<input type="number" name="ability3random" min="0" max="100"><br>
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
								pattern="[A-Za-z]{1,16}" required
							/>
						</div>
						<div class="container">
							<label for="ability4Description">
								<b>Ability4 Description</b>
							</label>
							<textarea placeholder="Write Ability 4 Description (max 5000chars)" name="ability4Description"
								style="min-height: 200px; width: 100%;" maxlength="5000" required
							></textarea>
						</div>
						
						<div class="container">
							<label for="ability4target">Who is the target of this ability?</label>
							<select name="ability4target" id="ability4target">
								<option value="self">Self</option>
								<option value="enemy">Enemy</option>
								<option value="ally">Ally</option>
							</select>
						</div>
						<div class="container">
							<label for="ability4damage">Does this ability do damage?</label>
							<select name="ability4damage" id="ability4damage" onchange="ability4DoesDamage(this)">
								<option value="no">No</option>
								<option value="yes">Yes</option>
							</select>
	
							<div id="ability4DoesDamage" style="display: none;">
								<label for="ability4damageNumber">Damage Value:</label>
								<input type="number" name="ability4damageNumber" min="0" max="1000">
								<label for="ability4damageDuration">Turn duration:</label>
								<input type="number" name="ability4damageDuration" min="0" max="1000">
							</div>	
							<br>
						</div>
						
						<div class="container">
							<label for="ability4increaseAbilityDamage">Ability damage increase per use (0 if none)</label>
							<input type="number" name="ability4taijutsu" min="0" max="1000">
							<br>
							<label for="ability4increasePermanentDamage">Permanent Character increase damage (0 if none)</label>
							<input type="number" name="ability4taijutsu" min="0" max="1000">
							<br>
							<label for="ability4stun">Stun Duration (0 if none)</label>
							<input type="number" name="ability4stun" min="0" max="1000">
							<br>
							<label for="ability4beInvul">How many turns this ability makes character invulnerable (0 if none)</label>
							<input type="number" name="ability4beInvul" min="0" max="1000">
							<br>
							<label for="ability4ignoreInvul">Does this ability ignore invulnerability?</label>
							<select name="ability4ignoreInvul" id="ability4ignoreInvul">
								<option value="no" >No</option>
								<option value="yes">Yes</option>
							</select>
							<br>
						</div>
				
						<div class="container">
							<label for="ability4removeNature">Does this ability remove Natures?</label>
							<select name="ability4removeNature" id="ability4removeNature" onchange="ability4removesNature(this)">
								<option value="no" >No</option>
								<option value="yes">Yes</option>
							</select>
	
							<div id="ability4removesNature" style="display: none;">
								<label for="ability4removesNatureNumber">How Many?:</label>
								<input type="number" name="ability4removesNatureNumber" min="0" max="1000">
								<label for="ability4removesNatureDuration">Turn duration:</label>
								<input type="number" name="ability4removesNatureDuration" min="0" max="1000">
							</div>	
							<br>
						</div>
						
						<div class="container">
							<label for="ability4gainNature">Does this ability gain Natures?</label>
							<select name="ability4gainNature" id="ability4gainNature" onchange="ability4gainsNature(this)">
								<option value="no" >No</option>
								<option value="yes">Yes</option>
							</select>
	
							<div id="ability4gainsNature" style="display: none;">
								<label for="ability4gainNatureNumber">How Many?:</label>
								<input type="number" name="ability4gainNatureNumber" min="0" max="1000">
								<label for="ability4gainNatureDuration">Turn duration:</label>
								<input type="number" name="ability4gainNatureDuration" min="0" max="1000">
							</div>	
							<br>
						</div>
						
						<div class="container">
							<label for="ability4gainHP">Does this ability gain Health?</label>
							<select name="ability4gainHP" id="ability4gainHP" onchange="ability4gainsHP(this)">
								<option value="no" >No</option>
								<option value="yes">Yes</option>
							</select>
	
							<div id="ability4gainsHP" style="display: none;">
								<label for="ability4gainHPNumber">How Many?:</label>
								<input type="number" name="ability4gainHPNumber" min="0" max="1000">
								<label for="ability4gainHPDuration">Turn duration:</label>
								<input type="number" name="ability4gainHPDuration" min="0" max="1000">
							</div>	
							<br>
						</div>
						
						<div class="container">
							<label for="ability4gainDD">Does this ability gain Destructible Defense?</label>
							<select name="ability4gainDD" id="ability4gainDD" onchange="ability4gainsDD(this)">
								<option value="no" >No</option>
								<option value="yes">Yes</option>
							</select>
	
							<div id="ability4gainsDD" style="display: none;">
								<label for="ability4gainDDNumber">How Many?:</label>
								<input type="number" name="ability4gainDDNumber" min="0" max="1000">
								<label for="ability4gainDDDuration">Turn duration:</label>
								<input type="number" name="ability4gainDDDuration" min="0" max="1000">
							</div>	
							<br>
						</div>
						
						<div class="container">
							<label for="ability4gainDR">Does this ability gain Damage Reduction?</label>
							<select name="ability4gainDR" id="ability4gainDR" onchange="ability4gainsDR(this)">
								<option value="no" >No</option>
								<option value="yes">Yes</option>
							</select>
	
							<div id="ability4gainsDR" style="display: none;">
								<label for="ability4gainDRNumber">How Many?:</label>
								<input type="number" name="ability4gainDRNumber" min="0" max="1000">
								<label for="ability4gainDRDuration">Turn duration:</label>
								<input type="number" name="ability4gainDRDuration" min="0" max="1000">
							</div>	
							<br>
						</div>
						
						
						<div class="container">
							<label for="ability4taijutsu">Taijutsu (minimum 0):</label>
							<input type="number" name="ability4taijutsu" min="0" max="100"><br>
							<label for="ability4heart">Heart (minimum 0):</label>
							<input type="number" name="ability4heart" min="0" max="100"><br>
							<label for="ability4energy">Energy (minimum 0):</label>
							<input type="number" name="ability4energy" min="0" max="100"><br>
							<label for="ability4spirit">Spirit (minimum 0):</label>
							<input type="number" name="ability4spirit" min="0" max="100"><br>
							<label for="ability4random">Random (minimum 0):</label>
							<input type="number" name="ability4random" min="0" max="100"><br>
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
								<input type="text" placeholder="Mission Name" name="missionName"/>
							</div>
							<div class="container">
								<label for="missionDescription">
									<b>Mission Description</b>
								</label>
								<textarea placeholder="Write Mission Description (max 5000chars)" name="missionDescription"
									style="min-height: 200px; width: 100%;" 
								></textarea>
							</div>
							
							<div class="container">
								<label for="minLevel">Required Level (minimum 1):</label>
								<input type="number" name="minLevel" min="1" max="1000">
							</div>	
							
							<div class="container">
								<label for="missionImage">Mission Picture</label>
								<input type="file" accept="image/*" name="missionImage" />
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
	
    if (m.value=="Mission") {
        document.getElementById("showMission").style.display="block";
    }
    else if (m.value=="Default") {
        document.getElementById("showMission").style.display="none";
    }
}

function ability1DoesDamage(m) {
	
	if (m.value=="yes") {
		document.getElementById("ability1DoesDamage").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability1DoesDamage").style.display="none";
	}
}
function ability1removesNature(m) {

	if (m.value=="yes") {
		document.getElementById("ability1removesNature").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability1removesNature").style.display="none";
	}
}

function ability1gainsNature(m) {

	if (m.value=="yes") {
		document.getElementById("ability1gainsNature").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability1gainsNature").style.display="none";
	}
}

function ability1gainsHP(m) {

	if (m.value=="yes") {
		document.getElementById("ability1gainsHP").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability1gainsHP").style.display="none";
	}
}
function ability1gainsDD(m) {

	if (m.value=="yes") {
		document.getElementById("ability1gainsDD").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability1gainsDD").style.display="none";
	}
}

function ability1gainsDR(m) {

	if (m.value=="yes") {
		document.getElementById("ability1gainsDR").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability1gainsDR").style.display="none";
	}
}
//2
function ability2DoesDamage(m) {
	
	if (m.value=="yes") {
		document.getElementById("ability2DoesDamage").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability2DoesDamage").style.display="none";
	}
}
function ability2removesNature(m) {

	if (m.value=="yes") {
		document.getElementById("ability2removesNature").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability2removesNature").style.display="none";
	}
}

function ability2gainsNature(m) {

	if (m.value=="yes") {
		document.getElementById("ability2gainsNature").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability2gainsNature").style.display="none";
	}
}

function ability2gainsHP(m) {

	if (m.value=="yes") {
		document.getElementById("ability2gainsHP").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability2gainsHP").style.display="none";
	}
}
function ability2gainsDD(m) {

	if (m.value=="yes") {
		document.getElementById("ability2gainsDD").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability2gainsDD").style.display="none";
	}
}

function ability2gainsDR(m) {

	if (m.value=="yes") {
		document.getElementById("ability2gainsDR").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability2gainsDR").style.display="none";
	}
}

//3
function ability3DoesDamage(m) {
	
	if (m.value=="yes") {
		document.getElementById("ability3DoesDamage").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability3DoesDamage").style.display="none";
	}
}
function ability3removesNature(m) {

	if (m.value=="yes") {
		document.getElementById("ability3removesNature").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability3removesNature").style.display="none";
	}
}

function ability3gainsNature(m) {

	if (m.value=="yes") {
		document.getElementById("ability3gainsNature").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability3gainsNature").style.display="none";
	}
}

function ability3gainsHP(m) {

	if (m.value=="yes") {
		document.getElementById("ability3gainsHP").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability3gainsHP").style.display="none";
	}
}
function ability3gainsDD(m) {

	if (m.value=="yes") {
		document.getElementById("ability3gainsDD").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability3gainsDD").style.display="none";
	}
}

function ability3gainsDR(m) {

	if (m.value=="yes") {
		document.getElementById("ability3gainsDR").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability3gainsDR").style.display="none";
	}
}

//4
function ability4DoesDamage(m) {
	
	if (m.value=="yes") {
		document.getElementById("ability4DoesDamage").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability4DoesDamage").style.display="none";
	}
}
function ability4removesNature(m) {

	if (m.value=="yes") {
		document.getElementById("ability4removesNature").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability4removesNature").style.display="none";
	}
}

function ability4gainsNature(m) {

	if (m.value=="yes") {
		document.getElementById("ability4gainsNature").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability4gainsNature").style.display="none";
	}
}

function ability4gainsHP(m) {

	if (m.value=="yes") {
		document.getElementById("ability4gainsHP").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability4gainsHP").style.display="none";
	}
}
function ability4gainsDD(m) {

	if (m.value=="yes") {
		document.getElementById("ability4gainsDD").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability4gainsDD").style.display="none";
	}
}

function ability4gainsDR(m) {

	if (m.value=="yes") {
		document.getElementById("ability4gainsDR").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability4gainsDR").style.display="none";
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