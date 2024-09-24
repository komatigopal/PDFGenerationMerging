
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfGeneration {

	static PDFMerging pDFMerging = new PDFMerging();

	final String FS = File.separator;

	static String outputPath = "C:\\Users\\Gopal Komati\\Downloads\\zomato2\\";
	static String path = "C:\\Users\\Gopal Komati\\Downloads\\zomato2\\";

	/*
	 * public static void main(String[] args) { for (File f : new
	 * File(path).listFiles()) { if (!f.isDirectory() &&
	 * f.getName().endsWith(".jpg")) { try { imageToPdf(outputPath + f.getName() +
	 * ".pdf", f.getName()); } catch (Exception e) { e.printStackTrace(); } } } }
	 */

	public static void main(String[] args) {
		String fileMerged = outputPath + "_merged.pdf";
		System.out.println(outputPath);
		try {
			OutputStream outputStream = new FileOutputStream(fileMerged);

			List<InputStream> inputPdfList = new ArrayList<InputStream>();
			List<String> set = new ArrayList<String>();
			for (File f : new File(outputPath).listFiles()) {
				set.add(f.getName());
			}

			Collections.sort(set);

			for (String t : set) {
				System.out.println("t - " + t);
			}

			for (String t : set) {
				if (t.endsWith(".pdf") && !t.startsWith("_merged")) {
					try {
						System.out.println(outputPath + t);
						inputPdfList.add(new FileInputStream(outputPath + t));
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}

			try {
				pDFMerging.mergePdfFiles(inputPdfList, outputStream);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	public String createPDF() throws Exception {
		String path = "C:\\Users\\sdc\\Documents\\Scanned Documents\\test\\";

		String destPath = path;

		System.out.println(destPath);

		String imagePath = path + FS + "images" + FS + "logo.png";

		File reportsDir = new File(destPath);
		if (!reportsDir.isDirectory()) {
			reportsDir.mkdirs();
		}

		this.path = destPath;
		String file1 = destPath + "_userInput.pdf";
		String file2 = destPath + "_officersAction.pdf";
		String file3 = destPath + "_userUploadFile.pdf";
		String fileMerged = destPath + "_merged.pdf";

		/*
		 * userInputPDFGeneration(grievance, imagePath, file1);
		 * acePDFGeneration(grievance.getGrievanceId(), grievanceMoves, file2, sections,
		 * imagePath, loginType, sectionId);
		 */

		List<InputStream> inputPdfList = new ArrayList<InputStream>();

		OutputStream outputStream = new FileOutputStream(fileMerged);
		inputPdfList.add(new FileInputStream(file1));

		String extension = ".jpg";

		writeByte(grievance.getUpload_File(), grievance.getGrievanceId(), extension);

		try {
			imageToPdf(file3, "id");
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("id" + "_userUploadFile.pdf" + " creation is success");

		inputPdfList.add(new FileInputStream(file3));

		inputPdfList.add(new FileInputStream(file2));

		pDFMerging.mergePdfFiles(inputPdfList, outputStream);

		String fileContent = Base64.getEncoder().encodeToString(readBytesFromFile(fileMerged));

		deleteDirectory(reportsDir);

		return fileContent;
	}

	/*
	 * private void userInputPDFGeneration(Grievance grievance, String imagePath,
	 * String fileName) { String GrievanceId = grievance.getGrievanceId();
	 * 
	 * try { Document document = new Document();
	 * 
	 * System.out.println("ofter_while_loop_11111");
	 * 
	 * File letterfile = new File(fileName); PdfWriter.getInstance(document, new
	 * FileOutputStream(letterfile));
	 * 
	 * PdfPTable header = new PdfPTable(3); try {
	 * 
	 * header.setWidths(new int[] { 3, 25, 5 }); header.setTotalWidth(500);
	 * header.setLockedWidth(true); header.getDefaultCell().setFixedHeight(60);
	 * header.getDefaultCell().setBorder(Rectangle.BOTTOM);
	 * header.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);
	 * 
	 * Image logo = Image.getInstance(imagePath); header.addCell(logo);
	 * 
	 * PdfPCell text = new PdfPCell(); text.setPaddingBottom(20);
	 * text.setPaddingLeft(30); text.setBorder(Rectangle.BOTTOM);
	 * text.setBorderColor(BaseColor.LIGHT_GRAY); text.addElement(new
	 * Phrase("Jawaharlal Nehru Technological University Hyderabad", new
	 * Font(Font.FontFamily.HELVETICA, 14)));
	 * 
	 * text.addElement(new
	 * Phrase(" University Examination Branch Kukatpally,Hyderabad-500085", new
	 * Font(Font.FontFamily.HELVETICA, 12))); header.addCell(text);
	 * 
	 * document.open();
	 * 
	 * PdfPCell cell1 = new PdfPCell(); cell1.setBorder(Rectangle.BOTTOM);
	 * cell1.setBorderColor(BaseColor.LIGHT_GRAY); header.addCell(cell1);
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * Paragraph para1 = new Paragraph();
	 * 
	 * Font font1 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL); Font
	 * font2 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
	 * 
	 * Chunk glue = new Chunk(new VerticalPositionMark()); Paragraph p = new
	 * Paragraph(); p.add(new Chunk(glue)); p.add("Dt:" + new
	 * SimpleDateFormat("dd-MM-YYYY").format(new Date()));
	 * 
	 * para1.add( new Paragraph("Information Provided by Candidate of " +
	 * GrievanceId + " Grievance Details", font2));
	 * 
	 * PdfPTable table = new PdfPTable(2); table.setWidthPercentage(100f); PdfPCell
	 * cell = new PdfPCell();
	 * 
	 * // cell.setColspan(5); cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	 * 
	 * table.addCell(new PdfPCell(new Phrase("GrievanceId", font1)));
	 * table.addCell(new PdfPCell(new Phrase(grievance.getGrievanceId(), font1)));
	 * table.addCell(new PdfPCell(new Phrase("Htno", font1))); table.addCell(new
	 * PdfPCell(new Phrase(grievance.getHtno(), font1))); table.addCell(new
	 * PdfPCell(new Phrase("Name", font1))); table.addCell(new PdfPCell(new
	 * Phrase(grievance.getName(), font1))); table.addCell(new PdfPCell(new
	 * Phrase("Mobile", font1))); table.addCell(new PdfPCell(new
	 * Phrase(grievance.getMobile(), font1))); table.addCell(new PdfPCell(new
	 * Phrase("Email", font1))); table.addCell(new PdfPCell(new
	 * Phrase(grievance.getEmail(), font1))); table.addCell(new PdfPCell(new
	 * Phrase("Sub Cat", font1))); table.addCell(new PdfPCell(new
	 * Phrase(grievance.getSubCategoryId(), font1))); table.addCell(new PdfPCell(new
	 * Phrase("Description", font1))); table.addCell(new PdfPCell(new
	 * Phrase(grievance.getDescription(), font1))); table.addCell(new PdfPCell(new
	 * Phrase("Upload Time", font1))); table.addCell(new PdfPCell(new
	 * Phrase(grievance.getUpload_Time(), font1))); table.addCell(new PdfPCell(new
	 * Phrase("Status", font1))); table.addCell(new PdfPCell(new
	 * Phrase(grievance.getStatus(), font1)));
	 * 
	 * table.setSpacingBefore(10.0f); table.setSpacingAfter(5.0f);
	 * 
	 * Paragraph p1 = new Paragraph(); if
	 * (!grievance.getUploadFileType().equals("empty")) { p1 = new Paragraph();
	 * p1.add(new Chunk(glue)); p1.setAlignment(Element.ALIGN_CENTER); p1.add(new
	 * Paragraph(
	 * "Candidate Uploaded Supporting documents are in next page of this PDF                            "
	 * , font1)); }
	 * 
	 * document.open();
	 * 
	 * document.add(header); document.add(Chunk.NEWLINE);
	 * document.add(Chunk.NEWLINE); document.add(Chunk.NEWLINE); document.add(p);
	 * document.add(para1); document.add(table); document.add(p1);
	 * 
	 * document.close();
	 * 
	 * System.out.println(grievance.getGrievanceId() + "_userInput.pdf" +
	 * " creation is success");
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } }
	 */

	/*
	 * private void acePDFGeneration(String grievanceId, List<GrievanceMove>
	 * grievanceMoves, String fileName, List<Section> sections, String imagePath,
	 * String loginType, String sectionId) {
	 * 
	 * SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss a");
	 * String currentTimeStamp = formatter.format(new Date());
	 * 
	 * SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); String
	 * currentTimeStamp2 = format.format(new Date());
	 * 
	 * String endingString = "This report is generated on " + currentTimeStamp; if
	 * (!sectionId.equalsIgnoreCase("Grievance Clerk")) { endingString +=
	 * " by System for " + loginType + " of "; for (Section section : sections) { if
	 * (section.getId().equalsIgnoreCase(sectionId)) { endingString +=
	 * section.getSectionName(); } } } else { endingString +=
	 * " by System for Grievance Clerk"; }
	 * 
	 * try {
	 * 
	 * Document document = new Document(PageSize.A4);
	 * 
	 * System.out.println("ofter_while_loop_11111");
	 * 
	 * File letterfile = new File(fileName); PdfWriter.getInstance(document, new
	 * FileOutputStream(letterfile));
	 * 
	 * PdfPTable table = new PdfPTable(8); table.setWidthPercentage(110f); PdfPCell
	 * cell = new PdfPCell(); cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	 * 
	 * Font font1 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL); Font
	 * font2 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	 * 
	 * Chunk glue = new Chunk(new VerticalPositionMark()); Paragraph p = new
	 * Paragraph(); p.add(new Chunk(glue)); p.setAlignment(Element.ALIGN_CENTER);
	 * p.add(new Paragraph("------======" + endingString +
	 * "======------                ", font1));
	 * 
	 * Paragraph para1 = new Paragraph();
	 * 
	 * para1.add(new Paragraph("Officers Action Details against Grievance " +
	 * grievanceId, font2)); para1.add(new Paragraph(" ", font2));
	 * 
	 * if (grievanceMoves.size() > 0) {
	 * 
	 * table.addCell(new PdfPCell(new Phrase("Grievance Id", font1)));
	 * table.addCell(new PdfPCell(new Phrase("Section", font1))); table.addCell(new
	 * PdfPCell(new Phrase("Remarks", font1))); table.addCell(new PdfPCell(new
	 * Phrase("Arrival Time", font1))); table.addCell(new PdfPCell(new
	 * Phrase("Depature Time", font1))); table.addCell(new PdfPCell(new
	 * Phrase("Status", font1))); table.addCell(new PdfPCell(new
	 * Phrase("Remarks visible for Students", font1))); table.addCell(new
	 * PdfPCell(new Phrase("Duration At Section", font1)));
	 * 
	 * for (GrievanceMove grievanceMove : grievanceMoves) {
	 * 
	 * table.addCell(new PdfPCell(new Phrase(grievanceMove.getGrievanceId(),
	 * font1)));
	 * 
	 * for (Section section : sections) { if
	 * (grievanceMove.getSendSection().equalsIgnoreCase(section.getId())) {
	 * table.addCell(new PdfPCell(new Phrase(section.getSectionName(), font1))); } }
	 * 
	 * table.addCell(new PdfPCell(new Phrase(grievanceMove.getRemarks(), font1)));
	 * table.addCell(new PdfPCell(new Phrase(grievanceMove.getArrivalTime(),
	 * font1))); table.addCell(new PdfPCell(new
	 * Phrase(grievanceMove.getDepatureTime(), font1))); table.addCell(new
	 * PdfPCell(new Phrase(grievanceMove.getStatus(), font1))); if (null ==
	 * grievanceMove.getStudentViewStatus()) { table.addCell(new PdfPCell(new
	 * Phrase("Not yet Close / Transfer", font1))); } else if
	 * (grievanceMove.getStudentViewStatus().equalsIgnoreCase("1")) {
	 * table.addCell(new PdfPCell(new Phrase("Yes", font1))); } else {
	 * table.addCell(new PdfPCell(new Phrase("No", font1))); }
	 * 
	 * if (null == grievanceMove.getDepatureTime()) { String duration =
	 * getDuration(grievanceMove.getArrivalTime(), currentTimeStamp2);
	 * table.addCell(new PdfPCell(new Phrase("Pending since " + duration, font1)));
	 * } else { String duration = getDuration(grievanceMove.getArrivalTime(),
	 * grievanceMove.getDepatureTime()); table.addCell(new PdfPCell(new
	 * Phrase(duration, font1))); System.out.println(duration); }
	 * table.setSpacingBefore(10.0f); table.setSpacingAfter(5.0f); } }
	 * 
	 * document.open();
	 * 
	 * document.add(Chunk.NEWLINE); document.add(Chunk.NEWLINE);
	 * document.add(Chunk.NEWLINE); document.add(Chunk.NEWLINE);
	 * 
	 * document.add(para1); // document.add(Chunk.NEWLINE); document.add(table);
	 * document.add(Chunk.NEWLINE); document.add(p); document.close();
	 * 
	 * System.out.println(grievanceId + "_officersAction.pdf" +
	 * " creation is success");
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } }
	 */

	private String getDuration(String s1, String s2) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String duration = "";
		try {
			Date startDate = format.parse(s1);
			Date endDate = format.parse(s2);

			long diffInMilliSec = endDate.getTime() - startDate.getTime();
			long seconds = (diffInMilliSec / 1000) % 60;
			long minutes = (diffInMilliSec / (1000 * 60)) % 60;
			long hours = (diffInMilliSec / (1000 * 60 * 60)) % 24;
			long days = (diffInMilliSec / (1000 * 60 * 60 * 24)) % 365;
			long years = (diffInMilliSec / (1000l * 60 * 60 * 24 * 365));
			duration = years + " years, "/* + months + " months, " */ + days + " days, " + hours + " hours, " + minutes
					+ " minutes, " + seconds + " seconds";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return duration;
	}

	private static void imageToPdf(String outputFile, String grievanceId) throws Exception {
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(new File(outputFile)));
		document.open();
		document.newPage();
		Image image = Image.getInstance(path + grievanceId);
		image.setAbsolutePosition(0, 0);
		image.setBorderWidth(0);
		image.scaleAbsoluteHeight(PageSize.A4.getHeight());
		image.scaleAbsoluteWidth(PageSize.A4.getWidth());
		document.add(image);
		/*
		 * for (String f : files) { document.newPage(); //Image image =
		 * Image.getInstance(new File(root, f).getAbsolutePath()); Image
		 * image=Image.getInstance(path + grievanceId +".jpg");
		 * image.setAbsolutePosition(0, 0); image.setBorderWidth(0);
		 * image.scaleAbsoluteHeight(PageSize.A4.getHeight());
		 * image.scaleAbsoluteWidth(PageSize.A4.getWidth()); document.add(image); }
		 */
		document.close();
	}

	private void writeByte(byte[] bytes, String grievanceId, String extension) {
		try {
			OutputStream os = new FileOutputStream(path + grievanceId + extension);
			os.write(bytes);
			System.out.println("Successfully" + " byte inserted");
			os.close();
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
	}

	private byte[] readBytesFromFile(String filePath) {
		FileInputStream fileInputStream = null;
		byte[] bytesArray = null;
		try {
			File file = new File(filePath);
			bytesArray = new byte[(int) file.length()];
			// read file into bytes[]
			fileInputStream = new FileInputStream(file);
			fileInputStream.read(bytesArray);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return bytesArray;
	}

	private boolean deleteDirectory(File dir) {
		if (dir.isDirectory()) {
			File[] children = dir.listFiles();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDirectory(children[i]);
				if (!success) {
					return false;
				}
			}
		} // either file or an empty directory
		System.out.println("removing file or directory : " + dir.getName());
		return dir.delete();
	}

	/*
	 * public String mergeAllFilesAsOnePDFAction(MultipartFile[] uploadedFiles,
	 * String path, String htno) throws IOException, Exception { List<InputStream>
	 * inputPdfList = new ArrayList<InputStream>(); String destPath = path + FS +
	 * "files" + FS + "reports" + FS + "generatedPDFs" + FS + htno + FS; File
	 * reportsDir = new File(destPath); if (!reportsDir.isDirectory()) {
	 * reportsDir.mkdirs(); } int count = 0; for (MultipartFile mFile :
	 * uploadedFiles) { if (mFile.getContentType().contains("image")) { String
	 * fileName = destPath + FS + mFile.getName() + count + ".pdf";
	 * imageBytesToPdf(fileName, mFile.getBytes()); inputPdfList.add(new
	 * FileInputStream(fileName)); } else if
	 * (mFile.getContentType().contains("pdf")) { InputStream is = new
	 * ByteArrayInputStream(mFile.getBytes()); inputPdfList.add(is); } count++; }
	 * 
	 * String fileMerged = destPath + FS + htno + "_merged.pdf";
	 * 
	 * OutputStream outputStream = new FileOutputStream(fileMerged);
	 * 
	 * pDFMerging.mergePdfFiles(inputPdfList, outputStream);
	 * 
	 * String fileContent =
	 * Base64.getEncoder().encodeToString(readBytesFromFile(fileMerged));
	 * 
	 * deleteDirectory(reportsDir);
	 * 
	 * return fileContent; }
	 */

	private void imageBytesToPdf(String outputFile, byte[] imageBytes) throws Exception {
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(new File(outputFile)));
		document.open();
		document.newPage();
		Image image = Image.getInstance(imageBytes);
		image.setAbsolutePosition(0, 0);
		image.setBorderWidth(0);
		image.scaleAbsoluteHeight(PageSize.A4.getHeight());
		image.scaleAbsoluteWidth(PageSize.A4.getWidth());
		document.add(image);
		document.close();
	}
}