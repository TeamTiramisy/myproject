package com.dev.store.DAO;

import com.dev.store.entity.Basket;
import com.dev.store.entity.User;
import com.dev.store.util.ConnectionManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BasketDao implements Dao<Long, Basket> {

    private static final BasketDao INSTANCE = new BasketDao();

    private static final String SAVE = """
            INSERT INTO basket (users_id, technic_id) 
            VALUES (?, ?) 
            """;

    private static final String FIND_BY_USERS_ID = """
            SELECT technic_id FROM basket WHERE users_id = ?
            """;

    private static final String FIND_BY_USERS_ID_AND_TECHNIC_ID = """
            SELECT * FROM basket WHERE users_id = ? AND technic_id = ?
            """;

    private static final String DELETE_PRODUCT = """
            DELETE FROM basket WHERE users_id = ? AND technic_id = ?
            """;

    @SneakyThrows
    public Optional<Basket> findByUsersIdAndTechnicId(Long userId, Long technicId){
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_USERS_ID_AND_TECHNIC_ID)) {
            preparedStatement.setObject(1, userId);
            preparedStatement.setObject(2, technicId);
            ResultSet resultSet = preparedStatement.executeQuery();

            Basket basket = null;

            if (resultSet.next()) {
                basket = Basket.builder()
                        .id(resultSet.getObject("id", Long.class))
                        .users_id(resultSet.getObject("id", Long.class))
                        .technic_id(resultSet.getObject("id", Long.class))
                        .build();
            }

            return Optional.ofNullable(basket);
        }
    }

    @SneakyThrows
    public List<Long> findByUsersId(Long id){
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_USERS_ID)) {
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Long> list = new ArrayList<>();

            while (resultSet.next()) {
                list.add(resultSet.getObject("technic_id", Long.class));
            }

            return list;
        }
    }

    @Override
    public List<Basket> findAll() {
        return null;
    }

    @Override
    public Optional<Basket> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @SneakyThrows
    public boolean deleteProduct(Long usersId, Long technicId) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT)) {
            preparedStatement.setObject(1, usersId);
            preparedStatement.setObject(2, technicId);

            return preparedStatement.executeUpdate() > 0;
        }
    }

    @Override
    public void update(Basket entity) {

    }

    @SneakyThrows
    @Override
    public Basket save(Basket entity) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, entity.getUsers_id());
            preparedStatement.setObject(2, entity.getTechnic_id());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            entity.setId(generatedKeys.getObject("id", Long.class));
            return entity;
        }
    }

    public static BasketDao getInstance(){
        return INSTANCE;
    }
}
