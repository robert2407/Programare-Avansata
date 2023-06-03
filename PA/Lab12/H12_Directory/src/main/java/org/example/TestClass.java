package org.example;


import org.junit.jupiter.api.Test;

import static org.example.JavaClassAnalyzer.analyzeClasses;
import static org.example.JavaClassAnalyzer.printTestStatistics;

public class TestClass {
    @Test
    public void test(){
        System.out.println("e un test fara test");
    }
    @Test
    public void testAnalyze() {
        String inputPath = "C:\\Users\\Robert\\IdeaProjects\\C12\\target\\classes\\org\\example";
        analyzeClasses(inputPath);
        printTestStatistics();
    }
    @Test
    public static void testAdevarat(){
        System.out.println("e un test cu test");
    }
}
