package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import controller.LoginController;
import model.KhoanChiModel;
import model.KhoanThuModel;

public class ChiTieuFile {
	 private static HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
	        HSSFFont font = workbook.createFont();
	        font.setBold(true);
	        HSSFCellStyle style = workbook.createCellStyle();
	        style.setFont(font);
	        return style;
	    }
		
		public static void exportFile(List<KhoanChiModel> listKT) throws IOException {
			HSSFWorkbook workbook = new HSSFWorkbook();
	        HSSFSheet sheet = workbook.createSheet("KhoanChi sheet");
	 
	 
	        int rownum = 3;
	        Cell cell;
	        Row row;
	        //
	        HSSFCellStyle style = createStyleForTitle(workbook);
	 
	        row = sheet.createRow(rownum);
	        cell = row.createCell(3, CellType.STRING);
	        cell.setCellValue("DANH SÁCH KHOẢN Chi");
	        cell.setCellStyle(style);
	        
	        
	        
	        rownum++;
	        row = sheet.createRow(rownum);
	        cell = row.createCell(2, CellType.STRING);
	        cell.setCellValue("ID Người dùng");
	        cell = row.createCell(3, CellType.STRING);
	        cell.setCellValue(LoginController.idUser);
	        
	        rownum++;
	        cell = row.createCell(2, CellType.STRING);
	        cell.setCellValue("Tên người dùng");
	        row = sheet.createRow(rownum);
	        cell = row.createCell(3, CellType.STRING);
	        cell.setCellValue(LoginController.username);
	        
	        rownum++;
	        row = sheet.createRow(rownum);

	        // EmpNo
	        cell = row.createCell(1, CellType.STRING);
	        cell.setCellValue("ID Chi");
	        cell.setCellStyle(style);
	        // EmpName
	        cell = row.createCell(2, CellType.STRING);
	        cell.setCellValue("ID Danh Muc");
	        cell.setCellStyle(style);
	        // Salary
	        cell = row.createCell(3, CellType.STRING);
	        cell.setCellValue("Tên Danh Mục");
	        cell.setCellStyle(style);
	        // Grade
	        cell = row.createCell(4, CellType.STRING);
	        cell.setCellValue("ID Ví");
	        cell.setCellStyle(style);
	        // Bonus
	        cell = row.createCell(5, CellType.STRING);
	        cell.setCellValue("Tên ví");
	        cell.setCellStyle(style);
	        
	        cell = row.createCell(6, CellType.STRING);
	        cell.setCellValue("Số tiền");
	        cell.setCellStyle(style);
	        
	        cell = row.createCell(7, CellType.STRING);
	        cell.setCellValue("Ngày");
			cell.setCellStyle(style);
	 
//	        // Data
	        for (KhoanChiModel temp : listKT) {
	            rownum++;
	            row = sheet.createRow(rownum); 
	            // EmpNo (A)
	            cell = row.createCell(1, CellType.STRING);
	            cell.setCellValue(temp.getMaChi());
	            // EmpName (B)
	            cell = row.createCell(2, CellType.STRING);
	            cell.setCellValue(temp.getMaDanhMuc());

	            cell = row.createCell(3, CellType.STRING);
	            cell.setCellValue(temp.getTenDanhChi());
	            
	            cell = row.createCell(4, CellType.STRING);
	            cell.setCellValue(temp.getMaVi());
	            
	            cell = row.createCell(5, CellType.STRING);
	            cell.setCellValue(temp.getTenVi());
	            
	            cell = row.createCell(6, CellType.STRING);
	            cell.setCellValue(temp.getSoTien());
	            
	            cell = row.createCell(7, CellType.STRING);
	            cell.setCellValue(temp.getNgay());

	        }
	        File file = new File("C:\\Users\\PhienTran\\eclipse-workspace\\dataemployee2.xls");
	        file.getParentFile().mkdirs();
	 
	        FileOutputStream outFile = new FileOutputStream(file);
	        workbook.write(outFile);
	        System.out.println("Created file: " + file.getAbsolutePath());
		}

		public static List<KhoanChiModel> importFile() throws IOException {
			List<KhoanChiModel> listKhoanThu = new ArrayList<>();

			// Đọc một file XSL.
			FileInputStream inputStream = new FileInputStream(
					new File("C:\\Users\\PhienTran\\eclipse-workspace\\khoanchiinput.xls"));

			// Đối tượng workbook cho file XSL.
			HSSFWorkbook workbook = new HSSFWorkbook(inputStream);

			// Lấy ra sheet đầu tiên từ workbook
			HSSFSheet sheet = workbook.getSheetAt(0);

			// Lấy ra Iterator cho tất cả các dòng của sheet hiện tại.
			Iterator<Row> rowIterator = sheet.iterator();
			rowIterator.next();

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				// Lấy Iterator cho tất cả các cell của dòng hiện tại.
				Iterator<Cell> cellIterator = row.cellIterator();
				KhoanChiModel khoanChiModel = new KhoanChiModel();
				while (cellIterator.hasNext()) {
					Cell nextCell = cellIterator.next();
					int columnIndex = nextCell.getColumnIndex();

					switch (columnIndex) {
					case 0:
						System.out.println(nextCell.getNumericCellValue());
						khoanChiModel.setMaVi((int) nextCell.getNumericCellValue()+"");
						break;
					case 1:
						System.out.println(nextCell.getNumericCellValue());
						
						khoanChiModel.setMaDanhMuc((int) nextCell.getNumericCellValue()+"");
						break;
					case 2:
						System.out.println((int) nextCell.getNumericCellValue());
						khoanChiModel.setSoTien((int) nextCell.getNumericCellValue() );
						break;
					case 3:
						System.out.println(nextCell.getStringCellValue());
						khoanChiModel.setNgay(nextCell.getStringCellValue());
						break;

					}
				}
				listKhoanThu.add(khoanChiModel);


			}
			System.out.println(listKhoanThu.size());
			return listKhoanThu;

		}
		public static void main(String[] args) throws IOException {
			importFile();
		}

}
