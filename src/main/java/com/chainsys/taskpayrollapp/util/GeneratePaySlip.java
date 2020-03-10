package com.chainsys.taskpayrollapp.util;

import com.chainsys.taskpayrollapp.model.PaySlipModel;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;

public class GeneratePaySlip {
	public int paySlip(PaySlipModel obj, int id) throws Exception {
		// OutputStream file = new FileOutputStream(new File("D:\\Test.pdf"));
		String salaryFile = "C:/Users/selv2403/Downloads/taskpayrollapp/taskpayrollapp/src/main/webapp/salary" + id
				+ ".pdf";
		try {
			PdfDocument pdfDoc = new PdfDocument(new PdfWriter(salaryFile));
			Document doc = new Document(pdfDoc);
			Table table = new Table(2);
			table.addHeaderCell("-CATEGORY-");
			table.addHeaderCell("-DETAILS-");
			table.addCell("Employee ID ");
			table.addCell("" + obj.getId());
			table.addCell("Employee Name");
			table.addCell(obj.getName());
			table.addCell("BasePay");
			table.addCell("Rs. " + obj.getSalary());
			table.addCell("Performance_grade");
			table.addCell("" + obj.getPerformanceGrade());
			table.addCell("Salary Increment");
			table.addCell("Rs. " + obj.getSalaryIncrement());
			table.addCell("Allowance");
			table.addCell("Rs. " + obj.getAllowance());
			table.addCell("Leaves Taken");
			table.addCell("" + obj.getLeavesTaken());
			table.addCell("Loss Of Pay");
			table.addCell("Rs. " + obj.getLossOfPay());
			table.addCell("Food_detection");
			table.addCell("Rs. " + obj.getFoodDeduction());
			table.addCell("Cab Deduction");
			table.addCell("Rs. " + obj.getCabDeduction());
			table.addCell("Provident Fund");
			table.addCell("Rs. " + obj.getProvidentFund());
			table.addCell("Total Salary");
			table.addCell("Rs. " + obj.getSalaryToBeCredited());
			doc.add(table);
			doc.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return 1;
	}
}