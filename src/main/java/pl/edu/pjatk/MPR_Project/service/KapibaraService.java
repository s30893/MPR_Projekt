package pl.edu.pjatk.MPR_Project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pjatk.MPR_Project.model.Kapibara;
import pl.edu.pjatk.MPR_Project.repository.KapibaraRepository;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class KapibaraService {

    private KapibaraRepository kapibaraRepository;
    List<Kapibara> kapibaraList = new ArrayList<>();

    @Autowired
    public KapibaraService(KapibaraRepository repository) {
        this.kapibaraRepository = repository;

        this.kapibaraRepository.save(new Kapibara("Gord", "brązowy"));
        this.kapibaraRepository.save(new Kapibara("Adam", "złoty"));
        //kapibaraList.add(new Kapibara("name", "color")); - stara wersja pracy z listą
    }

    public List<Kapibara> getKapibaraByName(String name) {
        return this.kapibaraRepository.findByName(name);
    }

    public List<Kapibara> getAllKapibaras() {
        return (List<Kapibara>) this.kapibaraRepository.findAll();
    }

    public void addKapibara(Kapibara kapibara) {
        this.kapibaraRepository.save(kapibara);
    }

    public Optional<Kapibara> get(Long id) {
        return this.kapibaraRepository.findById(id);
    }

    ;

    public void killKapibara(Long id) {
        if (this.kapibaraRepository.existsById(id)) {
            this.kapibaraRepository.deleteById(id);
        } else {
            System.out.println(" ");
        }
    }


    public void updateKapibara(Long id, Kapibara kapibara) {
        Optional<Kapibara> kapibaraSchrodingera = this.kapibaraRepository.findById(id);
        if (kapibaraSchrodingera.isPresent()) {
            kapibaraSchrodingera.get().setName(kapibara.getName());
            kapibaraSchrodingera.get().setColor(kapibara.getColor());
            this.kapibaraRepository.save(kapibara);
        }
    }
}
