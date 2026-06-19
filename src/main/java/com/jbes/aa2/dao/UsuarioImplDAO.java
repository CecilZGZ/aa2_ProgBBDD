package com.jbes.aa2.dao;

import com.jbes.aa2.model.Usuario;
import com.jbes.aa2.util.ConexionBBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioImplDAO implements UsuarioDAO {

    @Override
    public Usuario autenticar(String nombreUsuario, String contrasena) {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuarios WHERE nombre_usuario = ? AND contrasena = ?";

        try (Connection conexion = ConexionBBDD.getConexion();
             PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, nombreUsuario);
            statement.setString(2, contrasena);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    usuario = new Usuario();
                    usuario.setId(resultSet.getInt("id"));
                    usuario.setNombreUsuario(resultSet.getString("nombre_usuario"));
                    usuario.setContrasena(resultSet.getString("contrasena"));
                    usuario.setRol(resultSet.getString("rol"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }
}
