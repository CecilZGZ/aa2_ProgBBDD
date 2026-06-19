package com.jbes.aa2.servlet;

import com.jbes.aa2.dao.RegionDAO;
import com.jbes.aa2.dao.RegionImplDAO;
import com.jbes.aa2.dao.RutaDAO;
import com.jbes.aa2.dao.RutaImplDAO;
import com.jbes.aa2.model.Ruta;
import com.jbes.aa2.model.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/gestion-rutas")
public class RutasServlet extends HttpServlet {

    private RutaDAO rutaDAO = new RutaImplDAO();
    private RegionDAO regionDAO = new RegionImplDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogueado");
        if (usuario == null) { response.sendRedirect("login"); return; }

        String action = request.getParameter("action");
        if ("borrar".equals(action) && "Administrador".equals(usuario.getRol())) {
            rutaDAO.eliminar(Integer.parseInt(request.getParameter("id")));
            response.sendRedirect("gestion-rutas");
            return;
        }

        String qNombre = request.getParameter("qNombre");
        String qClima = request.getParameter("qClima");

        if ("null".equals(qNombre)) qNombre = "";
        if ("null".equals(qClima)) qClima = "";

        List<Ruta> lista = rutaDAO.buscador(
                qNombre != null ? qNombre.trim() : "",
                qClima != null ? qClima.trim() : ""
        );

        request.setAttribute("listaRutas", lista);
        request.setAttribute("qNombre", qNombre != null ? qNombre : "");
        request.setAttribute("qClima", qClima != null ? qClima : "");
        request.setAttribute("listaRegiones", regionDAO.obtenerTodas());

        request.getRequestDispatcher("gestion-rutas.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogueado");
        if (usuario == null || !"Administrador".equals(usuario.getRol())) {
            response.sendRedirect("gestion-rutas");
            return;
        }

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
