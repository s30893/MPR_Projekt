package pl.edu.pjatk.MPR_Project;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import pl.edu.pjatk.MPR_Project.repository.KapibaraRepository;
import pl.edu.pjatk.MPR_Project.service.KapibaraService;

import java.io.IOException;

public class pdfGenerator {
    KapibaraService kapibaraService;

    public pdfGenerator(KapibaraService kapibaraService) {
        this.kapibaraService = kapibaraService;
    }

    public void generete() throws IOException {
        PDDocument document = new PDDocument();
        PDPage pdPage = new PDPage();
        PDPageContentStream stream = new PDPageContentStream(document, pdPage);
        stream.beginText();
        stream.setFont(new PDType1Font(Standard14Fonts.FontName.TIMES_ROMAN), 14);
        stream.showText(kapibaraService.getAllKapibaras().toString());
        stream.endText();
        stream.close();
    }
}
