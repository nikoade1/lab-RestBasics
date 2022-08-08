package com.epam.esm.dao.impl;

import com.epam.esm.dao.TagDAO;
import com.epam.esm.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Component
public class TagDaoJDBC implements TagDAO {

    private final JdbcTemplate jdbcTemplate;
    private final String querySelectStringById = "SELECT * FROM tags WHERE id =?";
    private final String queryDeleteStringById = "DELETE FROM tags WHERE id = ?";
    private final String queryInsertIntoString = "INSERT INTO tags(name) VALUES(?)";
    private final String querySelectAllTags = "SELECT * FROM tags";


    @Autowired
    public TagDaoJDBC(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Tag getTagById(int id) {
        return jdbcTemplate.query(querySelectStringById,
                new Object[]{id}, new BeanPropertyRowMapper<>(Tag.class)).stream().findAny().orElse(null);
    }

    @Override
    public Tag getTagByName(String name) {
        return null;
    }

    @Override
    public void deleteTagById(int id) {
        jdbcTemplate.update(queryDeleteStringById, id);
    }

    @Override
    public void createTag(Tag string) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement =
                    con.prepareStatement(queryInsertIntoString, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, string.getName());
            return preparedStatement;
        }, keyHolder);
    }

    @Override
    public List<Tag> getAllTags() {
        return jdbcTemplate.query(querySelectAllTags, new BeanPropertyRowMapper<>(Tag.class));
    }

}
