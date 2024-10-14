package pl.edu.pjatk.MPR_Project.services;

import org.springframework.stereotype.Component;
import pl.edu.pjatk.MPR_Project.model.Kapibara;

import java.util.ArrayList;
import java.util.List;

@Component
public class KapibaraService {
    List<Kapibara> kapibaraList = new ArrayList<>();

    public KapibaraService() {
        kapibaraList.add(new Kapibara("Gord", "brązowy"));
        kapibaraList.add(new Kapibara("Adam", "złoty"));
    }

    public List<Kapibara> getKapibaraList() {
        return this.kapibaraList;
    }

    public void createKapibara(Kapibara kapibara) {
        this.kapibaraList.add(kapibara);
    }

    public List<Kapibara> get(int id) {
return null;
    };

    public void deleteKapibara(int id) {this.kapibaraList.remove(id);}


    public void updateKapibara(int id, Kapibara kapibara) {
        Kapibara newKapibara = kapibaraList.get(id);
        newKapibara.setName(kapibara.getName());
        newKapibara.setColor(kapibara.getColor());
    }
}
