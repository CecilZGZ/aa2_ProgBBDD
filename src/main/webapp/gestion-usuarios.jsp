<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.jbes.aa2.model.Usuario" %>
<%
  Usuario u = (Usuario) session.getAttribute("usuarioLogueado");
  if (u == null || !"Administrador".equals(u.getRol())) { response.sendRedirect("login"); return; }
%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Gestión de Usuarios</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
  <div class="d-flex justify-content-between mb-4">
    <h2>Control de Usuarios (Administrador)</h2>
    <a href="index.jsp" class="btn btn-secondary">Volver al Inicio</a>
  </div>

  <div class="card mb-4 shadow-sm">
    <div class="card-header bg-dark text-white">Registrar Nuevo Usuario</div>
    <div class="card-body">
      <form action="gestion-usuarios" method="POST" class="row g-3">
        <div class="col-md-4"><input type="text" class="form-control" name="nombreUsuario" placeholder="Nombre de usuario" required></div>
        <div class="col-md-4"><input type="password" class="form-control" name="contrasena" placeholder="Contraseña" required></div>
        <div class="col-md-3">
          <select name="rol" class="form-select" required>
            <option value="Entrenador">Entrenador</option>
            <option value="Administrador">Administrador</option>
          </select>
        </div>
        <div class="col-md-1"><button type="submit" class="btn btn-primary w-100">Crear</button></div>
      </form>
    </div>
  </div>

  <table class="table table-striped shadow-sm bg-white">
    <thead class="table-dark">
    <tr><th>ID</th><th>Nombre</th><th>Rol</th><th>Acciones</th></tr>
    </thead>
    <tbody>
    <%
      List<Usuario> lista = (List<Usuario>) request.getAttribute("listaUsuarios");
      if (lista != null) { for (Usuario usuario : lista) {
    %>
    <tr>
      <td><%= usuario.getId() %></td>
      <td><strong><%= usuario.getNombreUsuario() %></strong></td>
      <td><%= usuario.getRol() %></td>
      <td>
        <% if ("oak".equalsIgnoreCase(usuario.getNombreUsuario())) { %>
        <span class="badge bg-warning text-dark">Protegido (Admin Supremo)</span>
        <% } else { %>
        <a href="gestion-usuarios?action=borrar&id=<%= usuario.getId() %>" class="btn btn-sm btn-danger" onclick="return confirm('¿Seguro que deseas eliminar a este usuario?');">Eliminar</a>
        <% } %>
      </td>
    </tr>
    <% }} %>
    </tbody>
  </table>
</div>

</body>
</html>