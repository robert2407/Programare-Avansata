package org.example;
import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;

public class JavaClassAnalyzer {

    public static void main(String[] args) {
        String classFilePath = "org.example.TestClass";
        Class<?> mainClass = loadClass(classFilePath);
        analyzeClass(mainClass);
    }

    private static Class<?> loadClass(String classFilePath) {
        try {
            File file = new File(classFilePath);
            URL url = file.toURI().toURL();
            URLClassLoader classLoader = new URLClassLoader(new URL[]{url});
            String className = file.getName().replace(".class", "");
            return classLoader.loadClass(className);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void analyzeClass(Class<?> clazz) {
        if (clazz != null) {

            Package pack = clazz.getPackage();
            System.out.println("Package: " + pack.getName());
            System.out.println("");

            System.out.println("Class: " + clazz.getName());
            System.out.println("");

            System.out.println("Methods:");
            for (Method method : clazz.getDeclaredMethods()) {
                System.out.println(method.toString());
            }
            System.out.println("");

            System.out.println("Running tests:");
            for (Method method : clazz.getDeclaredMethods()) {
                if (Modifier.isStatic(method.getModifiers()) && method.getParameterCount() == 0) {  //doar cea statica sa fie afisata
                    try {
                        method.setAccessible(true);
                        method.invoke(null); // ca sa se afiseze metoada: "e un test cu test"
                        System.out.println("Method: " + method.toString());
                    } catch (Exception e) {
                        System.out.println("Method failed: " + method.toString());
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
