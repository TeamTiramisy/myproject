package com.dev.store.DAO;

import com.dev.store.entity.*;
import com.dev.store.util.ConnectionManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDao implements Dao<Long, User> {

    private static final UserDao INSTANCE = new UserDao();

    private static final String FIND_ALL = """
            SELECT * FROM users
            """;

    private static final String SAVE_USER = """
            INSERT INTO users (firstname, lastname, email, password, tel, address, role, gender, blacklist) 
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) 
            """;

    private static final String FIND_BY_EMAIL_AND_PASSWORD = """
            SELECT * FROM users WHERE email = ? AND password = ?
            """;

    private static final String FIND_BY_ID = """
            SELECT * FROM users WHERE id = ?
            """;

    private static final String UPDATE = """
            UPDATE users 
            SET firstname = ?,
            lastname = ?,
            email = ?,
            password = ?,
            tel = ?,
            address = ?,
            role = ?,
            gender = ?,
            blacklist = ?
            WHERE id = ?
            """;

    @SneakyThrows
    @Override
    public List<User> findAll() {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> technics = new ArrayList<>();

            while (resultSet.next()) {
                technics.add(buildUser(resultSet));
            }

            return technics;
        }
    }

    @SneakyThrows
    @Override
    public Optional<User> findById(Long id) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setObject(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            User user = null;

            if (resultSet.next()) {
                user = buildUser(resultSet);
            }
            return Optional.ofNullable(user);
        }
    }

    @SneakyThrows
    public Optional<User> findByEmailAndPassword(String email, String password) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_EMAIL_AND_PASSWORD)) {

            preparedStatement.setObject(1, email);
            preparedStatement.setObject(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            User user = null;

            if (resultSet.next()) {
                user = buildUser(resultSet);
            }
            return Optional.ofNullable(user);
        }
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @SneakyThrows
    @Override
    public void update(User entity) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setObject(1, entity.getFirstname());
            preparedStatement.setObject(2, entity.getLastname());
            preparedStatement.setObject(3, entity.getEmail());
            preparedStatement.setObject(4, entity.getPassword());
            preparedStatement.setObject(5, entity.getTel());
            preparedStatement.setObject(6, entity.getAddress());
            preparedStatement.setObject(7, entity.getRole().name());
            preparedStatement.setObject(8, entity.getGender().name());
            preparedStatement.setObject(9, entity.getBlackList().name());
            preparedStatement.setObject(10, entity.getId());

            preparedStatement.executeUpdate();
        }
    }

    @SneakyThrows
    @Override
    public User save(User entity) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_USER, RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, entity.getFirstname());
            preparedStatement.setObject(2, entity.getLastname());
            preparedStatement.setObject(3, entity.getEmail());
            preparedStatement.setObject(4, entity.getPassword());
            preparedStatement.setObject(5, entity.getTel());
            preparedStatement.setObject(6, entity.getAddress());
            preparedStatement.setObject(7, entity.getRole().name());
            preparedStatement.setObject(8, entity.getGender().name());
            preparedStatement.setObject(9, entity.getBlackList().name());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            entity.setId(generatedKeys.getObject("id", Long.class));
            return entity;
        }
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }

    @SneakyThrows
    private User buildUser(ResultSet resultSet) {
        return new User(
                resultSet.getObject("id", Long.class),
                resultSet.getObject("firstname", String.class),
                resultSet.getObject("lastname", String.class),
                resultSet.getObject("email", String.class),
                resultSet.getObject("password", String.class),
                resultSet.getObject("tel", String.class),
                resultSet.getObject("address", String.class),
                Role.valueOf(resultSet.getObject("role", String.class)),
                Gender.valueOf(resultSet.getObject("gender", String.class)),
                BlackList.valueOf(resultSet.getObject("blacklist", String.class))
        );
    }
}
