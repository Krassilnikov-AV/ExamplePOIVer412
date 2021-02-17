/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

import org.apache.poi.xwpf.usermodel.*;

import java.io.*;

/**
 * Класс ClassPOIExample
 */
public class ClassPOIExample {


	public static void main(String[] args) throws Exception {

		//Blank Document
		XWPFDocument document = new XWPFDocument();

		//Write the Document in file system
		FileOutputStream out = new FileOutputStream(new File("D:\\REPOSITORIES-2\\fontstyle.docx"));

		//create paragraph
		XWPFParagraph paragraph = document.createParagraph();

		//Set Bold an Italic
		XWPFRun paragraphOneRunOne = paragraph.createRun();
		paragraphOneRunOne.setBold(true);
		paragraphOneRunOne.setItalic(true);
		paragraphOneRunOne.setText("Font Style");
		paragraphOneRunOne.addBreak();

		//Set text Position
		XWPFRun paragraphOneRunTwo = paragraph.createRun();

		paragraphOneRunTwo.setTextPosition(100);

		//Set Strike through and Font Size and Subscript
		XWPFRun paragraphOneRunThree = paragraph.createRun();
		paragraphOneRunThree.setStrike(true);
		paragraphOneRunThree.setFontSize(20);
		paragraphOneRunThree.setSubscript(VerticalAlign.SUBSCRIPT);
		paragraphOneRunThree.setText(" Different Font Styles");

		document.write(out);
		out.close();
		System.out.println("fontstyle.docx written successully");
	}
}