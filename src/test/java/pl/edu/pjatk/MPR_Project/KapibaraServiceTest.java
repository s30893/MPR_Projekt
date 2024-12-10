package pl.edu.pjatk.MPR_Project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.edu.pjatk.MPR_Project.exception.KapibaraExist;
import pl.edu.pjatk.MPR_Project.exception.KapibaraNotFoundException;
import pl.edu.pjatk.MPR_Project.model.Kapibara;
import pl.edu.pjatk.MPR_Project.repository.KapibaraRepository;
import pl.edu.pjatk.MPR_Project.service.KapibaraService;
import pl.edu.pjatk.MPR_Project.service.StringUtilsService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

//zapodanie tych samych danych co w KapibaraService
//mock jest obiektem, który ma te same metody i pola jednak są nulami
public class KapibaraServiceTest {
    private KapibaraService kapibaraService; //tego nie mokujemy!!!

    private StringUtilsService stringUtilsService;
    private KapibaraRepository kapibaraRepository;

    @BeforeEach //przed każdym wywołaniem załączy się
    public void setUp() {
     this.stringUtilsService = Mockito.mock(StringUtilsService.class); //stworzenie tekturowej makiety
     this.kapibaraRepository = Mockito.mock(KapibaraRepository.class); //
     this.kapibaraService = new KapibaraService(kapibaraRepository, stringUtilsService); //stworzenie serwisu z podstawieniem makiet
     Mockito.clearInvocations(kapibaraRepository);
    }

    @Test
    //test działa poprawnie
    public void getKapibaraByNameShowsKapibaraWithLookedName(){
        String name ="name";
        Kapibara kapibara = new Kapibara("name", "color");
        List <Kapibara> newList = List.of(kapibara);
        Mockito.when(kapibaraRepository.findByName(name)).thenReturn(newList);
        List<Kapibara> kapibaraList = this.kapibaraService.getKapibaraByName(name);
        Mockito.verify(kapibaraRepository, Mockito.times(1)).findByName(name);
        assertFalse(kapibaraList.isEmpty());

    }
    //test działa poprawnie
    @Test
    public void getKapibaraByNameFindNothing(){
        String name = "name";
        assertThrows(KapibaraNotFoundException.class, () -> {
        this.kapibaraService.getKapibaraByName(name);
        });
        Mockito.verify(kapibaraRepository, Mockito.times(1)).findByName(name);
    }
    //test działa poprawnie
    @Test
    public void getAllKapibarasShowALlKapibars(){
        Kapibara kapibara = new Kapibara("name", "color");
        List <Kapibara> newList = List.of(kapibara);
        Mockito.when(kapibaraRepository.findAll()).thenReturn(newList);
        List<Kapibara> kapibaraList = this.kapibaraService.getAllKapibaras();
        Mockito.verify(kapibaraRepository,Mockito.times(1)).findAll();
        assertFalse(kapibaraList.isEmpty());
    }
    //test działa poprawnie
    @Test
    public void getAllKapibarasShowsNothing(){
        assertThrows(KapibaraNotFoundException.class, () -> {
            this.kapibaraService.getAllKapibaras();
        });
        Mockito.verify(kapibaraRepository, Mockito.times(1)).findAll();
    }
    //test działa poprawnie
    @Test
    public void addSetsKapibaraToUpperCase() {
        //stworzenie testowej kapibary
        Kapibara kapibara = new Kapibara("Piotuś", "none");
        List<Kapibara> kapibaraList = this.kapibaraRepository.findByIdentyfikator(kapibara.getIdentyfikator());
        //wywołanie testowej czynności -> przy dodawaniu dane maja być zapisane w wielkiech literach
        this.kapibaraService.addKapibara(kapibara);
        //przeprowadzenie wróżby -> stringUtilsService zostanie wywołane 2 razy z metodą toUpperCase z prypadkowym argumentem
        Mockito.verify(stringUtilsService, Mockito.times(2)).toUpperCase(any());
        //przeprowadzenie wróżby nr2 -> kapibaraRepository zostanie wywołane 1 raz z metodą save z prypadkowym argumentem
        Mockito.verify(kapibaraRepository, Mockito.times(1)).save(kapibara);
        assertTrue(kapibaraList.isEmpty());
    }
    //test działa poprawnie
    @Test
    public void addFindKapibaraWithSameIndex(){
        Kapibara kapibara = new Kapibara("Piotuś", "none");
        kapibara.setIdentyfikator(1L);
        Mockito.when(kapibaraRepository.findByIdentyfikator(kapibara.getIdentyfikator()))
                .thenReturn(List.of(kapibara));
        assertThrows(KapibaraExist.class, () -> {
            kapibaraService.addKapibara(kapibara);
        });
        Mockito.verify(kapibaraRepository, Mockito.times(1)).findByIdentyfikator(kapibara.getIdentyfikator());
    }
    //test działa poprawnie
    @Test
    public void getSetKapibaraToLowerCase(){
        Long id = 1L;
        Kapibara kapibara = new Kapibara("name", "color");

        //Kiedy wywołana zostanie metoda podana w (...), zwróć określoną wartość
        //Optional.of(kapibara) owija obiekt kapibara w obiekt Optional,
        // co symuluje scenariusz, w którym znaleziono kapibarę o podanym id.
        when(kapibaraRepository.findById(id)).thenReturn(Optional.of(kapibara));

        Optional<Kapibara> kapi = Optional.ofNullable(kapibaraService.get(id));
        Mockito.verify(kapibaraRepository, Mockito.times(1)).findById(any());
        Mockito.verify(stringUtilsService, Mockito.times(2)).toLowerCase(any());
        assertTrue(kapi.isPresent());
        assertFalse(kapi.isEmpty());
    }
    //test działa poprawnie
    @Test
    public void getFindNothing(){
        Long id = 1L;
        assertThrows(KapibaraNotFoundException.class, () -> {
            this.kapibaraService.get(id);
        });
        Mockito.verify(kapibaraRepository, Mockito.times(1)).findById(id);
    }
    @Test
    public void killKapibaraDeleteKapibara(){
        Long id = 1L;
        when(kapibaraRepository.existsById(id)).thenReturn(true);
        kapibaraService.killKapibara(id);
        Mockito.verify(kapibaraRepository, Mockito.times(1)).deleteById(any());
    }
    @Test
    public void killKapibaraDoNotFoundCapibara(){
        Long id = 1L;
        assertThrows(KapibaraNotFoundException.class, () -> {
            this.kapibaraService.killKapibara(id);
        });
        Mockito.verify(kapibaraRepository, Mockito.times(1)).existsById(id);
    }
    @Test
    public void updateKapibaraChangesKapibara(){
        Long id = 1L;
        Kapibara kapibara = new Kapibara("none", "color");

        when(kapibaraRepository.findById(id)).thenReturn(Optional.of(kapibara));

        kapibaraService.updateKapibara(id, kapibara);
        Mockito.verify(kapibaraRepository, Mockito.times(1)).findById(id);
        Mockito.verify(kapibaraRepository, Mockito.times(1)).save(kapibara);
    }
    @Test
    public void updateKapibaraNotFoundKapibara(){
        Long id = 1L;
        Kapibara kapibara = new Kapibara("none", "color");
        assertThrows(KapibaraNotFoundException.class, () -> {
            this.kapibaraService.updateKapibara(id, kapibara);
        });
    }

}
