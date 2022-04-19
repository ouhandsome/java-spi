package org.example;

/**
 * SPI HelloImpl2
 *
 * @author handsome
 * @version V1.0
 * @className HelloSpi
 * @date 2022/04/11 15:50
 **/
public class HelloImpl2 implements HelloSpi {
    @Override
    public void sayHello() {
        System.out.println("Hello Impl 2");
    }
}
