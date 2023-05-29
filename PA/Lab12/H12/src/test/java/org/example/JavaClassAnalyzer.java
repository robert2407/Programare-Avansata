package org.example;
import org.testng.annotations.Test;

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

public class JavaClassAnalyzer {
    private static int totalTests = 0;
    private static int successfulTests = 0;
    private static int failedTests = 0;

    public static void main(String[] args) {
        String filePath = "C:\\Users\\Robert\\Desktop\\Test2.jar";
        analyzeFiles(filePath);
        printTestStatistics();
    }
    private static void analyzeFiles(String filePath) {
        File file = new File(filePath);
        if (file.isDirectory()) {
            analyzeDirectory(file);
        } else if (file.isFile()) {
            if (file.getName().endsWith(".class")) {
                analyzeClassFile(file);
            } else if (file.getName().endsWith(".jar")) {
                analyzeJar(file);
            } else {
                System.out.println("Invalid: " + file.getName());
            }
        } else {
            System.out.println("Fisier negasit: " + filePath);
        }
    }

    private static void analyzeDirectory(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    analyzeDirectory(file);
                } else if (file.isFile() && file.getName().endsWith(".class")) {
                    analyzeClassFile(file);
                }
            }
        }
    }

    private static void analyzeJar(File jarFile) {
        try {
            URLClassLoader classLoader = new URLClassLoader(new URL[]{jarFile.toURI().toURL()});
            List<String> classFilePaths = new ArrayList<>();
            JarFile jar = new JarFile(jarFile);
            Enumeration<JarEntry> entries = jar.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
                    classFilePaths.add(entry.getName());
                }
            }
            jar.close();

            for (String classFilePath : classFilePaths) {
                String className = classFilePath.replace("/", ".").replace(".class", "");
                Class<?> clazz = classLoader.loadClass(className);
                analyzeClass(clazz);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void analyzeClassFile(File classFile) {
        try {
            URLClassLoader classLoader = new URLClassLoader(new URL[]{classFile.getParentFile().toURI().toURL()});
            String className = classFile.getName().replace(".class", "");
            Class<?> clazz = classLoader.loadClass(className);
            analyzeClass(clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void analyzeClass(Class<?> clazz) {
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

    private static boolean invokeTestMethod(Class<?> clazz, Method method) {
        try {
            if (!Modifier.isStatic(method.getModifiers())) {
                Object instance = clazz.getDeclaredConstructor().newInstance();
                method.invoke(instance);
            } else {
                List<Object> args = new ArrayList<>();
                for (Parameter parameter : method.getParameters()) {
                    if (parameter.getType() == int.class) {
                        args.add(0);
                    } else if (parameter.getType() == String.class) {
                        args.add("class");
                    } else {
                        args.add(null);
                    }
                }
                method.invoke(null, args.toArray());
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + method.getName());
            e.printStackTrace();
            return false;
        }
    }

    private static void printTestStatistics() {
        System.out.println("Test Statistics:");
        System.out.println("Total tests: " + totalTests);
        System.out.println("Successful tests: " + successfulTests);
        System.out.println("Failed tests: " + failedTests);
    }
}
