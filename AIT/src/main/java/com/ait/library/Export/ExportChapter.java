package com.ait.library.Export;

import com.ait.library.model.Chapter;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;

public class ExportChapter {
    public static ByteArrayInputStream chaptertoPdf(Chapter chapter) throws IOException {

        XWPFDocument document = new XWPFDocument();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        // Create new Paragraph
        XWPFParagraph paragraph1 = document.createParagraph();
        paragraph1.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph1.createRun();
        run.setFontSize(30);
        run.setSubscript(VerticalAlign.SUBSCRIPT);
        run.setText(chapter.getNameChapter());

        XWPFParagraph paragraph2 = document.createParagraph();
        paragraph2.setAlignment(ParagraphAlignment.LEFT);
        run.setFontSize(20);
        run = paragraph2.createRun();
        run.setText(chapter.getContentChapter());

        // Write the Document in file system
        document.write(out);
        out.close();
        document.close();
        System.out.println("successully");
        return new ByteArrayInputStream(out.toByteArray());


    }
}




