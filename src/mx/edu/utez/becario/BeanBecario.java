package mx.edu.utez.becario;

import mx.edu.utez.horario.BeanHorario;
import mx.edu.utez.persona.BeanPersona;

public class BeanBecario extends BeanPersona {

    private int idBecario;
    private double beca;

    public BeanBecario() {
    }

    public BeanBecario(BeanHorario horario, int idPersona, String nombre, String primerApellido, String segundoApellido, String fechaDeNacimiento, String correoInstitucional, String contrasenia, String matricula,String correoPersonal, String numeroTelefonico, String numeroCasa, String carreraDeEgreso, String universidadDeEgreso, String desempenio, String fechaDeIngreso, String fechaDeEgreso, int idBecario, double beca) {
        super(horario, idPersona, nombre, primerApellido, segundoApellido, fechaDeNacimiento, correoInstitucional, contrasenia, matricula,correoPersonal, numeroTelefonico, numeroCasa, carreraDeEgreso, universidadDeEgreso, desempenio, fechaDeIngreso, fechaDeEgreso);
        this.idBecario = idBecario;
        this.beca = beca;
    }

    public int getIdBecario() {
        return idBecario;
    }

    public void setIdBecario(int idBecario) {
        this.idBecario = idBecario;
    }

    public double getBeca() {
        return beca;
    }

    public void setBeca(double beca) {
        this.beca = beca;
    }
}
