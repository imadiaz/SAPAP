package mx.edu.utez.egresados;

import mx.edu.utez.horario.BeanHorario;
import mx.edu.utez.persona.BeanPersona;

public class BeanEgresados extends BeanPersona {

    private int idEgresado;
    private String nombreDeLaEmpresa;
    private String puesto;

    public BeanEgresados() {
    }

    public BeanEgresados(BeanHorario horario, int idPersona, String nombre, String primerApellido, String segundoApellido, String fechaDeNacimiento, String correoInstitucional, String correoPersonal, String numeroTelefonico, String numeroCasa, String carreraDeEgreso, String universidadDeEgreso, String desempenio, String fechaDeIngreso, String fechaDeEgreso, int idEgresado, String nombreDeLaEmpresa, String puesto) {
        super(horario, idPersona, nombre, primerApellido, segundoApellido, fechaDeNacimiento, correoInstitucional, correoPersonal, numeroTelefonico, numeroCasa, carreraDeEgreso, universidadDeEgreso, desempenio, fechaDeIngreso, fechaDeEgreso);
        this.idEgresado = idEgresado;
        this.nombreDeLaEmpresa = nombreDeLaEmpresa;
        this.puesto = puesto;
    }

    public int getIdEgresado() {
        return idEgresado;
    }

    public void setIdEgresado(int idEgresado) {
        this.idEgresado = idEgresado;
    }

    public String getNombreDeLaEmpresa() {
        return nombreDeLaEmpresa;
    }

    public void setNombreDeLaEmpresa(String nombreDeLaEmpresa) {
        this.nombreDeLaEmpresa = nombreDeLaEmpresa;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }
}
