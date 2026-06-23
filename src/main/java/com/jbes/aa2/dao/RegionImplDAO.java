package com.jbes.aa2.dao;

import com.jbes.aa2.model.Region;
import com.jbes.aa2.util.ConexionBBDD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegionImplDAO implements RegionDAO {

    public List<Region> obtenerTodas() {
        List<Region> lista = new ArrayList<>();
        String sql = "SELECT * FROM regiones";
        try (Connection conexion = ConexionBBDD.getConexion();
             PreparedStatement statement = conexion.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Region region = new Region();
                region.setId(resultSet.getInt("id"));
                region.setNombre(resultSet.getString("nombre"));
                region.setIniciales(resultSet.getString("iniciales"));
                region.setVillanos(resultSet.getString("villanos"));
                region.setProfesor(resultSet.getString("profesor"));
                region.setVideojuegoOrigen(resultSet.getString("videojuego_origen"));
                region.setFechaLanzamiento(resultSet.getDate("fecha_lanzamiento"));
                region.setTieneConcursos(resultSet.getBoolean("tiene_concursos"));
                lista.add(region);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    public boolean registrar(Region region) {
        String sql = "INSERT INTO regiones (nombre, iniciales, villanos, profesor, videojuego_origen, fecha_lanzamiento, tiene_concursos) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conexion = ConexionBBDD.getConexion();
             PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, region.getNombre());
            statement.setString(2, region.getIniciales());
            statement.setString(3, region.getVillanos());
            statement.setString(4, region.getProfesor());
            statement.setString(5, region.getVideojuegoOrigen());
            statement.setDate(6, region.getFechaLanzamiento());
            statement.setBoolean(7, region.isTieneConcursos());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM regiones WHERE id = ?";
        try (Connection conexion = ConexionBBDD.getConexion();
             PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }

    @Override
    public List<Region> buscador(String nombre, String profesor) {
        List<Region> lista = new ArrayList<>();

        String sql = "SELECT * FROM regiones WHERE IFNULL(nombre, '') LIKE ? AND IFNULL(profesor, '') LIKE ?";

        try (Connection conexion = ConexionBBDD.getConexion();
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setString(1, (nombre != null && !nombre.isEmpty()) ? "%" + nombre + "%" : "%");
            statement.setString(2, (profesor != null && !profesor.isEmpty()) ? "%" + profesor + "%" : "%");

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Region region = new Region();
                    region.setId(resultSet.getInt("id"));
                    region.setNombre(resultSet.getString("nombre"));
                    region.setIniciales(resultSet.getString("iniciales"));
                    region.setVillanos(resultSet.getString("villanos"));
                    region.setProfesor(resultSet.getString("profesor"));
                    region.setVideojuegoOrigen(resultSet.getString("videojuego_origen"));
                    region.setFechaLanzamiento(resultSet.getDate("fecha_lanzamiento"));
                    region.setTieneConcursos(resultSet.getBoolean("tiene_concursos"));
                    lista.add(region);
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }
}
