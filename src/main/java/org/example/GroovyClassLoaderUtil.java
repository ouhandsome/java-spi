package org.example;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.control.CompilerConfiguration;

import java.io.File;
import java.util.List;

public class GroovyClassLoaderUtil {
    private static GroovyClassLoader groovyClassLoader = null;

    public static void initGroovyClassLoader() {
        CompilerConfiguration config = new CompilerConfiguration();
        config.setSourceEncoding("UTF-8");
        // 设置该GroovyClassLoader的父ClassLoader为当前线程的加载器(默认)
        groovyClassLoader = new GroovyClassLoader(Thread.currentThread().getContextClassLoader(), config);
    }


    public static void main(String[] args) {
        loadClass();
        System.out.println("======================");
        loadFile();
    }

    /**
     * 通过类加载groovy
     */
    private static void loadClass() {
        GroovyObject groovyObject = null;
        try {
            groovyObject = (GroovyObject) GroovyClassLoaderUtil.class.getClassLoader().loadClass("com.chy.TestGroovy").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 执行无参函数
        groovyObject.invokeMethod("print", null);

        System.out.println("============================");

        // 执行有参函数
        Object[] objects = new Object[]{"abc", "def", "ghi"};
        List<String> ls = (List<String>) groovyObject.invokeMethod("printArgs", objects);
        ls.stream().forEach(System.out::println);
    }

    /**
     * 通过文件路径加载groovy
     *
     * @return
     */
    public static boolean loadFile() {
//        File groovyFile = new File("src/main/java/com/chy/TestGroovy.groovy");
        File groovyFile = new File("src/main/resources/HelloGroovyImpl.groovy");

        if (!groovyFile.exists()) {
            System.out.println("文件不存在");
            return false;
        }

        initGroovyClassLoader();

        try {
            List<String> result;
//            String groovyFile = GroovyClassLoaderUtil.class.getResource("/HelloGroovyImpl.groovy").getFile();
            // 获得TestGroovy加载后的class
            Class<?> groovyClass = groovyClassLoader.parseClass(groovyFile);
            // 获得TestGroovy的实例
            GroovyObject groovyObject = (GroovyObject) groovyClass.newInstance();
            // 反射调用printArgs方法得到返回值
            Object methodResult = groovyObject.invokeMethod("sayHello", new Object[]{});
            if (methodResult != null) {
                result = (List<String>) methodResult;
                result.stream().forEach(System.out::println);
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

}
