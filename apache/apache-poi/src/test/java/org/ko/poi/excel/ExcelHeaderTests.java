package org.ko.poi.excel;

import org.junit.jupiter.api.Test;
import org.ko.poi.excel.mock.City;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

public class ExcelHeaderTests {

    private static String FILE_NAME = "city.xlsx";

    @Test
    public void excelHeader () {
        List<String> header = ExcelHelper.excelHeader(City.class);
        header.forEach(System.out::println);
    }

    @Test
    public void exportExcel () throws IOException {
        List<City> rows = ExcelHelper.readExcel(FILE_NAME, City.class, (sheet -> {
            //添加校验
        }));
        long startTime = System.currentTimeMillis();
        ExcelHelper.export(rows, "city_column.xlsx", City.class);
        long endTime = System.currentTimeMillis();
        System.out.printf("Time: %ds", (endTime - startTime) / 1000);
    }

    @Test void readExcel () throws IOException {
        List<City> data = ExcelHelper.readExcel(FILE_NAME, City.class, (sheet -> {
            //添加校验
        }));
        //with header
        assert data.size() == 3306;
    }

    @Test
    public void field () throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        Field[] fields = City.class.getFields();
        for (Field field : fields) {
            assert field.getType().newInstance() instanceof Number;
        }
    }
}
