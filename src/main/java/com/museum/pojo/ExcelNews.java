package com.museum.pojo;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 名称:ExcelNews
 * 描述:
 *
 * @version 1.0
 * @author:Nagisa
 * @datetime:2023-07-02 12:07
 */
public class ExcelNews {

    public List<News> parseExcelNews(InputStream file) {
        List<News> newsList = new ArrayList<>();

        try {
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                News news = new News();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    int columnIndex = cell.getColumnIndex();
                    switch (columnIndex) {
                        case 0:
                            news.setTitle(cell.getStringCellValue());
                            //System.out.println(news.getTitle());
                            break;
                        case 1:
                            news.setContent(cell.getStringCellValue());
                            //System.out.println(news.getContent());
                            break;
                        case 2:
                            news.setAddress(cell.getStringCellValue());
                            //System.out.println(news.getAddress());
                            break;
                        default:
                            break;
                    }
                }
               // System.out.println(news);
                newsList.add(news);
            }
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 返回文物对象列表
        return newsList;
    }
}

