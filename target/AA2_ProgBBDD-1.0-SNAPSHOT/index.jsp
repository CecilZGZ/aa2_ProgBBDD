<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Panel de Control - Pokédex</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="text-center mb-5">
        <h1 class="text-primary display-4 fw-bold">Base de Datos Pokémon</h1>
        <p class="text-muted fs-5">Panel Central de Gestión y Mantenimiento</p>
    </div>

    <div class="row g-4 justify-content-center">
        <div class="col-md-4">
            <div class="card h-100 shadow-sm border-primary">
                <div class="card-body text-center d-flex flex-column justify-content-between">
                    <div>
                        <h3 class="card-title text-primary mb-3">Pokémon</h3>
                        <p class="card-text text-muted">Consulta la Pokédex Nacional, añade nuevas criaturas, edita parámetros o elimina registros.</p>
                    </div>
                    <div class="mt-4">
                        <a href="listado-pokemon" class="btn btn-primary w-100 fw-bold">Ver Pokédex</a>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card h-100 shadow-sm border-success">
                <div class="card-body text-center d-flex flex-column justify-content-between">
                    <div>
                        <h3 class="card-title text-success mb-3">Regiones</h3>
                        <p class="card-text text-muted">Administra las regiones del mundo Pokémon, incluyendo profesores, villanos y fechas de lanzamiento.</p>
                    </div>
                    <div class="mt-4">
                        <a href="gestion-regiones" class="btn btn-success w-100 fw-bold">Gestionar Regiones</a>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card h-100 shadow-sm border-info">
                <div class="card-body text-center d-flex flex-column justify-content-between">
                    <div>
                        <h3 class="card-title text-info mb-3">Rutas</h3>
                        <p class="card-text text-muted">Configura las rutas y entornos de cada región, controlando niveles, entrenadores y climas.</p>
                    </div>
                    <div class="mt-4">
                        <a href="gestion-rutas" class="btn btn-info text-white w-100 fw-bold">Gestionar Rutas</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>