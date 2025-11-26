package com.sqc.academy.kiemtra.service;

import com.sqc.academy.kiemtra.model.MatBang;
import java.time.LocalDate;
import java.util.List;

public interface IMatBangService {
    List<MatBang> getAll();
    MatBang getByCode(String code);
    MatBang create(MatBang mb);
    MatBang update(String code, MatBang mb);
    void delete(String code);
    List<MatBang> search(String code, String name, String address,
                         Double areaFrom, Double areaTo,
                         Integer typeId, String priceRange,
                         LocalDate startFrom, LocalDate startTo);
}
