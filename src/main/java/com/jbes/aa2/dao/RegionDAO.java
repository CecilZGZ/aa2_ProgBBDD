package com.jbes.aa2.dao;

import com.jbes.aa2.model.Region;
import java.util.List;

public interface RegionDAO {
    List<Region> obtenerTodas();
    boolean registrar(Region r);
    boolean eliminar(int id);
    List<Region> buscador(String nombre, String profesor);
}
