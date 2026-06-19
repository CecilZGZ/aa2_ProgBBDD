<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.jbes.aa2.model.Usuario" %>
<%
  Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
  if (usuario == null) { response.sendRedirect("login"); return; }
%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Mi Perfil - Pokédex</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<nav class="navbar navbar-dark bg-dark mb-5">
  <div class="container">
    <span class="navbar-brand mb-0 h1">Zona Privada</span>
    <a href="index.jsp" class="btn btn-outline-light btn-sm">Volver al Panel</a>
  </div>
</nav>

<div class="container">
  <div class="row justify-content-center">
    <div class="col-md-6">
      <div class="card shadow-sm">
        <div class="card-header bg-primary text-white">
          <h4 class="mb-0">Gestionar mi Cuenta</h4>
        </div>
        <div class="card-body">

          <% if (request.getAttribute("mensajeExito") != null) { %>
          <div class="alert alert-success"><%= request.getAttribute("mensajeExito") %></div>
          <% } %>
          <% if (request.getAttribute("mensajeError") != null) { %>
          <div class="alert alert-danger"><%= request.getAttribute("mensajeError") %></div>
          <% } %>

          <div class="mb-3">
            <label class="form-label text-muted fw-bold">Nombre de Usuario</label>
            <input type="text" class="form-control bg-white" value="<%= usuario.getNombreUsuario() %>" disabled>
          </div>

          <div class="mb-4">
            <label class="form-label text-muted fw-bold">Rol Asignado</label>
            <input type="text" class="form-control bg-white" value="<%= usuario.getRol() %>" disabled>
          </div>

          <hr>

          <form action="perfil" method="POST" class="mt-4">
            <h5>Cambiar Contraseña</h5>
            <div class="mb-3">
              <label class="form-label">Nueva Contraseña</label>
              <input type="password" class="form-control" name="nuevaContrasena" required minlength="4">
            </div>
            <button type="submit" class="btn btn-success w-100 fw-bold">Guardar Cambios</button>
          </form>

        </div>
      </div>
    </div>
  </div>
</div>

</body>
</html>