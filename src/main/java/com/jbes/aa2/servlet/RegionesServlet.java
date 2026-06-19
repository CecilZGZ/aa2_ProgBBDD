package com.jbes.aa2.servlet;

import com.jbes.aa2.dao.RegionDAO;
import com.jbes.aa2.dao.RegionImplDAO;
import com.jbes.aa2.model.Region;
import com.jbes.aa2.model.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/gestion-regiones")
public class RegionesServlet extends HttpServlet {

    private RegionDAO regionDAO = new RegionImplDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario u = (Usuario) request.getSession().getAttribute("usuarioLogueado");
        if (u == null) { response.sendRedirect("login"); return; }

        String action = request.getParameter("action");
        if ("borrar".equals(action) && "Administrador".equals(u.getRol())) {
            int id = Integer.parseInt(request.getParameter("id"));
            regionDAO.eliminar(id);
            response.sendRedirect("gestion-regiones");
            return;
        }

        String query = request.getParameter("q");

        List<Region> lista = regionDAO.buscador(query);

        request.setAttribute("listaRegiones", lista);
        request.setAttribute("searchQuery", query != null ? query : "");
        request.getRequestDispatcher("gestion-regiones.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario u = (Usuario) request.getSession().getAttribute("usuarioLogueado");

        if (u == null || !"Administrador".equals(u.getRol())) {
            response.sendRedirect("gestion-regiones");
            return;
        }

        Region r = new Region();
        r.setNombre(request.getParameter("nombre"));
        r.setIniciales(request.getParameter("iniciales"));
        r.setVillanos(request.getParameter("villanos"));
        r.setProfesor(request.getParameter("profesor"));
        r.setVideojuegoOrigen(request.getParameter("videojuegoOrigen"));

        String fechaStr = request.getParameter("fechaLanzamiento");
        if (fechaStr != null && !fechaStr.isEmpty()) { r.setFechaLanzamiento(java.sql.Date.valueOf(fechaStr)); }
        r.setTieneConcursos(request.getParameter("tieneConcursos") != null);

        regionDAO.registrar(r);
        response.sendRedirect("gestion-regiones");
    }
}
