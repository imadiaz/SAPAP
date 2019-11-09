package mx.edu.utez.proyecto;

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
    private boolean estado;

    public BeanProyecto() {
    }

    public BeanProyecto(int idProyecto, String identificador, String nombre, String nombreDelCliente, String correoDelCliente, String numTelefonico, String direccionCliente, String fechaInicio, String fechaFin, String descripcion, String costo, boolean estado) {
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
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreDelCliente() {
        return nombreDelCliente;
    }

    public void setNombreDelCliente(String nombreDelCliente) {
        this.nombreDelCliente = nombreDelCliente;
    }

    public String getCorreoDelCliente() {
        return correoDelCliente;
    }

    public void setCorreoDelCliente(String correoDelCliente) {
        this.correoDelCliente = correoDelCliente;
    }

    public String getNumTelefonico() {
        return numTelefonico;
    }

    public void setNumTelefonico(String numTelefonico) {
        this.numTelefonico = numTelefonico;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
