package org.ko.jexcel;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.ko.jexcel.helper.JExcelHelper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JExcelIntegrationTest {

    private static String FILE_NAME = "temp.xls";
    private String fileLocation;

    @BeforeAll
    public void generateExcelFile() throws IOException, WriteException {

        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        fileLocation = path.substring(0, path.length() - 1) + FILE_NAME;

        JExcelHelper.writeJExcel();

    }

    @Test
    public void whenParsingJExcelFile_thenCorrect() throws IOException, BiffException {
        Map<Integer, List<String>> data = JExcelHelper.readJExcel(fileLocation);

        assertEquals("Name", data.get(0)
            .get(0));
        assertEquals("Age", data.get(0)
            .get(1));

        assertEquals("John Smith", data.get(2)
            .get(0));
        assertEquals("20", data.get(2)
            .get(1));
    }

    @AfterAll
    public void cleanup(){
        File testFile = new File(fileLocation);
        if (testFile.exists()) {
           testFile.delete();     
        }
    }
}