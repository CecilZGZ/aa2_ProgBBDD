package com.jbes.aa2.dao;

import com.jbes.aa2.model.Pokemon;
import com.jbes.aa2.util.ConexionBBDD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PokemonImplDAO implements PokemonDAO {

    @Override
    public boolean registrar(Pokemon pokemon) {
        String sql = "INSERT INTO pokemon (numero_pokedex, nombre, primer_tipo, segundo_tipo, generacion, tiene_evolucion, descripcion, altura, peso, id_region, imagen) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
            statement.setString(11, pokemon.getImagen());

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
                pokemon.setImagen(resultSet.getString("imagen"));

                lista.add(pokemon);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el listado: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public Pokemon obtenerPorId(int id) {

        Pokemon pokemonSeleccionado = null;
        String sql = "SELECT * FROM pokemon WHERE id = ?";

        try (Connection conexion = ConexionBBDD.getConexion();
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    pokemonSeleccionado = new Pokemon();
                    pokemonSeleccionado.setId(resultSet.getInt("id"));
                    pokemonSeleccionado.setNumeroPokedex(resultSet.getInt("numero_pokedex"));
                    pokemonSeleccionado.setNombre(resultSet.getString("nombre"));
                    pokemonSeleccionado.setPrimerTipo(resultSet.getString("primer_tipo"));
                    pokemonSeleccionado.setSegundoTipo(resultSet.getString("segundo_tipo"));
                    pokemonSeleccionado.setGeneracion(resultSet.getString("generacion"));
                    pokemonSeleccionado.setTieneEvolucion(resultSet.getBoolean("tiene_evolucion"));
                    pokemonSeleccionado.setDescripcion(resultSet.getString("descripcion"));
                    pokemonSeleccionado.setAltura(resultSet.getDouble("altura"));
                    pokemonSeleccionado.setPeso(resultSet.getDouble("peso"));
                    pokemonSeleccionado.setIdRegion(resultSet.getInt("id_region"));
                    pokemonSeleccionado.setImagen(resultSet.getString("imagen"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el Pokémon por ID: " + e.getMessage());
        }
        return pokemonSeleccionado;
    }

    @Override
    public boolean modificar(Pokemon pokemon) {

        String sql = "UPDATE pokemon SET numero_pokedex=?, nombre=?, primer_tipo=?, segundo_tipo=?, generacion=?, tiene_evolucion=?, descripcion=?, altura=?, peso=?, id_region=?, imagen=? WHERE id=?";

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
            statement.setString(11, pokemon.getImagen());
            statement.setInt(12, pokemon.getId());

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al modificar el Pokémon: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM regiones WHERE id = ?";
        try (Connection conexion = ConexionBBDD.getConexion();
             PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }

    @Override
    public List<Pokemon> buscador(String nombre, String tipo) {
        List<Pokemon> lista = new ArrayList<>();

        String sql = "SELECT * FROM pokemon WHERE nombre LIKE ? AND (primer_tipo LIKE ? OR IFNULL(segundo_tipo, '') LIKE ?) ORDER BY numero_pokedex ASC";

        try (Connection conexion = ConexionBBDD.getConexion();
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            String filtroNombre = (nombre != null && !nombre.isEmpty()) ? "%" + nombre + "%" : "%";
            String filtroTipo = (tipo != null && !tipo.isEmpty()) ? "%" + tipo + "%" : "%";

            statement.setString(1, filtroNombre);
            statement.setString(2, filtroTipo);
            statement.setString(3, filtroTipo);

            try (ResultSet resultSet = statement.executeQuery()) {
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
                    pokemon.setImagen(resultSet.getString("imagen"));
                    lista.add(pokemon);
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

}
