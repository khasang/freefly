package io.khasang.freefly.dao;

import io.khasang.freefly.entity.Order;

public interface OrderDao extends BasicDao<Order> {
    /**
     * method for updating Order
     *
     * @param entity order for updating
     * @return updated order.
     *
     * Warning: if not exists entity in repository, the method will do nothing, but will return specific entity
     * For check exists specific entity use @link io.khasang.freefly.dao.BasicDao#getById
     */
    Order update (Order entity);

    /**
     * method for delete order
     *
     * @param id order's id, which need delete
     * @return deleted order. return null if not existed entity with specific id
     */
    Order deleteById(long id);
}
