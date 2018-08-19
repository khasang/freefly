package io.khasang.freefly.service.impl;

import io.khasang.freefly.dao.HumanDao;
import io.khasang.freefly.entity.Human;
import io.khasang.freefly.service.HumanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HumanServiceImpl implements HumanService {

    @Autowired
    private HumanDao humanDao;

    @Override
    public Human addHuman(Human human) {
        return humanDao.create(human);
    }

    @Override
    public Human getById(long id) {
        return humanDao.getById(id);
    }

    @Override
    public Human deleteById(long id) {
        return humanDao.delById(id);
    }
}
