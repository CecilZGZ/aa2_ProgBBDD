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
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogueado");
        if (usuario == null) { response.sendRedirect("login"); return; }

        String action = request.getParameter("action");
        if ("borrar".equals(action) && "Administrador".equals(usuario.getRol())) {
            int id = Integer.parseInt(request.getParameter("id"));
            regionDAO.eliminar(id);
            response.sendRedirect("gestion-regiones");
            return;
        }

        String qNombre = request.getParameter("qNombre");
        String qProf = request.getParameter("qProf");

        List<Region> lista;

        if ((qNombre == null || qNombre.trim().isEmpty()) && (qProf == null || qProf.trim().isEmpty())) {
            lista = regionDAO.obtenerTodas();
        } else {
            lista = regionDAO.buscador(qNombre, qProf);
        }

        request.setAttribute("listaRegiones", lista);
        request.setAttribute("qNombre", qNombre != null ? qNombre : "");
        request.setAttribute("qProf", qProf != null ? qProf : "");
        request.getRequestDispatcher("gestion-regiones.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogueado");

        if (usuario == null || !"Administrador".equals(usuario.getRol())) {
            response.sendRedirect("gestion-regiones");
            return;
        }

        Region region = new Region();
        region.setNombre(request.getParameter("nombre"));
        region.setIniciales(request.getParameter("iniciales"));
        region.setVillanos(request.getParameter("villanos"));
        region.setProfesor(request.getParameter("profesor"));
        region.setVideojuegoOrigen(request.getParameter("videojuegoOrigen"));

        String fechaStr = request.getParameter("fechaLanzamiento");
        if (fechaStr != null && !fechaStr.isEmpty()) { region.setFechaLanzamiento(java.sql.Date.valueOf(fechaStr)); }
        region.setTieneConcursos(request.getParameter("tieneConcursos") != null);

        regionDAO.registrar(region);
        response.sendRedirect("gestion-regiones");
    }
}
