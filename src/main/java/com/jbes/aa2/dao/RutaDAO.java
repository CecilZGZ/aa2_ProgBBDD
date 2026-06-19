package com.jbes.aa2.dao;

import com.jbes.aa2.model.Ruta;
import java.util.List;

public interface RutaDAO {
    List<Ruta> obtenerTodas();
    boolean registrar(Ruta ruta );
    boolean eliminar(int id);
}
