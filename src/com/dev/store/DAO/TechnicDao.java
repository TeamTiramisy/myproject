package com.dev.store.DAO;

import com.dev.store.entity.Category;
import com.dev.store.entity.Technic;
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
public class TechnicDao implements Dao<Long, Technic>{

    private static final TechnicDao INSTANCE = new TechnicDao();

    public static final String FIND_ALL = """
            SELECT * FROM technic
            """;

    public static final String FIND_ALL_BY_CATEGORY = """
            SELECT * FROM technic WHERE category = ?
            """;

    public static final String FIND_BY_ID = """
            SELECT * FROM technic WHERE id = ?
            """;

    public static final String FIND_ALL_BY_NAME = """
            SELECT * FROM technic WHERE lower(name) like lower('%' || ? || '%')
            """;

    public static final String ADD_PRODUCT = """
            INSERT INTO technic (name, category, description, price, amount, image) VALUES 
            (?, ?, ?, ?, ?, ?)
            """;

    private static final String UPDATE = """
            UPDATE technic 
            SET name = ?,
            category = ?,
            description = ?,
            price = ?,
            amount = ?,
            image = ?
            WHERE id = ?
            """;

    private static String DELETE = """
            DELETE FROM technic WHERE id = ?
            """;

    @SneakyThrows
    @Override
    public List<Technic> findAll() {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Technic> technics = new ArrayList<>();

            while (resultSet.next()){
                technics.add(buildTechnic(resultSet));
            }

            return technics;
        }
    }

    @SneakyThrows
    public List<Technic> findAllByCategory(String category) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BY_CATEGORY)) {
            preparedStatement.setObject(1, category);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Technic> technics = new ArrayList<>();

            while (resultSet.next()){
                technics.add(buildTechnic(resultSet));
            }

            return technics;
        }
    }

    @SneakyThrows
    public List<Technic> findAllByName(String name) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BY_NAME)) {
            preparedStatement.setObject(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Technic> technics = new ArrayList<>();

            while (resultSet.next()){
                technics.add(buildTechnic(resultSet));
            }

            return technics;
        }
    }

    @SneakyThrows
    @Override
    public Optional<Technic> findById(Long id) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setObject(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            Technic technic = null;

            if (resultSet.next()){
                technic = buildTechnic(resultSet);
            }

            return Optional.ofNullable(technic);
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
    @Override
    public void update(Technic entity) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setObject(1, entity.getName());
            preparedStatement.setObject(2, entity.getCategory().name());
            preparedStatement.setObject(3, entity.getDescription());
            preparedStatement.setObject(4, entity.getPrice());
            preparedStatement.setObject(5, entity.getAmount());
            preparedStatement.setObject(6, entity.getImage());
            preparedStatement.setObject(7, entity.getId());

            preparedStatement.executeUpdate();
        }
    }

    @SneakyThrows
    @Override
    public Technic save(Technic entity) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_PRODUCT, RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, entity.getName());
            preparedStatement.setObject(2, entity.getCategory().name());
            preparedStatement.setObject(3, entity.getDescription());
            preparedStatement.setObject(4, entity.getPrice());
            preparedStatement.setObject(5, entity.getAmount());
            preparedStatement.setObject(6, entity.getImage());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            entity.setId(generatedKeys.getObject("id", Long.class));
            return entity;
        }
    }

    public static TechnicDao getInstance(){
        return INSTANCE;
    }

    @SneakyThrows
    private Technic buildTechnic(ResultSet resultSet) {
        return new Technic(
                resultSet.getObject("id", Long.class),
                resultSet.getObject("name", String.class),
                Category.valueOf(resultSet.getObject("category", String.class)),
                resultSet.getObject("description", String.class),
                resultSet.getObject("price", Integer.class),
                resultSet.getObject("amount", Integer.class),
                resultSet.getObject("image", String.class)
        );
    }
}
