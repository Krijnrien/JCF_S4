package woordenapplicatie;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

public class utilTest {

    String ten_thousand;
    String million;

    @Before
    public void setUp() {
        ten_thousand = util.usingBufferedReader("res/10 000w.txt");
        million = util.usingBufferedReader("res/millionw.txt");
    }

    @Test
    public void mediumWordCountPerformanceTest() {
        long startTime = System.currentTimeMillis();

        ArrayList<String> list = util.stringToWordList(ten_thousand);
        util.removeDuplicates(list).size();

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;

        System.out.println("10.000 counting; elapsed time: " + elapsedTime + ", max time: 100");
        assertTrue(elapsedTime < 100);
    }

    @Test
    public void largeWordCountPerformanceTest() {
        long startTime = System.currentTimeMillis();

        ArrayList<String> list = util.stringToWordList(million);
        util.removeDuplicates(list).size();

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;

        System.out.println("Million frequency; elapsed time: " + elapsedTime + ", max time: 1500");
        assertTrue(elapsedTime < 1500);
    }

    @Test
    public void mediumWordFrequencyPerformanceTest() {
        long startTime = System.currentTimeMillis();

        util.getWordFrequency(ten_thousand);

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;

        System.out.println("10.000 frequency; elapsed time: " + elapsedTime + ", max time: 200");
        assertTrue(elapsedTime < 200);
    }

    @Test
    public void largeWordFrequencyPerformanceTest() {
        long startTime = System.currentTimeMillis();

        util.getWordFrequency(million);

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;

        System.out.println("Million frequency; elapsed time: " + elapsedTime + ", max time: 600");
        assertTrue(elapsedTime < 600);
    }

    @Test
    public void mediumWordSortingPerformanceTest() {
        long startTime = System.currentTimeMillis();

        ArrayList<String> words = util.stringToWordList(ten_thousand);
        words.sort(Collections.reverseOrder());

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;

        System.out.println("10.000 sorting; elapsed time: " + elapsedTime + ", max time: 25");
        assertTrue(elapsedTime < 25);
    }

    @Test
    public void largeWordSortingPerformanceTest() {
        long startTime = System.currentTimeMillis();

        ArrayList<String> words = util.stringToWordList(million);
        words.sort(Collections.reverseOrder());

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;

        System.out.println("Million sorting; elapsed time: " + elapsedTime + ", max time: 2000");
        assertTrue(elapsedTime < 2000);
    }

}