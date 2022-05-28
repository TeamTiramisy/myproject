package com.dev.store.DAO;

import com.dev.store.entity.Order;
import com.dev.store.entity.Status;
import com.dev.store.entity.Technic;
import com.dev.store.util.ConnectionManager;
import com.oracle.wls.shaded.org.apache.xpath.operations.Or;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderDao implements Dao<Long, Order> {

    private static final OrderDao INSTANCE = new OrderDao();

    private static final String FIND_ALL = """
            SELECT * FROM orders
            """;

    private static final String SAVE = """
            INSERT INTO orders (product, user_id, date_registration, date_close, status, total)
             VALUES (?, ?, ?, ?, ?, ?)
            """;

    private static final String FIND_ALL_BY_USER_ID = """
            SELECT * FROM orders WHERE user_id = ?
            """;

    private static final String DELETE = """
            DELETE FROM orders WHERE id = ?
            """;

    public static final String FIND_ALL_BY_PRODUCT = """
            SELECT * FROM orders WHERE product like ('%' || ? || '%')
            """;

    public static final String FIND_ALL_BY_STATUS = """
            SELECT * FROM orders WHERE status = ?
            """;

    private static final String UPDATE = """
            UPDATE orders
            SET product = ?,
            user_id = ?,
            date_registration = ?,
            date_close = ?,
            status = ?,
            total = ?
            WHERE id = ?
            """;

    private static final String FIND_BY_ID = """
            SELECT * FROM orders WHERE id = ?
            """;

    private static final String DELETE_DATE_CLOSE = """
            DELETE FROM orders WHERE (status = 'COMPLETED' OR status = 'REJECTED') AND date_close < ?
            """;

    @SneakyThrows
    @Override
    public List<Order> findAll() {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Order> orders = new ArrayList<>();

            while (resultSet.next()) {
                orders.add(buildOrder(resultSet));
            }
            return orders;
        }
    }

    @SneakyThrows
    public List<Order> findAllByProduct(String name) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BY_PRODUCT)) {
            preparedStatement.setObject(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Order> orders = new ArrayList<>();

            while (resultSet.next()) {
                orders.add(buildOrder(resultSet));
            }
            return orders;
        }
    }

    @SneakyThrows
    public List<Order> findAllByStatus(String status) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BY_STATUS)) {
            preparedStatement.setObject(1, status);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Order> orders = new ArrayList<>();

            while (resultSet.next()) {
                orders.add(buildOrder(resultSet));
            }
            return orders;
        }
    }

    @SneakyThrows
    public List<Order> findAllByUserId(Long userId) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BY_USER_ID)) {
            preparedStatement.setObject(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Order> orders = new ArrayList<>();

            while (resultSet.next()) {
                orders.add(buildOrder(resultSet));
            }
            return orders;
        }
    }

    @SneakyThrows
    @Override
    public Optional<Order> findById(Long id) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setObject(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            Order order = null;

            if (resultSet.next()){
                order = buildOrder(resultSet);
            }
            return Optional.ofNullable(order);
        }
    }

    @Override
    @SneakyThrows
    public boolean delete(Long id) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setObject(1, id);

            return preparedStatement.executeUpdate() > 0;
        }
    }

    @SneakyThrows
    public boolean deleteDateClose(LocalDate date) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DATE_CLOSE)) {
            preparedStatement.setObject(1, date);

            return preparedStatement.executeUpdate() > 0;
        }
    }

    @SneakyThrows
    @Override
    public void update(Order entity) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setObject(1, entity.getProduct());
            preparedStatement.setObject(2, entity.getUserId());
            preparedStatement.setObject(3, entity.getDateRegistration());
            preparedStatement.setObject(4, entity.getDateClose());
            preparedStatement.setObject(5, entity.getStatus().name());
            preparedStatement.setObject(6, entity.getTotal());
            preparedStatement.setObject(7, entity.getId());

            preparedStatement.executeUpdate();
        }
    }

    @SneakyThrows
    @Override
    public Order save(Order entity) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, entity.getProduct());
            preparedStatement.setObject(2, entity.getUserId());
            preparedStatement.setObject(3, entity.getDateRegistration());
            preparedStatement.setObject(4, entity.getDateClose());
            preparedStatement.setObject(5, entity.getStatus().name());
            preparedStatement.setObject(6, entity.getTotal());

            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            entity.setId(generatedKeys.getObject("id", Long.class));

            return entity;
        }
    }

    public static OrderDao getInstance() {
        return INSTANCE;
    }

    private Order buildOrder(ResultSet resultSet) throws java.sql.SQLException {
        return Order.builder()
                .id(resultSet.getObject("id", Long.class))
                .product(resultSet.getObject("product", String.class))
                .userId(resultSet.getObject("user_id", Long.class))
                .dateRegistration(resultSet.getObject("date_registration", Date.class).toLocalDate())
                .dateClose(resultSet.getObject("date_close", Date.class).toLocalDate())
                .status(Status.valueOf(resultSet.getObject("status", String.class)))
                .total(resultSet.getObject("total", Integer.class))
                .build();
    }
}
