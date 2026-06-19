package com.jbes.aa2.dao;
import com.jbes.aa2.model.Usuario;

import java.util.List;

public interface UsuarioDAO {

    Usuario autenticar(String nombreUsuario, String contrasena);

    List<Usuario> obtenerTodos();
    boolean registrar(Usuario usuario);
    boolean eliminar(int id);
}
