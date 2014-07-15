package edu.bionic.easyfly.presentation;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;

@Named
@Scope("session")
public class FileExportProcessor implements Serializable {

	public FileExportProcessor() {
	}

	public void preProcessPDF(Object document) throws BadElementException,
			MalformedURLException, DocumentException, IOException {
		Document pdf = (Document) document;
		pdf.open();
		pdf.setPageSize(PageSize.A4);
		pdf.addCreationDate();
		pdf.add(new Paragraph(" "));
		pdf.add(new Paragraph("Report by date"));
		pdf.add(new Paragraph(" "));
		pdf.add(new Paragraph(" "));

	}

	public void preProcessPDF2(Object document) throws BadElementException,
			MalformedURLException, DocumentException, IOException {
		Document pdf = (Document) document;
		pdf.open();
		pdf.setPageSize(PageSize.A4);
		pdf.addCreationDate();
		pdf.add(new Paragraph(" "));
		pdf.add(new Paragraph("Report by destination"));
		pdf.add(new Paragraph(" "));
		pdf.add(new Paragraph(" "));

	}

}
