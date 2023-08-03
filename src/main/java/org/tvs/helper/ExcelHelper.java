package org.tvs.helper;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.tvs.model.Customer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {
  public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
  static String[] HEADERs = { "Id", "fullName", "email", "mobile", "fatherName", "aadharNo", "city", "address", "delaerName" };
  static String SHEET = "Customers";

  public static boolean hasExcelFormat(MultipartFile file) {

    if (!TYPE.equals(file.getContentType())) {
      return false;
    }

    return true;
  }

  public static ByteArrayInputStream customersToExcel(List<Customer> customers) {

    try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
      Sheet sheet = workbook.createSheet(SHEET);

      // Header
      Row headerRow = sheet.createRow(0);

      for (int col = 0; col < HEADERs.length; col++) {
        Cell cell = headerRow.createCell(col);
        cell.setCellValue(HEADERs[col]);
      }

      int rowIdx = 1;
      for (Customer customer : customers) {
        Row row = sheet.createRow(rowIdx++);

//        row.createCell(0).setCellValue(customer.getId());
//        row.createCell(1).setCellValue(customer.getTitle());
//        row.createCell(2).setCellValue(customer.getDescription());
//        row.createCell(3).setCellValue(customer.isPublished());
      }

      workbook.write(out);
      return new ByteArrayInputStream(out.toByteArray());
    } catch (IOException e) {
      throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
    }
  }

  public static List<Customer> excelToCustomers(InputStream is) {
    System.out.println("Excel read start time "+ System.currentTimeMillis());
    try {
      Workbook workbook = new XSSFWorkbook(is);

      Sheet sheet = workbook.getSheet(SHEET);
      Iterator<Row> rows = sheet.iterator();

      List<Customer> customers = new ArrayList<Customer>();

      int rowNumber = 0;
      while (rows.hasNext()) {
        Row currentRow = rows.next();

        // skip header
        if (rowNumber == 0) {
          rowNumber++;
          continue;
        }

        Iterator<Cell> cellsInRow = currentRow.iterator();

        Customer customer = new Customer();

        int cellIdx = 0;
        while (cellsInRow.hasNext()) {
          Cell currentCell = cellsInRow.next();

          switch (cellIdx) {
          case 0:
            customer.setId((long) currentCell.getNumericCellValue());
            break;

          case 1:
            customer.setFullName(currentCell.getStringCellValue());
            break;

          case 2:
            customer.setEmail(currentCell.getStringCellValue());
            break;

          case 3:
            customer.setMobile(String.valueOf(currentCell.getNumericCellValue()));
            break;

            case 4:
              customer.setFatherName(currentCell.getStringCellValue());
              break;

            case 5:
              customer.setAadhaarNo(String.valueOf(currentCell.getNumericCellValue()));
              break;

            case 6:
              customer.setCity(currentCell.getStringCellValue());
              break;

            case 7:
              customer.setAddress(currentCell.getStringCellValue());
              break;

            case 8:
              customer.setDealerName(currentCell.getStringCellValue());
              break;

          default:
            break;
          }

          cellIdx++;
        }

        customers.add(customer);
      }

      workbook.close();
      System.out.println("Excel read end time "+ System.currentTimeMillis());
      return customers;
    } catch (IOException e) {
      throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
    }
  }
}
