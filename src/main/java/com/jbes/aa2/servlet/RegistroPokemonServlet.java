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

@WebServlet("/registro-pokemon")
public class RegistroPokemonServlet extends HttpServlet {

    private PokemonDAO pokemonDAO;

    @Override
    public void init() throws ServletException {
        pokemonDAO = new PokemonImplDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        int numero = Integer.parseInt(request.getParameter("numero"));
        String nombre = request.getParameter("nombre");
        String tipo1 = request.getParameter("tipo1");
        double altura = Double.parseDouble(request.getParameter("altura"));
        double peso = Double.parseDouble(request.getParameter("peso"));
        String descripcion = request.getParameter("descripcion");

        String generacion = request.getParameter("generacion");
        int idRegion = Integer.parseInt(request.getParameter("idRegion"));
        boolean tieneEvolucion = Boolean.parseBoolean(request.getParameter("tieneEvolucion"));

        Pokemon nuevoPokemon = new Pokemon();
        nuevoPokemon.setNumeroPokedex(numero);
        nuevoPokemon.setNombre(nombre);
        nuevoPokemon.setPrimerTipo(tipo1);
        nuevoPokemon.setAltura(altura);
        nuevoPokemon.setPeso(peso);
        nuevoPokemon.setDescripcion(descripcion);
        nuevoPokemon.setGeneracion(generacion);
        nuevoPokemon.setIdRegion(idRegion);
        nuevoPokemon.setTieneEvolucion(tieneEvolucion);

        boolean exito = pokemonDAO.registrar(nuevoPokemon);

        if (exito) {
            response.sendRedirect("index.jsp");
        } else {
            response.sendRedirect("registro-pokemon.jsp");
        }
    }
}