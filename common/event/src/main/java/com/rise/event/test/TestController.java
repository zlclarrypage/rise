package com.rise.event.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 张牧之
 * @date 2023-04-07 01:51:40
 * @Email zhanglichang99@gmail.com
 */

@RestController
@RequestMapping("event")
public class TestController {
    @Autowired
    private TestRepository testRepository;


    @GetMapping("publisher")
    public void publisher () {
        DomainTest domainTest = new DomainTest();
        domainTest.whenTest();
        testRepository.test(domainTest);
    }
}
