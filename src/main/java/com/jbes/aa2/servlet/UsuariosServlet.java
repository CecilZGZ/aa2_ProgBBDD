package com.jbes.aa2.servlet;

import com.jbes.aa2.dao.UsuarioDAO;
import com.jbes.aa2.dao.UsuarioImplDAO;
import com.jbes.aa2.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/gestion-usuarios")
public class UsuariosServlet extends HttpServlet {
    private UsuarioDAO usuarioDAO = new UsuarioImplDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogueado");
        if (usuario == null || !"Administrador".equals(usuario.getRol())) { response.sendRedirect("index.jsp"); return; }

        String action = request.getParameter("action");
        if ("borrar".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            usuarioDAO.eliminar(id);
            response.sendRedirect("gestion-usuarios");
            return;
        }
        request.setAttribute("listaUsuarios", usuarioDAO.obtenerTodos());
        request.getRequestDispatcher("gestion-usuarios.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario nuevo = new Usuario();
        nuevo.setNombreUsuario(request.getParameter("nombreUsuario"));
        nuevo.setContrasena(request.getParameter("contrasena"));
        nuevo.setRol(request.getParameter("rol"));
        usuarioDAO.registrar(nuevo);
        response.sendRedirect("gestion-usuarios");
    }
}