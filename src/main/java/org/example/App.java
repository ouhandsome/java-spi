package org.example;

/**
 * SPI Demo App
 *
 * @author handsome
 * @version V1.0
 * @className HelloSpi
 * @date 2022/04/11 15:50
 **/
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        // 第一种 JAVA SPI
//        ServiceLoader<HelloSpi> serviceLoader = ServiceLoader.load(HelloSpi.class);
//        for (HelloSpi helloSPI : serviceLoader) {
//            helloSPI.sayHello();
//        }

        // 第二种 Groovy
        GroovyUtils.runGroovyImpl();
        //按照JSR223，使用标准接口ScriptEngineManager调用。
//        GroovyUtils.runScript();
//        GroovyUtils.runShell();

        //
//        GroovyClassLoaderUtil.loadFile();
    }
}
