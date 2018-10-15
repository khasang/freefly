package io.khasang.freefly.dao.impl;

import io.khasang.freefly.dao.RoleDao;
import io.khasang.freefly.entity.Role;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class RoleDaoImpl extends BasicDaoImpl<Role> implements RoleDao {
    public RoleDaoImpl(Class<Role> entityClass) {
        super(entityClass);
    }

    @Override
    public List<Role> getListByName(String name) {
        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Role> criteriaQuery = builder.createQuery(Role.class);
        Root<Role> root = criteriaQuery.from(Role.class);

        criteriaQuery.select(root);
        criteriaQuery.where(builder.equal(root.get("name"), name));

        TypedQuery<Role> typedQuery = session.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }
}
