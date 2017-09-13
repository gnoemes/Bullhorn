package com.gnoemes.bullhorn;

import com.gnoemes.bullhorn.Utils.TextUtilites;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void parse_NameToId() {
        List<String> testName = new ArrayList<>();
        testName.add("BBC News");
        testName.add("ABC News (AU)");
        testName.add("Reddit/r/all");
        testName.add("Wired.de");
        testName.add("T3n");
        testName.add("The New York Times");


        List<String> testId = new ArrayList<>();
        testId.add("bbc-news");
        testId.add("abc-news-au");
        testId.add("reddit-r-all");
        testId.add("wired-de");
        testId.add("t3n");
        testId.add("the-new-york-times");

        for (int i = 0; i < testId.size(); i++) {
             assertEquals(testId.get(i), TextUtilites.parseNameToId(testName.get(i)));
        }
    }
}