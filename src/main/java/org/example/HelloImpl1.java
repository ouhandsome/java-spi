package org.example;

/**
 * SPI HelloImpl1
 *
 * @author handsome
 * @version V1.0
 * @className HelloSpi
 * @date 2022/04/11 15:50
 **/
public class HelloImpl1 implements HelloSpi {
    @Override
    public void sayHello() {
        System.out.println("Hello Impl 1");
    }
}
