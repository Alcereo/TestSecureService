package ru.alcereo;

import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ServiceTestController {

    Logger logger = LoggerFactory.getLogger(ServiceTestController.class);

    @Autowired
    private EntityService service;

    @Autowired
    private SecureModule secured;

    public EntityService getService() {
        return service;
    }

    public void setService(EntityService service) {
        this.service = service;
    }

    @RequestMapping("/setScript")
    public void setScript(@RequestParam("script") String script){
        secured.setScript(script);
    }

    @RequestMapping(path = "/welcome")
    @Secured("ROLE_ServiceTestController.welcome")
    @Timed(name = "welcome")
    public String welcome(){
        return "welcome!";
    }

    @RequestMapping(path = "/get")
    @Secured("ROLE_ServiceTestController.get")
    @Timed(name = "get")
    public @ResponseBody List<TestEntity> getAll(){
        return service.getList();
    }

    @RequestMapping(path = "/add")
    @Secured("ROLE_ServiceTestController.add")
    @Timed(name = "add")
    public void add(@RequestParam("name") String name, @RequestParam("age") int age){
        service.add(new TestEntity(name, age));
    }

}
