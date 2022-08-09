package com.epam.esm.dao.impl;

import com.epam.esm.dao.WrapperGiftTagsDao;
import com.epam.esm.model.GiftCertificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WrapperGiftTagsDaoJDBC implements WrapperGiftTagsDao {

    private final JdbcTemplate jdbcTemplate;

    private final String QueryCreateRecord = "INSERT INTO gifts_to_tags VALUES(?, ?)";
    private final String QuerySelectGiftCertificatesByTagName
            = "SELECT * FROM gift_certificates as gc JOIN gifts_to_tags as gtt " +
            "on gc.id = gtt.gift_certificate_id JOIN tags on gtt.tag_id = tags.id " +
            "WHERE tags.name=? ORDER BY create_date DESC, gc.name ASC";

    private final String QuerySelectGiftCertificatesByTagNameSubstring
            = "SELECT * FROM gift_certificates as gc JOIN gifts_to_tags as gtt " +
            "on gc.id = gtt.gift_certificate_id JOIN tags on gtt.tag_id = tags.id " +
            "WHERE tags.name like CONCAT('%', ?, '%') " +
            "ORDER BY create_date DESC, gc.name ASC";


    @Autowired
    public WrapperGiftTagsDaoJDBC(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createRecord(int giftCertificateId, int tagId) {
        this.jdbcTemplate.update(QueryCreateRecord, giftCertificateId, tagId);
    }

    @Override
    public List<GiftCertificate> getGiftCertificatesByTagName(String tagName) {
        return jdbcTemplate.query(QuerySelectGiftCertificatesByTagName, new GiftCertificateRowMapper(), tagName);
    }

    @Override
    public List<GiftCertificate> getGiftCertificatesByTagNameSubstring(String tagNameSubstring) {
        return jdbcTemplate.query(QuerySelectGiftCertificatesByTagNameSubstring, new GiftCertificateRowMapper(), tagNameSubstring);
    }
}
