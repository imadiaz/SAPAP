package mx.edu.utez.persona;

import mx.edu.utez.horario.BeanHorario;

public class BeanPersona {

    private BeanHorario horario;
    private int idPersona;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String fechaDeNacimiento;
    private String correoInstitucional;
    private String contrasenia;
    private String matricula;
    private String correoPersonal;
    private String numeroTelefonico;
    private String numeroCasa;
    private String carreraDeEgreso;
    private String universidadDeEgreso;
    private String desempenio;
    private String fechaDeIngreso;
    private String fechaDeEgreso;
    private String codigo;

    public BeanPersona() {
    }

    public BeanPersona(BeanHorario horario, int idPersona, String nombre, String primerApellido, String segundoApellido, String fechaDeNacimiento, String correoInstitucional, String contrasenia, String matricula, String correoPersonal, String numeroTelefonico, String numeroCasa, String carreraDeEgreso, String universidadDeEgreso, String desempenio, String fechaDeIngreso, String fechaDeEgreso, String codigo) {
        this.horario = horario;
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.correoInstitucional = correoInstitucional;
        this.contrasenia = contrasenia;
        this.matricula = matricula;
        this.correoPersonal = correoPersonal;
        this.numeroTelefonico = numeroTelefonico;
        this.numeroCasa = numeroCasa;
        this.carreraDeEgreso = carreraDeEgreso;
        this.universidadDeEgreso = universidadDeEgreso;
        this.desempenio = desempenio;
        this.fechaDeIngreso = fechaDeIngreso;
        this.fechaDeEgreso = fechaDeEgreso;
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public BeanHorario getHorario() {
        return horario;
    }

    public void setHorario(BeanHorario horario) {
        this.horario = horario;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getCorreoInstitucional() {
        return correoInstitucional;
    }

    public void setCorreoInstitucional(String correoInstitucional) {
        this.correoInstitucional = correoInstitucional;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCorreoPersonal() {
        return correoPersonal;
    }

    public void setCorreoPersonal(String correoPersonal) {
        this.correoPersonal = correoPersonal;
    }

    public String getNumeroTelefonico() {
        return numeroTelefonico;
    }

    public void setNumeroTelefonico(String numeroTelefonico) {
        this.numeroTelefonico = numeroTelefonico;
    }

    public String getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(String numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

    public String getCarreraDeEgreso() {
        return carreraDeEgreso;
    }

    public void setCarreraDeEgreso(String carreraDeEgreso) {
        this.carreraDeEgreso = carreraDeEgreso;
    }

    public String getUniversidadDeEgreso() {
        return universidadDeEgreso;
    }

    public void setUniversidadDeEgreso(String universidadDeEgreso) {
        this.universidadDeEgreso = universidadDeEgreso;
    }

    public String getDesempenio() {
        return desempenio;
    }

    public void setDesempenio(String desempenio) {
        this.desempenio = desempenio;
    }

    public String getFechaDeIngreso() {
        return fechaDeIngreso;
    }

    public void setFechaDeIngreso(String fechaDeIngreso) {
        this.fechaDeIngreso = fechaDeIngreso;
    }

    public String getFechaDeEgreso() {
        return fechaDeEgreso;
    }

    public void setFechaDeEgreso(String fechaDeEgreso) {
        this.fechaDeEgreso = fechaDeEgreso;
    }
}
