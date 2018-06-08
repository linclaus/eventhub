package com.microsoft.azure.eventhubs.samples.SimpleSend.core;

import com.microsoft.azure.eventhubs.samples.SimpleSend.bean.EventhubProperties;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("conf.xml");
        EventhubProperties eventhubProperties = (EventhubProperties) beanFactory.getBean("eventhubProperties");
        System.out.println(eventhubProperties.getConsumerGroupName());
    }
}
