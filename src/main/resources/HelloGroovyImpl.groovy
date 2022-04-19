package org.example;

/**
 * SPI HelloGroovyImpl1
 *
 * @author handsome
 * @version V1.0
 * @className HelloSpi
 * @date 2022/04/11 15:50
 **/
class HelloGroovyImpl implements HelloSpi {
    @Override
    public void sayHello() {
        System.out.println("Hello Groovy Impl 1");
    }
}
