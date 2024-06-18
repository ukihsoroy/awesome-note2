package org.ko.poi.excel;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.ko.poi.excel.mock.City;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ExcelIntegrationTests {

    private static String FILE_NAME = "city.xlsx";

    @Test
    public void exportExcel() throws IOException {

        List<String> header = Arrays.asList(
                "序号",
                "主键",
                "城市路径",
                "城市名称",
                "简写",
                "创建时间"
        );

        //mock data
        List<City> cities = readJson();
        List<List<String>> rows = new ArrayList<>();
        for (int i = 0; i < cities.size(); i++) {
            City city = cities.get(i);
            List<String> row = new ArrayList<>();
            row.add(String.valueOf(i + 1));
            row.add(String.valueOf(city.getId()));
            row.add(city.getCityPath());
            row.add(city.getCity());
            row.add(city.getShorten());
            row.add(ExcelHelper.formatDateTime(city.getCreateDate()));
            rows.add(row);
        }
        long startTime = System.currentTimeMillis();
        ExcelHelper.export(header, rows, FILE_NAME);
        long endTime = System.currentTimeMillis();

        System.out.printf("Time: %ds", (endTime - startTime) / 1000);

    }

    @Test
    public void readExcel() throws IOException {
        Map<Integer, List<String>> data = ExcelHelper.readExcel(FILE_NAME);
        //with header
        assert data.size() == 3307;
    }

    private List<City> readJson () {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File("city.json"), mapper.getTypeFactory().constructParametricType(List.class, City.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}