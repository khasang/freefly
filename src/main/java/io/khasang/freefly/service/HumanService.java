package io.khasang.freefly.service;

import io.khasang.freefly.entity.Human;

public interface HumanService {

    Human addHuman(Human human);

    Human getById(long id);

    Human deleteById(long id);
}
