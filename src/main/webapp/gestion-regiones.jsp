<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.jbes.aa2.model.Region" %>
<%@ page import="com.jbes.aa2.model.Usuario" %>
<%
  Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
  if(usuario == null) { response.sendRedirect("login"); return; }
  boolean esAdmin = "Administrador".equals(usuario.getRol());
%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Gestión de Regiones</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
  <div class="d-flex justify-content-between align-items-center mb-4">
    <h2>Directorio de Regiones</h2>
    <a href="index.jsp" class="btn btn-secondary">Volver al Inicio</a>
  </div>

  <form action="gestion-regiones" method="GET" class="mb-4 row g-2">
    <div class="col-md-5">
      <input type="text" class="form-control shadow-sm" name="qNombre" placeholder="Nombre de región..." value="<%= request.getAttribute("qNombre") != null ? request.getAttribute("qNombre") : "" %>">
    </div>
    <div class="col-md-5">
      <input type="text" class="form-control shadow-sm" name="qProf" placeholder="Nombre del profesor..." value="<%= request.getAttribute("qProf") != null ? request.getAttribute("qProf") : "" %>">
    </div>
    <div class="col-md-2">
      <button class="btn btn-primary w-100 shadow-sm" type="submit">Filtrar</button>
    </div>
  </form>

  <% if(esAdmin) { %>
  <div class="card mb-4 shadow-sm">
    <div class="card-header bg-success text-white">Añadir Nueva Región (Solo Administrador)</div>
    <div class="card-body">
      <form action="gestion-regiones" method="POST" class="row g-3">
        <div class="col-md-3"><label>Nombre</label><input type="text" class="form-control" name="nombre" required></div>
        <div class="col-md-3"><label>Iniciales</label><input type="text" class="form-control" name="iniciales"></div>
        <div class="col-md-3"><label>Villanos</label><input type="text" class="form-control" name="villanos"></div>
        <div class="col-md-3"><label>Profesor</label><input type="text" class="form-control" name="profesor"></div>
        <div class="col-md-4"><label>Videojuego Origen</label><input type="text" class="form-control" name="videojuegoOrigen"></div>
        <div class="col-md-4"><label>Fecha Lanzamiento</label><input type="date" class="form-control" name="fechaLanzamiento"></div>
        <div class="col-md-4 d-flex align-items-center mt-4">
          <div class="form-check"><input class="form-check-input" type="checkbox" name="tieneConcursos" id="chk"><label class="form-check-label" for="chk">¿Tiene Concursos?</label></div>
        </div>
        <div class="col-12"><button type="submit" class="btn btn-success">Guardar Región</button></div>
      </form>
    </div>
  </div>
  <% } %>

  <table class="table table-striped shadow-sm">
    <thead class="table-dark">
    <tr>
      <th>ID</th>
      <th>Nombre</th>
      <th>Iniciales</th>
      <th>Villanos</th>
      <th>Profesor</th>
      <th>Juego Origen</th>
      <th>Lanzamiento</th>
      <th>Concursos</th>
      <% if(esAdmin) { %><th>Acciones</th><% } %>
    </tr>
    </thead>
    <tbody>
    <%
      List<Region> listaRegionesCompleta = (List<Region>) request.getAttribute("listaRegiones");
      if(listaRegionesCompleta != null && !listaRegionesCompleta.isEmpty()) {
        for(Region region : listaRegionesCompleta) {
    %>
    <tr>
      <td><%= region.getId() %></td>
      <td><strong><%= region.getNombre() %></strong></td>
      <td><%= region.getIniciales() != null ? region.getIniciales() : "-" %></td>
      <td><%= region.getVillanos() != null ? region.getVillanos() : "-" %></td>
      <td><%= region.getProfesor() != null ? region.getProfesor() : "-" %></td>
      <td><%= region.getVideojuegoOrigen() != null ? region.getVideojuegoOrigen() : "-" %></td>
      <td><%= region.getFechaLanzamiento() != null ? region.getFechaLanzamiento() : "-" %></td>
      <td><span class="badge <%= region.isTieneConcursos() ? "bg-success" : "bg-secondary" %>"><%= region.isTieneConcursos() ? "Sí" : "No" %></span></td>
      <% if(esAdmin) { %>
      <td>
        <a href="gestion-regiones?action=borrar&id=<%= region.getId() %>" class="btn btn-sm btn-danger" onclick="return confirm('¿Borrar esta región?');">Borrar</a>
      </td>
      <% } %>
    </tr>
    <% }} else { %>
    <tr><td colspan="<%= esAdmin ? 9 : 8 %>" class="text-center">No hay regiones.</td></tr>
    <% } %>
    </tbody>
  </table>
</div>

</body>
</html>
