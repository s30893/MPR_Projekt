package pl.edu.pjatk.MPR_Project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.MPR_Project.model.Kapibara;
import pl.edu.pjatk.MPR_Project.service.KapibaraService;
import pl.edu.pjatk.MPR_Project.service.PdfService;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;

@RestController
public class MyRestController {
    private KapibaraService kapibaraService;
    private PdfService pdfService;

    @Autowired
    public MyRestController(KapibaraService kapibaraService , PdfService pdfService) {
        this.kapibaraService = kapibaraService;
        this.pdfService = pdfService;
    }
    @GetMapping("kapibara/all")//daje  wszystkie kapibary na BD
    public ResponseEntity<List<Kapibara>> getAll(){

        return new ResponseEntity<>(this.kapibaraService.getAllKapibaras(), HttpStatus.OK);
    }

    @GetMapping("kapibara/name/{name}")//znajduje kapibarę po imieniu na BD
    public ResponseEntity<List<Kapibara>> getByName(@PathVariable String name){

        return new ResponseEntity<>(this.kapibaraService.getKapibaraByName(name), HttpStatus.OK);
    }

    @GetMapping("kapibara/{id}")//szuka kapibary z numerkiem na BD
    public ResponseEntity<Kapibara> get(@PathVariable Long id){
        return new ResponseEntity<>(this.kapibaraService.get(id), HttpStatus.OK);
    }

    @PostMapping("kapibara/add")//będą tworzone kapibary na BD
    public ResponseEntity<Kapibara> addKapibara(@RequestBody Kapibara kapibara) {

        this.kapibaraService.addKapibara(kapibara);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
//created
    @DeleteMapping("kapibara/delete/{id}")
    public ResponseEntity<Kapibara> killKapibara(@PathVariable Long id) {
        this.kapibaraService.killKapibara(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("kapibara/update/{id}")
    public ResponseEntity<Kapibara> updateKapibara(@PathVariable Long id, @RequestBody Kapibara kapibara) {
        this.kapibaraService.updateKapibara(id, kapibara);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("kapibara/{id}/pdf")
    public ResponseEntity<byte[]> getKapibaraAsPdf(@PathVariable Long id) {
        Kapibara kapibara = kapibaraService.get(id);
        ByteArrayOutputStream pdfOutput = pdfService.generateKapibaraPdf(kapibara);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"kapibara_" + id + ".pdf\"")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfOutput.toByteArray());
    }
}//sprawdzić czy jest i użyć save jeśli jest
