package cn.coderblue.springcloud.controller;


import cn.coderblue.springcloud.entity.CommonResult;
import cn.coderblue.springcloud.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author coderblue
 * @create 2020-02-18 17:23
 */
@RestController
@Slf4j
public class OrderController {
    // public static final String PAYMENT_URL = "http://localhost:9001";

    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    // @Resource
    // private LoadBalancer loadBalancer;

    // @Resource
    // private DiscoveryClient discoveryClient;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }

    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id") Long id) {
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);

        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        } else {
            return new CommonResult<>(444, "操作失败");
        }
    }

    // @GetMapping(value = "/consumer/payment/lb")
    // public String getPaymentLB() {
    //     List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
    //
    //     if (instances == null || instances.size() <= 0) {
    //         return null;
    //     }
    //
    //     ServiceInstance serviceInstance = loadBalancer.instances(instances);
    //     URI uri = serviceInstance.getUri();
    //
    //     return restTemplate.getForObject(uri + "/payment/cn.coderblue.springcloud.lb", String.class);
    //
    // }
    //
    // // ====================> zipkin+sleuth
    // @GetMapping("/consumer/payment/zipkin")
    // public String paymentZipkin() {
    //     String result = restTemplate.getForObject("http://localhost:8001" + "/payment/zipkin/", String.class);
    //     return result;
    // }
}
