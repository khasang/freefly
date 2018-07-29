package io.khasang.freefly.model.impl;

import io.khasang.freefly.model.Call;
import org.springframework.stereotype.Component;

@Component
public class CallImpl implements Call {
    @Override
    public String getInfo() {
        return "hola";
    }
}
