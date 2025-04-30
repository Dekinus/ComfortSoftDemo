package com.example.demo.service;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.DataNotFoundException;
import com.example.demo.exception.OpenCloseFileException;
import com.example.demo.model.dto.DemoRequest;
import com.example.demo.model.dto.DemoResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DemoService {

    public DemoResponse getNthSmallestElement(DemoRequest demoRequest) {

        int N = demoRequest.getNumber();
        int maxValueInSet = Integer.MAX_VALUE;
        Set<Integer> set = new HashSet<>(N);

        try (FileInputStream file = new FileInputStream(demoRequest.getFilePath())) {

            XSSFWorkbook workbook = new XSSFWorkbook(file);

            XSSFSheet sheet = workbook.getSheetAt(0);
            if (sheet.getPhysicalNumberOfRows() < N) throw new BadRequestException("N больше чем строк в файле");

            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    int value = (int) cell.getNumericCellValue();

                    if (set.size() < N) {
                        set.add(value);
                        maxValueInSet = Collections.max(set);
                    } else {
                        if (value < maxValueInSet && !set.contains(value)) {
                            set.remove(maxValueInSet);
                            set.add(value);
                            maxValueInSet = Collections.max(set);
                        } else break;
                    }
                }
            }

            if (set.size() < N) throw new BadRequestException("Недостаточно уникальных значений в файле для N = " + N);

        } catch (FileNotFoundException e) {
            throw new DataNotFoundException("Файл с таким именем не был найден");
        } catch (IOException e) {
            throw new OpenCloseFileException("Не удалось открыть/закрыть файл");
        }
        return new DemoResponse(N + "-е минимальное число в файле = " + maxValueInSet);
    }
}
