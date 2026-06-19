package com.jbes.aa2.dao;

import com.jbes.aa2.model.Pokemon;
import com.jbes.aa2.util.ConexionBBDD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PokemonImplDAO implements PokemonDAO {

    @Override
    public boolean registrar(Pokemon pokemon) {
        String sql = "INSERT INTO pokemon (numero_pokedex, nombre, primer_tipo, segundo_tipo, generacion, tiene_evolucion, descripcion, altura, peso, id_region) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexion = ConexionBBDD.getConexion();
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setInt(1, pokemon.getNumeroPokedex());
            statement.setString(2, pokemon.getNombre());
            statement.setString(3, pokemon.getPrimerTipo());

            if (pokemon.getSegundoTipo() == null || pokemon.getSegundoTipo().trim().isEmpty()) {
                statement.setNull(4, java.sql.Types.VARCHAR);
            } else {
                statement.setString(4, pokemon.getSegundoTipo());
            }

            statement.setString(5, pokemon.getGeneracion());
            statement.setBoolean(6, pokemon.isTieneEvolucion());
            statement.setString(7, pokemon.getDescripcion());
            statement.setDouble(8, pokemon.getAltura());
            statement.setDouble(9, pokemon.getPeso());
            statement.setInt(10, pokemon.getIdRegion());

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al registrar el Pokémon: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Pokemon> obtenerTodos() {
        List<Pokemon> lista = new ArrayList<>();
        String sql = "SELECT * FROM pokemon ORDER BY numero_pokedex ASC";

        try (Connection conexion = ConexionBBDD.getConexion();
             PreparedStatement statement = conexion.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Pokemon pokemon = new Pokemon();
                pokemon.setId(resultSet.getInt("id"));
                pokemon.setNumeroPokedex(resultSet.getInt("numero_pokedex"));
                pokemon.setNombre(resultSet.getString("nombre"));
                pokemon.setPrimerTipo(resultSet.getString("primer_tipo"));
                pokemon.setSegundoTipo(resultSet.getString("segundo_tipo"));
                pokemon.setGeneracion(resultSet.getString("generacion"));
                pokemon.setTieneEvolucion(resultSet.getBoolean("tiene_evolucion"));
                pokemon.setDescripcion(resultSet.getString("descripcion"));
                pokemon.setAltura(resultSet.getDouble("altura"));
                pokemon.setPeso(resultSet.getDouble("peso"));
                pokemon.setIdRegion(resultSet.getInt("id_region"));

                lista.add(pokemon);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el listado: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public Pokemon obtenerPorId(int id) {

        Pokemon pokemonSelecionado = null;
        String sql = "SELECT * FROM pokemon WHERE id = ?";

        try (Connection conexion = ConexionBBDD.getConexion();
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    pokemonSelecionado = new Pokemon();
                    pokemonSelecionado.setId(resultSet.getInt("id"));
                    pokemonSelecionado.setNumeroPokedex(resultSet.getInt("numero_pokedex"));
                    pokemonSelecionado.setNombre(resultSet.getString("nombre"));
                    pokemonSelecionado.setPrimerTipo(resultSet.getString("primer_tipo"));
                    pokemonSelecionado.setSegundoTipo(resultSet.getString("segundo_tipo"));
                    pokemonSelecionado.setGeneracion(resultSet.getString("generacion"));
                    pokemonSelecionado.setTieneEvolucion(resultSet.getBoolean("tiene_evolucion"));
                    pokemonSelecionado.setDescripcion(resultSet.getString("descripcion"));
                    pokemonSelecionado.setAltura(resultSet.getDouble("altura"));
                    pokemonSelecionado.setPeso(resultSet.getDouble("peso"));
                    pokemonSelecionado.setIdRegion(resultSet.getInt("id_region"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el Pokémon por ID: " + e.getMessage());
        }
        return pokemonSelecionado;
    }

    @Override
    public boolean modificar(Pokemon pokemon) {
        String sql = "UPDATE pokemon SET numero_pokedex=?, nombre=?, primer_tipo=?, segundo_tipo=?, generacion=?, tiene_evolucion=?, descripcion=?, altura=?, peso=?, id_region=? WHERE id=?";

        try (Connection conexion = ConexionBBDD.getConexion();
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setInt(1, pokemon.getNumeroPokedex());
            statement.setString(2, pokemon.getNombre());
            statement.setString(3, pokemon.getPrimerTipo());

            if (pokemon.getSegundoTipo() == null || pokemon.getSegundoTipo().trim().isEmpty()) {
                statement.setNull(4, java.sql.Types.VARCHAR);
            } else {
                statement.setString(4, pokemon.getSegundoTipo());
            }
            statement.setString(5, pokemon.getGeneracion());
            statement.setBoolean(6, pokemon.isTieneEvolucion());
            statement.setString(7, pokemon.getDescripcion());
            statement.setDouble(8, pokemon.getAltura());
            statement.setDouble(9, pokemon.getPeso());
            statement.setInt(10, pokemon.getIdRegion());
            statement.setInt(11, pokemon.getId());

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al modificar el Pokémon: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM pokemon WHERE id = ?";

        try (Connection conexion = ConexionBBDD.getConexion();
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setInt(1, id);
            int filasAfectadas = statement.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar el Pokémon: " + e.getMessage());
            return false;
        }
    }

}