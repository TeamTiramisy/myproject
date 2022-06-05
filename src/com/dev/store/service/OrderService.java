package com.dev.store.service;

import com.dev.store.DAO.OrderDao;
import com.dev.store.dto.OrderCreateDto;
import com.dev.store.dto.OrderReadDto;
import com.dev.store.dto.TechnicReadDto;
import com.dev.store.entity.Order;
import com.dev.store.entity.Status;
import com.dev.store.mapper.OrderCreateMapper;
import com.dev.store.mapper.OrderReadMapper;
import com.dev.store.mapper.TechnicReadMapper;
import com.dev.store.util.LocalDateFormatter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderService {

    private static final OrderService INSTANCE = new OrderService();

    private final BasketService basketService = BasketService.getInstance();
    private final OrderDao orderDao = OrderDao.getInstance();
    private final OrderCreateMapper orderCreateMapper = OrderCreateMapper.getInstance();
    private final OrderReadMapper mapper = OrderReadMapper.getInstance();
    private final TechnicReadMapper technicMapper = TechnicReadMapper.getInstance();

    public List<OrderReadDto> findAll(){
        return orderDao.findAll().stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    public List<OrderReadDto> findAllByProduct(String name){
        return orderDao.findAllByProduct(name).stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    public List<OrderReadDto> findAllByStatus(String status){
        return orderDao.findAllByStatus(status).stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    public List<OrderReadDto> findAllByStatusByUserId(String status, Long userId){
        return orderDao.findAllByStatusByUserId(status, userId).stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    public List<TechnicReadDto> findAllBasket(Long id) {
        return basketService.findAllBasket(id);
    }

    public List<OrderReadDto> findAllByUserId(Long userId) {
        return orderDao.findAllByUserId(userId).stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    public void update(Long id, OrderCreateDto orderCreateDto){
        Order order = orderDao.findById(id).orElseThrow();

        order.setStatus(Status.valueOf(orderCreateDto.getStatus()));

        if (orderCreateDto.getDateClose() != null){
            order.setDateClose((LocalDateFormatter.format(orderCreateDto.getDateClose())));
        }

        orderDao.update(order);
    }

    public Long save(Long id, String[] amounts){
        List<TechnicReadDto> orders = getOrder(id, amounts);
        Integer total = getTotalOrder(orders);
        String product = getProductOrder(orders);

        OrderCreateDto orderCreateDto = OrderCreateDto.builder()
                .product(product)
                .userId(id)
                .dateRegistration(LocalDate.now().toString())
                .dateClose("1900-01-01")
                .status("PROCESSING")
                .total(total)
                .build();

        Order order = orderCreateMapper.map(orderCreateDto);

        orderDao.save(order);

        if (order.getId() > 0){
            basketService.delete(id);
        }

        return order.getId();
    }

    public boolean delete(Long id){
        return orderDao.delete(id);
    }

    public boolean deleteDateClose(LocalDate date) {
       return orderDao.deleteDateClose(date);
    }

    public static OrderService getInstance(){
        return INSTANCE;
    }

    private List<TechnicReadDto> getOrder(Long id, String[] amounts){
        List<TechnicReadDto> baskets = findAllBasket(id);
        List<TechnicReadDto> orders = new ArrayList<>();

        for (int i = 0; i < baskets.size(); i++) {
            TechnicReadDto order = technicMapper.copy(baskets.get(i), amounts[i]);
            orders.add(order);
        }
        return orders;
    }

    private Integer getTotalOrder(List<TechnicReadDto> orders){
        return orders.stream()
                .map(order -> (order.getPrice() * order.getAmount()))
                .reduce(Integer::sum).orElseThrow();
    }

    private String getProductOrder(List<TechnicReadDto> orders){
        List<String> products = orders.stream()
                .map(order -> (order.getName() + " - " + order.getAmount())).toList();

        String product = String.join(" , ", products);

        return product;
    }
}
