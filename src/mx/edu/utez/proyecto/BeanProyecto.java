

package mx.edu.utez.proyecto;

import mx.edu.utez.persona.BeanPersona;

import java.util.List;

public class BeanProyecto {
    private int idProyecto;
    private String identificador;
    private String nombre;
    private String nombreDelCliente;
    private String correoDelCliente;
    private String numTelefonico;
    private String direccionCliente;
    private String fechaInicio;
    private String fechaFin;
    private String descripcion;
    private String costo;
    private int estado;
    private List<BeanPersona> aps;
    private List<BeanPersona> rds;
    private List<BeanPersona> rapes;

    public List<BeanPersona> getAps() {
        return aps;
    }

    public void setAps(List<BeanPersona> aps) {
        this.aps = aps;
    }

    public List<BeanPersona> getRds() {
        return rds;
    }

    public void setRds(List<BeanPersona> rds) {
        this.rds = rds;
    }

    public List<BeanPersona> getRapes() {
        return rapes;
    }

    public void setRapes(List<BeanPersona> rapes) {
        this.rapes = rapes;
    }

    public BeanProyecto() {
    }

    public BeanProyecto(int idProyecto, String identificador, String nombre, String nombreDelCliente, String correoDelCliente, String numTelefonico, String direccionCliente, String fechaInicio, String fechaFin, String descripcion, String costo, int estado, List<BeanPersona> aps, List<BeanPersona> rds, List<BeanPersona> rapes) {
        this.idProyecto = idProyecto;
        this.identificador = identificador;
        this.nombre = nombre;
        this.nombreDelCliente = nombreDelCliente;
        this.correoDelCliente = correoDelCliente;
        this.numTelefonico = numTelefonico;
        this.direccionCliente = direccionCliente;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.descripcion = descripcion;
        this.costo = costo;
        this.estado = estado;
        this.aps = aps;
        this.rds = rds;
        this.rapes = rapes;
    }

    public int getIdProyecto() {
        return this.idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getIdentificador() {
        return this.identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreDelCliente() {
        return this.nombreDelCliente;
    }

    public void setNombreDelCliente(String nombreDelCliente) {
        this.nombreDelCliente = nombreDelCliente;
    }

    public String getCorreoDelCliente() {
        return this.correoDelCliente;
    }

    public void setCorreoDelCliente(String correoDelCliente) {
        this.correoDelCliente = correoDelCliente;
    }

    public String getNumTelefonico() {
        return this.numTelefonico;
    }

    public void setNumTelefonico(String numTelefonico) {
        this.numTelefonico = numTelefonico;
    }

    public String getDireccionCliente() {
        return this.direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public String getFechaInicio() {
        return this.fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return this.fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCosto() {
        return this.costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public int getEstado() {
        return this.estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
