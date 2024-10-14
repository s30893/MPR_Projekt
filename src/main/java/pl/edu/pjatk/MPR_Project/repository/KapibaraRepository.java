package pl.edu.pjatk.MPR_Project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjatk.MPR_Project.model.Kapibara;

import java.util.List;

@Repository //komunikacja z bazą danych
//@Component
public interface KapibaraRepository extends CrudRepository<Kapibara, Long>  {//<-typ generyczny <>
  public List<Kapibara> findByName(String name); //sprawdz w obiekcie kapibara czy znajduje się dane imię
}
