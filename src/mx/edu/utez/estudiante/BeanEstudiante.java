package mx.edu.utez.estudiante;

import mx.edu.utez.horario.BeanHorario;
import mx.edu.utez.persona.BeanPersona;

public class BeanEstudiante extends BeanPersona {

    private int idEstudiante;
    private String cuatrimestreIngreso;
    private String grupoActual;
    private String matricula;

    public BeanEstudiante() {
    }

    public BeanEstudiante(BeanHorario horario, int idPersona, String nombre, String primerApellido, String segundoApellido, String fechaDeNacimiento, String correoInstitucional, String contrasenia, String matricula, String correoPersonal, String numeroTelefonico, String numeroCasa, String carreraDeEgreso, String universidadDeEgreso, String desempenio, String fechaDeIngreso, String fechaDeEgreso, boolean estado, String codigo, int idEstudiante, String cuatrimestreIngreso, String grupoActual, String matricula1) {
        super(horario, idPersona, nombre, primerApellido, segundoApellido, fechaDeNacimiento, correoInstitucional, contrasenia, matricula, correoPersonal, numeroTelefonico, numeroCasa, carreraDeEgreso, universidadDeEgreso, desempenio, fechaDeIngreso, fechaDeEgreso, estado, codigo);
        this.idEstudiante = idEstudiante;
        this.cuatrimestreIngreso = cuatrimestreIngreso;
        this.grupoActual = grupoActual;
        this.matricula = matricula1;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getCuatrimestreIngreso() {
        return cuatrimestreIngreso;
    }

    public void setCuatrimestreIngreso(String cuatrimestreIngreso) {
        this.cuatrimestreIngreso = cuatrimestreIngreso;
    }

    public String getGrupoActual() {
        return grupoActual;
    }

    public void setGrupoActual(String grupoActual) {
        this.grupoActual = grupoActual;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
