<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.jbes.aa2.model.Pokemon" %>
<%@ page import="com.jbes.aa2.model.Usuario" %>

<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Detalle del Pokémon</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<%
  Pokemon pokemon = (Pokemon) request.getAttribute("pokemon");
  if (pokemon == null) {
    response.sendRedirect("listado-pokemon");
    return;
  }
%>

<div class="container mt-5">
  <div class="row justify-content-center">
    <div class="col-md-6">
      <div class="card shadow border-dark">
        <div class="card-header bg-dark text-white text-center">
          <h2 class="mb-0">#<%= pokemon.getNumeroPokedex() %> - <%= pokemon.getNombre() %></h2>
        </div>
        <div class="card-body">
          <div class="row mb-3 text-center">
            <div class="col">
              <span class="badge bg-primary fs-6"><%= pokemon.getPrimerTipo() %></span>
              <% if (pokemon.getSegundoTipo() != null && !pokemon.getSegundoTipo().isEmpty()) { %>
              <span class="badge bg-secondary fs-6"><%= pokemon.getSegundoTipo() %></span>
              <% } %>
            </div>
          </div>

          <ul class="list-group list-group-flush mb-3">
            <li class="list-group-item"><strong>Generación de aparición:</strong> <%= pokemon.getGeneracion() %></li>
            <li class="list-group-item"><strong>Región de origen:</strong> <%= pokemon.getNombreRegion() %></li>
            <li class="list-group-item"><strong>Altura:</strong> <%= pokemon.getAltura() %> m</li>
            <li class="list-group-item"><strong>Peso:</strong> <%= pokemon.getPeso() %> kg</li>
            <li class="list-group-item"><strong>¿Evoluciona?:</strong> <%= pokemon.isTieneEvolucion() ? "Sí" : "No" %></li>
          </ul>

          <div class="p-3 bg-light border rounded">
            <p class="mb-0"><em>"<%= pokemon.getDescripcion() != null ? pokemon.getDescripcion() : "Sin descripción en la Pokédex." %>"</em></p>
          </div>
        </div>
        <div class="card-footer d-flex justify-content-between bg-white">
          <a href="listado-pokemon" class="btn btn-outline-secondary">Volver al listado</a>

          <%-- Verificación de rol para mostrar opciones de edición --%>
          <%
            Usuario userDetalle = (Usuario) session.getAttribute("usuarioLogueado");
            if (userDetalle != null && "Administrador".equals(userDetalle.getRol())) {
          %>
          <div>
            <a href="modificar-pokemon?id=<%= pokemon.getId() %>" class="btn btn-warning">Editar</a>
            <a href="borrado-pokemon?id=<%= pokemon.getId() %>" class="btn btn-danger" onclick="return confirm('¿Estás seguro de que quieres borrar este Pokémon?');">Borrar</a>
          </div>
          <% } %>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>
