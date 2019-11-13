<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">

<head>

  <%@ page contentType="text/html;charset=UTF-8" language="java" %>

  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">


  <title>SAPAP</title>

  <!-- Custom fonts for this template-->
  <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body class="bg-gradient-primary">

<div class="container">

  <!-- Outer Row -->
  <div class="row justify-content-center">

    <div class="col-xl-10 col-lg-12 col-md-9">

      <div class="card o-hidden border-0 shadow-lg my-5">
        <div class="card-body p-0">
          <!-- Nested Row within Card Body -->
          <div class="row">
            <div class="col-lg-6 d-none d-lg-block">
              <img src="img/cds.png" height="400" width="400" style="margin-left: 30px">
            </div>
            <div class="col-lg-6">
              <div class="p-5">
                <div class="text-center">
                  <br>
                  <h1 class="h4 text-gray-900 mb-4">Inicio de Sesión</h1>

                </div>
                <form class="user" action="consultarPersonas" method="post">
                  <div class="form-group">
                    <input type="email" class="form-control form-control-user" name="bean.correoInstitucional" id="correo" aria-describedby="emailHelp" placeholder="Ingresa correo">
                  </div>
                  <div class="form-group">
                    <input type="password" class="form-control form-control-user" name="bean.contrasenia" id="contra" placeholder="<s:property value="mensaje" />">
                  </div>
                  <button class="btn btn-primary btn-user btn-block">
                    Ingresar
                  </button>
                    <p><s:property value="mensaje" /></p>
                  <hr>
<%--                  <a href="index.html" class="btn btn-google btn-user btn-block">--%>
<%--                    <i class="fab fa-google fa-fw"></i> Inicia sesión con Google--%>
<%--                  </a>--%>

                </form>

                <div class="text-center">
                  <a class="small" href="RecuperarContrasena.jsp">¿Olvidaste tu contraseña?</a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

    </div>

  </div>

</div>

<!-- Bootstrap core JavaScript-->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="js/sb-admin-2.min.js"></script>

</body>

</html>
