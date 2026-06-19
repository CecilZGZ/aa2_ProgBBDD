package com.jbes.aa2.servlet;

import com.jbes.aa2.dao.RegionDAO;
import com.jbes.aa2.dao.RegionImplDAO;
import com.jbes.aa2.dao.RutaDAO;
import com.jbes.aa2.dao.RutaImplDAO;
import com.jbes.aa2.model.Ruta;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/gestion-rutas")
public class RutasServlet extends HttpServlet {

    private RutaDAO rutaDAO = new RutaImplDAO();
    private RegionDAO regionDAO = new RegionImplDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("borrar".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            rutaDAO.eliminar(id);
            response.sendRedirect("gestion-rutas");
            return;
        }

        request.setAttribute("listaRutas", rutaDAO.obtenerTodas());
        request.setAttribute("listaRegiones", regionDAO.obtenerTodas());
        request.getRequestDispatcher("gestion-rutas.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Ruta ruta = new Ruta();
        ruta.setNombre(request.getParameter("nombre"));
        ruta.setTieneAgua(request.getParameter("tieneAgua") != null);
        ruta.setClimaPrimario(request.getParameter("climaPrimario"));
        ruta.setCantidadEntrenadores(Integer.parseInt(request.getParameter("cantidadEntrenadores")));
        ruta.setEntorno(request.getParameter("entorno"));
        ruta.setNivelMin(Integer.parseInt(request.getParameter("nivelMin")));
        ruta.setNivelMax(Integer.parseInt(request.getParameter("nivelMax")));
        ruta.setIdRegion(Integer.parseInt(request.getParameter("idRegion")));

        rutaDAO.registrar(ruta);
        response.sendRedirect("gestion-rutas");
    }
}