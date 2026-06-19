<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.jbes.aa2.model.Usuario" %>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
    if (usuario == null) {
        response.sendRedirect("login");
        return;
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Panel de Control - Pokédex</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<nav class="navbar navbar-dark bg-dark shadow-sm">
    <div class="container">
        <span class="navbar-brand mb-0 h1">
            Pokédex Sistema
        </span>
        <div class="d-flex align-items-center">
            <span class="text-light me-3">
                Conectado: <strong><%= usuario.getNombreUsuario() %></strong>
                <span class="badge bg-secondary ms-1"><%= usuario.getRol() %></span>
            </span>
            <a href="logout" class="btn btn-outline-danger btn-sm">Cerrar Sesión</a>
        </div>
    </div>
</nav>

<div class="container mt-5">
    <div class="text-center mb-5">
        <h1 class="text-primary display-4 fw-bold">Base de Datos Pokémon</h1>
        <p class="text-muted fs-5">Panel Central</p>
    </div>

    <<div class="row g-4 justify-content-center">
    <div class="col-md-4">
        <div class="card h-100 shadow-sm border-primary">
            <div class="card-body text-center d-flex flex-column justify-content-between">
                <div>
                    <h3 class="card-title text-primary mb-3">Pokémon</h3>
                    <p class="card-text text-muted">Consulta la Pokédex.</p>
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
                    <p class="card-text text-muted">Consulta las regiones del mundo Pokémon.</p>
                </div>
                <div class="mt-4">
                    <a href="gestion-regiones.jsp" class="btn btn-success w-100 fw-bold">Entrar a Regiones</a>
                </div>
            </div>
        </div>
    </div>

    <div class="col-md-4">
        <div class="card h-100 shadow-sm border-info">
            <div class="card-body text-center d-flex flex-column justify-content-between">
                <div>
                    <h3 class="card-title text-info mb-3">Rutas</h3>
                    <p class="card-text text-muted">Explora la configuración de rutas.</p>
                </div>
                <div class="mt-4">
                    <a href="gestion-rutas.jsp" class="btn btn-info text-white w-100 fw-bold">Entrar a Rutas</a>
                </div>
            </div>
        </div>
    </div>

    <% if ("Administrador".equals(usuario.getRol())) { %>
    <div class="col-md-4 mb-4">
        <div class="card h-100 shadow-sm border-dark">
            <div class="card-body text-center d-flex flex-column justify-content-between">
                <div>
                    <h3 class="card-title text-dark mb-3">Usuarios</h3>
                    <p class="card-text text-muted">Gestión de cuentas de Entrenadores y Administradores del sistema.</p>
                </div>
                <div class="mt-4">
                    <a href="gestion-usuarios" class="btn btn-dark w-100 fw-bold">Gestionar Cuentas</a>
                </div>
            </div>
        </div>
    </div>
    <% } %>
</div>
</div>

</body>
</html>