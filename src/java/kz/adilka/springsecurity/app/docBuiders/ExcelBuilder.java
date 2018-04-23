package kz.adilka.springsecurity.app.docBuiders;

import kz.adilka.springsecurity.app.model.Contact;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.web.servlet.view.document.AbstractExcelView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class ExcelBuilder extends AbstractExcelView {
    @Override
    protected void buildExcelDocument(Map<String, Object> map, HSSFWorkbook hssfWorkbook,
                                      HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws Exception {
        List<Contact> contactList = (List<Contact>) map.get("listContacts");

        HSSFSheet sheet = hssfWorkbook.createSheet("Contacts");
        sheet.setDefaultColumnWidth(30);

        CellStyle style = hssfWorkbook.createCellStyle();
        Font font = hssfWorkbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        style.setFont(font);

        HSSFRow header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID");
        header.getCell(0).setCellStyle(style);
        header.createCell(1).setCellValue("Name");
        header.getCell(1).setCellStyle(style);
        header.createCell(2).setCellValue("City");
        header.getCell(2).setCellStyle(style);
        header.createCell(3).setCellValue("No");
        header.getCell(3).setCellStyle(style);
        header.createCell(4).setCellValue("Mail");
        header.getCell(4).setCellStyle(style);

        int rowCount = 1;

        for (Contact contact : contactList) {
            HSSFRow aRow = sheet.createRow(rowCount++);
            aRow.createCell(0).setCellValue(contact.getId());
            aRow.createCell(1).setCellValue(contact.getContactName());
            aRow.createCell(2).setCellValue(contact.getContactCity());
            aRow.createCell(3).setCellValue(contact.getContactPhone());
            aRow.createCell(4).setCellValue(contact.getContactEmail());
        }

    }
}
