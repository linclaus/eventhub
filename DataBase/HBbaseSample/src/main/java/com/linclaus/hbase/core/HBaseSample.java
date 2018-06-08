package com.linclaus.hbase.core;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Zhiqiang Lin
 * @Description
 * @create 2018/6/7.
 */
public class HBaseSample {
    public static void main(String[] args) throws Exception {
        Connection connection = null;
        Statement statement = null;
        String connectionStr;
        String url;
        if (args == null || args.length < 2) {
            args = new String[]{"192.168.114.129", "192.168.114.129:2181", "yes"};
        }
        url = args[0];
        connectionStr = args[1];
        System.out.println("begin: " + url + "," + connectionStr);

        if (args.length == 3) {
            HBaseDemo hBaseDemo = new HBaseDemo();
            hBaseDemo.setUp(url);
            hBaseDemo.queryTable();
        }

        try {
            Class.forName("org.apache.phoenix.jdbc.PhoenixDriver");
            connection = DriverManager.getConnection("jdbc:phoenix:" + connectionStr, "root", "hadoop");
            System.out.println("connect success");
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from person");
            while (resultSet.next()) {
            }
        } catch (Exception e) {
            System.out.println("connect failed");
            e.printStackTrace();
        }
    }
}
