<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Login - Pokédex</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light d-flex align-items-center" style="height: 100vh;">

<div class="container">
  <div class="row justify-content-center">
    <div class="col-md-4">
      <div class="card shadow">
        <div class="card-header bg-primary text-white text-center">
          <h4>Acceso al Sistema</h4>
        </div>
        <div class="card-body">
          <% if (request.getAttribute("error") != null) { %>
          <div class="alert alert-danger"><%= request.getAttribute("error") %></div>
          <% } %>

          <form action="login" method="POST">
            <div class="mb-3">
              <label class="form-label">Usuario</label>
              <input type="text" class="form-control" name="nombreUsuario" required>
            </div>
            <div class="mb-3">
              <label class="form-label">Contraseña</label>
              <input type="password" class="form-control" name="contrasena" required>
            </div>
            <button type="submit" class="btn btn-primary w-100">Entrar</button>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>

</body>
</html>
