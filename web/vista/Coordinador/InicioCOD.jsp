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
    <link href="<%=context%>/css/sb-admin-2.css" rel="stylesheet">
    <%--    <link href="<%=context%>/css/sb-admin-2.min.css" rel="stylesheet">--%>

    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>

    <script src="https://www.gstatic.com/firebasejs/7.3.0/firebase-app.js"></script>

    <!-- TODO: Add SDKs for Firebase products that you want to use
         https://firebase.google.com/docs/web/setup#available-libraries -->
    <script src="https://www.gstatic.com/firebasejs/7.3.0/firebase-analytics.js"></script>
    <script src="https://www.gstatic.com/firebasejs/7.3.0/firebase-database.js"></script>
    <script src="https://www.gstatic.com/firebasejs/7.3.0/firebase-storage.js"></script>


</head>
<s:if test="#session.usuario.nombre==null">
    <script>
        alert("No hay sesion");
        window.location.href = "<%=context%>/index.jsp";
    </script>
</s:if>

<body id="page-top" onload="consultarJustificantesPendientesCOD()">
<input type="hidden" id="idUsSesion" value="<s:property value="#session.usuario.idPersona" />">
<input id='idJustificante' type='hidden'/>
<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

        <!-- Sidebar - Brand -->
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="InicioCOD.jsp">
            <div class="sidebar-brand-icon rotate-n-15">
                <i class="fas fa-building"></i>
            </div>
            <div class="sidebar-brand-text mx-3">Coordinador CDS</div>
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
                    <a class="collapse-item" href="InicioCOD.jsp">Gestionar justificantes</a>
                    <a class="collapse-item" href="HistorialJustificantesCOD.jsp">Historial</a>
                </div>
            </div>
        </li>

        <!-- Divider -->
        <hr class="sidebar-divider">

        <!-- Heading -->
        <div class="sidebar-heading">
            Cursos
        </div>

        <!-- Nav Item - Charts -->
        <li class="nav-item">
            <a class="nav-link" href="CursosCOD.jsp">
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
                            <a class="dropdown-item" href="MiPerfilCOD.jsp">
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
                        <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            <span class="mr-2 d-none d-lg-inline text-gray-600 small"><i
                                                    class="fas fa-user fa-fw"
                                                    style="margin-right: 20px">
                                </i><s:property value="#session.usuario.nombre"/></span>
                        </a>

                        </a>

                        <!-- Dropdown - User Information -->
                        <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
                            <a class="dropdown-item" href="MiPerfilCOD.jsp">
                                <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> Perfil
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

                <!-- Page Heading -->
                <h1 class="h3 mb-4 text-gray-800">Justificantes pendientes</h1>


                <table class="table table-bordered dataTable" id="dataTable" width="100%" cellspacing="0"
                       role="grid" aria-describedby="dataTable_info" style="width: 100%;">
                    <thead>
                    <tr role="row">
                        <th class="sorting_asc" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1"
                            aria-sort="ascending" aria-label="Name: activate to sort column descending"
                            style="width: 161px;">Fecha de elaboración
                        </th>
                        <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1"
                            aria-label="Position: activate to sort column ascending" style="width: 246px;">
                            Recurso
                        </th>
                        <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1"
                            aria-label="Office: activate to sort column ascending" style="width: 116px;">
                            Nombre
                        </th>
                        <th class="sorting">Proyecto
                        </th>
                        <th class="sorting">Motivo
                        </th>
                        <th class="sorting">Imagen
                        </th>
                        <th class="sorting">Acciones
                        </th>
                    </tr>
                    </thead>
                    <tfoot>
                    <tr>
                        <th rowspan="1" colspan="1">Fecha de elaboración</th>
                        <th rowspan="1" colspan="1">Recurso</th>
                        <th rowspan="1" colspan="1">Nombre</th>
                        <th rowspan="1" colspan="1">Proyecto</th>
                        <th rowspan="1" colspan="1">Motivo</th>
                        <th rowspan="1" colspan="1">Imagen</th>
                        <th rowspan="1" colspan="1">Acciones</th>
                    </tr>
                    </tfoot>
                    <tbody id="tableBody">

                    </tbody>
                </table>
            </div>
        </div>

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


<!-- Modal Aprobar Justificante-->
<div class="modal fade" id="modalAprobarJustificante" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Contraseña</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <!-- Todo el Textiño -->
                <div class="row" class="form-group">
                    <div class="col-md-9 offset-1">
                        <p>Ingresa tu contraseña para continuar</p>
                        <input class="form-control" type="hidden" id="aprobar"/> <br>
                        <input class="form-control" type="password" id="contrasena" placeholder="Contraseña"/>
                    </div>
                </div>
                <br>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                <button id="botonContrasena" class="btn btn-info" onclick="validarContrasenaCOD()" data-dismiss="modal">Enviar</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal Motivo Justificante-->
<div class="modal fade" id="modalMotivo" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Motivo</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <!-- Todo el Textiño -->
                <div class="row" class="form-group">
                    <div class="col-md-9 offset-1">
                        <p>Ingresa el motivo por el cual rechazas el justificante</p>
                        <input class="form-control" type="text" id="motivo" placeholder="Motivo"/>
                    </div>
                </div>
                <br>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                <button id="botonMotivo" class="btn btn-info" onclick="accionJustificanteCOD()" data-dismiss="modal">Enviar</button>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript-->
<script src="<%=context%>/vendor/jquery/jquery.min.js"></script>
<script src="<%=context%>/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="<%=context%>/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="<%=context%>/js/sb-admin-2.min.js"></script>

<!--Nuestros recursos-->
<script src="<%=context%>/js/justificantes/justificantesJS.js"></script>

<script>
    $(document).on("click", "#btnAprobar", function () {
        var id = $(this).data("justificante");
        var id2 = $(this).data("idjustificante");
        $("#aprobar").val(id);
        $("#idJustificante").val(id2);
    });
    $(document).on("click", "#btnRechazar", function () {
        var id = $(this).data("justificante");
        var id2 = $(this).data("idjustificante");
        $("#aprobar").val(id);
        $("#idJustificante").val(id2);
    });
</script>
<script src="<%=context%>/vendor/datatables/jquery.dataTables.min.js"></script>
<script src="<%=context%>/vendor/datatables/dataTables.bootstrap4.js"></script>
<script>
    // Call the dataTables jQuery plugin
    $(document).ready(function() {
        $('#dataTable').DataTable({
            "language": {
                "sProcessing":    "Procesando...",
                "sLengthMenu":    "Mostrar _MENU_ registros",
                "sZeroRecords":   "No se encontraron resultados",
                "sEmptyTable":    "Ningún dato disponible en esta tabla",
                "sInfo":          "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
                "sInfoEmpty":     "Mostrando registros del 0 al 0 de un total de 0 registros",
                "sInfoFiltered":  "(filtrado de un total de _MAX_ registros)",
                "sInfoPostFix":   "",
                "sSearch":        "Buscar:",
                "sUrl":           "",
                "sInfoThousands":  ",",
                "sLoadingRecords": "Cargando...",
                "oPaginate": {
                    "sFirst":    "Primero",
                    "sLast":    "Último",
                    "sNext":    "Siguiente",
                    "sPrevious": "Anterior"
                },
                "oAria": {
                    "sSortAscending":  ": Activar para ordenar la columna de manera ascendente",
                    "sSortDescending": ": Activar para ordenar la columna de manera descendente"
                }
            },
            "scrollX": true
        });

    });
</script>
</body>

</html>
