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
    private final String QuerySelectStringById = "SELECT * FROM tags WHERE id =?";
    private final String QueryDeleteStringById = "DELETE FROM tags WHERE id = ?";
    private final String QueryInsertIntoString = "INSERT INTO tags(name) VALUES(?)";
    private final String QuerySelectAllTags = "SELECT * FROM tags";
    private final String QuerySelectStringByName = "SELECT * FROM tags WHERE name =?";


    @Autowired
    public TagDaoJDBC(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Tag getTagById(int id) {
        return jdbcTemplate.query(QuerySelectStringById,
                new Object[]{id}, new BeanPropertyRowMapper<>(Tag.class)).stream().findAny().orElse(null);
    }

    @Override
    public Tag getTagByName(String name) {
        return jdbcTemplate.query(QuerySelectStringByName,
                new Object[]{name}, new BeanPropertyRowMapper<>(Tag.class)).stream().findAny().orElse(null);
    }

    @Override
    public void deleteTagById(int id) {
        jdbcTemplate.update(QueryDeleteStringById, id);
    }

    @Override
    public void createTag(Tag string) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement =
                    con.prepareStatement(QueryInsertIntoString, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, string.getName());
            return preparedStatement;
        }, keyHolder);
    }

    @Override
    public List<Tag> getAllTags() {
        return jdbcTemplate.query(QuerySelectAllTags, new BeanPropertyRowMapper<>(Tag.class));
    }

}
