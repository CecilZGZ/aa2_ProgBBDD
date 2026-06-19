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

@WebServlet("/perfil")
public class PerfilServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO = new UsuarioImplDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("usuarioLogueado") == null) {
            response.sendRedirect("login");
            return;
        }
        request.getRequestDispatcher("perfil.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogueado");
        if (usuario == null) { response.sendRedirect("login"); return; }

        String nuevaPass = request.getParameter("nuevaContrasena");

        if (nuevaPass != null && !nuevaPass.trim().isEmpty()) {
            boolean exito = usuarioDAO.cambiarContrasena(usuario.getId(), nuevaPass.trim());
            if (exito) {
                request.setAttribute("mensajeExito", "Contraseña modificada con éxito.");
                usuario.setContrasena(nuevaPass.trim());
                request.getSession().setAttribute("usuarioLogueado", usuario);
            } else {
                request.setAttribute("mensajeError", "No se pudo actualizar la contraseña en la Base de Datos.");
            }
        } else {
            request.setAttribute("mensajeError", "La contraseña no puede estar vacía.");
        }

        request.getRequestDispatcher("perfil.jsp").forward(request, response);
    }
}