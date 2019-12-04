<!DOCTYPE html>
<html lang="en">

<head>
    <%
        String context = request.getContextPath();
    %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="s" uri="/struts-tags" %>

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

    <link href="<%=context%>/css/sb-admin-2.css" rel="stylesheet">
    <%--    <link href="<%=context%>/css/sb-admin-2.min.css" rel="stylesheet">--%>

</head>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

        <!-- Sidebar - Brand -->
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="<%=context%>/vista/RD/InicioRD.jsp">
            <div class="sidebar-brand-icon rotate-n-15">
                <i class="fas fa-user-tie"></i>
            </div>
            <div class="sidebar-brand-text mx-3">Responsable de Desarrollo</div>
        </a>

        <!-- Heading -->
        <div class="sidebar-heading">
            Proyectos
        </div>

        <!-- Nav Item - Charts -->
        <li class="nav-item">
            <a class="nav-link" href="<%=context%>/vista/RD/InicioRD.jsp">
                <i class="fas fa-fw fa-chart-area"></i>
                <span>Proyectos</span></a>
        </li>

        <!-- Divider -->
        <hr class="sidebar-divider d-none d-md-block">

        <!-- Heading -->
        <div class="sidebar-heading">
            Cursos
        </div>

        <!-- Nav Item - Charts -->
        <li class="nav-item">
            <a class="nav-link" href="<%=context%>/vista/RD/CursosRD.jsp">
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
                                </i><s:property value="#session.usuario.nombre"/></span>
                        </a>

                        <!-- Dropdown - User Information -->
                        <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
                            <a class="dropdown-item" href="#">
                                <form action="buscarPerfilRD" method="POST">
                                    <input type="hidden" name="bean.idPersona"
                                           value="<s:property value="#session.usuario.idPersona"/>"/>

                                    <button type="submit" value="" class="btn">
                                        <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> Perfil
                                    </button>
                                </form>
                            </a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="<%=context%>/cerrarSesion" >
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
                        <h1 class="h3 mb-4 text-gray-800">Modificar Curso</h1>
                    </div>
                </div>


                <form class="user"  id="formCursoModificar">
                    <div class="form-group row">
                        <div class="col-sm-6 mb-3 mb-sm-0">
                            <input type="text" value="<s:property value="response.bean.nombre"/>" required class="form-control form-control-user" id="nombre" placeholder="Nombre" />
                        </div>
                        <div class="col-sm-6">
                            <input type="date" value="<s:property value="response.bean.fecha"/>"  required class="form-control form-control-user" id="fecha" placeholder="Fecha" />
                        </div>
                    </div>
                    <div class="form-group">
                        <input type="text" value="<s:property value="response.bean.descripcion"/>" required class="form-control form-control-user" id="descripcion" placeholder="Descripcion" />
                    </div>
                    <div class="form-group row">
                        <div class="col-md-3">
                            <select id="tipo" value="Taller" required class="form-control ">
                                <option selected disabled>Seleccione una opcion</option>
                                <s:if test="%{response.bean.tipoCurso == 1}">
                                    <option value="1" selected>Curso</option>
                                    <option value="2">Taller</option>
                                    <option value="3">Certificacion</option>
                                    <option value="4">Capacitacion</option>
                                </s:if>
                                <s:elseif test="%{response.bean.tipoCurso == 2}">
                                    <option value="1" >Curso</option>
                                    <option value="2" selected>Taller</option>
                                    <option value="3">Certificacion</option>
                                    <option value="4">Capacitacion</option>
                                </s:elseif>
                                <s:elseif test="%{response.bean.tipoCurso == 3}">
                                    <option value="1" >Curso</option>
                                    <option value="2">Taller</option>
                                    <option value="3" selected>Certificacion</option>
                                    <option value="4">Capacitacion</option>
                                </s:elseif>
                                <s:elseif test="%{response.bean.tipoCurso == 4}">
                                    <option value="1">Curso</option>
                                    <option value="2">Taller</option>
                                    <option value="3">Certificacion</option>
                                    <option value="4" selected>Capacitacion</option>
                                </s:elseif>

                            </select>

                        </div>
                        <input type="hidden" id="idCurso" value="<s:property value="response.bean.idCurso"/>"/>
                    </div>
                    <button type="button" class="btn btn-primary btn-user btn-block" onclick="modificarCurso();">
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

<script src="<%=context%>/js/cursos/cursosJS.js"></script>

</body>

</html>
