<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.jbes.aa2.model.Ruta" %>
<%@ page import="com.jbes.aa2.model.Region" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Gestión de Rutas</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
  <h2 class="mb-4">Gestión de Rutas</h2>

  <div class="card mb-4 shadow-sm">
    <div class="card-header bg-primary text-white">Añadir Nueva Ruta</div>
    <div class="card-body">
      <form action="gestion-rutas" method="POST" class="row g-3">
        <div class="col-md-3">
          <label>Nombre</label>
          <input type="text" class="form-control" name="nombre" required>
        </div>
        <div class="col-md-3">
          <label>Región</label>
          <select class="form-select" name="idRegion" required>
            <%
              List<Region> regiones = (List<Region>) request.getAttribute("listaRegiones");
              if(regiones != null) {
                for(Region region : regiones) {
            %>
            <option value="<%= region.getId() %>"><%= region.getNombre() %></option>
            <% }} %>
          </select>
        </div>
        <div class="col-md-3">
          <label>Clima</label>
          <select class="form-select" name="climaPrimario" required>
            <option value="Despejado">Despejado</option>
            <option value="Soleado">Soleado</option>
            <option value="Lluvia">Lluvia</option>
            <option value="Granizo">Granizo</option>
            <option value="Tormenta de arena">Tormenta de arena</option>
            <option value="Niebla">Niebla</option>
            <option value="Especial">Especial</option>
          </select>
        </div>
        <div class="col-md-3">
          <label>Entorno</label>
          <input type="text" class="form-control" name="entorno">
        </div>
        <div class="col-md-3">
          <label>Nivel Mínimo</label>
          <input type="number" class="form-control" name="nivelMin" required>
        </div>
        <div class="col-md-3">
          <label>Nivel Máximo</label>
          <input type="number" class="form-control" name="nivelMax" required>
        </div>
        <div class="col-md-3">
          <label>Nº Entrenadores</label>
          <input type="number" class="form-control" name="cantidadEntrenadores" required>
        </div>
        <div class="col-md-3 d-flex align-items-center mt-4">
          <div class="form-check">
            <input class="form-check-input" type="checkbox" name="tieneAgua" id="checkAgua">
            <label class="form-check-label" for="checkAgua">¿Tiene Agua?</label>
          </div>
        </div>
        <div class="col-12">
          <button type="submit" class="btn btn-primary">Guardar Ruta</button>
          <a href="index.jsp" class="btn btn-secondary">Volver al Inicio</a>
        </div>
      </form>
    </div>
  </div>

  <table class="table table-striped shadow-sm">
    <thead class="table-dark">
    <tr>
      <th>ID</th><th>Nombre</th><th>Clima</th><th>Niveles</th><th>Acciones</th>
    </tr>
    </thead>
    <tbody>
    <%
      List<Ruta> lista = (List<Ruta>) request.getAttribute("listaRutas");
      if(lista != null && !lista.isEmpty()) {
        for(Ruta ruta : lista) {
    %>
    <tr>
      <td><%= ruta.getId() %></td>
      <td><strong><%= ruta.getNombre() %></strong></td>
      <td><%= ruta.getClimaPrimario() %></td>
      <td>Lv. <%= ruta.getNivelMin() %> - <%= ruta.getNivelMax() %></td>
      <td>
        <a href="gestion-rutas?action=borrar&id=<%= ruta.getId() %>"
           class="btn btn-sm btn-danger" onclick="return confirm('¿Borrar esta ruta?');">Borrar</a>
      </td>
    </tr>
    <% }} else { %>
    <tr><td colspan="5" class="text-center">No hay rutas.</td></tr>
    <% } %>
    </tbody>
  </table>
</div>

</body>
</html>