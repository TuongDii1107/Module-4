package com.sqc.academy.kiemtra.repository;

import com.sqc.academy.kiemtra.BaseRepository;
import com.sqc.academy.kiemtra.model.LoaiMB;
import com.sqc.academy.kiemtra.model.MatBang;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MbRepositoryImpl extends BaseRepository implements IMbRepository {

    @Override
    public List<MatBang> findAll() {
        List<MatBang> list = new ArrayList<>();
        String sql = "SELECT mb.*, lm.name AS type_name FROM matbang mb LEFT JOIN loaimb lm ON mb.type_id = lm.id";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Optional<MatBang> findByCode(String code) {
        String sql = "SELECT mb.*, lm.name AS type_name FROM matbang mb LEFT JOIN loaimb lm ON mb.type_id = lm.id WHERE mb.code=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return Optional.of(mapRow(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void save(MatBang mb) {
        String sql = "INSERT INTO matbang(code,name,address,area,type_id,price,rent_date) VALUES(?,?,?,?,?,?,?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, mb.getCode());
            ps.setString(2, mb.getName());
            ps.setString(3, mb.getAddress());
            ps.setDouble(4, mb.getArea());
            ps.setInt(5, mb.getType().getId());
            ps.setDouble(6, mb.getPrice());
            ps.setDate(7, Date.valueOf(mb.getRentDate()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(String code, MatBang mb) {
        String sql = "UPDATE matbang SET name=?, address=?, area=?, type_id=?, price=?, rent_date=? WHERE code=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, mb.getName());
            ps.setString(2, mb.getAddress());
            ps.setDouble(3, mb.getArea());
            ps.setInt(4, mb.getType().getId());
            ps.setDouble(5, mb.getPrice());
            ps.setDate(6, Date.valueOf(mb.getRentDate()));
            ps.setString(7, code);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String code) {
        String sql = "DELETE FROM matbang WHERE code=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, code);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<MatBang> search(String code, String name, String address,
                                Double areaFrom, Double areaTo,
                                Integer loaiId, String rentRange,
                                LocalDate startFrom, LocalDate startTo) {
        List<MatBang> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
                "SELECT mb.*, lm.name AS type_name FROM matbang mb LEFT JOIN loaimb lm ON mb.type_id=lm.id WHERE 1=1 "
        );
        List<Object> params = new ArrayList<>();

        if (code != null && !code.isEmpty()) { sql.append("AND mb.code=? "); params.add(code); }
        if (name != null && !name.isEmpty()) { sql.append("AND mb.name LIKE ? "); params.add("%"+name+"%"); }
        if (address != null && !address.isEmpty()) { sql.append("AND mb.address LIKE ? "); params.add("%"+address+"%"); }
        if (areaFrom != null) { sql.append("AND mb.area>=? "); params.add(areaFrom); }
        if (areaTo != null) { sql.append("AND mb.area<=? "); params.add(areaTo); }
        if (loaiId != null) { sql.append("AND mb.type_id=? "); params.add(loaiId); }
        if (rentRange != null) {
            switch (rentRange) {
                case "under2": sql.append("AND mb.price<2000000 "); break;
                case "2-5": sql.append("AND mb.price BETWEEN 2000000 AND 4999999 "); break;
                case "5-10": sql.append("AND mb.price BETWEEN 5000000 AND 9999999 "); break;
                case "above10": sql.append("AND mb.price>=10000000 "); break;
            }
        }
        if (startFrom != null) { sql.append("AND mb.rent_date>=? "); params.add(Date.valueOf(startFrom)); }
        if (startTo != null) { sql.append("AND mb.rent_date<=? "); params.add(Date.valueOf(startTo)); }

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) ps.setObject(i + 1, params.get(i));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private MatBang mapRow(ResultSet rs) throws SQLException {
        LoaiMB type = new LoaiMB();
        type.setId(rs.getInt("type_id"));
        type.setName(rs.getString("type_name"));

        MatBang mb = new MatBang();
        mb.setCode(rs.getString("code"));
        mb.setName(rs.getString("name"));
        mb.setAddress(rs.getString("address"));
        mb.setArea(rs.getDouble("area"));
        mb.setType(type);
        mb.setPrice(rs.getDouble("price"));
        mb.setRentDate(rs.getDate("rent_date").toLocalDate());
        return mb;
    }
}
