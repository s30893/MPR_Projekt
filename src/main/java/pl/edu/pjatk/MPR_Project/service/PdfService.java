package pl.edu.pjatk.MPR_Project.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.springframework.stereotype.Component;
import pl.edu.pjatk.MPR_Project.model.Kapibara;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Component
public class PdfService {

    public ByteArrayOutputStream generateKapibaraPdf(Kapibara kapibara) {
        try (PDDocument document = new PDDocument();
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            PDPage page = new PDPage();
            document.addPage(page);

            File fontFile = new File("src/main/resources/fonts/arial.ttf");
            PDType0Font font = PDType0Font.load(document, fontFile);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(font, 12);
                contentStream.beginText();
                contentStream.setLeading(14.5f);
                contentStream.newLineAtOffset(50, 750);

                contentStream.showText("Kapibara Details:");
                contentStream.newLine();
                contentStream.newLine();

                contentStream.showText("ID: " + kapibara.getId());
                contentStream.newLine();
                contentStream.showText("Name: " + kapibara.getName());
                contentStream.newLine();
                contentStream.showText("Color: " + kapibara.getColor());
                contentStream.newLine();

                contentStream.endText();
            }

            document.save(outputStream);
            return outputStream;
        } catch (IOException e) {
            throw new RuntimeException("Error generating PDF", e);
        }
    }
}
