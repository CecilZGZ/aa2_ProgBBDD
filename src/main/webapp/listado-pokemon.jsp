<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.jbes.aa2.model.Pokemon" %>
<%@ page import="com.jbes.aa2.model.Usuario" %>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
    boolean esAdmin = (usuario != null && "Administrador".equals(usuario.getRol()));
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Listado de Pokémon</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2>Listado Nacional de Pokémon</h2>
        <div>
            <% if (esAdmin) { %>
            <a href="registro-pokemon" class="btn btn-success me-2">Añadir Nuevo Pokémon</a>
            <% } %>
            <a href="index.jsp" class="btn btn-secondary">Volver al Inicio</a>
        </div>
    </div>

    <form action="listado-pokemon" method="GET" class="mb-4 row g-2">
        <div class="col-md-5">
            <input type="text" class="form-control shadow-sm" name="qNombre" placeholder="Buscar por Nombre..."
                   value="<%= request.getAttribute("qNombre") != null ? request.getAttribute("qNombre") : "" %>">
        </div>
        <div class="col-md-5">
            <input type="text" class="form-control shadow-sm" name="qTipo" placeholder="Buscar por Tipo..."
                   value="<%= request.getAttribute("qTipo") != null ? request.getAttribute("qTipo") : "" %>">
        </div>
        <div class="col-md-2">
            <button class="btn btn-primary w-100 shadow-sm" type="submit">Filtrar</button>
        </div>
    </form>

    <table class="table table-striped shadow-sm bg-white rounded">
        <thead class="table-dark">
        <tr>
            <th>Nº Pokedex</th>
            <th>Nombre</th>
            <th>Primer Tipo</th>
            <th>Segundo Tipo</th>
            <th>Sprite</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Pokemon> lista = (List<Pokemon>) request.getAttribute("listaPokemon");
            if (lista != null && !lista.isEmpty()) {
                for (Pokemon pokemon : lista) {
        %>
        <tr>
            <td>#<%= pokemon.getNumeroPokedex() %></td>
            <td><strong><%= pokemon.getNombre() %></strong></td>
            <td><span class="badge bg-primary"><%= pokemon.getPrimerTipo() %></span></td>
            <td>
                <%= (pokemon.getSegundoTipo() != null && !pokemon.getSegundoTipo().isEmpty()) ?
                        "<span class='badge bg-secondary'>" + pokemon.getSegundoTipo() + "</span>" : "-" %>
            </td>
            <td>
                <img src="imagenes/<%= pokemon.getImagen() != null ? pokemon.getImagen() : "default.gif" %>" alt="<%= pokemon.getNombre() %>" style="width: 60px; height: 60px; object-fit: contain;">
            </td>
            <td>
                <a href="detalle-pokemon?id=<%= pokemon.getId() %>" class="btn btn-sm btn-info text-white">Ver Ficha</a>
                <% if (esAdmin) { %>
                <a href="modificar-pokemon?id=<%= pokemon.getId() %>" class="btn btn-sm btn-warning">Editar</a>
                <a href="borrado-pokemon?id=<%= pokemon.getId() %>" class="btn btn-sm btn-danger" onclick="return confirm('¿Seguro?');">Borrar</a>
                <% } %>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr><td colspan="5" class="text-center">No se encontraron Pokémon.</td></tr>
        <% } %>
        </tbody>
    </table>
</div>

</body>
</html>