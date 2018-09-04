package io.khasang.freefly.dao.impl;

import io.khasang.freefly.dao.UserDao;
import io.khasang.freefly.entity.User;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserDaoImpl extends BasicDaoImpl<User> implements UserDao {
    public UserDaoImpl(Class<User> entityClass) {
        super(entityClass);
    }

    @Override
    public List<User> getUsersByLogin(String login) {
        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);

        criteriaQuery.select(root);
        criteriaQuery.where(builder.equal(root.get("login"), login));

        TypedQuery<User> typedQuery = session.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    @Override
    public List<User> getUsersByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);

        criteriaQuery.select(root);
        criteriaQuery.where(builder.equal(root.get("email"), email));

        TypedQuery<User> typedQuery = session.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }
}