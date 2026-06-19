package com.jbes.aa2.servlet;

import com.jbes.aa2.dao.PokemonDAO;
import com.jbes.aa2.dao.PokemonImplDAO;
import com.jbes.aa2.model.Pokemon;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;

@WebServlet("/modificar-pokemon")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 10)
public class ModificarPokemonServlet extends HttpServlet {

    private PokemonDAO pokemonDAO;

    @Override
    public void init() throws ServletException {
        pokemonDAO = new PokemonImplDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Pokemon pokemon = pokemonDAO.obtenerPorId(id);

        request.setAttribute("pokemonEditar", pokemon);
        request.getRequestDispatcher("modificar-pokemon.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));

            Pokemon pokemon = new Pokemon();
            pokemon.setId(id);
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
            String imagenActual = request.getParameter("imagenActual");
            String fileName = (imagenActual != null && !imagenActual.isEmpty()) ? imagenActual : "default.gif";

            if (filePart != null && filePart.getSize() > 0) {
                String submittedFileName = filePart.getSubmittedFileName();
                if (submittedFileName != null && !submittedFileName.isEmpty()) {
                    fileName = Paths.get(submittedFileName).getFileName().toString();
                    String uploadPath = getServletContext().getRealPath("/") + "imagenes";
                    File uploadDir = new File(uploadPath);
                    if (!uploadDir.exists()) {
                        uploadDir.mkdirs();
                    }
                    filePart.write(uploadPath + File.separator + fileName);
                }
            }
            pokemon.setImagen(fileName);

            pokemonDAO.modificar(pokemon);
            response.sendRedirect("listado-pokemon");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Error al modificar los datos del Pokémon");
        }
    }
}