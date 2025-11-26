package com.sqc.academy.kiemtra.service;

import com.sqc.academy.kiemtra.model.MatBang;
import com.sqc.academy.kiemtra.repository.IMbRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MatBangServiceImpl implements IMatBangService {

    private final IMbRepository repo;

    public MatBangServiceImpl(IMbRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<MatBang> getAll() {
        return repo.findAll();
    }

    @Override
    public MatBang getByCode(String code) {
        Optional<MatBang> mb = repo.findByCode(code);
        return mb.orElse(null);
    }

    @Override
    public MatBang create(MatBang mb) {
        repo.save(mb);
        return mb;
    }

    @Override
    public MatBang update(String code, MatBang mb) {
        repo.update(code, mb);
        return mb;
    }

    @Override
    public void delete(String code) {
        repo.delete(code);
    }

    @Override
    public List<MatBang> search(String code, String name, String address,
                                Double areaFrom, Double areaTo,
                                Integer typeId, String priceRange,
                                LocalDate startFrom, LocalDate startTo) {
        return repo.search(code,name,address,areaFrom,areaTo,typeId,priceRange,startFrom,startTo);
    }
}
