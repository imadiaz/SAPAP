<!DOCTYPE html>
<html lang="en">

<head>
    <%
        String context = request.getContextPath();
    %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SAPAP</title>

    <!-- Custom fonts for this template-->
    <link href="<%=context%>/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>

    <!-- Custom styles for this template-->
    <link href="<%=context%>/css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

        <!-- Sidebar - Brand -->
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="InicioRH.jsp">
            <div class="sidebar-brand-icon rotate-n-15">
                <i class="fas fa-pen"></i>
            </div>
            <div class="sidebar-brand-text mx-3">Recursos Humanos</div>
        </a>

        <!-- Heading -->
        <div class="sidebar-heading">
            Justificantes
        </div>

        <!-- Nav Item - Pages Collapse Menu -->
        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">
                <i class="fas fa-fw fa-paper-plane"></i>
                <span>Lista de Justificantes</span>
            </a>
            <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <h6 class="collapse-header">Justificantes:</h6>
                    <a class="collapse-item" href="InicioRH.jsp">Gestionar justificantes</a>
                    <a class="collapse-item" href="HistorialJustificantes.jsp">Historial</a>
                </div>
            </div>
        </li>

        <!-- Divider -->
        <hr class="sidebar-divider">

        <!-- Heading -->
        <div class="sidebar-heading">
            Empleados
        </div>

        <!-- Nav Item - Charts -->
        <li class="nav-item">
            <a class="nav-link" href="EmpleadosRH.jsp">
                <i class="fas fa-fw fa-user-cog"></i>
                <span>Empleados</span></a>
        </li>

        <!-- Divider -->
        <hr class="sidebar-divider">

        <!-- Heading -->
        <div class="sidebar-heading">
            Proyectos
        </div>

        <!-- Nav Item - Charts -->
        <li class="nav-item">
            <a class="nav-link" href="ProyectosRH.jsp">
                <i class="fas fa-fw fa-chart-area"></i>
                <span>Proyectos</span></a>
        </li>

        <!-- Heading -->
        <div class="sidebar-heading">
            Cursos
        </div>

        <!-- Nav Item - Charts -->
        <li class="nav-item">
            <a class="nav-link" href="CursosRH.jsp">
                <i class="fas fa-fw fa-tablet-alt"></i>
                <span>Cursos</span></a>
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

                <!-- Topbar Search -->
                <form class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                    <div class="input-group">
                        <input type="text" class="form-control bg-light border-0 small" placeholder="Buscar..." aria-label="Search" aria-describedby="basic-addon2">
                        <div class="input-group-append">
                            <button class="btn btn-primary" type="button">
                                <i class="fas fa-search fa-sm"></i>
                            </button>
                        </div>
                    </div>
                </form>

                <!-- Topbar Navbar -->
                <ul class="navbar-nav ml-auto">

                    <!-- Nav Item - Alerts -->
                    <li class="nav-item dropdown no-arrow mx-1">
                        <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-list-ul fa-fw" style="margin-right: 20px">Roles</i>
                        </a>
                    </li>

                    <div class="topbar-divider d-none d-sm-block"></div>

                    <!-- Nav Item - User Information -->
                    <li class="nav-item dropdown no-arrow">
                        <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="mr-2 d-none d-lg-inline text-gray-600 small"><i class="fas fa-user fa-fw" style="margin-right: 20px">
                                </i>Nombre</span>
                        </a>

                        <!-- Dropdown - User Information -->
                        <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
                            <a class="dropdown-item" href="MiPerfilRH.jsp">
                                <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                Perfil
                            </a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="<%=context%>/index.jsp" >
                                <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                Salir
                            </a>
                        </div>
                    </li>

                </ul>

            </nav>
            <!-- End of Topbar -->

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <div class="row">
                    <div class="col-md-4">
                        <h1 class="h3 mb-4 text-gray-800">Agregar Curso</h1>
                    </div>
                </div>


                <form class="user"  id="formCurso">
                    <div class="form-group row">
                        <div class="col-sm-6 mb-3 mb-sm-0">
                            <input type="text"  required class="form-control form-control-user" id="nombre" placeholder="Nombre">
                        </div>
                        <div class="col-sm-6">
                            <input type="date"  required class="form-control form-control-user" id="fecha" placeholder="Fecha">
                        </div>
                    </div>
                    <div class="form-group">
                        <input type="text" required class="form-control form-control-user" id="descripcion" placeholder="Descripcion">
                    </div>
                    <div class="form-group row">
                        <div class="col-md-3">
                            <select id="tipo" required class="form-control ">
                                <option selected disabled>Seleccione una opcion</option>
                                <option value="1">Curso</option>
                                <option value="2">Taller</option>
                                <option value="3">Certificacion</option>
                                <option value="4">Capacitacion</option>
                            </select>
                        </div>
                        <div class="col-sm-9">
                            <input type="file" class="form-control" id="evidencia" placeholder="Evidencia">

                        </div>
                    </div>
                    <button type="button" class="btn btn-primary btn-user btn-block" onclick="guardarCurso();">
                        Guardar
                    </button>
                </form>






            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- End of Main Content -->

        <!-- Footer -->
        <footer class="sticky-footer bg-white">
            <div class="container my-auto">
                <div class="copyright text-center my-auto">
                    <span>Copyright &copy; Centro de Desarrollo de Software</span>
                </div>
            </div>
        </footer>
        <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<!-- Bootstrap core JavaScript-->
<script src="<%=context%>/vendor/jquery/jquery.min.js"></script>
<script src="<%=context%>/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="<%=context%>/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="<%=context%>/js/sb-admin-2.min.js"></script>

<!-- The core Firebase JS SDK is always required and must be listed first -->
<script src="https://www.gstatic.com/firebasejs/7.3.0/firebase-app.js"></script>

<!-- TODO: Add SDKs for Firebase products that you want to use
     https://firebase.google.com/docs/web/setup#available-libraries -->
<script src="https://www.gstatic.com/firebasejs/7.3.0/firebase-analytics.js"></script>
<script src="https://www.gstatic.com/firebasejs/7.3.0/firebase-database.js"></script>
<script src="https://www.gstatic.com/firebasejs/7.3.0/firebase-storage.js"></script>
<script src="<%=context%>/js/cursos/cursosJS.js"></script>
<script>
    ejecutarEvento();
</script>

</body>

</html>