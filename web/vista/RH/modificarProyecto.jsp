<!DOCTYPE html>
<html lang="en">
<%@taglib prefix="s" uri="/struts-tags" %>
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
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>

    <!-- Custom styles for this template-->
    <link href="<%=context%>/css/sb-admin-2.css" rel="stylesheet">
    <%--    <link href="<%=context%>/css/sb-admin-2.min.css" rel="stylesheet">--%>
    <link href="<%=context%>/css/bootstrap-select.min.css" rel="stylesheet">

</head>

<body id="page-top" onload="d()">

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
                                </i><s:property value="#session.usuario.nombre"/></span>
                        </a>

                        <!-- Dropdown - User Information -->
                        <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
                            <a class="dropdown-item" href="MiPerfilRH.jsp">
                                <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                Perfil
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
                        <h1 class="h3 mb-4 text-gray-800">Modificar Proyecto</h1>
                    </div>
                </div>


                <form class="user" >

                    <div class="form-group row">
                        <div class="col-sm-4 mb-3 mb-sm-0">
                            <label for="nombre">Identificador</label>
                            <input type="text" class="form-control form-control-user" id="identificador" required placeholder="Identificador" value="<s:property value="beanProyecto.identificador"/>">
                        </div>
                        <div class="col-sm-4">
                            <label for="nombre">Nombre del Proyecto</label>
                            <input type="text" class="form-control form-control-user" id="nombre" required placeholder="Nombre del Proyecto" value="<s:property value="beanProyecto.nombre"/>">
                        </div>
                        <div class="col-sm-4">
                            <label for="nombreCliente">Nombre del Cliente</label>
                            <input type="text" class="form-control form-control-user" id="nombreCliente" placeholder="Nombre del Cliente" value="<s:property value="beanProyecto.nombreDelCliente"/>">
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-4 mb-3 mb-sm-0">
                            <label for="correoE">Correo Electronico del Cliente</label>
                            <input type="text" class="form-control form-control-user" id="correoE" value="<s:property value="beanProyecto.correoDelCliente"/>" required placeholder="Correo Electronico del Cliente">
                        </div>
                        <div class="col-sm-4">
                            <label for="numeroTelefonico">Número Telefónico</label>
                            <input type="number" class="form-control form-control-user" id="numeroTelefonico" value="<s:property value="beanProyecto.numTelefonico"/>" required placeholder="Número Telefónico">
                        </div>
                        <div class="col-sm-4">
                            <label for="direccionDelCliente">Dirección del Cliente</label>
                            <input type="text" class="form-control form-control-user" id="direccionDelCliente" value="<s:property value="beanProyecto.direccionCliente"/>" placeholder="Dirección del Cliente">
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-4 mb-3 mb-sm-0">
                            <label for="fechaInicio">Fecha Inicio</label>
                            <input type="date" class="form-control form-control-user" id="fechaInicio" value="<s:property value="beanProyecto.fechaInicio"/>" required placeholder="Fecha Inicio">
                        </div>
                        <div class="col-sm-4">
                            <label for="fechaFin">Fecha Fin</label>
                            <input type="date" class="form-control form-control-user" value="<s:property value="beanProyecto.fechaFin"/>" id="fechaFin" required placeholder="Fecha Fin">
                        </div>
                        <div class="col-sm-4">
                            <label for="descripcion">Descripción</label>
                            <input type="text" class="form-control form-control-user" id="descripcion" value="<s:property value="beanProyecto.descripcion"/>" placeholder="Descripción">
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-4 mb-3 mb-sm-0">
                            <label for="nombre">Costo</label>
                            <input type="number" class="form-control form-control-user" id="costo" value="<s:property value="beanProyecto.costo"/>" required placeholder="Costo">
                        </div>

                    </div>
                    <input type="hidden" id="idProyecto" name="idProyecto" value="<s:property value="beanProyecto.idProyecto"/>">
                    <div class="form-group row">
                        <div class="col-sm-4 mb-3 mb-sm-0">
                            <label for="rapeSelect">RAPE</label>
                            <select title="Seleccione uno.." name="rape" id="rapeSelect" class="form-control form-control-file selectpicker" multiple>
                                <s:iterator value="rapes" status="stat" var="lista">
                                    <option value="<s:property value="idPersona"></s:property>"><s:property value="nombre"></s:property> <s:property value="primerApellido"></s:property> </option>
                                </s:iterator>
                            </select>
                        </div>
                        <div class="col-sm-4">
                            <label for="rdSelect">RD</label>
                            <select title="Seleccione uno.." name="rd" id="rdSelect" class="form-control form-control-file selectpicker" multiple>
                                <s:iterator value="rds" status="stat" var="lista">
                                    <option value="<s:property value="idPersona"></s:property>"><s:property value="nombre"></s:property> <s:property value="primerApellido"></s:property></option>
                                </s:iterator>

                            </select>

                        </div>
                        <div class="col-sm-4">
                            <label for="apSelect">AP</label>
                            <select title="Seleccione uno.." name="ap" id="apSelect" class="form-control form-control-file selectpicker" multiple>

                                <s:iterator value="aps" status="stat" var="lista">
                                    <option value="<s:property value="idPersona"></s:property>"><s:property value="nombre"></s:property> <s:property value="primerApellido"></s:property> </option>
                                </s:iterator>
                            </select>

                        </div>
                    </div>

                </form>


                <button class="btn btn-primary btn-user btn-block" onclick="modificarProyecto()">Enviar</button>



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
<script src="<%=context%>/js/Proyecto/proyectosJS.js"></script>
<script src="<%=context%>/js/select/bootstrap-select.min.js"></script>

<script>



    function  d() {

        var aps=JSON.parse('<s:property value="respuestas.aps" />');
        var rds=JSON.parse('<s:property value="respuestas.rds" />');
        var rapes=JSON.parse('<s:property value="respuestas.rapesitos" />');




        $("#rapeSelect").selectpicker('val',rapes);
        $("#apSelect").selectpicker('val',aps);
        $("#rdSelect").selectpicker('val',rds);

    }
</script>
</body>

</html>