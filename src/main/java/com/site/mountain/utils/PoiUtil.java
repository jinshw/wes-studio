package com.site.mountain.utils;

import com.site.mountain.entity.PoiNode;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PoiUtil {

    private final static Logger logger = LoggerFactory.getLogger(PoiUtil.class);
    private final static String AUTHOR = "太极联睿";
    public final static List<PoiNode> USER_TITLES = new ArrayList<>();


    public static ResponseEntity<byte[]> export2Excel(String sheetName, List<PoiNode> titles,
                                                      List<Map<String, Object>> datas) {
        HttpHeaders headers = null;
        ByteArrayOutputStream baos = null;
        try {
            // 1.创建Excel文档
            HSSFWorkbook workbook = new HSSFWorkbook();
            generateWorkbook(sheetName, workbook);
            // 创建Excel表单
            HSSFSheet sheet = workbook.createSheet(sheetName);
            // 创建日期显示格式
            HSSFCellStyle dateCellStyle = workbook.createCellStyle();


            HSSFDataFormat df = workbook.createDataFormat();//format类
            dateCellStyle.setDataFormat(df.getFormat("yyyy-MM-dd HH:mm:ss"));
//            dateCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
            // 创建标题的显示样式
            HSSFCellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.index);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            // 定义列的宽度
            sheet.setColumnWidth(0, 8 * 256);
            for (int j = 0; j < titles.size(); j++) {
                sheet.setColumnWidth(j + 1, titles.get(j).getColumnWidth() * 256);
            }
            // 设置表头
            HSSFRow headerRow = sheet.createRow(0);
            HSSFCell cell0 = headerRow.createCell(0);
            cell0.setCellValue("编号");
            cell0.setCellStyle(headerStyle);
            for (int j = 0; j < titles.size(); j++) {
                HSSFCell cell = headerRow.createCell(j + 1);
                cell.setCellValue(titles.get(j).getTitle());
                cell.setCellStyle(headerStyle);
            }
            // 装数据
            for (int i = 0; i < datas.size(); i++) {
                HSSFRow row = sheet.createRow(i + 1);
                Map<String, Object> map = datas.get(i);
                row.createCell(0).setCellValue(i + 1);
                for (int j = 0; j < titles.size(); j++) {
                    Object nowValue = map.get(titles.get(j).getKey());
                    if (nowValue == null) {
                        continue;
                    }
                    if (nowValue instanceof Date) {
                        HSSFCell dateCell = row.createCell(j + 1);
                        dateCell.setCellValue((Date) nowValue);
                        dateCell.setCellStyle(dateCellStyle);
                    } else {
                        row.createCell(j + 1).setCellValue(String.valueOf(nowValue));
                    }
                }
            }
            headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment",
                    new String((sheetName + ".xls").getBytes("UTF-8"), "iso-8859-1"));
            //headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentType(MediaType.valueOf("application/octet-stream;charset=utf-8"));
            baos = new ByteArrayOutputStream();
            workbook.write(baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.CREATED);
    }

    public static ResponseEntity<byte[]> export2Excel(String sheetName, List<PoiNode> titles,
                                                      List<Map<String, Object>> datas, String format) {
        HttpHeaders headers = null;
        ByteArrayOutputStream baos = null;
        try {
            // 1.创建Excel文档
            HSSFWorkbook workbook = new HSSFWorkbook();
            generateWorkbook(sheetName, workbook);
            // 创建Excel表单
            HSSFSheet sheet = workbook.createSheet(sheetName);
            // 创建日期显示格式
            HSSFCellStyle dateCellStyle = workbook.createCellStyle();
            short df = workbook.createDataFormat().getFormat(format);
            dateCellStyle.setDataFormat(df);
            // 创建标题的显示样式
            HSSFCellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            // 定义列的宽度
            sheet.setColumnWidth(0, 8 * 256);
            for (int j = 0; j < titles.size(); j++) {
                sheet.setColumnWidth(j + 1, titles.get(j).getColumnWidth() * 256);
            }
            // 设置表头
            HSSFRow headerRow = sheet.createRow(0);
            HSSFCell cell0 = headerRow.createCell(0);
            cell0.setCellValue("编号");
            cell0.setCellStyle(headerStyle);
            for (int j = 0; j < titles.size(); j++) {
                HSSFCell cell = headerRow.createCell(j + 1);
                cell.setCellValue(titles.get(j).getTitle());
                cell.setCellStyle(headerStyle);
            }
            // 装数据
            for (int i = 0; i < datas.size(); i++) {
                HSSFRow row = sheet.createRow(i + 1);
                Map<String, Object> map = datas.get(i);
                row.createCell(0).setCellValue(i + 1);
                for (int j = 0; j < titles.size(); j++) {
                    Object nowValue = map.get(titles.get(j).getKey());
                    if (nowValue == null) {
                        continue;
                    }
                    if (nowValue instanceof Date) {
                        HSSFCell dateCell = row.createCell(j + 1);
                        dateCell.setCellValue((Date) nowValue);
                        dateCell.setCellStyle(dateCellStyle);
                    } else {
                        row.createCell(j + 1).setCellValue(String.valueOf(nowValue));
                    }
                }
            }
            headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment",
                    new String((sheetName + ".xls").getBytes("UTF-8"), "iso-8859-1"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            baos = new ByteArrayOutputStream();
            workbook.write(baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.CREATED);
    }


    public static ResponseEntity<byte[]> exportExcelSheets(Map<String, Object> params) {

        List<String> sheetNames = (List<String>) params.get("sheetNames");
        List<List<Map<String, Object>>> listDatas = (List<List<Map<String, Object>>>) params.get("listDatas");
        List<List<PoiNode>> listTitles = (List<List<PoiNode>>) params.get("listTitles");
        HttpHeaders headers = null;
        ByteArrayOutputStream baos = null;
        String format = "yyyy-mm-dd";
        try {
            // 1.创建Excel文档
            HSSFWorkbook workbook = new HSSFWorkbook();
            for (int k = 0; k < sheetNames.size(); k++) {
                String sheetName = sheetNames.get(k);
                List<PoiNode> titles = listTitles.get(k);
                List<Map<String, Object>> datas = listDatas.get(k);

                generateWorkbook(sheetName, workbook);
                // 创建Excel表单
                HSSFSheet sheet = workbook.createSheet(sheetName);
                // 创建日期显示格式
                HSSFCellStyle dateCellStyle = workbook.createCellStyle();
                short df = workbook.createDataFormat().getFormat(format);
                dateCellStyle.setDataFormat(df);
                // 创建标题的显示样式
                HSSFCellStyle headerStyle = workbook.createCellStyle();
                headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
                headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                // 定义列的宽度
                sheet.setColumnWidth(0, 8 * 256);
                for (int j = 0; j < titles.size(); j++) {

                    sheet.setColumnWidth(j + 1, titles.get(j).getColumnWidth() * 256);
                }
                // 设置表头
                HSSFRow headerRow = sheet.createRow(0);
                HSSFCell cell0 = headerRow.createCell(0);
                cell0.setCellValue("编号");
                cell0.setCellStyle(headerStyle);
                for (int j = 0; j < titles.size(); j++) {
                    HSSFCell cell = headerRow.createCell(j + 1);
                    cell.setCellValue(titles.get(j).getTitle());
                    cell.setCellStyle(headerStyle);
                }
                // 装数据
                for (int i = 0; i < datas.size(); i++) {
                    HSSFRow row = sheet.createRow(i + 1);
                    Map<String, Object> map = datas.get(i);
                    row.createCell(0).setCellValue(i + 1);
                    for (int j = 0; j < titles.size(); j++) {
                        Object nowValue = map.get(titles.get(j).getKey());
                        if (nowValue == null) {
                            continue;
                        }
                        HSSFCell dateCell = row.createCell(j + 1);
                        if (nowValue instanceof Date) {
                            dateCell.setCellValue((Date) nowValue);
                            dateCell.setCellStyle(dateCellStyle);
                        } else {
                            dateCell.setCellValue(String.valueOf(nowValue));
                            if (k == 0) {
                                HSSFFont font = workbook.createFont();
                                font.setColor(Font.COLOR_RED);
                                HSSFCellStyle cellStyle = workbook.createCellStyle();
                                cellStyle.setFont(font);
                                dateCell.setCellStyle(cellStyle);
                            }
                        }

                    }
                }
            }
            headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment",
                    new String((sheetNames.get(1) + ".xls").getBytes("UTF-8"), "iso-8859-1"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            baos = new ByteArrayOutputStream();
            workbook.write(baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.CREATED);
    }


    private static void generateWorkbook(String sheetName, HSSFWorkbook workbook) {
        // 2.创建文档摘要
        workbook.createInformationProperties();
        // 3.获取文档信息，并配置
        DocumentSummaryInformation dsi = workbook.getDocumentSummaryInformation();
        // 3.1文档类别
        dsi.setCategory(sheetName);
        // 3.2设置文档管理员
        dsi.setManager("");
        // 3.3设置组织机构
        dsi.setCompany(AUTHOR);
        // 4.获取摘要信息并配置
        SummaryInformation si = workbook.getSummaryInformation();
        // 4.1设置文档主题
        si.setSubject(sheetName);
        // 4.2.设置文档标题
        si.setTitle(sheetName);
        // 4.3 设置文档作者
        si.setAuthor(AUTHOR);
        // 4.4设置文档备注
        si.setComments("备注信息暂无");
    }

    private static String disposeXlsData(String filePath, String title) {
        Map<String, String> map = new HashMap<String, String>();
        // 创建对Excel工作簿文件的引用
        try {
            //filePath是文件地址。
            HSSFWorkbook wookbook = new HSSFWorkbook(new FileInputStream(filePath));
            // 在Excel文档中，第一张工作表的缺省索引是0
            HSSFSheet sheet = wookbook.getSheetAt(0);
            // 获取到Excel文件中的所有行数
            int rows = sheet.getPhysicalNumberOfRows();
            int max_cells = 0;
            // 获取最长的列，在实践中发现如果列中间有空值的话，那么读到空值的地方就停止了。所以我们需要取得最长的列。<br>　　　　　　　　　　　　　　//如果每个列正好都有一个空值得话，通过这种方式获得的列长会比真实的列要少一列。所以我自己会在将要倒入数据库中的EXCEL表加一个表头<br>　　　　　　　　　　　　　　//防止列少了，而插入数据库中报错。
            for (int i = 0; i < rows; i++) {
                HSSFRow row = sheet.getRow(i);
                if (row != null) {
                    int cells = row.getPhysicalNumberOfCells();
                    if (max_cells < cells) {
                        max_cells = cells;
                    }
                }
            }
            // 遍历行
            for (int i = 0; i < rows; i++) {
                // 读取左上端单元格
                HSSFRow row = sheet.getRow(i);
                // 行不为空
                if (row != null) {
                    String value = "";
                    // 遍历列
                    String b_id = null;
                    for (int j = 0; j < max_cells; j++) {
                        // 获取到列的值
                        HSSFCell cell = row.getCell(j);//把所有是空值的都换成NULL
                        if (cell == null) {
                            value += "NULL,";
                        } else {
                            switch (cell.getCellType()) {
                                case FORMULA:
                                    try {
                                        value += "'" + String.valueOf(cell.getNumericCellValue()).replaceAll("'", "") + "',";
                                    } catch (IllegalStateException e) {
                                        value += "'" + String.valueOf(cell.getRichStringCellValue()).replaceAll("'", "") + "',";
                                    }
                                    break;
                                case NUMERIC:
                                    // 如果有日期的话，那么就读出日期格式
                                    // 如果是数字的话，就写出数字格式
                                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                        SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                        Date date2 = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
                                        String date1 = dff.format(date2);
                                        value += "'" + date1.replaceAll("'", "") + "',";

                                    } else {
                                        value += "'" + (int) cell.getNumericCellValue() + "',";
                                    }
                                    break;
                                case STRING:
                                    String ss = cell.getStringCellValue().replaceAll("'", "");
                                    //如果文本有空值的话，就把它写成null
                                    if (ss == null || "".equals(ss)) {
                                        value += "NULL,";
                                    } else {
                                        value += "'" + cell.getStringCellValue().replaceAll("'", "") + "',";
                                    }

                                    break;
                                default:
                                    value += "NULL,";
                                    break;
                            }
                        }

                        if (j == 0) {

                            b_id = value.substring(1, value.length() - 2);
                        }

                    }
                    String valueresult = value.substring(0, value.length() - 1);
                    map.put(b_id, valueresult);

                }
            }

            wookbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        map.remove(title);
        return null;
    }
}
