package mx.edu.utez.curso;

public class BeanCurso {
    private int idCurso;
    private String nombre;
    private String fecha;
    private String descripcion;
    private String tipoCurso;
    private String evidencia;
    private String referencia;

    private int idPersona;



    public BeanCurso() {
    }

    public BeanCurso(int idCurso, String nombre, String fecha, String descripcion, String tipoCurso, String evidencia, int idPersona) {
        this.idCurso = idCurso;
        this.nombre = nombre;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.tipoCurso = tipoCurso;
        this.evidencia = evidencia;
        this.idPersona = idPersona;
    }
    public BeanCurso(int idCurso, String nombre, String fecha, String descripcion, String tipoCurso, String evidencia, int idPersona,String referencia) {
        this.idCurso = idCurso;
        this.nombre = nombre;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.tipoCurso = tipoCurso;
        this.evidencia = evidencia;
        this.idPersona = idPersona;
        this.referencia=referencia;
    }

    public BeanCurso(String nombre, String fecha, String descripcion, String tipoCurso, String evidencia,String referencia ,int idPersona) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.tipoCurso = tipoCurso;
        this.evidencia = evidencia;
        this.referencia=referencia;
        this.idPersona = idPersona;
    }


    public BeanCurso(int idCurso, String nombre, String fecha, String descripcion, String tipoCurso, String evidencia) {
        this.idCurso = idCurso;
        this.nombre = nombre;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.tipoCurso = tipoCurso;
        this.evidencia=evidencia;
    }
    public BeanCurso(int idCurso, String nombre, String fecha, String descripcion, String tipoCurso, String evidencia,String referencia) {
        this.idCurso = idCurso;
        this.nombre = nombre;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.tipoCurso = tipoCurso;
        this.evidencia=evidencia;
        this.referencia=referencia;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }


    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getEvidencia() {
        return evidencia;
    }

    public void setEvidencia(String evidencia) {
        this.evidencia = evidencia;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoCurso() {
        return tipoCurso;
    }

    public void setTipoCurso(String tipoCurso) {
        this.tipoCurso = tipoCurso;
    }

    @Override
    public String toString() {
        return "BeanCurso{" +
                "idCurso=" + idCurso +
                ", nombre='" + nombre + '\'' +
                ", fecha='" + fecha + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", tipoCurso='" + tipoCurso + '\'' +
                ", evidencia='" + evidencia + '\'' +
                ", idPersona=" + idPersona +
                '}';
    }
}
