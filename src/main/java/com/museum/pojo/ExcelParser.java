package com.museum.pojo;

/**
 * 名称ExcelParser
 * 描述Excel文件处理
 *
 * @version 1.0
 * @author:DuanMM
 * @datatime:2023-06-30 17:29
 */
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelParser {

    public List<Artifact> parseExcel(InputStream file) {
        List<Artifact> artifacts = new ArrayList<>();

        try {
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Artifact artifact = new Artifact();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    int columnIndex = cell.getColumnIndex();
                    switch (columnIndex) {
                        case 0:
                            artifact.setArtname(cell.getStringCellValue());
                            break;
                        case 1:
                            artifact.setDynasty(cell.getStringCellValue());
                            break;
                        case 2:
                            artifact.setType(cell.getStringCellValue());
                            break;
                        case 3:
                            artifact.setPlace(cell.getStringCellValue());
                            break;
                        case 4:
                            artifact.setTime(cell.getStringCellValue());
                            break;
                        case 5:
                            artifact.setCollected(cell.getStringCellValue());
                            break;
                        case 6:
                            artifact.setImage(cell.getStringCellValue());
                            break;
                        case 7:
                            artifact.setIntroduction(cell.getStringCellValue());
                            break;
                        default:
                            //
                            break;
                    }
                }
                artifacts.add(artifact);
            }
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 返回文物对象列表
        return artifacts;
    }
}


