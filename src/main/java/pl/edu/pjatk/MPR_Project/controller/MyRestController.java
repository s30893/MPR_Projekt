package pl.edu.pjatk.MPR_Project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.MPR_Project.model.Kapibara;
import pl.edu.pjatk.MPR_Project.service.KapibaraService;

import java.util.List;
import java.util.Optional;

@RestController
public class MyRestController {
    private KapibaraService kapibaraService;

    @Autowired
    public MyRestController(KapibaraService kapibaraService) {
        this.kapibaraService = kapibaraService;
    }
    @GetMapping("kapibara/all")//daje  wszystkie kapibary na BD
    public List<Kapibara> getAll(){
        return this.kapibaraService.getAllKapibaras();
    }

    @GetMapping("kapibara/name/{name}")//znajduje kapibarę po imieniu na BD
    public List<Kapibara> getByName(@PathVariable String name){
        return this.kapibaraService.getKapibaraByName(name);
    }

    @GetMapping("kapibara/{id}")//szuka kapibary z numerkiem na BD
    public Optional<Kapibara> get(@PathVariable Long id){
        return this.kapibaraService.get(id);
    }

    @PostMapping("kapibara/add")//będą tworzone kapibary na BD
    public void addKapibara(@RequestBody Kapibara kapibara) {
        this.kapibaraService.addKapibara(kapibara);
    }

    @DeleteMapping("kapibara/delete/{id}")
    public void killKapibara(@PathVariable Long id) { this.kapibaraService.killKapibara(id);}

    @PutMapping("kapibara/update/{id}")
    public void updateKapibara(@PathVariable Long id, @RequestBody Kapibara kapibara) {this.kapibaraService.updateKapibara(id, kapibara);}
}//sprawdzić czy jest i użyć save jeśli jest
