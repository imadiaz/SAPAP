package mx.edu.utez.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.omg.PortableInterceptor.Interceptor;

import java.util.Map;

public class Interceptores extends AbstractInterceptor {

    @Override
    public void destroy() {
        System.out.println("Destruye lo creado");
    }

    @Override
    public void init() {
        System.out.println("Se crean datos para interceptor");
    }


    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        Map session = ActionContext.getContext().getSession();
        if (session.get("usuario") != null) {
            return actionInvocation.invoke();
        }
        return "NOLOGIN";
    }
}
