package com.ait.library.Export;

import com.ait.library.model.Book;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExportBook {
    public static ByteArrayInputStream bookToExcel(List<Book> book) throws IOException {
        String[] COLUMNs = {"STT", "Tên Sách ", "Tên thể loại", "Tên tác giả"};
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


            Row HRow = sheet.createRow(1);

            sheet.setColumnWidth(0, 256 * 5);
            sheet.setColumnWidth(1, 256 * 50);
            sheet.setColumnWidth(2, 256 * 20);
            sheet.setColumnWidth(3, 256 * 25);
            sheet.addMergedRegion(CellRangeAddress.valueOf("B2:C2"));
            Cell cell1 = HRow.createCell(1);
            cell1.setCellValue("Danh sách truyện");
            cell1.setCellStyle(headerCellStyle);
            // Row for Header
            Row headerRow = sheet.createRow(3);
            // Header

            for (int col = 0; col < COLUMNs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(COLUMNs[col]);
                cell.setCellStyle(headerCellStyle);
            }

            int rowIdx = 4;
            for (Book book1 : book) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(rowIdx - 4);
                row.createCell(1).setCellValue(book1.getNameBook());
                row.createCell(2).setCellValue(book1.getNameCategory());
                row.createCell(3).setCellValue(book1.getNameAuthor());
                sheet.autoSizeColumn(rowIdx);

            }
            sheet.setColumnWidth(400,200);

            workbook.write(out);

            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}








