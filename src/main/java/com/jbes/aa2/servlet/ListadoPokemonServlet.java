package com.jbes.aa2.servlet;

import com.jbes.aa2.dao.PokemonDAO;
import com.jbes.aa2.dao.PokemonImplDAO;
import com.jbes.aa2.model.Pokemon;
import com.jbes.aa2.model.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/listado-pokemon")
public class ListadoPokemonServlet extends HttpServlet {

    private PokemonDAO pokemonDAO = new PokemonImplDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario u = (Usuario) request.getSession().getAttribute("usuarioLogueado");
        if (u == null) { response.sendRedirect("login"); return; }

        String qNombre = request.getParameter("qNombre");
        String qTipo = request.getParameter("qTipo");

        List<Pokemon> lista = pokemonDAO.buscador(qNombre, qTipo);

        request.setAttribute("listaPokemon", lista);
        request.setAttribute("qNombre", qNombre != null ? qNombre : "");
        request.setAttribute("qTipo", qTipo != null ? qTipo : "");

        request.getRequestDispatcher("listado-pokemon.jsp").forward(request, response);
    }
}