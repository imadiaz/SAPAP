package mx.edu.utez.curso;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import mx.edu.utez.persona.BeanPersona;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.opensymphony.xwork2.Action.SUCCESS;

public class CursoController extends ActionSupport {
    Map response = new HashMap();
    Map session = ActionContext.getContext().getSession();
    BeanPersona bean = (BeanPersona) session.get("usuario");
    private boolean status = false;
    private DaoCurso daoCurso = new DaoCurso();
    private JSONObject jsonObject;
    private String params;
    private String idCursoModificar;

    public String listaCursos(){
        try{
            response.put("listaCursos",daoCurso.getListaByID(bean.getIdPersona()));
        }catch(Exception e){
            System.out.println("Error, listaCursos" + e.getMessage());
        }
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
                    jsonObject.getString("referencia"),
                    bean.getIdPersona()
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

    public String eliminaCurso(){
        try{
           //jsonObject = new JSONObject(params);
            System.out.println("El ID" + params);
            System.out.println("INT" + Integer.parseInt(params));
            if(daoCurso.eliminar(Integer.parseInt(params))){
                status = true;
            }
            response.put("status",status);

        }catch(Exception e){
            System.out.println("Error eliminar controller" +e.getMessage());
        }

        return SUCCESS;
    }


    public String buscarCursoModificar(){
        System.out.println(session.toString());
        response.put("bean",daoCurso.buquedaByID(Integer.parseInt(idCursoModificar)));
        return "AP";
    }

    public String actualizaCurso(){
        try{
            jsonObject=new JSONObject(params);
            System.out.println(jsonObject);


            if(daoCurso.actualizar(new BeanCurso(
                    Integer.parseInt(jsonObject.getString("idCurso")),
                    jsonObject.getString("nombre"),
                    jsonObject.getString("fecha"),
                    jsonObject.getString("descripcion"),
                    jsonObject.getString("tipoCurso"),
                    null
            ))){
                status = true;
            }
            response.put("status",status);
        }catch(Exception e){
            System.out.println("Error actualizar Controller"+e.getMessage());
        }

        return SUCCESS;
    }


    public String modificaArchivo(){
        try{
            jsonObject = new JSONObject(params);
            System.out.println(jsonObject);
            if(daoCurso.actualizarArchivo(
                    jsonObject.getString("url"),
                    jsonObject.getString("ref"),
                    jsonObject.getString("cursoID"))){
                status = true;
            }
            response.put("status",status);
        }catch(Exception e){
        }
        return  SUCCESS;
    }


    public String getIdCursoModificar() {
        return idCursoModificar;
    }

    public void setIdCursoModificar(String idCursoModificar) {
        this.idCursoModificar = idCursoModificar;
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

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}
