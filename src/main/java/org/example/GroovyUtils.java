package org.example;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyShell;

import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.File;
import java.util.Date;

/**
 * SPI GroovyUtils
 *
 * @author handsome
 * @version V1.0
 * @className HelloSpi
 * @date 2022/04/11 15:50
 **/
public class GroovyUtils {

    /**
     * ClassLoader：就是类的装载器，它使JVM可以动态的载入Java类，JVM并不需要知道从什么地方（本地文件、网络等）载入Java类，这些都由ClassLoader完成。
     * 获取当前类装载器
     */
    private final static ClassLoader classLoader = GroovyUtils.class.getClassLoader();

    /**
     * GroovyClassLoader：负责在运行时编译groovy源代码为Class的工作，从而使Groovy实现了将groovy源代码动态加载为Class的功能。
     */
    public final static GroovyClassLoader groovyClassLoader = new GroovyClassLoader(classLoader);


    /**
     * GroovyClassLoader
     * 执行groovy脚本
     *
     * @param
     */
    public static void runGroovyImpl() {
        try {
            String path = GroovyUtils.class.getResource("/HelloGroovyImpl.groovy").getFile();
            Class taskClz = groovyClassLoader.parseClass(new File(path));
            HelloSpi objClass = (HelloSpi) taskClz.newInstance();
            //调用脚本方法
            objClass.sayHello();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ScriptEngine 需要添加而外的依赖库 groovy-jsr223
     * <!-- https://mvnrepository.com/artifact/org.codehaus.groovy/groovy-jsr223 -->
     * <dependency>
     * <groupId>org.codehaus.groovy</groupId>
     * <artifactId>groovy-jsr223</artifactId>
     * <version>3.0.10</version>
     * </dependency>
     */

    public static void runScript() {
        try {
            ScriptEngineManager factory = new ScriptEngineManager();
            // 每次生成一个engine实例
            ScriptEngine engine = factory.getEngineByName("groovy");
            Bindings binding = engine.createBindings();
            // 入参
            binding.put("date", new Date());
            // 如果script文本来自文件,请首先获取文件内容
            engine.eval("def getTime(){return date.getTime();}", binding);
            engine.eval("def sayHello(name,age){return 'Hello,I am ' + name + ',age' + age;}");
            // 反射到方法
            Long time = (Long) ((Invocable) engine).invokeFunction("getTime", null);
            System.out.println(time);
            String message = (String) ((Invocable) engine).invokeFunction("sayHello", "handsome", 12);
            System.out.println(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * GroovyShell
     */
    public static void runShell() {
        try {
            GroovyShell groovyShell = new GroovyShell();
            groovyShell.evaluate("println 'GroovyShell Hello World -- handsome'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}