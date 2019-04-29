package greedc.utils;

import com.google.gson.internal.LinkedTreeMap;
import ecustom.util.CustomUtil;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.*;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.*;

import java.awt.*;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JXLUtil {

	private static final Colour[] COLOURS = {
			// Colour.UNKNOWN,	// 修改不生效
			// Colour.BLACK,	// 修改不生效
			Colour.WHITE,
			// Colour.DEFAULT_BACKGROUND1,	// 修改不生效
			// Colour.DEFAULT_BACKGROUND,	// 修改不生效
			Colour.PALETTE_BLACK,
			Colour.RED,
			Colour.BRIGHT_GREEN,
			Colour.BLUE,
			Colour.YELLOW,
			Colour.PINK,
			Colour.TURQUOISE,
			Colour.DARK_RED,
			Colour.GREEN,
			Colour.DARK_BLUE,
			Colour.DARK_YELLOW,
			Colour.VIOLET,
			Colour.TEAL,
			Colour.GREY_25_PERCENT,
			Colour.GREY_50_PERCENT,
			Colour.PERIWINKLE,
			Colour.PLUM2,
			Colour.IVORY,
			Colour.LIGHT_TURQUOISE2,
			Colour.DARK_PURPLE,
			Colour.CORAL,
			Colour.OCEAN_BLUE,
			Colour.ICE_BLUE,
			Colour.DARK_BLUE2,
			Colour.PINK2,
			Colour.YELLOW2,
			Colour.TURQOISE2,
			Colour.VIOLET2,
			Colour.DARK_RED2,
			Colour.TEAL2,
			Colour.BLUE2,
			Colour.SKY_BLUE,
			Colour.LIGHT_TURQUOISE,
			Colour.LIGHT_GREEN,
			Colour.VERY_LIGHT_YELLOW,
			Colour.PALE_BLUE,
			Colour.ROSE,
			Colour.LAVENDER,
			Colour.TAN,
			Colour.LIGHT_BLUE,
			Colour.AQUA,
			Colour.LIME,
			Colour.GOLD,
			Colour.LIGHT_ORANGE,
			Colour.ORANGE,
			Colour.BLUE_GREY,
			Colour.GREY_40_PERCENT,
			Colour.DARK_TEAL,
			Colour.SEA_GREEN,
			Colour.DARK_GREEN,
			Colour.OLIVE_GREEN,
			Colour.BROWN,
			Colour.PLUM,
			Colour.INDIGO,
			Colour.GREY_80_PERCENT,
			// Colour.AUTOMATIC,	// 修改不生效
			Colour.GRAY_80,
			Colour.GRAY_50,
			Colour.GRAY_25
	};

	private WritableWorkbook workbook;
	private WritableSheet sheet;
	private Map<String, Integer> colourMap = new HashMap<String, Integer>();
	private List<String> ignoreCells = new ArrayList<String>();

	private int getCellIndex(int rowIndex, int cellIndex) {
		while (ignoreCells.contains(rowIndex + "_" + cellIndex)) {
			cellIndex++;
		}
		return cellIndex;
	}

	private void addToIgnoreCells(int startRowIndex, int startCellIndex, int endRowIndex, int endCellIndex) {
		for (int rowIndex = startRowIndex; rowIndex <= endRowIndex; rowIndex++) {
			for (int cellIndex = startCellIndex; cellIndex <= endCellIndex; cellIndex++) {
				ignoreCells.add(rowIndex + "_" + cellIndex);
			}
		}
	}

	private int getColourIndex(String colorHex) {
		if (!colourMap.containsKey(colorHex)) {
			Color color = Color.decode(colorHex);
			int colourIndex = colourMap.size();
			workbook.setColourRGB(COLOURS[colourIndex], color.getRed(), color.getGreen(), color.getBlue());
			colourMap.put(colorHex, colourIndex);
		}
		return colourMap.get(colorHex);
	}

	public void createCell(Map cellMap, int rowIndex, int cellIndex) throws Exception {
		String value = (String)cellMap.get("value");
		String backgroundColor = (String)cellMap.get("backgroundColor");
		String fontColor = (String)cellMap.get("fontColor");
		String fontWeight = (String)cellMap.get("fontWeight");
		String textAlign = (String)cellMap.get("textAlign");

		WritableFont font = new WritableFont(WritableFont.ARIAL, 11, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
		if ("bold".equalsIgnoreCase(fontWeight)) {
			font.setBoldStyle(WritableFont.BOLD);
		}
		if (CustomUtil.isNotBlank(fontColor)) {
			String colorHex = fontColor.toUpperCase();
			font.setColour(COLOURS[getColourIndex(colorHex)]);
		}
		WritableCellFormat format = new WritableCellFormat(font);
		format.setVerticalAlignment(VerticalAlignment.CENTRE);
		if ("center".equalsIgnoreCase(textAlign)) {
			format.setAlignment(Alignment.CENTRE);
		} else if ("right".equalsIgnoreCase(textAlign)) {
			format.setAlignment(Alignment.RIGHT);
		}
		if (CustomUtil.isNotBlank(backgroundColor)) {
			String colorHex = backgroundColor.toUpperCase();
			format.setBackground(COLOURS[getColourIndex(colorHex)]);
		}
		String colorHex = "#000000";
		format.setBorder(Border.ALL, BorderLineStyle.THIN, COLOURS[getColourIndex(colorHex)]);
		sheet.addCell(new Label(cellIndex, rowIndex, value, format));
	}

	public void createWookbook(String sheetName, List rows, OutputStream out) throws Exception {
		this.workbook = Workbook.createWorkbook(out);
		this.sheet = this.workbook.createSheet(sheetName, 0);
		generateWorkbook(rows);
		workbook.write();
		workbook.close();
	}

	private void generateWorkbook(List rows) throws Exception {
		int maxCellIndex = 1;
		for (int rowIndex = 0; rowIndex < rows.size(); rowIndex++) {
			int cellIndex = 0;
			for (Object cellObj : (List)rows.get(rowIndex)) {
				LinkedTreeMap cell = (LinkedTreeMap)cellObj;
				cellIndex = getCellIndex(rowIndex, cellIndex);
				createCell(cell, rowIndex, cellIndex);
				int colspan = CustomUtil.getInt((String)cell.get("colspan"), 0);
				if (colspan > 0) {
					sheet.mergeCells(cellIndex, rowIndex, cellIndex + colspan - 1, rowIndex);
					addToIgnoreCells(rowIndex, cellIndex, rowIndex, cellIndex + colspan - 1);

				}
				int rowspan = CustomUtil.getInt((String)cell.get("rowspan"), 0);
				if (rowspan > 0) {
					sheet.mergeCells(cellIndex, rowIndex, cellIndex, rowIndex + rowspan - 1);
					addToIgnoreCells(rowIndex, cellIndex, rowIndex + rowspan - 1, cellIndex);
				}
				maxCellIndex = maxCellIndex > cellIndex ? maxCellIndex : cellIndex;
				cellIndex++;
			}
		}
	}
}
