package com.yzc.springcloud.utils;

import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.apache.poi.ss.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;

/**
 * 导出工具类
 */
public class ExportCvsUtil {

    /**
     * 基本表头样式和标题体样式设置
     * @return
     */
    public static HorizontalCellStyleStrategy styleWrite() {
        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        headWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short) 12);
        //是否加粗
        headWriteFont.setBold(true);
        headWriteCellStyle.setWriteFont(headWriteFont);
        // 内容的策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        WriteFont contentWriteFont = new WriteFont();
        // 字体大小
        contentWriteFont.setFontHeightInPoints((short) 10);
        contentWriteCellStyle.setWriteFont(contentWriteFont);
        //设置 自动换行
        contentWriteCellStyle.setWrapped(true);
        //设置 垂直居中
        contentWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        //设置 水平居中
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        //设置边框样式
        /*contentWriteCellStyle.setBorderLeft(MEDIUM);
        contentWriteCellStyle.setBorderTop(MEDIUM);
        contentWriteCellStyle.setBorderRight(MEDIUM);
        contentWriteCellStyle.setBorderBottom(MEDIUM);*/
        // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
        return horizontalCellStyleStrategy;
    }

    /**
     * 导出设置
     * @param name
     * @param response
     * @throws UnsupportedEncodingException
     */
    public static void setExportCvs(String name, HttpServletResponse response) throws UnsupportedEncodingException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode(name, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename="+fileName+".csv");
    }

    /**
     * 导出设置
     * @param name
     * @param response
     * @throws UnsupportedEncodingException
     */
    public static void setExportXls(String name, HttpServletResponse response) throws UnsupportedEncodingException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode(name, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename="+fileName+".xls");
    }

    /**
     * 集拼-导出物流商样式
     */
    public static class OutorderGatherCellWriteHandler implements CellWriteHandler {

        @Override
        public void beforeCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row, Head head, Integer integer, Integer integer1, Boolean aBoolean) {

        }

        @Override
        public void afterCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Cell cell, Head head, Integer integer, Boolean aBoolean) {

        }

        @Override
        public void afterCellDataConverted(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, CellData cellData, Cell cell, Head head, Integer integer, Boolean aBoolean) {

        }

        @Override
        public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, List<CellData> list, Cell cell, Head head, Integer integer, Boolean aBoolean) {
            Sheet sheet = writeSheetHolder.getSheet();
            Workbook workbook = sheet.getWorkbook();
            Row row = sheet.getRow(sheet.getPhysicalNumberOfRows()-2);
            if (null != row && row.equals(cell.getRow())){
                CellStyle cellStyle = workbook.createCellStyle();
                cellStyle.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
                cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                row.setRowStyle(cellStyle);
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()){
                    if (cell.equals(cellIterator.next())){
                        Font font = workbook.createFont();
                        font.setFontHeightInPoints((short) 11);
                        font.setFontName("Calibri");
                        font.setBold(true);
                        cellStyle.setFont(font);
                        cellStyle.setAlignment(HorizontalAlignment.CENTER);
                        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                        cell.setCellStyle(cellStyle);
                    }
                }
            }
        }
    }
}
