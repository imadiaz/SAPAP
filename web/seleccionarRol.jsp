<%--
  Created by IntelliJ IDEA.
  User: isaac
  Date: 10/11/2019
  Time: 09:45 a. m.
  To change this template use File | Settings | File Templates.
--%>
    <%@taglib prefix="s" uri="/struts-tags" %>
        <%@ page contentType="text/html;charset=UTF-8" language="java" %>
            <html>

            <head>

                <%@ page contentType="text/html;charset=UTF-8" language="java" %>

                    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">


                    <title>SAPAP</title>

                    <!-- Custom fonts for this template-->
                    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
                    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">


                    <!-- Custom styles for this template-->
                <link href="css/sb-admin-2.css" rel="stylesheet">
                <%--    <link href="css/sb-admin-2.min.css" rel="stylesheet">--%>
            </head>

            <body class="bg-gradient-primary">


                <div class="container">

                    <div class="row justify-content-center">
                        <h1 style="color:white;">Selecione un Rol</h1>

                        <br/>
<%--                        <h1><s:property value="#session.usuario" /></h1>--%>
<%--                        <h1><s:property value="#session.roles" /></h1>--%>
<%--                        <s:iterator value="#session.roles" var="rol">--%>
<%--                            <p><s:property value="tipo" /></p>--%>
<%--                        </s:iterator>--%>




                        <div class="col-md-12">
                            <div class="row">
                                <s:iterator value="listaRoles" status="stat" var="lista">
                                    <div class="col-md-4">
                                        <div class="card">
                                            <div class="card-body">
                                                <p><s:property value="tipo" /></p>
                                            </div>
                                            <div class="card-footer">
                                                <s:if test="#lista.tipo=='Estadia'">
                                                    <form action="vista/AP/InicioAP.jsp" method="post">
                                                        <button class="btn btn-lg btn-primary">Ir</button>
                                                    </form>
                                                </s:if>
                                                <s:elseif test="#lista.tipo=='Administradora de Recursos Humanos'">
                                                    <form action="vista/RH/InicioRH.jsp" method="post">
                                                        <button class="btn btn-lg btn-primary">Ir</button>
                                                    </form>
                                                </s:elseif>
                                                <s:elseif test="#lista.tipo=='Responsable de Desarrollo'">
                                                    <form action="vista/RD/InicioRD.jsp" method="post">
                                                        <button class="btn btn-lg btn-primary">Ir</button>
                                                    </form>
                                                </s:elseif>
                                                <s:elseif test="#lista.tipo=='RAPE'">
                                                    <form action="vista/RAPE/InicioRAPE.jsp" method="post">
                                                        <button class="btn btn-lg btn-primary">Ir</button>
                                                    </form>
                                                </s:elseif>
                                                <s:elseif test="#lista.tipo=='Coordinador del CDS'">
                                                    <form action="vista/Coordinador/InicioCOD.jsp" method="post">
                                                        <button class="btn btn-lg btn-primary">Ir</button>
                                                    </form>
                                                </s:elseif>
                                                <s:elseif test="#lista.tipo=='Analista Programador'">
                                                    <form action="vista/AP/InicioAP.jsp" method="post">
                                                        <button class="btn btn-lg btn-primary">Ir</button>
                                                    </form>
                                                </s:elseif>

                                            </div>
                                        </div>
                                        <br>
                                    </div>

                                </s:iterator>
                                <br>
                            </div>


                        </div>
                    </div>
                </div>

                </div>

            </body>

            </html>