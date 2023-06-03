package org.example;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.io.File;
import java.lang.reflect.*;

public class JavaClassAnalyzer {
    public static int totalTests = 0;
    public static int successfulTests = 0;
    public static int failedTests = 0;

    public static void main(String[] args) {
        String inputPath = "C:\\Users\\Robert\\IdeaProjects\\C12\\target\\classes\\org\\example";
        analyzeClasses(inputPath);
        printTestStatistics();
    }

    public static void analyzeClasses(String inputPath) {
        File file = new File(inputPath);
        if (file.isDirectory()) {
            analyzeClassesDirectoryOrJar(file);
        } else if (file.isFile()) {
            if (file.getName().endsWith(".class")) {
                analyzeClassFile(file);
            } else {
                System.out.println("Invalid: " + file.getName());
            }
        } else {
            System.out.println("Fisier negasit: ");
        }
    }

    public static void analyzeClassFile(File classFile) {
        try {
            URLClassLoader classLoader = new URLClassLoader(new URL[]{classFile.getParentFile().toURI().toURL()});
            String className = classFile.getName().replace(".class", "");
            Class<?> clazz = classLoader.loadClass(className);
            analyzeClass(clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void analyzeClass(Class<?> clazz) {
        if (clazz != null) {
            boolean isTestClass = clazz.isAnnotationPresent(Test.class);
            boolean isPublic = Modifier.isPublic(clazz.getModifiers());

            if (isPublic && isTestClass) {
                System.out.println("Test class: " + clazz.getName());
                System.out.println("");

                System.out.println("Running tests:");
                for (Method method : clazz.getDeclaredMethods()) {
                    if (method.isAnnotationPresent(Test.class)) {
                        totalTests++;
                        System.out.println("Running test: " + method.getName());
                        if (invokeTestMethod(clazz, method)) {
                            successfulTests++;
                        } else {
                            failedTests++;
                        }
                        System.out.println("");
                    }
                }
            }
        }
    }

    public static boolean invokeTestMethod(Class<?> clazz, Method method) {
        try {
            if (!Modifier.isStatic(method.getModifiers())) {
                Object instance = clazz.getDeclaredConstructor().newInstance();
                method.invoke(instance);
            } else {
                method.invoke(null);
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + method.getName());
            e.printStackTrace();
            return false;
        }
    }


    public static void analyzeClassesDirectoryOrJar(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    analyzeClassesDirectoryOrJar(file);
                } else if (file.isFile() && file.getName().endsWith(".class")) {
                    String className = getClassName(file.getAbsolutePath(), directory.getAbsolutePath());
                    try {
                        Class<?> clazz = Class.forName(className);
                        analyzeAndPrintMethods(clazz);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void analyzeAndPrintMethods(Class<?> clazz) {
        System.out.println("Class: " + clazz.getName());

        Method[] methods = clazz.getDeclaredMethods();
        if (methods.length > 0) {
            System.out.println("Methods:");
            for (Method method : methods) {
                String modifierString = Modifier.toString(method.getModifiers());
                System.out.println(modifierString + " " + method.getReturnType().getSimpleName() + " " + method.getName());
            }
        }
        System.out.println();
    }

    public static String getClassName(String filePath, String basePath) {
        String className = filePath.substring(basePath.length() + 1);
        className = className.replace(File.separator, ".");
        className = className.substring(0, className.length() - 6);
        className = "org.example." + className;
        return className;
    }

    public static void printTestStatistics() {
        System.out.println("Test Statistics:");
        System.out.println("Total tests: " + totalTests);
        System.out.println("Successful tests: " + successfulTests);
        System.out.println("Failed tests: " + failedTests );
    }
}