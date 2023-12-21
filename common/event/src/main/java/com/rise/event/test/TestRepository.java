package com.rise.event.test;

import com.rise.event.inter.IEventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author 张牧之
 * @date 2023-04-07 01:41:29
 * @Email zhanglichang99@gmail.com
 */


@Slf4j
@Service
public class TestRepository implements IEventRepository {

    public void test (DomainTest domainTest) {
        log.info("repository test domainTest");
    }
}
