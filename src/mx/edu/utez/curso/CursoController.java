package mx.edu.utez.curso;


import static com.opensymphony.xwork2.Action.SUCCESS;

public class CursoController {
private String name;

    public String testAjax(){
        System.out.println(name);
        return SUCCESS;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
