package pl.edu.pjatk.MPR_Project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.MPR_Project.model.Kapibara;
import pl.edu.pjatk.MPR_Project.services.KapibaraService;

import java.util.List;

@RestController
public class MyRestController {
    private KapibaraService kapibaraService;

    @Autowired
    public MyRestController(KapibaraService kapibaraService) {
        this.kapibaraService = kapibaraService;
    }
    @GetMapping("kapibara/all")//daje  wszystkie kapibary
    public List<Kapibara> getAll(){
        return this.kapibaraService.getKapibaraList();
    }
    @GetMapping("kapibara/{id}")//daje kapibary
    public List<Kapibara> get(@PathVariable int id){
        return this.kapibaraService.get(id);
    }
    @PostMapping("kapibara")//będą tworzone kapibary
    public void create(@RequestBody Kapibara kapibara) {
        this.kapibaraService.createKapibara(kapibara);
    }
    @DeleteMapping("kapibara/delete/{id}")
    public void delete(@PathVariable int id) { this.kapibaraService.deleteKapibara(id);}
    @PutMapping("kapibara/update/{id}")
    public void update(@PathVariable int id, @RequestBody Kapibara kapibara) {this.kapibaraService.updateKapibara(id, kapibara);}
}
