package ru.alcereo;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service
public class EntityService{

    @Autowired
    private SecureModule secure;

    public void setSecure(SecureModule secure) {
        this.secure = secure;
    }

    private static List<TestEntity> dbList = new ArrayList<>();

    static {
        dbList.add(new TestEntity("Ivan", 25));
        dbList.add(new TestEntity("Andrey", 14));
        dbList.add(new TestEntity("Alexander", 18));
        dbList.add(new TestEntity("Maxim", 38));
        dbList.add(new TestEntity("Kirill", 28));
    }

    public List<TestEntity> getList(){
        return secure.secureList(() -> {
            return new ArrayList<>(dbList);
        });
    }

    public void add(TestEntity entity){
        dbList.add(entity);
    }


//    Testing
    public static void main(String[] args) throws IOException {
        EntityService service = new EntityService();
        SecureModule secure = new SecureModule();

        secure.setScript("false");
        service.setSecure(secure);

        List<TestEntity> list = service.getList();

        System.out.println(list);

    }
}
