<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.jbes.aa2.model.Ruta" %>
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
  <title>Gestión de Rutas</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
  <div class="d-flex justify-content-between align-items-center mb-4">
    <h2>Directorio de Rutas</h2>
    <a href="index.jsp" class="btn btn-secondary">Volver al Inicio</a>
  </div>

  <form action="gestion-rutas" method="GET" class="mb-4 row g-2">
    <div class="col-md-5">
      <input type="text" class="form-control shadow-sm" name="qNombre" placeholder="Nombre de ruta..." value="<%= request.getAttribute("qNombre") != null ? request.getAttribute("qNombre") : "" %>">
    </div>
    <div class="col-md-5">
      <input type="text" class="form-control shadow-sm" name="qClima" placeholder="Clima (ej. Soleado)..."
             value="<%= request.getAttribute("qClima") != null ? request.getAttribute("qClima") : "" %>">
    </div>
    <div class="col-md-2">
      <button class="btn btn-primary w-100 shadow-sm" type="submit">Filtrar</button>
    </div>
  </form>

  <% if(esAdmin) { %>
  <div class="card mb-4 shadow-sm">
    <div class="card-header bg-primary text-white">Añadir Nueva Ruta (Solo Administrador)</div>
    <div class="card-body">
      <form action="gestion-rutas" method="POST" class="row g-3">
        <div class="col-md-3"><label>Nombre</label><input type="text" class="form-control" name="nombre" required></div>
        <div class="col-md-3"><label>Región</label>
          <select class="form-select" name="idRegion" required>
            <% List<Region> regs = (List<Region>) request.getAttribute("listaRegiones");
              if(regs != null) { for(Region reg : regs) { %>
            <option value="<%= reg.getId() %>"><%= reg.getNombre() %></option>
            <% }} %>
          </select>
        </div>
        <div class="col-md-3"><label>Clima</label>
          <select class="form-select" name="climaPrimario" required>
            <option value="Despejado">Despejado</option><option value="Soleado">Soleado</option><option value="Lluvia">Lluvia</option><option value="Granizo">Granizo</option><option value="Especial">Especial</option>
          </select>
        </div>
        <div class="col-md-3"><label>Entorno</label><input type="text" class="form-control" name="entorno"></div>
        <div class="col-md-3"><label>Nivel Mín</label><input type="number" class="form-control" name="nivelMin" required></div>
        <div class="col-md-3"><label>Nivel Máx</label><input type="number" class="form-control" name="nivelMax" required></div>
        <div class="col-md-3"><label>Nº Entrenadores</label><input type="number" class="form-control" name="cantidadEntrenadores" required></div>
        <div class="col-md-3 d-flex align-items-center mt-4">
          <div class="form-check"><input class="form-check-input" type="checkbox" name="tieneAgua" id="agua"><label class="form-check-label" for="agua">¿Agua?</label></div>
        </div>
        <div class="col-12"><button type="submit" class="btn btn-primary">Guardar Ruta</button></div>
      </form>
    </div>
  </div>
  <% } %>

  <table class="table table-striped shadow-sm">
    <thead class="table-dark">
    <tr>
      <th>Nombre</th>
      <th>Región</th>
      <th>Clima</th>
      <th>Entorno</th>
      <th>Niveles</th>
      <th>Entrenadores</th>
      <th>¿Agua?</th>
      <% if(esAdmin) { %><th>Acciones</th><% } %>
    </tr>
    </thead>
    <tbody>
    <%
      List<Ruta> listaRutasCompleta = (List<Ruta>) request.getAttribute("listaRutas");
      if(listaRutasCompleta != null && !listaRutasCompleta.isEmpty()) {
        for(Ruta ruta : listaRutasCompleta) {
    %>
    <tr>
      <td><strong><%= ruta.getNombre() %></strong></td>
      <td><span class="badge bg-secondary"><%= ruta.getNombreRegion() != null ? ruta.getNombreRegion() : "Sin región" %></span></td>
      <td><%= ruta.getClimaPrimario() %></td>
      <td><%= ruta.getEntorno() != null ? ruta.getEntorno() : "-" %></td>
      <td>Lv. <%= ruta.getNivelMin() %> - <%= ruta.getNivelMax() %></td>
      <td><%= ruta.getCantidadEntrenadores() %></td>
      <td><%= ruta.isTieneAgua() ? "Sí 💧" : "No" %></td>
      <% if(esAdmin) { %>
      <td>
        <a href="gestion-rutas?action=borrar&id=<%= ruta.getId() %>" class="btn btn-sm btn-danger" onclick="return confirm('¿Borrar ruta?');">Borrar</a>
      </td>
      <% } %>
    </tr>
    <% }} else { %>
    <tr><td colspan="<%= esAdmin ? 9 : 8 %>" class="text-center">No hay rutas.</td></tr>
    <% } %>
    </tbody>
  </table>
</div>

</body>
</html>
