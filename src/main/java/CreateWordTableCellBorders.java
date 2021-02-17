/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.FileOutputStream;

/**
 * Класс CreateWordTableCellBorders
 */
public class CreateWordTableCellBorders {

	private enum Border { LEFT, TOP, BOTTOM, RIGHT }

	static void setTableCellBorder(XWPFTableCell cell, Border border, STBorder.Enum type) {
		CTTc tc = cell.getCTTc();
		CTTcPr tcPr = tc.getTcPr(); if (tcPr == null) tcPr = tc.addNewTcPr();
		CTTcBorders tcBorders = tcPr.getTcBorders(); if (tcBorders == null) tcBorders = tcPr.addNewTcBorders();
		if (border == Border.LEFT) {
			CTBorder left = tcBorders.getLeft(); if(left == null) left = tcBorders.addNewLeft();
			left.setVal(type);
		} else if (border == Border.TOP) {
			CTBorder top = tcBorders.getTop(); if(top == null) top = tcBorders.addNewTop();
			top.setVal(type);
		} else if (border == Border.BOTTOM) {
			CTBorder bottom = tcBorders.getBottom(); if(bottom == null) bottom = tcBorders.addNewBottom();
			bottom.setVal(type);
		} else if (border == Border.RIGHT) {
			CTBorder right = tcBorders.getRight(); if(right == null) right = tcBorders.addNewRight();
			right.setVal(type);
		}
	}

	public static void main(String[] args) throws Exception {

		XWPFDocument document= new XWPFDocument();

		XWPFParagraph paragraph = document.createParagraph();
		XWPFRun run=paragraph.createRun();
		run.setText("The table:");

		//create the table
		XWPFTable table = document.createTable(2,3);

		//set column widths and table cell borders
		for (XWPFTableRow row : table.getRows()) {
			row.getCell(0).setWidth("5000");
			row.getCell(1).setWidth("500");
			setTableCellBorder(row.getCell(1), Border.TOP, STBorder.NIL);
			setTableCellBorder(row.getCell(1), Border.BOTTOM, STBorder.NIL);
			row.getCell(2).setWidth("5000");
		}

		FileOutputStream out = new FileOutputStream("D:\\REPOSITORIES-2\\CreateWordTableCellBorders.docx");
		document.write(out);
		out.close();
		document.close();
	}
}