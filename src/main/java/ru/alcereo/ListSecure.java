package ru.alcereo;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Component
public class ListSecure {

    private Script script;
    private Binding sharedData = new Binding();

    private HttpServletRequest request;
    private HttpSession session;

    public void setContext(String name, Object object){
        sharedData.setProperty(name, object);
    }

    public ListSecure(String scriptText) {
        setScript(scriptText);
    }

    public ListSecure() {
        setScript("true");
    }

    public void setScript(String scriptText){
        GroovyShell shell = new GroovyShell(sharedData);
        script = shell.parse(scriptText);
    }

    public <TYPE> List<TYPE> filter(ListInterface<TYPE> listGetter) {
        List<TYPE> list = listGetter.getList();
        list.removeIf(element -> {
            sharedData.setProperty("element", element);
            return !(boolean) script.run();
        });
        return list;
    }

}
