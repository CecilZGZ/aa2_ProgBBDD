package com.jbes.aa2.dao;
import com.jbes.aa2.model.Usuario;

public interface UsuarioDAO {

    Usuario autenticar(String nombreUsuario, String contrasena);
}
