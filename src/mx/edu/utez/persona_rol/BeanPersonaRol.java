package mx.edu.utez.persona_rol;

import mx.edu.utez.persona.BeanPersona;
import mx.edu.utez.rol.BeanRol;

public class BeanPersonaRol {

    private int id;
    private BeanPersona Persona;
    private BeanRol rol;

    public BeanPersonaRol(){}

    public BeanPersonaRol(int id, BeanPersona persona, BeanRol rol) {
        this.id = id;
        Persona = persona;
        this.rol = rol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BeanPersona getPersona() {
        return Persona;
    }

    public void setPersona(BeanPersona persona) {
        Persona = persona;
    }

    public BeanRol getRol() {
        return rol;
    }

    public void setRol(BeanRol rol) {
        this.rol = rol;
    }
}
