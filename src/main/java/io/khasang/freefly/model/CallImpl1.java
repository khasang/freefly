package io.khasang.freefly.model;

import org.springframework.stereotype.Component;

@Component
public class CallImpl1 implements Call {

    @Override
    public String doFoo() {
        return "Из интерфейса1";
    }
}
