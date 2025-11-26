package com.sqc.academy.kiemtra.controller;

import com.sqc.academy.kiemtra.model.MatBang;
import com.sqc.academy.kiemtra.service.IMatBangService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/matbang")
public class MatBangController {

    private final IMatBangService service;

    public MatBangController(IMatBangService service) {
        this.service = service;
    }

    @GetMapping
    public List<MatBang> getAll() {
        return service.getAll();
    }

    @GetMapping("/{code}")
    public MatBang getByCode(@PathVariable String code) {
        MatBang mb = service.getByCode(code);
        if (mb == null) throw new RuntimeException("Mặt bằng không tồn tại");
        return mb;
    }

    @PostMapping
    public MatBang create(@RequestBody MatBang mb) {
        return service.create(mb);
    }

    @PutMapping("/{code}")
    public MatBang update(@PathVariable String code, @RequestBody MatBang mb) {
        return service.update(code, mb);
    }

    @DeleteMapping("/{code}")
    public void delete(@PathVariable String code) {
        service.delete(code);
    }

    @GetMapping("/search")
    public List<MatBang> search(
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) Double areaFrom,
            @RequestParam(required = false) Double areaTo,
            @RequestParam(required = false) Integer typeId,
            @RequestParam(required = false) String priceRange,
            @RequestParam(required = false) String rentDateFrom,
            @RequestParam(required = false) String rentDateTo
    ) {
        LocalDate startFrom = rentDateFrom != null ? LocalDate.parse(rentDateFrom) : null;
        LocalDate startTo = rentDateTo != null ? LocalDate.parse(rentDateTo) : null;

        return service.search(code, name, address, areaFrom, areaTo, typeId, priceRange, startFrom, startTo);
    }
}
