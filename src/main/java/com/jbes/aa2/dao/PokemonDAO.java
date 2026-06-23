package com.jbes.aa2.dao;

import com.jbes.aa2.model.Pokemon;
import java.util.List;

public interface PokemonDAO {

    boolean registrar(Pokemon pokemon);

    List<Pokemon> obtenerTodos();

    Pokemon obtenerPorId(int id);

    boolean modificar(Pokemon pokemon);

    boolean eliminar(int id);

    List<Pokemon> buscador(String nombre, String tipo);
}
