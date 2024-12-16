package pl.edu.pjatk.MPR_Project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    @GetMapping("/deleteForm")
    public String displayDeleteForm(@RequestParam Long id) {
        this.kapibaraService.killKapibara(id);
        return "redirect:/view/all";
    }

    @GetMapping("/updateForm")
    public String displayUpdateForm(@RequestParam Long id, Model model) {
        Kapibara kapibara = kapibaraService.get(id);
        model.addAttribute("kapibara", kapibara);
        return "updateForm";
    }



    @PostMapping("/updateForm")
    public String submitUpdateForm(@ModelAttribute Kapibara kapibara) {
        kapibaraService.updateKapibara(kapibara.getId(), kapibara);
        return "redirect:/view/all";
    }

}
