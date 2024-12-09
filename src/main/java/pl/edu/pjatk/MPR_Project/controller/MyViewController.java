package pl.edu.pjatk.MPR_Project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.edu.pjatk.MPR_Project.model.Kapibara;
import pl.edu.pjatk.MPR_Project.service.KapibaraService;

import java.util.List;

@Controller
public class MyViewController {
    private KapibaraService kapibaraService;

    public MyViewController(KapibaraService kapibaraService) {
        this.kapibaraService = kapibaraService;
    }

    @GetMapping("/view/all")
    public String displayAllKapibaras(Model model) {
        model.addAttribute("nazwa", "wartość");
        List<Kapibara> kapibaraList = this.kapibaraService.getAllKapibaras();
        model.addAttribute("kapibara", kapibaraList);
        return "viewAll";
    }
    @GetMapping("/addForm")
    public String displayAddForm(Model model) {
        model.addAttribute("kapibara", new Kapibara());
        return "addForm";
    }
    @PostMapping("/addForm")
    public String submitForm(@ModelAttribute Kapibara kapibara) {
        this.kapibaraService.addKapibara(kapibara);
        return "redirect:/view/all";
    }
    @DeleteMapping("/giveUpKapibara")
    public String deleteKapibara(Model model) {
        model.addAttribute("kapibara", new Kapibara());
        return "giveUpKapibara";
    }
}
