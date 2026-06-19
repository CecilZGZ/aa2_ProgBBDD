<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.jbes.aa2.model.Pokemon" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Listado de Pokémon</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <h2 class="text-primary mb-4">Pokédex Nacional</h2>

    <div class="table-responsive shadow">
        <table class="table table-striped table-hover mb-0">
            <thead class="table-dark">
            <tr>
                <th>Nº</th>
                <th>Nombre</th>
                <th>Tipos</th>
                <th>Acciones</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<Pokemon> lista = (List<Pokemon>) request.getAttribute("listadoPokemon");

                if (lista != null && !lista.isEmpty()) {
                    for (Pokemon p : lista) {
            %>
            <tr>
                <td class="align-middle">#<%= p.getNumeroPokedex() %></td>
                <td class="align-middle"><strong><%= p.getNombre() %></strong></td>
                <td class="align-middle">
                    <span class="badge bg-success"><%= p.getPrimerTipo() %></span>
                    <% if(p.getSegundoTipo() != null && !p.getSegundoTipo().isEmpty()) { %>
                    <span class="badge bg-secondary"><%= p.getSegundoTipo() %></span>
                    <% } %>
                </td>
                <td class="align-middle">
                    <a href="#" class="btn btn-sm btn-info text-white">Ver</a>
                    <a href="#" class="btn btn-sm btn-warning">Editar</a>
                    <a href="borrado-pokemon?id=<%= p.getId() %>" class="btn btn-sm btn-danger" onclick="return confirm('¿Estás seguro de que quieres liberar a este Pokémon y borrarlo de la base de datos?');">Borrar</a>
                </td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="4" class="text-center py-4">No hay Pokémon registrados en la base de datos.</td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>

    <div class="mt-4">
        <a href="index.jsp" class="btn btn-secondary">Volver al inicio</a>
        <a href="registro-pokemon.jsp" class="btn btn-success float-end">Añadir Nuevo</a>
    </div>
</div>

</body>
</html>