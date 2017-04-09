package ru.alcereo;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class SecureModule {

    private static Script script;
    private static Binding sharedData = new Binding();

    static {
        GroovyShell shell = new GroovyShell(sharedData);
        script = shell.parse("element.age > 20");
    }

    public void setScript(String scriptText){
        GroovyShell shell = new GroovyShell(sharedData);
        script = shell.parse(scriptText);
    }

    public <TYPE> List<TYPE> secureList(ListInterface<TYPE> listGetter) {
        List<TYPE> list = listGetter.getList();
        list.removeIf(element -> {
            sharedData.setProperty("element", element);
            return !(boolean) script.run();
        });
        return list;
    }
}
