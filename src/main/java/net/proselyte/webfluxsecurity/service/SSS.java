package net.proselyte.webfluxsecurity.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class SSS {

    @PostConstruct
    void init() {
        System.out.printf("sss created");
    }

}
