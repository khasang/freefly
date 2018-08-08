package io.khasang.freefly.service.impl;

import io.khasang.freefly.dao.CatDao;
import io.khasang.freefly.entity.Cat;
import io.khasang.freefly.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatServiceImpl implements CatService {

    @Autowired
    private CatDao catDao;

    @Override
    public List<Cat> getAllCats() {
        return catDao.getList();
    }

    @Override
    public Cat getCatById(long id) {
        return catDao.getById(id);
    }

    @Override
    public Cat addCat(Cat cat) {
        return catDao.create(cat);
    }

    @Override
    public Cat updateCat(Cat cat) {
        return catDao.update(cat);
    }

    @Override
    public void deleteCatById(long id) {
       catDao.deleteById(id);
    }
}
