package com.epam.esm.dao.impl;

import com.epam.esm.dao.TagDAO;
import com.epam.esm.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TagDaoJDBC implements TagDAO {

    private final JdbcTemplate jdbcTemplate;
    private final String QuerySelectTagById = "SELECT * FROM tag WHERE id =?";
    private final String QueryDeleteTagById = "DELETE FROM tag WHERE id = ?";
    private final String QueryInsertIntoTag = "INSERT INTO tag VALUES(1, ?)";
    private final String QuerySelectAllTags = "SELECT * FROM tag";


    @Autowired
    public TagDaoJDBC(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Tag getTagById(int id) {
        return jdbcTemplate.query(QuerySelectTagById,
                new Object[]{id}, new BeanPropertyRowMapper<>(Tag.class)).stream().findAny().orElse(null);
    }

    @Override
    public Tag getTagByName(String name) {
        return null;
    }

    @Override
    public void deleteTagById(int id) {
        jdbcTemplate.update(QueryDeleteTagById, id);
    }

    @Override
    public void createTag(Tag tag) {
        jdbcTemplate.update(QueryInsertIntoTag, tag.getName());
    }

    @Override
    public List<Tag> getAllTags() {
        return jdbcTemplate.query(QuerySelectAllTags, new BeanPropertyRowMapper<>(Tag.class));
    }

    @Override
    public Tag update(int id, Tag tag) {
        return null;
    }
}
