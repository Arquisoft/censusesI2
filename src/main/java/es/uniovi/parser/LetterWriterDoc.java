package es.uniovi.parser;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class LetterWriterDoc extends LetterWriter {

	private XWPFDocument document;

	public LetterWriterDoc() {
		super();
	}

	public void writeLetter(Voter voter) {
		document = new XWPFDocument();
		XWPFParagraph tmpParagraph = document.createParagraph();
		XWPFRun tmpRun = tmpParagraph.createRun();
		tmpRun.setText(getText(voter));
		tmpRun.setFontSize(18);
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(new File(parentDir, voter.getNif()
					+ ".doc"));

			document.write(fos);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
