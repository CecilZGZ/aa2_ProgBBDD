package com.jbes.aa2.servlet;

import com.jbes.aa2.dao.PokemonDAO;
import com.jbes.aa2.dao.PokemonImplDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/borrado-pokemon")
public class BorradoPokemonServlet extends HttpServlet {

    private PokemonDAO pokemonDAO;

    @Override
    public void init() throws ServletException {
        pokemonDAO = new PokemonImplDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        pokemonDAO.eliminar(id);

        response.sendRedirect("listado-pokemon");
    }
}