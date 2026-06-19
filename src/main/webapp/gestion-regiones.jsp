<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.jbes.aa2.model.Region" %>

<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Gestión de Regiones</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
  <h2 class="mb-4">Gestión de Regiones</h2>

  <div class="card mb-4 shadow-sm">
    <div class="card-header bg-success text-white">Añadir Nueva Región</div>
    <div class="card-body">
      <form action="gestion-regiones" method="POST" class="row g-3">
        <div class="col-md-3">
          <label>Nombre</label>
          <input type="text" class="form-control" name="nombre" required>
        </div>
        <div class="col-md-3">
          <label>Iniciales (ej. Bulbasaur...)</label>
          <input type="text" class="form-control" name="iniciales">
        </div>
        <div class="col-md-3">
          <label>Villanos</label>
          <input type="text" class="form-control" name="villanos">
        </div>
        <div class="col-md-3">
          <label>Profesor</label>
          <input type="text" class="form-control" name="profesor">
        </div>
        <div class="col-md-4">
          <label>Videojuego de Origen</label>
          <input type="text" class="form-control" name="videojuegoOrigen">
        </div>
        <div class="col-md-4">
          <label>Fecha Lanzamiento</label>
          <input type="date" class="form-control" name="fechaLanzamiento">
        </div>
        <div class="col-md-4 d-flex align-items-center mt-4">
          <div class="form-check">
            <input class="form-check-input" type="checkbox" name="tieneConcursos" id="checkConcursos">
            <label class="form-check-label" for="checkConcursos">¿Tiene Concursos?</label>
          </div>
        </div>
        <div class="col-12">
          <button type="submit" class="btn btn-success">Guardar Región</button>
          <a href="index.jsp" class="btn btn-secondary">Volver al Inicio</a>
        </div>
      </form>
    </div>
  </div>

  <table class="table table-striped shadow-sm">
    <thead class="table-dark">
    <tr>
      <th>ID</th><th>Nombre</th><th>Profesor</th><th>Lanzamiento</th><th>Acciones</th>
    </tr>
    </thead>
    <tbody>
    <%
      List<Region> lista = (List<Region>) request.getAttribute("listaRegiones");
      if(lista != null && !lista.isEmpty()) {
        for(Region region : lista) {
    %>
    <tr>
      <td><%= region.getId() %></td>
      <td><strong><%= region.getNombre() %></strong></td>
      <td><%= region.getProfesor() != null ? region.getProfesor() : "-" %></td>
      <td><%= region.getFechaLanzamiento() != null ? region.getFechaLanzamiento() : "-" %></td>
      <td>
        <a href="gestion-regiones?action=borrar&id=<%= region.getId() %>"
           class="btn btn-sm btn-danger" onclick="return confirm('¿Borrar esta región?');">Borrar</a>
      </td>
    </tr>
    <% }} else { %>
    <tr><td colspan="5" class="text-center">No hay regiones.</td></tr>
    <% } %>
    </tbody>
  </table>
</div>

</body>
</html>
