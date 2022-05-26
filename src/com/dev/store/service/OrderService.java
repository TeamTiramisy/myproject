package com.dev.store.service;

import com.dev.store.DAO.OrderDao;
import com.dev.store.dto.OrderCreateDto;
import com.dev.store.dto.OrderReadDto;
import com.dev.store.dto.TechnicReadDto;
import com.dev.store.entity.Order;
import com.dev.store.entity.Status;
import com.dev.store.mapper.OrderCreateMapper;
import com.dev.store.mapper.OrderReadMapper;
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

    public List<TechnicReadDto> findAllBasket(Long id) {
        return basketService.findAllBasket(id);
    }

    public List<OrderReadDto> findAllByUserId(Long userId) {
        return orderDao.findAllByUserId(userId).stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    public Long save(Long id, String[] amounts){
        List<TechnicReadDto> basket = findAllBasket(id);
        List<TechnicReadDto> orders = new ArrayList<>();

        for (int i = 0; i < basket.size(); i++) {
            TechnicReadDto order = copy(basket.get(i), amounts[i]);
            orders.add(order);
        }

        Integer total = orders.stream()
                .map(order -> (order.getPrice() * order.getAmount()))
                .reduce(Integer::sum).orElseThrow();

        List<String> products = orders.stream()
                .map(order -> (order.getName() + " - " + order.getAmount())).toList();

        String product = String.join(" , ", products);

        OrderCreateDto orderCreateDto = OrderCreateDto.builder()
                .product(product)
                .userId(id)
                .dateRegistration(LocalDate.now().toString())
                .dateClose("1900-01-01")
                .status("PROCESSING")
                .total(total)
                .build();

        Order map = orderCreateMapper.map(orderCreateDto);

        orderDao.save(map);

        if (map.getId() > 0){
            basketService.delete(id);
        }

        return map.getId();
    }

    public boolean delete(Long id){
        return orderDao.delete(id);
    }

    public static OrderService getInstance(){
        return INSTANCE;
    }

    private TechnicReadDto copy(TechnicReadDto technicReadDto, String amount){
        return TechnicReadDto.builder()
                .id(technicReadDto.getId())
                .name(technicReadDto.getName())
                .category(technicReadDto.getCategory())
                .description(technicReadDto.getDescription())
                .price(technicReadDto.getPrice())
                .amount(Integer.valueOf(amount))
                .image(technicReadDto.getImage())
                .build();
    }
}
