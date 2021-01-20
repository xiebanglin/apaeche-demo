package com.xiebl.common;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;

public class DownloadStyleUtils {

	/**
	 * 设置边框
	 * 
	 * @param style
	 */
	private static void setBorder(HSSFCellStyle style) {
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
	}

	/**
	 * 设置合并单元格的边框
	 * 
	 * @param region
	 * @param sheet
	 */
	public static void setRegionBorder(CellRangeAddress region, org.apache.poi.ss.usermodel.Sheet sheet) {
		RegionUtil.setBorderTop(BorderStyle.THIN, region, sheet);
		RegionUtil.setBorderBottom(BorderStyle.THIN, region, sheet);
		RegionUtil.setBorderLeft(BorderStyle.THIN, region, sheet);
		RegionUtil.setBorderRight(BorderStyle.THIN, region, sheet);
	}

	/**
	 * 生成字体
	 * 
	 * @param wb       工作簿
	 * @param fontName 字体名称 -宋体、黑体等（具体查看poi官方）
	 * @param fontSize
	 * @param bold     true-粗体
	 * @param color    例如 红色 ：HSSFColorPredefined.RED.getIndex()
	 * @return
	 */
	public static HSSFFont genFont(HSSFWorkbook wb, String fontName, int fontSize, boolean bold, short color) {
		HSSFFont font = wb.createFont();
		font.setFontName(fontName);// 设置字体
		font.setFontHeightInPoints((short) fontSize);// 设置字体大小
		font.setBold(bold);// 设置是否粗体
		font.setColor(color);
		return font;
	}

	/**
	 * 生成风格
	 * 
	 * @param wb     工作簿
	 * @param font   字体
	 * @param align  水平对齐，例如 HorizontalAlignment.CENTER
	 * @param valign 垂直对齐，例如 VerticalAlignment.CENTER
	 * @param border true-设置边框
	 * @param r      背景颜色，若为空则不生效
	 * @param g      背景颜色，若为空则不生效
	 * @param b      背景颜色，若为空则不生效
	 * @return
	 */
	public static HSSFCellStyle genStyle(HSSFWorkbook wb, HSSFFont font, HorizontalAlignment align,
			VerticalAlignment valign, boolean border, Integer r, Integer g, Integer b) {
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(align);
		style.setVerticalAlignment(valign);
		style.setFont(font);
		if (border) {
			setBorder(style);
		}
		if (r != null && g != null && b != null) {
			HSSFPalette customPalette = wb.getCustomPalette();
			customPalette.setColorAtIndex(IndexedColors.GREY_25_PERCENT.index, r.byteValue(), g.byteValue(),
					b.byteValue());
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
			style.setWrapText(true);
		}
		return style;
	}

}
