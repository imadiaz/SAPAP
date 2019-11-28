<!DOCTYPE html>
<html lang="en">

<head>
    <%
        String context = request.getContextPath();
    %>
    <%@taglib prefix="s" uri="/struts-tags" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SAPAP</title>

    <!-- Custom fonts for this template-->
    <link href="<%=context%>/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
    <link href="<%=context%>/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
    <!-- Custom styles for this template-->
    <link href="<%=context%>/css/sb-admin-2.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>

</head>

<body id="page-top" onload="consultaRegistro()">

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
                    <li class="nav-item dropdown no-arrow">
                        <a class="nav-link dropdown-toggle" href="#" id="rolesDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                               <span class="mr-2 d-none d-lg-inline text-gray-600 small">
                                   <i class="fas fa-user fa-fw" style="margin-right: 20px">
                                </i>Roles</span>
                        </a>

                        <!-- Dropdown - User Information -->
                        <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                             aria-labelledby="rolesDropdown">
                            <a class="dropdown-item" href="MiPerfilAP.jsp">
                                <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> Perfil
                            </a>
                            <div class="dropdown-divider"></div>
                            <s:iterator value="#session.roles" var="rol">


                                <s:if test="#rol.tipo=='Estadia'">
                                    <a class="dropdown-item" href="<%=context%>/vista/AP/InicioAP.jsp">

                                        <i class="fas fa-circle fa-sm fa-fw mr-2 text-gray-400">

                                        </i>
                                        <s:property value="tipo"/>
                                    </a>

                                </s:if>
                                <s:elseif test="#rol.tipo=='Administradora de Recursos Humanos'">

                                    <a class="dropdown-item" href="<%=context%>/vista/RH/InicioRH.jsp">

                                        <i class="fas fa-circle fa-sm fa-fw mr-2 text-gray-400">

                                        </i>
                                        <s:property value="tipo"/>
                                    </a>
                                </s:elseif>
                                <s:elseif test="#rol.tipo=='Responsable de Desarrollo'">


                                    <a class="dropdown-item" href="<%=context%>/vista/RD/InicioRD.jsp">

                                        <i class="fas fa-circle fa-sm fa-fw mr-2 text-gray-400">

                                        </i>
                                        <s:property value="tipo"/>
                                    </a>
                                </s:elseif>
                                <s:elseif test="#rol.tipo=='RAPE'">

                                    <a class="dropdown-item" href="<%=context%>/vista/RAPE/InicioRAPE.jsp">

                                        <i class="fas fa-circle fa-sm fa-fw mr-2 text-gray-400">

                                        </i>
                                        <s:property value="tipo"/>
                                    </a>
                                </s:elseif>
                                <s:elseif test="#rol.tipo=='Coordinador del CDS'">


                                    <a class="dropdown-item" href="<%=context%>/vista/Coordinador/InicioCOD.jsp">

                                        <i class="fas fa-circle fa-sm fa-fw mr-2 text-gray-400">

                                        </i>
                                        <s:property value="tipo"/>
                                    </a>
                                </s:elseif>
                                <s:elseif test="#rol.tipo=='Analista Programador'">

                                    <a class="dropdown-item" href="<%=context%>/vista/AP/InicioAP.jsp">

                                        <i class="fas fa-circle fa-sm fa-fw mr-2 text-gray-400">

                                        </i>
                                        <s:property value="tipo"/>
                                    </a>
                                </s:elseif>


                                </a>
                            </s:iterator>
                        </div>
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

                <!-- Page Heading -->
                <h1 class="h3 mb-4 text-gray-800">Agregar Empleado</h1>


                <form class="user" onsubmit="registroPersona()">
                    <div class="form-group row">
                        <div class="col-sm-4 mb-3 mb-sm-0">
                            <label for="nombre">Nombre</label>
                            <input type="text" class="form-control form-control-user" id="nombre" required placeholder="Nombre">
                        </div>
                        <div class="col-sm-4">
                            <label for="primerApellido">Primer Apellido</label>
                            <input type="text" class="form-control form-control-user" id="primerApellido" required placeholder="Primer Apellido">
                        </div>
                        <div class="col-sm-4">
                            <label for="segundoApellido">Segundo Apellido</label>
                            <input type="text" class="form-control form-control-user" id="segundoApellido" placeholder="Segundo Apellido">
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-4 mb-3 mb-sm-0">
                            <label for="matricula">Matricula</label>
                            <input onkeyup="modificar()" type="text" class="form-control form-control-user" id="matricula" placeholder="Matricula">
                        </div>
                        <div class="col-sm-4">
                            <label for="correoPersonal">Correo Personal</label>
                            <input type="text" class="form-control form-control-user" id="correoPersonal" placeholder="Correo Personal">
                        </div>
                        <div class="col-sm-4">
                            <label for="correoInstitucional">Correo Institucional</label>
                            <div class="input-group mb-2">

                                <input disabled type="text" class="form-control form-control-user" id="correoInstitucional" placeholder="Correo Institucional">
                                <div class="input-group-prepend">
                                    <div class="input-group-text">@utez.edu.mx</div>
                                </div>

                            </div>
                        </div>
                    </div>



                    <div class="form-group row">
                        <div class="col-sm-4 mb-3 mb-sm-0">
                            <label for="fechaDeNacimiento">Fecha de Nacimiento</label>
                            <input max="2019-11-19" type="date" class="form-control form-control-user" id="fechaDeNacimiento" >
                        </div>
                        <div class="col-sm-4">
                            <label for="numeroTelefonico" >Número Telefónico</label>
                            <input type="number" pattern="[0-9]" class="form-control form-control-user" id="numeroTelefonico" placeholder="Número Telefónico">

                        </div>
                        <div class="col-sm-4">
                            <label for="numeroPersonal">Número De Casa</label>
                            <div class="input-group mb-2">

                                <input type="number"  pattern="[0-9]" class="form-control form-control-user" id="numeroPersonal" placeholder="Número Personal">

                            </div>
                        </div>
                    </div>

                    <div class="form-group row">
                        <div class="col-sm-4 mb-3 mb-sm-0">
                            <label for="carreraDeEgreso">Carrera de Egreso</label>
                            <select id="carreraDeEgreso" class="form-control form-control-file">
                                <option>DATIC</option>
                                <option>DAMI</option>
                                <option>DACEA</option>
                                <option>DATEFI</option>
                            </select>
                        </div>
                        <div class="col-sm-4">
                            <label for="universidadDeEgreso" >Universidad de Egreso</label>
                            <input type="text"  class="form-control form-control-user" id="universidadDeEgreso" placeholder="Universidad de Egreso">

                        </div>
                        <div class="col-sm-4">
                            <label for="fechaDeIngreso">Fecha de Ingreso</label>
                            <div class="input-group mb-2">

                                <input type="date"   class="form-control form-control-user" id="fechaDeIngreso" placeholder="fechaDeIngreso">

                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" id="gridCheck" onchange="esEstudiante()">
                            <label class="form-check-label" for="gridCheck">
                               ¿Es Estudiante?
                            </label>
                        </div>
                    </div>

                    <div id="estudiante" class="form-group row">

                    </div>

                    <div id="becarioDiv" class="form-group row">
                        <div class="col-sm-4" id="becarioDivDiv">
                        </div>
                    </div>


                    <div class="form-group row">
                        <div class="col-sm-4 mb-3 mb-sm-0">

                                <label for="direccion">Direccion</label>
                                <input type="text"   class="form-control form-control-user" id="direccion" placeholder="Direccion">



                        </div>
                        <div class="col-sm-4">
                            <label for="horario">Horario</label>
                            <select id="select" id="horario" class="form-control form-control-file">

                            </select>
                        </div>
                        <div class="col-sm-4">
                            <label for="contrasena">Contraseña</label>
                            <div class="input-group mb-2">

                                <input type="password"   class="form-control form-control-user" id="contrasena" placeholder="Contraseña">

                            </div>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-4 mb-3 mb-sm-0">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th scope="col">Rol</th>
                                    <th scope="col">Seleccionar</th>
                                </tr>
                                </thead>
                                <tbody id="tbody">


                                </tbody>
                            </table>

                        </div>

                    </div>

                    <button type="submit" class="btn btn-primary btn-user btn-block" >Enviar</button>
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

</body>
<!--Nuestros recursos-->
<script src="<%=context%>/js/persona/personaJS.js"></script>
<script src="<%=context%>/js/sb-admin-2.min.js"></script>
<script src="<%=context%>/js/cursos/cursosJS.js"></script>
<script src="<%=context%>/vendor/datatables/jquery.dataTables.min.js"></script>
<script src="<%=context%>/vendor/datatables/dataTables.bootstrap4.js"></script>
<script src="<%=context%>/js/demo/datatables-demo.js"></script>
<script>

    function modificar() {
        var matricula2;
        matricula2=document.getElementById("matricula").value;
        document.getElementById("correoInstitucional").value=matricula2;
    }
</script>
</html>
