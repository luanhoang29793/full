package com.ait.library.Export;

import com.ait.library.model.Book;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExportBook {
    public static ByteArrayInputStream bookToExcel(List<Book> book) throws IOException {
        String[] COLUMNs = {"Số thứ tự", "Tên Sách ", "Giới thiệu", "Tên thể loại", "Tên tác giả"};
        try (
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
        ) {

            CreationHelper createHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet("danh sach truyen");


            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.WHITE.getIndex());


            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);
            headerCellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
            headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerCellStyle.setBorderBottom(BorderStyle.THIN);
            headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            headerCellStyle.setAlignment(HorizontalAlignment.CENTER);



            // Row for Header
            Row headerRow = sheet.createRow(3);
            // Header
            for (int col = 0; col < COLUMNs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(COLUMNs[col]);
                cell.setCellStyle(headerCellStyle);
                sheet.autoSizeColumn(col);
            }

            int rowIdx = 4;
            for (Book book1 : book) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(rowIdx - 4);
                row.createCell(1).setCellValue(book1.getNameBook());

                row.createCell(2).setCellValue(book1.getSummaryBook());
                row.createCell(3).setCellValue(book1.getNameCategory());
                row.createCell(4).setCellValue(book1.getNameAuthor());

            }

            workbook.write(out);

            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}








