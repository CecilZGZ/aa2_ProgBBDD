package com.jbes.aa2.servlet;

import com.jbes.aa2.dao.PokemonDAO;
import com.jbes.aa2.dao.PokemonImplDAO;
import com.jbes.aa2.model.Pokemon;
import com.jbes.aa2.model.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@WebServlet("/registro-pokemon")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class RegistroPokemonServlet extends HttpServlet {

    private PokemonDAO pokemonDAO = new PokemonImplDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogueado");
        if (usuario == null || !"Administrador".equals(usuario.getRol())) { response.sendRedirect("listado-pokemon"); return; }
        request.getRequestDispatcher("registro-pokemon.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Pokemon pokemon = new Pokemon();
        pokemon.setNumeroPokedex(Integer.parseInt(request.getParameter("numeroPokedex")));
        pokemon.setNombre(request.getParameter("nombre"));
        pokemon.setPrimerTipo(request.getParameter("primerTipo"));
        pokemon.setSegundoTipo(request.getParameter("segundoTipo"));
        pokemon.setGeneracion(request.getParameter("generacion"));
        pokemon.setTieneEvolucion(request.getParameter("tieneEvolucion") != null);
        pokemon.setDescripcion(request.getParameter("descripcion"));
        pokemon.setAltura(Double.parseDouble(request.getParameter("altura")));
        pokemon.setPeso(Double.parseDouble(request.getParameter("peso")));
        pokemon.setIdRegion(Integer.parseInt(request.getParameter("idRegion")));

        Part filePart = request.getPart("imagen");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        if (fileName != null && !fileName.isEmpty()) {

            String uploadPath = getServletContext().getRealPath("") + File.separator + "imagenes";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();

            filePart.write(uploadPath + File.separator + fileName);
            pokemon.setImagen(fileName);
        } else {
            pokemon.setImagen("default.gif");
        }

        pokemonDAO.registrar(pokemon);
        response.sendRedirect("listado-pokemon");
    }
}