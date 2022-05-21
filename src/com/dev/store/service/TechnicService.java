package com.dev.store.service;

import com.dev.store.DAO.TechnicDao;
import com.dev.store.dto.TechnicCreateDto;
import com.dev.store.dto.TechnicReadDto;
import com.dev.store.entity.Technic;
import com.dev.store.exception.ValidationException;
import com.dev.store.mapper.TechnicCreateMapper;
import com.dev.store.mapper.TechnicReadMapper;
import com.dev.store.util.ConnectionManager;
import com.dev.store.validator.AddProductValidator;
import com.dev.store.validator.UpdateProductValidator;
import com.dev.store.validator.ValidationResult;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TechnicService {

    private static final TechnicService INSTANCE = new TechnicService();


    private final TechnicDao technicDao = TechnicDao.getInstance();

    private final TechnicReadMapper mapper = TechnicReadMapper.getInstance();
    private final TechnicCreateMapper mapperCreate = TechnicCreateMapper.getInstance();
    private final AddProductValidator validator = AddProductValidator.getInstance();
    private final UpdateProductValidator updateProductValidator = UpdateProductValidator.getInstance();
    private final ImageService imageService = ImageService.getInstance();


    public List<TechnicReadDto> findAll() {
        return technicDao.findAll().stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    public List<TechnicReadDto> findAllByCategory(String category) {
        return technicDao.findAllByCategory(category).stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    public List<TechnicReadDto> findAllByName(String name) {
        return technicDao.findAllByName(name).stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    public Set<String> findAllCategory() {
        return technicDao.findAll().stream()
                .map(Technic::getCategory)
                .map(Enum::name)
                .collect(Collectors.toSet());
    }

    public Optional<TechnicReadDto> findById(Long id) {
        return technicDao.findById(id)
                .map(mapper::map);
    }

    public void update(Long id, TechnicCreateDto technicCreateDto){
        ValidationResult valid = updateProductValidator.isValid(technicCreateDto);
        if (!valid.isValid()) {
            throw new ValidationException(valid.getErrors());
        }

        Technic technic = technicDao.findById(id).orElseThrow();
        technic.setName(technicCreateDto.getName());
        technic.setDescription(technicCreateDto.getDescription());
        technic.setPrice(Integer.valueOf(technicCreateDto.getPrice()));
        technic.setAmount(Integer.valueOf(technicCreateDto.getAmount()));
        technicDao.update(technic);
    }

    public static TechnicService getInstance() {
        return INSTANCE;
    }

    @SneakyThrows
    public Long create(TechnicCreateDto technicCreateDto) {
        ValidationResult valid = updateProductValidator.isValid(technicCreateDto);
        if (!valid.isValid()) {
            throw new ValidationException(valid.getErrors());
        }

        Technic technic = mapperCreate.map(technicCreateDto);
        imageService.upload(technic.getImage(), technicCreateDto.getImage().getInputStream());
        technicDao.save(technic);
        return technic.getId();
    }
}
