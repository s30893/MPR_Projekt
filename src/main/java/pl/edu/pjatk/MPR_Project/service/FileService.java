package pl.edu.pjatk.MPR_Project.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pjatk.MPR_Project.model.Kapibara;
import pl.edu.pjatk.MPR_Project.repository.FileRepository;
import pl.edu.pjatk.MPR_Project.repository.KapibaraRepository;

import java.io.IOException;

@Component
public class FileService {
    private FileRepository fileRepository;
    private KapibaraRepository kapibaraRepository;
    private KapibaraService kapibaraService;


    @Autowired
    public FileService(FileRepository fileRepository, KapibaraRepository kapibaraRepository, KapibaraService kapibaraService) {
        this.fileRepository = fileRepository;
        this.kapibaraRepository = kapibaraRepository;


    }

}
