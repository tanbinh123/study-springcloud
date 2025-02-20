package cn.coderblue.springcloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Blue
 * @create 2020-02-19 14:15
 */
@SpringBootApplication
@EnableDiscoveryClient //该注解用于向使用consul或者zookeeper作为注册中心时注册服务
public class PaymentZkApplication8004
{
    public static void main(String[] args) {
            SpringApplication.run(PaymentZkApplication8004.class, args);
    }
}
