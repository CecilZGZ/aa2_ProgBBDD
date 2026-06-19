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

@WebServlet("/modificar-pokemon")
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
        request.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));
        int numero = Integer.parseInt(request.getParameter("numero"));
        String nombre = request.getParameter("nombre");
        String tipo1 = request.getParameter("tipo1");
        String tipo2 = request.getParameter("tipo2");
        String generacion = request.getParameter("generacion");
        boolean tieneEvolucion = Boolean.parseBoolean(request.getParameter("tieneEvolucion"));
        int idRegion = Integer.parseInt(request.getParameter("idRegion"));

        double altura = Double.parseDouble(request.getParameter("altura"));
        double peso = Double.parseDouble(request.getParameter("peso"));
        String descripcion = request.getParameter("descripcion");

        Pokemon pokemonActualizado = new Pokemon();
        pokemonActualizado.setId(id);
        pokemonActualizado.setNumeroPokedex(numero);
        pokemonActualizado.setNombre(nombre);
        pokemonActualizado.setPrimerTipo(tipo1);
        pokemonActualizado.setSegundoTipo(tipo2);
        pokemonActualizado.setGeneracion(generacion);
        pokemonActualizado.setTieneEvolucion(tieneEvolucion);
        pokemonActualizado.setIdRegion(idRegion);
        pokemonActualizado.setAltura(altura);
        pokemonActualizado.setPeso(peso);
        pokemonActualizado.setDescripcion(descripcion);

        pokemonDAO.modificar(pokemonActualizado);

        response.sendRedirect("listado-pokemon");
    }
}