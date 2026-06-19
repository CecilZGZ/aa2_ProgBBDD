package com.jbes.aa2.dao;

import com.jbes.aa2.model.Ruta;
import com.jbes.aa2.util.ConexionBBDD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RutaImplDAO implements RutaDAO {

    public List<Ruta> obtenerTodas() {
        List<Ruta> lista = new ArrayList<>();
        String sql = "SELECT * FROM rutas";
        try (Connection conexion = ConexionBBDD.getConexion();
             PreparedStatement statement = conexion.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Ruta ruta = new Ruta();
                ruta.setId(resultSet.getInt("id"));
                ruta.setNombre(resultSet.getString("nombre"));
                ruta.setTieneAgua(resultSet.getBoolean("tiene_agua"));
                ruta.setClimaPrimario(resultSet.getString("clima_primario"));
                ruta.setCantidadEntrenadores(resultSet.getInt("cantidad_entrenadores"));
                ruta.setEntorno(resultSet.getString("entorno"));
                ruta.setNivelMin(resultSet.getInt("nivel_min"));
                ruta.setNivelMax(resultSet.getInt("nivel_max"));
                ruta.setIdRegion(resultSet.getInt("id_region"));
                lista.add(ruta);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    public boolean registrar(Ruta r) {
        String sql = "INSERT INTO rutas (nombre, tiene_agua, clima_primario, cantidad_entrenadores, entorno, nivel_min, nivel_max, id_region) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conexion = ConexionBBDD.getConexion();
             PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, r.getNombre());
            statement.setBoolean(2, r.isTieneAgua());
            statement.setString(3, r.getClimaPrimario());
            statement.setInt(4, r.getCantidadEntrenadores());
            statement.setString(5, r.getEntorno());
            statement.setInt(6, r.getNivelMin());
            statement.setInt(7, r.getNivelMax());
            statement.setInt(8, r.getIdRegion());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM rutas WHERE id = ?";
        try (Connection conexion = ConexionBBDD.getConexion();
             PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }

    @Override
    public List<Ruta> buscador(String query) {
        List<Ruta> lista = new ArrayList<>();

        String sql = "SELECT * FROM rutas WHERE nombre LIKE ? OR clima_primario LIKE ?";

        try (Connection conexion = ConexionBBDD.getConexion();
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            String filtro = (query != null && !query.trim().isEmpty()) ? "%" + query + "%" : "%";
            statement.setString(1, filtro);
            statement.setString(2, filtro);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Ruta ruta = new Ruta();
                    ruta.setId(rs.getInt("id"));
                    ruta.setNombre(rs.getString("nombre"));
                    ruta.setTieneAgua(rs.getBoolean("tiene_agua"));
                    ruta.setClimaPrimario(rs.getString("clima_primario"));
                    ruta.setCantidadEntrenadores(rs.getInt("cantidad_entrenadores"));
                    ruta.setEntorno(rs.getString("entorno"));
                    ruta.setNivelMin(rs.getInt("nivel_min"));
                    ruta.setNivelMax(rs.getInt("nivel_max"));
                    ruta.setIdRegion(rs.getInt("id_region"));
                    lista.add(ruta);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar Rutas: " + e.getMessage());
        }
        return lista;
    }
}
