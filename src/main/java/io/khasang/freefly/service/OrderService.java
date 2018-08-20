package io.khasang.freefly.service;

import io.khasang.freefly.dto.OrderDTO;
import io.khasang.freefly.entity.Order;

import java.util.List;

public interface OrderService {
    /**
     * method for add order
     *
     * @param orderDTO -  orderDTO for creation new order
     * @return orderDTO for created order
     */
    OrderDTO addOrder(OrderDTO orderDTO);

    /**
     * method for getting orderDTO by specific order's id
     *
     * @param id - order's id
     * @return orderDTO for certain order. return null if not exists order with specific id
     */
    OrderDTO getOrderDTOById(long id);

    /**
     * method for update order
     *
     * @param orderDTO - orderDTO for updating order
     * @return orderDTO for updated order
     * <p>
     * Warning: if not exists order in repository, the method do nothing, but return specific orderDTO
     * For check exists specific order use @link io.khasang.freefly.service.OrderService#getOrderDTOById
     */
    OrderDTO updateOrder(OrderDTO orderDTO);

    /**
     * method for delete orders by specific id
     *
     * @param id order's for delete
     * @return deleted order's orderDTO. return null if not existed order with specific id
     */
    OrderDTO deleteOrderById(long id);

    /**
     * method for getting ordersDTO for all orders
     *
     * @return orderDTO list
     */
    List<OrderDTO> getAllOrdersDTO();

}
