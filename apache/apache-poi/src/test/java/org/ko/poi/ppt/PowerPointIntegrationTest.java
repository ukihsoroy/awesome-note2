package org.ko.poi.ppt;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PowerPointIntegrationTest {

    private PowerPointHelper pph;
    private String fileLocation;
    private static final String FILE_NAME = "presentation.pptx";

    @BeforeAll
    public void setUp() throws Exception {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        fileLocation = path.substring(0, path.length() - 1) + FILE_NAME;

        pph = new PowerPointHelper();
        pph.createPresentation(fileLocation);
    }

    @Test
    public void whenReadingAPresentation_thenOK() throws Exception {
        XMLSlideShow xmlSlideShow = pph.readingExistingSlideShow(fileLocation);

        assertNotNull(xmlSlideShow);
        assertEquals(4, xmlSlideShow.getSlides().size());
    }

    @Test
    public void whenRetrievingThePlaceholdersForEachSlide_thenOK() throws Exception {
        XMLSlideShow xmlSlideShow = pph.readingExistingSlideShow(fileLocation);

        List<XSLFShape> onlyTitleSlidePlaceholders = pph.retrieveTemplatePlaceholders(xmlSlideShow.getSlides().get(0));
        List<XSLFShape> titleAndBodySlidePlaceholders = pph.retrieveTemplatePlaceholders(xmlSlideShow.getSlides().get(1));
        List<XSLFShape> emptySlidePlaceholdes = pph.retrieveTemplatePlaceholders(xmlSlideShow.getSlides().get(3));

        assertEquals(1, onlyTitleSlidePlaceholders.size());
        assertEquals(2, titleAndBodySlidePlaceholders.size());
        assertEquals(0, emptySlidePlaceholdes.size());

    }

    @Test
    public void whenSortingSlides_thenOK() throws Exception {
        XMLSlideShow xmlSlideShow = pph.readingExistingSlideShow(fileLocation);
        XSLFSlide slide4 = xmlSlideShow.getSlides().get(3);
        pph.reorderSlide(xmlSlideShow, 3, 1);

        assertEquals(slide4, xmlSlideShow.getSlides().get(1));
    }

    @Test
    public void givenPresentation_whenDeletingASlide_thenOK() throws Exception {
        XMLSlideShow xmlSlideShow = pph.readingExistingSlideShow(fileLocation);
        pph.deleteSlide(xmlSlideShow, 3);

        assertEquals(3, xmlSlideShow.getSlides().size());
    }

    @AfterAll
    public void tearDown() throws Exception {
        File testFile = new File(fileLocation);
        if (testFile.exists()) {
            testFile.delete();
        }
        pph = null;
    }
}
