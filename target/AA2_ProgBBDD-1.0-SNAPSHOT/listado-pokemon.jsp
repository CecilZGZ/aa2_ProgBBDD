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

    <div class="card mb-4 shadow-sm border-primary">
        <div class="card-body bg-light">
            <form action="listado-pokemon" method="GET" class="row g-3 align-items-center">

                <div class="col-auto">
                    <label for="nombreBusqueda" class="col-form-label fw-bold">Buscar por Nombre:</label>
                </div>
                <div class="col-md-3">
                    <input type="text" class="form-control" name="nombreBusqueda" id="nombreBusqueda" placeholder="Ej: Pikachu">
                </div>

                <div class="col-auto">
                    <label for="tipoBusqueda" class="col-form-label fw-bold">Filtrar por Tipo:</label>
                </div>
                <div class="col-md-3">
                    <%
                        String[] tiposBusqueda = {"Acero", "Agua", "Bicho", "Dragón", "Eléctrico", "Fantasma", "Fuego", "Hada", "Hielo", "Lucha", "Normal", "Planta", "Psíquico", "Roca", "Siniestro", "Tierra", "Veneno", "Volador"};
                    %>
                    <select class="form-select" name="tipoBusqueda" id="tipoBusqueda">
                        <option value="">Todos los tipos</option>
                        <% for(String t : tiposBusqueda) { %>
                        <option value="<%= t %>"><%= t %></option>
                        <% } %>
                    </select>
                </div>

                <div class="col-auto">
                    <button type="submit" class="btn btn-primary fw-bold">Buscar</button>
                    <a href="listado-pokemon" class="btn btn-outline-secondary">Limpiar filtros</a>
                </div>

            </form>
        </div>
    </div>

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
                    for (Pokemon pokemon : lista) {
            %>
            <tr>
                <td class="align-middle">#<%= pokemon.getNumeroPokedex() %></td>
                <td class="align-middle"><strong><%= pokemon.getNombre() %></strong></td>
                <td class="align-middle">
                    <span class="badge bg-success"><%= pokemon.getPrimerTipo() %></span>
                    <% if(pokemon.getSegundoTipo() != null && !pokemon.getSegundoTipo().isEmpty()) { %>
                    <span class="badge bg-secondary"><%= pokemon.getSegundoTipo() %></span>
                    <% } %>
                </td>
                <td class="align-middle">
                    <a href="detalle-pokemon?id=<%= pokemon.getId() %>" class="btn btn-sm btn-primary">Ver detalles</a>
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