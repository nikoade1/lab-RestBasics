package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificateDAO;
import com.epam.esm.model.GiftCertificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class GiftCertificateDaoJDBC implements GiftCertificateDAO {

    private final JdbcTemplate jdbcTemplate;

    private final String QuerySelectAllGiftCertificates = "SELECT * FROM gift_certificates";
    private final String QueryInsertIntoGiftCertificates
            = "INSERT INTO gift_certificates(name, description, price, duration, " +
            "create_date, last_update_date) VALUES(?, ?, ?, ?, ?, ?)";

    private final String QueryUpdateGiftCertificateById = "UPDATE gift_certificates SET " +
            "name=?, description=?, price=?, duration=?, last_update_date=? WHERE id=?";

    private final String QueryDeleteGiftCertificateById = "DELETE FROM gift_certificates WHERE id=?";
    private final String QueryGetGiftCertificateById = "SELECT * FROM gift_certificates WHERE id=?";

    @Autowired
    public GiftCertificateDaoJDBC(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public GiftCertificate getGiftCertificateById(int id) {
        return jdbcTemplate.query(QueryGetGiftCertificateById,
                new Object[]{id}, new GiftCertificateRowMapper()).stream().findAny().orElse(null);
    }

    @Override
    public void deleteGiftCertificateById(int id) {
        jdbcTemplate.update(QueryDeleteGiftCertificateById, id);
    }

    @Override
    public void createGiftCertificate(GiftCertificate giftCertificate) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement =
                    con.prepareStatement(QueryInsertIntoGiftCertificates, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, giftCertificate.getName());
            preparedStatement.setString(2, giftCertificate.getDescription());
            preparedStatement.setDouble(3, giftCertificate.getPrice());
            preparedStatement.setInt(4, giftCertificate.getDuration());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(giftCertificate.getCreate_date()));
            preparedStatement.setTimestamp(6, Timestamp.valueOf(giftCertificate.getLast_update_date()));
            return preparedStatement;
        }, keyHolder);
    }

    @Override
    public void update(GiftCertificate giftCertificate, int id) {

        GiftCertificate inDB = getGiftCertificateById(id);
        if (inDB == null) return;

        jdbcTemplate.update(QueryUpdateGiftCertificateById,
                giftCertificate.getName() == null ? inDB.getName() : giftCertificate.getName(),
                giftCertificate.getDescription() == null ? inDB.getDescription() : giftCertificate.getDescription(),
                giftCertificate.getPrice() == 0 ? inDB.getPrice() : giftCertificate.getPrice(),
                giftCertificate.getDuration() == 0 ? inDB.getDuration() : giftCertificate.getDuration(),
                Timestamp.valueOf(LocalDateTime.now()),
                id);
    }

    @Override
    public List<GiftCertificate> getAllGiftCertificates() {
        return jdbcTemplate.query(QuerySelectAllGiftCertificates, new GiftCertificateRowMapper());
    }
}
