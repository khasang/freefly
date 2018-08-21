package io.khasang.freefly.dao.impl;

import io.khasang.freefly.dao.CatDao;
import io.khasang.freefly.entity.Cat;
import org.hibernate.Criteria;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CatDaoImpl extends BasicDaoImpl<Cat> implements CatDao{
    public CatDaoImpl(Class<Cat> entityClass) {
        super(entityClass);
    }

    @Override
    public List<Cat> getCatsByName(String name) {
        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Cat> criteriaQuery = builder.createQuery(Cat.class);
        Root<Cat> root = criteriaQuery.from(Cat.class);

        criteriaQuery.select(root);
        criteriaQuery.where(builder.equal(root.get("name"), name));

        TypedQuery<Cat> typedQuery = session.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }
}
