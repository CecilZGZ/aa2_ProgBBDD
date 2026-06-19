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
import java.util.List;

@WebServlet("/listado-pokemon")
public class ListadoPokemonServlet extends HttpServlet {

    private PokemonDAO pokemonDAO;

    @Override
    public void init() throws ServletException {
        pokemonDAO = new PokemonImplDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Pokemon> lista = pokemonDAO.obtenerTodos();

        request.setAttribute("listadoPokemon", lista);

        request.getRequestDispatcher("listado-pokemon.jsp").forward(request, response);
    }
}