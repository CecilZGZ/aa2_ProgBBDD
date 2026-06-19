<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registrar Pokémon</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card shadow">
                <div class="card-header bg-success text-white">
                    <h3 class="mb-0">Registrar Nuevo Pokémon</h3>
                </div>
                <div class="card-body">
                    <form action="registro-pokemon" method="POST" enctype="multipart/form-data" class="row g-3">

                        <div class="row mb-3">
                            <div class="col-md-3">
                                <label class="form-label">Número Pokédex</label>
                                <input type="number" class="form-control" name="numero" required>
                            </div>
                            <div class="col-md-9">
                                <label class="form-label">Nombre</label>
                                <input type="text" class="form-control" name="nombre" minlength="3" required>
                            </div>
                        </div>

                        <%
                            String[] tipos = {"Acero", "Agua", "Bicho", "Dragón", "Eléctrico", "Fantasma", "Fuego", "Hada", "Hielo", "Lucha", "Normal", "Planta", "Psíquico", "Roca", "Siniestro", "Tierra", "Veneno", "Volador"};
                        %>
                        <div class="row mb-3">
                            <div class="col-md-3">
                                <label class="form-label">Primer Tipo</label>
                                <select class="form-select" name="tipo1" required>
                                    <option value="" disabled selected>Selecciona...</option>
                                    <% for(String t : tipos) { %>
                                    <option value="<%= t %>"><%= t %></option>
                                    <% } %>
                                </select>
                            </div>
                            <div class="col-md-3">
                                <label class="form-label">Segundo Tipo</label>
                                <select class="form-select" name="tipo2">
                                    <option value="" selected>Ninguno</option>
                                    <% for(String t : tipos) { %>
                                    <option value="<%= t %>"><%= t %></option>
                                    <% } %>
                                </select>
                            </div>
                            <div class="col-md-3">
                                <label class="form-label">Altura (m)</label>
                                <input type="number" step="0.1" class="form-control" name="altura" required>
                            </div>
                            <div class="col-md-3">
                                <label class="form-label">Peso (kg)</label>
                                <input type="number" step="0.1" class="form-control" name="peso" required>
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col-md-4">
                                <label class="form-label">Generación de aparición</label>
                                <select class="form-select" name="generacion" required>
                                    <option value="" disabled selected>Selecciona generación...</option>
                                    <option value="Primera">Primera</option>
                                    <option value="Segunda">Segunda</option>
                                    <option value="Tercera">Tercera</option>
                                    <option value="Cuarta">Cuarta</option>
                                    <option value="Quinta">Quinta</option>
                                    <option value="Sexta">Sexta</option>
                                    <option value="Séptima">Séptima</option>
                                    <option value="Octava">Octava</option>
                                    <option value="Novena">Novena</option>
                                    <option value="Otros">Otros</option>
                                </select>
                            </div>
                            <div class="col-md-4">
                                <label class="form-label">Región de origen</label>
                                <select class="form-select" name="idRegion" required>
                                    <option value="" disabled selected>Selecciona región...</option>
                                    <option value="1">Kanto</option>
                                    <option value="2">Johto</option>
                                    <option value="3">Hoenn</option>
                                    <option value="4">Sinnoh</option>
                                    <option value="5">Teselia</option>
                                    <option value="6">Kalos</option>
                                    <option value="7">Alola</option>
                                    <option value="8">Galar</option>
                                    <option value="9">Paldea</option>
                                    <option value="10">Hisui</option>
                                </select>
                            </div>
                            <div class="col-md-4">
                                <label class="form-label">¿Tiene evolución?</label>
                                <select class="form-select" name="tieneEvolucion" required>
                                    <option value="true">Sí</option>
                                    <option value="false">No</option>
                                </select>
                            </div>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Descripción</label>
                            <textarea class="form-control" name="descripcion" rows="3" placeholder="Añade una breve entrada para la Pokédex..."></textarea>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Sprite del Pokémon (GIF o PNG)</label>
                            <input type="file" class="form-control" name="imagen" accept="image/*">
                        </div>

                        <div class="d-flex justify-content-between">
                            <a href="listado-pokemon" class="btn btn-secondary">Cancelar</a>
                            <button type="submit" class="btn btn-success fw-bold">Registrar Pokémon</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>