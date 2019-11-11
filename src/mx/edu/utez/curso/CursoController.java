package mx.edu.utez.curso;


import com.opensymphony.xwork2.ActionSupport;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.opensymphony.xwork2.Action.SUCCESS;

public class CursoController extends ActionSupport {
    Map response = new HashMap();
    private boolean status = false;
    private DaoCurso daoCurso = new DaoCurso();
    private JSONObject jsonObject;


private String params;
    public String listaCursos(){
        response.put("listaCursos",daoCurso.getListaByID(1));
        return SUCCESS;
    }



    public String registrarCurso(){
        try {
            System.out.println("Data=========");
            jsonObject = new JSONObject(params);
            System.out.println(jsonObject);
            if(daoCurso.registrar(new BeanCurso(
                    jsonObject.getString("nombre"),
                    jsonObject.getString("fecha"),
                    jsonObject.getString("descripcion"),
                    jsonObject.getString("tipoCurso"),
                    jsonObject.getString("evidencia"),
                    1
            ))){
                System.out.println("entro al if");
                status=true;
            }
            response.put("status",status);
        } catch (JSONException e) {
            System.out.println("metodo registrar"+e.getMessage());
        }

        return SUCCESS;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Map getResponse() {
        return response;
    }

    public void setResponse(Map response) {
        this.response = response;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
