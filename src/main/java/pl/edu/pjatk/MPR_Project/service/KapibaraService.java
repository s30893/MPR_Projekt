package pl.edu.pjatk.MPR_Project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pjatk.MPR_Project.exception.KapibaraExist;
import pl.edu.pjatk.MPR_Project.exception.KapibaraNotFoundException;
import pl.edu.pjatk.MPR_Project.model.Kapibara;
import pl.edu.pjatk.MPR_Project.repository.KapibaraRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class KapibaraService {

    private KapibaraRepository kapibaraRepository;
    private StringUtilsService stringUtilsService;

    @Autowired
    public KapibaraService(KapibaraRepository repository, StringUtilsService stringUtilsService) {
        this.kapibaraRepository = repository;
        this.stringUtilsService = stringUtilsService;

        this.kapibaraRepository.save(new Kapibara("Gord", "brązowy"));
        this.kapibaraRepository.save(new Kapibara("Adam", "złoty"));
        //kapibaraList.add(new Kapibara("name", "color")); - stara wersja pracy z listą
    }
    //tested and exception
    public List<Kapibara> getKapibaraByName(String name) {
        List<Kapibara> kapibaraList = this.kapibaraRepository.findByName(name);
        if(kapibaraList.isEmpty()) {
            throw new KapibaraNotFoundException();
        }
        return kapibaraList;
    }
    //tested and exception
    public List<Kapibara> getAllKapibaras() {
        List<Kapibara> kapibaraList = (List<Kapibara>) this.kapibaraRepository.findAll();
        if(kapibaraList.isEmpty()){
            throw new KapibaraNotFoundException();
        }
        return kapibaraList;
    }
    //tested and exception
    public void addKapibara(Kapibara kapibara) {
        List<Kapibara> kapibaraList = this.kapibaraRepository.findByIdentyfikator(kapibara.getIdentyfikator());
        if(!kapibaraList.isEmpty()) {
            throw new KapibaraExist();
        }
        // a teraz sprawdz czy pola sa puste

        kapibara.setName(this.stringUtilsService.toUpperCase(kapibara.getName()));
        kapibara.setColor(this.stringUtilsService.toUpperCase(kapibara.getColor()));
        this.kapibaraRepository.save(kapibara);

    }
    //tested and exception
    public Kapibara get(Long id) {
        Optional<Kapibara> kapi = this.kapibaraRepository.findById(id);
        if (kapi.isPresent()) {
            kapi.get().setName(this.stringUtilsService.toLowerCase(kapi.get().getName()));
            kapi.get().setColor(this.stringUtilsService.toLowerCase(kapi.get().getColor()));
        }
        //jeśli nie ma kapibary -> wyrzuca błąd
        if(kapi.isEmpty()){
            throw new KapibaraNotFoundException();
        }
        return kapi.get();
    }

    //tested and exception
    public void killKapibara(Long id) {
        if (this.kapibaraRepository.existsById(id)) {
            this.kapibaraRepository.deleteById(id);
        }else{
            throw new KapibaraNotFoundException();
        }
    }

    //tested and exception
    public void updateKapibara(Long id, Kapibara kapibara) {
        Optional<Kapibara> kapibaraSchrodingera = this.kapibaraRepository.findById(id);
        if (kapibaraSchrodingera.isPresent()) {
            kapibaraSchrodingera.get().setName(kapibara.getName());
            kapibaraSchrodingera.get().setColor(kapibara.getColor());
            this.kapibaraRepository.save(kapibara);
        }else{
            throw new KapibaraNotFoundException();
        }
    }
}
