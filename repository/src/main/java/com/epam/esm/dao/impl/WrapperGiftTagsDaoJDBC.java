package com.epam.esm.dao.impl;

import com.epam.esm.dao.WrapperGiftTagsDao;
import com.epam.esm.model.GiftCertificate;
import com.epam.esm.model.Tag;
import com.epam.esm.model.WrapperGiftTags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WrapperGiftTagsDaoJDBC implements WrapperGiftTagsDao {

    private final JdbcTemplate jdbcTemplate;

    private final String QueryCreateRecord = "INSERT INTO gifts_to_tags VALUES(?, ?)";

    @Autowired
    public WrapperGiftTagsDaoJDBC(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createRecord(int giftCertificateId, int tagId) {
        this.jdbcTemplate.update(QueryCreateRecord, giftCertificateId, tagId);
    }
}
