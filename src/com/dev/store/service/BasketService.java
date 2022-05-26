package com.dev.store.service;

import com.dev.store.DAO.BasketDao;
import com.dev.store.DAO.TechnicDao;
import com.dev.store.dto.BasketCreateDto;
import com.dev.store.dto.BasketReadDto;
import com.dev.store.dto.TechnicReadDto;
import com.dev.store.entity.Basket;
import com.dev.store.entity.Technic;
import com.dev.store.mapper.BasketCreateMapper;
import com.dev.store.mapper.BasketReadMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BasketService {

    private static final BasketService INSTANCE = new BasketService();

    private final BasketDao basketDao = BasketDao.getInstance();
    private final BasketCreateMapper mapper = BasketCreateMapper.getInstance();
    private final TechnicService technicService = TechnicService.getInstance();
    private final BasketReadMapper readMapper = BasketReadMapper.getInstance();


    public List<TechnicReadDto> findAllBasket(Long id) {
        List<Long> byUsersId = basketDao.findByUsersId(id);

        List<TechnicReadDto> allByBasket = technicService.findAllByBasket(byUsersId);

        return allByBasket;
    }

    public Optional<BasketReadDto> findByUsersIdAndTechnicId(Long userId, Long technicId) {
        return basketDao.findByUsersIdAndTechnicId(userId, technicId)
                .map(readMapper::map);
    }

    public boolean deleteProduct(String usersId, String technicId) {
        return basketDao.deleteProduct(Long.valueOf(usersId), Long.valueOf(technicId));
    }

    public boolean delete(Long usersId) {
        return basketDao.delete(usersId);
    }

    public Long create(BasketCreateDto basketCreateDto) {
        Basket basket = mapper.map(basketCreateDto);
        basketDao.save(basket);
        return basket.getId();
    }

    public static BasketService getInstance() {
        return INSTANCE;
    }
}
