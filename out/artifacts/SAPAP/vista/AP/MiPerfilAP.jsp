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
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
          rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="<%=context%>/css/sb-admin-2.css" rel="stylesheet">
    <%--    <link href="<%=context%>/css/sb-admin-2.min.css" rel="stylesheet">--%>
</head>
<style>
    .ver{
        display: none;
        visibility: hidden;
    }
</style>
<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

        <!-- Sidebar - Brand -->
        <a class="sidebar-brand d-flex align-items-center justify-content-center"
           href="<%=context%>/vista/AP/InicioAP.jsp">
            <div class="sidebar-brand-icon rotate-n-15">
                <i class="fas fa-code"></i>
            </div>
            <div class="sidebar-brand-text mx-3">Analista Programador</div>
        </a>

        <!-- Heading -->
        <div class="sidebar-heading">
            Justificantes
        </div>

        <!-- Nav Item - Pages Collapse Menu -->
        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
               aria-expanded="true" aria-controls="collapseTwo">
                <i class="fas fa-fw fa-paper-plane"></i>
                <span>Lista de Justificantes</span>
            </a>
            <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <h6 class="collapse-header">Justificantes:</h6>
                    <a class="collapse-item" href="<%=context%>/vista/AP/InicioAP.jsp">Justificantes pendientes</a>
                    <a class="collapse-item" href="<%=context%>/vista/AP/HistorialJustificantesAP.jsp">Historial</a>
                </div>
            </div>
        </li>

        <!-- Divider -->
        <hr class="sidebar-divider">

        <!-- Heading -->
        <div class="sidebar-heading">
            Proyectos
        </div>

        <!-- Nav Item - Charts -->
        <li class="nav-item">
            <a class="nav-link" href="<%=context%>/vista/AP/ProyectosAP.jsp">
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
            <a class="nav-link" href="<%=context%>/vista/AP/CursosAP.jsp">
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
                        <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="mr-2 d-none d-lg-inline text-gray-600 small"><i class="fas fa-user fa-fw"
                                                                                         style="margin-right: 20px">
                                </i><s:property value="#session.usuario.nombre"/></span>
                        </a>


                        <!-- Dropdown - User Information -->
                        <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                             aria-labelledby="userDropdown">
                            <a class="dropdown-item" href="#">


                                <form action="buscarPerfil" method="POST">
                                    <input type="hidden" name="bean.idPersona"
                                           value="<s:property value="#session.usuario.idPersona"/>"/>

                                    <button type="submit" value="" class="btn">
                                        <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> Perfil
                                    </button>
                                </form>
                            </a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="<%=context%>/cerrarSesion">
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
                        <h1 class="h3 mb-4 text-gray-800">Mi perfil</h1>
                    </div>
                    <div class="col-md-8">
                        <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"
                           style="margin-left: 500px">
                            <i class="fas fa-paperclip fa-sm text-white-50"></i>Ver CV</a>
                    </div>
                </div>

                <form class="user" action="modificarAP" method="POST">
                    <div class="form-group row">

                        <input type="hidden" class="form-control form-control-user" name="bean.idPersona"
                               value="<s:property value="#session.usuario.idPersona" />" id="id" placeholder="Nombre">
                        <div class="col-sm-3 mb-3 mb-sm-0">
                            <label for="matricula">Matricula</label>
                            <input  type="text" class="form-control form-control-user" name="bean.matricula"
                                   value="<s:property value="bean.matricula" />" id="matricula" placeholder="Matrícula">
                        </div>
                        <div class="col-sm-3 mb-3 mb-sm-0">
                            <label for="nombre">Nombre</label>
                            <input maxlength="50" type="text" class="form-control form-control-user" name="bean.nombre"
                                   value="<s:property value="bean.nombre" />" id="nombre" placeholder="Nombre">
                        </div>
                        <div class="col-sm-3 mb-3 mb-sm-0">
                            <label for="pApellido">Apellido Paterno</label>
                            <input maxlength="50" type="text" class="form-control form-control-user"
                                   name="bean.primerApellido"
                                   value="<s:property value="bean.primerApellido" />" id="pApellido"
                                   placeholder="Apellido Paterno">
                        </div>
                        <div class="col-sm-3 mb-3 mb-sm-0">
                            <label for="sApellido">Apellido Materno</label>
                            <input maxlength="50" type="text" class="form-control form-control-user"
                                   name="bean.segundoApellido"
                                   value="<s:property value="bean.segundoApellido" />" id="sApellido"
                                   placeholder="Apellido Materno">
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-6 mb-3 mb-sm-0">
                            <label for="correoInsti">Correo Institucional</label>
                            <input  type="email" class="form-control form-control-user"
                                   name="bean.correoInstitucional"
                                   id="correoInsti" value="<s:property value="bean.correoInstitucional" />"
                                   placeholder="Correo electrónico Institucional">
                        </div>
                        <div class="col-sm-6 mb-3 mb-sm-0">
                            <label for="correoPers">Correo Personal</label>
                            <input type="email" class="form-control form-control-user" name="bean.correoPersonal"
                                   value="<s:property value="bean.correoPersonal" />" id="correoPers"
                                   placeholder="Correo electrónico Personal">
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-3 mb-3 mb-sm-0">
                            <label for="fechaNac">Fecha de Nacimiento</label>
                            <input  type="date" class="form-control form-control-user"
                                   name="bean.fechaDeNacimiento"
                                   value="<s:property value="bean.fechaDeNacimiento" />" id="fechaNac"
                                   placeholder="Fecha de Nacimiento">
                        </div>
                        <div class="col-sm-3 mb-3 mb-sm-0">
                            <label for="celular">Celular</label>
                            <input onkeypress='validate(event)' type="text" maxlength="10" max="10"
                                   class="form-control form-control-user" name="bean.numeroTelefonico"
                                   value="<s:property value="bean.numeroTelefonico" />" id="celular"
                                   placeholder="Celular">
                        </div>
                        <div class="col-sm-3 mb-3 mb-sm-0">
                            <label for="telefono">Teléfono de Casa</label>
                            <input onkeypress='validate(event)' type="text" maxlength="10"
                                   class="form-control form-control-user" id="telefono"
                                   name="bean.numeroCasa" value="<s:property value="bean.numeroCasa" />"
                                   placeholder="Teléfono">
                        </div>
                        <div class="col-sm-3 mb-3 mb-sm-0">
                            <label for="fechaDeIngreso">Fecha de Ingreso</label>
                            <input  type="date" class="form-control form-control-user"
                                   name="bean.fechaDeIngreso"
                                   value="<s:property value="bean.fechaDeIngreso" />" id="fechaDeIngreso"
                                   placeholder="Matrícula">
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-6 mb-3 mb-sm-0">
                            <label for="direccion">Dirección</label>
                            <input type="text" class="form-control form-control-user" name="bean.direccion"
                                   value="<s:property value="bean.direccion" />" id="direccion"
                                   placeholder="Celular">
                        </div>

                        <div class="col-sm-6 mb-3 mb-sm-0">
                            <label for="carreraEgreso">Carrera de Egreso</label>
                            <input  type="text" max="25" class="form-control form-control-user"
                                   name="bean.carreraDeEgreso"
                                   value="<s:property value="bean.carreraDeEgreso" />" id="carreraEgreso"
                                   placeholder="Matrícula">
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-3 mb-3 mb-sm-0">
                            <label for="horario">Horario</label>
                            <input disabled type="hidden" class="form-control form-control-user" name="bean.horario.idHorario"
                                   value="<s:property value="bean.horario.idHorario" />" id="idhorario"
                                   placeholder="Horario">

                            <input  type="text" class="form-control form-control-user" name="bean.horario.horario"
                                   value="<s:property value="bean.horario.horario" />" id="horario"
                                   placeholder="Horario">
                        </div>
                    </div>
                    <button class="btn btn-primary btn-user btn-block">
                        Enviar
                    </button>
                </form>

                <hr class="hr">
                <form id="modifcarContra" class="user" action="" >
                    <span id="msgError" class="text-danger" hidden><i class="fa fa-exclamation-circle"></i>Las contraseñas no coinciden</span>
                    <div class="form-group row">

                        <input type="hidden" class="form-control form-control-user" name="bean.idPersona"
                               value="<s:property value="#session.usuario.idPersona" />" id="idContra" placeholder="Nombre">
                        <div class="col-sm-3 mb-3 mb-sm-0">
                            <label for="ncontra">Nueva Contraseña</label>
                            <input maxlength="10" type="password" class="form-control form-control-user" name="bean.contrasenia"
                                    id="ncontra" >
                        </div>
                        <div class="col-sm-3 mb-3 mb-sm-0">
                            <label for="ccontra">Confirmar Contraseña</label>
                            <input maxlength="10" type="password" class="form-control form-control-user"
                                    id="ccontra" >
                        </div>
                        <div class="col-sm-3 mb-3 mb-sm-0">
                            <label for="contraA">Contraseña Actual</label>
                            <input max="10" type="password" class="form-control form-control-user" name="bean.contrasenia"
                                    id="contraA">
                        </div>
                        <input type="hidden" maxlength="10" class="form-control form-control-user" id="contraO"
                               name="bean.contrasenia" value="<s:property value="bean.contrasenia" />"
                               placeholder="Teléfono">
                    </div>
                    <button type="button" onclick="validarcontra()" class="btn btn-primary btn-user btn-block">
                        Modificar
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

</body>
<script>
    var form = document.getElementById('modifcarContra');
    var nuevaContra = document.getElementById("ncontra");
    var confirmarContra = document.getElementById("ccontra");
    var contraActual = document.getElementById('contraA')
    var contraOriginal = document.getElementById('contraO');
    var span =document.getElementById('msgError');


    var form = document.getElementById('modifcarContra');
    var nuevaContra = document.getElementById("ncontra");
    var confirmarContra = document.getElementById("ccontra");
    var contraActual = document.getElementById('contraA')
    var contraOriginal = document.getElementById('contraO');
    var span =document.getElementById('msgError');


    contraActual.addEventListener("change",validarcontra);
    confirmarContra.addEventListener("change",validarcontraIguales);

    function  validarcontra() {
        alert(contraOriginal.value);
        if(nuevaContra.value == confirmarContra.value && nuevaContra.value !=null && confirmarContra.value !=null) {
            if (contraActual.value == contraOriginal.value) {
                $('#msgError').addClass("ver");
            }
        }else{

            span.removeAttribute("hidden","");
        }
    }
    function  validarcontraIguales() {

        if(nuevaContra.value == confirmarContra.value && nuevaContra.value !=null && confirmarContra.value !=null) {

            $('#msgError').addClass("ver");

        }else{

            span.removeAttribute("hidden","");
        }
    }
   function validar(){

           if(nuevaContra.value == confirmarContra.value && nuevaContra.value !=null && confirmarContra.value !=null){
               alert("si son iguales");
               document.getElementById('modifcarContra').submit();
           }else{
               alert("no son iguales");
                span.removeAttribute("hidden");
           }

   }

    function validate(evt) {
        var theEvent = evt || window.event;

        // Handle paste
        if (theEvent.type === 'paste') {
            key = event.clipboardData.getData('text/plain');
        } else {
            // Handle key press
            var key = theEvent.keyCode || theEvent.which;
            key = String.fromCharCode(key);
        }
        var regex = /[0-9]|\./;
        if (!regex.test(key)) {
            theEvent.returnValue = false;
            if (theEvent.preventDefault) theEvent.preventDefault();
        }
    }



</script>

</html>
