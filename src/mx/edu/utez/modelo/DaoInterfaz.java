package mx.edu.utez.modelo;

import java.util.ArrayList;

public interface DaoInterfaz<T> {

    boolean registrar(T bean);
    boolean eliminar(T bean);
    boolean actualizar(T bean);
    ArrayList<T> getLista();
    T buquedaByID(int id);
}
