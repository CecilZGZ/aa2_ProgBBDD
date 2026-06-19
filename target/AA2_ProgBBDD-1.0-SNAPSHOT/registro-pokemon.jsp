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
                    <form action="registro-pokemon" method="POST">
                        <div class="row mb-3">
                            <div class="col">
                                <label class="form-label">Número Pokédex</label>
                                <input type="number" class="form-control" name="numero" required>
                            </div>
                            <div class="col">
                                <label class="form-label">Nombre</label>
                                <input type="text" class="form-control" name="nombre" minlength="3" required>
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col">
                                <label class="form-label">Primer Tipo</label>
                                <select class="form-select" name="tipo1" required>
                                    <option value="Planta">Planta</option>
                                    <option value="Fuego">Fuego</option>
                                    <option value="Agua">Agua</option>
                                    <option value="Normal">Normal</option>
                                    <option value="Eléctrico">Eléctrico</option>
                                </select>
                            </div>
                            <div class="col">
                                <label class="form-label">Altura (m)</label>
                                <input type="number" step="0.01" class="form-control" name="altura" required>
                            </div>
                            <div class="col">
                                <label class="form-label">Peso (kg)</label>
                                <input type="number" step="0.01" class="form-control" name="peso" required>
                            </div>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Descripción</label>
                            <textarea class="form-control" name="descripcion" rows="3"></textarea>
                        </div>

                        <input type="hidden" name="generacion" value="Primera">
                        <input type="hidden" name="idRegion" value="1">
                        <input type="hidden" name="tieneEvolucion" value="true">

                        <div class="d-flex justify-content-between">
                            <a href="index.jsp" class="btn btn-secondary">Volver</a>
                            <button type="submit" class="btn btn-success">Guardar en Base de Datos</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>