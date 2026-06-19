package com.jbes.aa2.dao;

import com.jbes.aa2.model.Pokemon;
import com.jbes.aa2.util.ConexionBBDD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PokemonImplDAO implements PokemonDAO {

    @Override
    public boolean registrar(Pokemon pokemon) {

        String sql = "INSERT INTO pokemon (numero_pokedex, nombre, primer_tipo, segundo_tipo, generacion, tiene_evolucion, descripcion, altura, peso, id_region, id_evoluciona_de) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexion = ConexionBBDD.getConexion();
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setInt(1, pokemon.getNumeroPokedex());
            statement.setString(2, pokemon.getNombre());
            statement.setString(3, pokemon.getPrimerTipo());
            statement.setString(4, pokemon.getSegundoTipo());
            statement.setString(5, pokemon.getGeneracion());
            statement.setBoolean(6, pokemon.isTieneEvolucion());
            statement.setString(7, pokemon.getDescripcion());
            statement.setDouble(8, pokemon.getAltura());
            statement.setDouble(9, pokemon.getPeso());
            statement.setInt(10, pokemon.getIdRegion());

            if (pokemon.getIdEvolucionaDe() == 0) {
                statement.setNull(11, Types.INTEGER);
            } else {
                statement.setInt(11, pokemon.getIdEvolucionaDe());
            }

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
                Pokemon p = new Pokemon();
                p.setId(resultSet.getInt("id"));
                p.setNumeroPokedex(resultSet.getInt("numero_pokedex"));
                p.setNombre(resultSet.getString("nombre"));
                p.setPrimerTipo(resultSet.getString("primer_tipo"));
                p.setSegundoTipo(resultSet.getString("segundo_tipo"));
                p.setGeneracion(resultSet.getString("generacion"));
                p.setTieneEvolucion(resultSet.getBoolean("tiene_evolucion"));
                p.setDescripcion(resultSet.getString("descripcion"));
                p.setAltura(resultSet.getDouble("altura"));
                p.setPeso(resultSet.getDouble("peso"));
                p.setIdRegion(resultSet.getInt("id_region"));

                lista.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el listado: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public Pokemon obtenerPorId(int id) {
        return null;
    }

    @Override
    public boolean modificar(Pokemon pokemon) {
        return false;
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