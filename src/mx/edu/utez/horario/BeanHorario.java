package mx.edu.utez.horario;

public class BeanHorario {

    private int idHorario;
    private String horario;

    public BeanHorario() {

    }

    public BeanHorario(int idHorario, String horario) {
        this.idHorario = idHorario;
        this.horario = horario;
    }

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
}
