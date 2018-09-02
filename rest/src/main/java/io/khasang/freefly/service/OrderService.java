package io.khasang.freefly.service;

import io.khasang.freefly.dto.OrderDTO;

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
     * @return orderDTO for certain order
     */
    OrderDTO getOrderDTOById(long id);

    /**
     * method for update order
     *
     * @param orderDTO - orderDTO for updating order
     * @return orderDTO for updated order
     */
    OrderDTO updateOrder(OrderDTO orderDTO);

    /**
     * method for delete orders by specific id
     *
     * @param id order's for delete
     * @return deleted order's orderDTO
     */
    OrderDTO deleteOrderById(long id);

    /**
     * method for getting ordersDTO for all orders
     *
     * @return orderDTO list
     */
    List<OrderDTO> getAllOrdersDTO();

}
