package mx.edu.utez.docente;

import mx.edu.utez.horario.BeanHorario;
import mx.edu.utez.persona.BeanPersona;

public class BeanDocente extends BeanPersona {

    private int idDocente;

    public BeanDocente() {
    }

    public BeanDocente(BeanHorario horario, int idPersona, String nombre, String primerApellido, String segundoApellido, String fechaDeNacimiento, String correoInstitucional, String contrasenia, String matricula,String correoPersonal, String numeroTelefonico, String numeroCasa, String carreraDeEgreso, String universidadDeEgreso, String desempenio, String fechaDeIngreso, String fechaDeEgreso, int idDocente) {
        super(horario, idPersona, nombre, primerApellido, segundoApellido, fechaDeNacimiento, correoInstitucional, contrasenia,matricula,correoPersonal, numeroTelefonico, numeroCasa, carreraDeEgreso, universidadDeEgreso, desempenio, fechaDeIngreso, fechaDeEgreso);
        this.idDocente = idDocente;
    }

    public int getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(int idDocente) {
        this.idDocente = idDocente;
    }
}
