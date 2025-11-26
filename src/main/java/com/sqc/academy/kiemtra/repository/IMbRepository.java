package com.sqc.academy.kiemtra.repository;

import com.sqc.academy.kiemtra.model.MatBang;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IMbRepository {
    List<MatBang> findAll();
    Optional<MatBang> findByCode(String code);
    void save(MatBang mb);
    void update(String code, MatBang mb);
    void delete(String code);
    List<MatBang> search(String code, String name, String address,
                         Double areaFrom, Double areaTo,
                         Integer loaiId,
                         String rentRange,
                         LocalDate startFrom, LocalDate startTo);
}
