package sddc.services;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sddc.services.domain.Category;
import sddc.services.domain.Service;
import sddc.services.domain.ServiceModule;
import sddc.services.domain.Size;

@RestController
public class ServiceController {
    
    @Autowired
    private ServiceRepo repo;
    
    @Autowired
    private ServiceModuleRepo moduleRepo;
    
    @PostConstruct
    private void createInitialData() {
    	cleanDb();
    	Set<ServiceModule> modules = new HashSet<ServiceModule>();
    	modules.add(new ServiceModule("Modul1",Size.S,Category.Network));
    	modules.add(new ServiceModule("Modul2",Size.L,Category.Compute));
        repo.save(new Service("something"));
        repo.save(new Service("something3",modules));
    }
    
    private void cleanDb() {
    	moduleRepo.deleteAll();
        repo.deleteAll();
    }
    
    @RequestMapping(value="/services/{id}")
    @ResponseBody
    public Service findService(@PathVariable("id") long id){
        return repo.findOne(id);
    }

    @RequestMapping("/services")
    @ResponseBody
    public List<Service> findAllServices() {
        List<Service> result = repo.findAll();
        return result;
    }
  

}
