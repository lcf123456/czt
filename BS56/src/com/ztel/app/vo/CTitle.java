package com.ztel.app.vo;

import jxl.format.Alignment;
import jxl.format.BorderLineStyle;
import jxl.write.Border;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WriteException;

public class CTitle {
	public String title;
	public int columnLines=1;
    private  WritableCellFormat formatTitle;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getColumnLines() {
		return columnLines;
	}
	public void setColumnLines(int columnLines) {
		this.columnLines = columnLines;
	}
	public void SetFormats(WritableCellFormat format)
	{
		this.formatTitle=format;
	}
	public WritableCellFormat getFormats() throws WriteException {
		if(formatTitle ==null)
		{
		WritableFont fontTitle = new WritableFont(WritableFont.TIMES, 10, WritableFont.NO_BOLD );  
		fontTitle.setColour(jxl.format.Colour.RED);  
		 formatTitle = new WritableCellFormat(fontTitle);  
		formatTitle.setWrap(true);  
		formatTitle.setAlignment(Alignment.CENTRE);
		formatTitle.setBorder(Border.ALL, BorderLineStyle.THIN);
		}
		return formatTitle;
		
	}
	
	

}
