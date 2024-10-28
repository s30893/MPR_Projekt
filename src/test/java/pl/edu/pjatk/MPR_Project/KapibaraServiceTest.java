package pl.edu.pjatk.MPR_Project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.edu.pjatk.MPR_Project.model.Kapibara;
import pl.edu.pjatk.MPR_Project.repository.KapibaraRepository;
import pl.edu.pjatk.MPR_Project.service.KapibaraService;
import pl.edu.pjatk.MPR_Project.service.StringUtilsService;

import static org.mockito.ArgumentMatchers.any;

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
    public void createSetsKapibaraToUpperCase() {
        //stworzenie testowej kapibary
        Kapibara kapibara = new Kapibara("Piotuś", "none");
        //wywołanie testowej czynności -> przy dodawaniu dane maja być zapisane w wielkiech literach
        this.kapibaraService.addKapibara(kapibara);
        //przeprowadzenie wróżby -> stringUtilsService zostanie wywołane 2 razy z metodą toUpperCase z prypadkowym argumentem
        Mockito.verify(stringUtilsService, Mockito.times(2)).toUpperCase(any());
        //przeprowadzenie wróżby nr2 -> kapibaraRepository zostanie wywołane 1 raz z metodą save z prypadkowym argumentem
        Mockito.verify(kapibaraRepository, Mockito.times(1)).save(any());
    }

}
