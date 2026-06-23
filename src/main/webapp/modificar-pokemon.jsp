<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.jbes.aa2.model.Pokemon" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Modificar Pokémon</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<%
    Pokemon pokemon = (Pokemon) request.getAttribute("pokemonEditar");
    if (pokemon == null) {
        response.sendRedirect("listado-pokemon");
        return;
    }
%>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card shadow">
                <div class="card-header bg-warning text-dark">
                    <h3 class="mb-0">Editar datos de <%= pokemon.getNombre() %></h3>
                </div>
                <div class="card-body">
                    <form action="modificar-pokemon" method="POST" enctype="multipart/form-data" class="row g-3">
                        <input type="hidden" name="id" value="<%= pokemon.getId() %>">
                        <input type="hidden" name="imagenActual" value="<%= pokemon.getImagen() != null ? pokemon.getImagen() : "default.gif" %>">

                        <div class="col-md-12 mb-3">
                            <label class="form-label">Actualizar Sprite del Pokémon (GIF o PNG)</label>
                            <div class="d-flex align-items-center gap-3">
                                <% if (pokemon.getImagen() != null) { %>
                                <img src="imagenes/<%= pokemon.getImagen() %>" alt="Actual" style="width: 50px; height: 50px; object-fit: contain;" class="border rounded bg-white p-1">
                                <% } %>
                                <input type="file" class="form-control" name="imagen" accept="image/*">
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col-md-3">
                                <label class="form-label">Número Pokédex</label>
                                <input type="number" class="form-control" name="numero" value="<%= pokemon.getNumeroPokedex() %>" required>
                            </div>
                            <div class="col-md-9">
                                <label class="form-label">Nombre</label>
                                <input type="text" class="form-control" name="nombre" value="<%= pokemon.getNombre() %>" minlength="3" required>
                            </div>
                        </div>

                        <%
                            String[] tipos = {"Acero", "Agua", "Bicho", "Dragón", "Eléctrico", "Fantasma", "Fuego", "Hada", "Hielo", "Lucha", "Normal", "Planta", "Psíquico", "Roca", "Siniestro", "Tierra", "Veneno", "Volador"};
                        %>
                        <div class="row mb-3">
                            <div class="col">
                                <label class="form-label">Primer Tipo</label>
                                <select class="form-select" name="tipo1" required>
                                    <% for(String t : tipos) { %>
                                    <option value="<%= t %>" <%= t.equals(pokemon.getPrimerTipo()) ? "selected" : "" %>><%= t %></option>
                                    <% } %>
                                </select>
                            </div>
                            <div class="col">
                                <label class="form-label">Segundo Tipo</label>
                                <select class="form-select" name="tipo2">
                                    <option value="" <%= (pokemon.getSegundoTipo() == null || pokemon.getSegundoTipo().isEmpty()) ? "selected" : "" %>>Ninguno</option>
                                    <% for(String t : tipos) { %>
                                    <option value="<%= t %>" <%= t.equals(pokemon.getSegundoTipo()) ? "selected" : "" %>><%= t %></option>
                                    <% } %>
                                </select>
                            </div>
                            <div class="col">
                                <label class="form-label">Altura (m)</label>
                                <input type="number" step="0.1" class="form-control" name="altura" value="<%= pokemon.getAltura() %>" required>
                            </div>
                            <div class="col">
                                <label class="form-label">Peso (kg)</label>
                                <input type="number" step="0.1" class="form-control" name="peso" value="<%= pokemon.getPeso() %>" required>
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col-md-4">
                                <label class="form-label">Generación de aparición</label>
                                <%
                                    String[] generaciones = {"Primera", "Segunda", "Tercera", "Cuarta", "Quinta", "Sexta", "Séptima", "Octava", "Novena", "Otros"};
                                %>
                                <select class="form-select" name="generacion" required>
                                    <% for(String gen : generaciones) { %>
                                    <option value="<%= gen %>" <%= gen.equals(pokemon.getGeneracion()) ? "selected" : "" %>><%= gen %></option>
                                    <% } %>
                                </select>
                            </div>
                            <div class="col">
                                <label class="form-label">Región de origen</label>
                                <select class="form-select" name="idRegion" required>
                                    <option value="1" <%= pokemon.getIdRegion() == 1 ? "selected" : "" %>>Kanto</option>
                                    <option value="2" <%= pokemon.getIdRegion() == 2 ? "selected" : "" %>>Johto</option>
                                    <option value="3" <%= pokemon.getIdRegion() == 3 ? "selected" : "" %>>Hoenn</option>
                                    <option value="4" <%= pokemon.getIdRegion() == 4 ? "selected" : "" %>>Sinnoh</option>
                                    <option value="5" <%= pokemon.getIdRegion() == 5 ? "selected" : "" %>>Teselia</option>
                                    <option value="6" <%= pokemon.getIdRegion() == 6 ? "selected" : "" %>>Kalos</option>
                                    <option value="7" <%= pokemon.getIdRegion() == 7 ? "selected" : "" %>>Alola</option>
                                    <option value="8" <%= pokemon.getIdRegion() == 8 ? "selected" : "" %>>Galar</option>
                                    <option value="9" <%= pokemon.getIdRegion() == 9 ? "selected" : "" %>>Paldea</option>
                                    <option value="10" <%= pokemon.getIdRegion() == 10 ? "selected" : "" %>>Hisui</option>
                                </select>
                            </div>
                            <div class="col">
                                <label class="form-label">¿Tiene evolución?</label>
                                <select class="form-select" name="tieneEvolucion" required>
                                    <option value="true" <%= pokemon.isTieneEvolucion() ? "selected" : "" %>>Sí</option>
                                    <option value="false" <%= !pokemon.isTieneEvolucion() ? "selected" : "" %>>No</option>
                                </select>
                            </div>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Descripción</label>
                            <textarea class="form-control" name="descripcion" rows="3"><%= pokemon.getDescripcion() != null ? pokemon.getDescripcion() : "" %></textarea>
                        </div>

                        <div class="d-flex justify-content-between">
                            <a href="listado-pokemon" class="btn btn-secondary">Cancelar</a>
                            <button type="submit" class="btn btn-warning text-dark fw-bold">Guardar Cambios</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>