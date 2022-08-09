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
import java.util.Objects;

@Component
public class TagDaoJDBC implements TagDAO {

    private final JdbcTemplate jdbcTemplate;
    private final String QuerySelectTagById = "SELECT * FROM tags WHERE id =?";
    private final String QueryDeleteTagById = "DELETE FROM tags WHERE id = ?";
    private final String QueryInsertIntoTag = "INSERT INTO tags(name) VALUES(?)";
    private final String QuerySelectAllTags = "SELECT * FROM tags";
    private final String QuerySelectTagByName = "SELECT * FROM tags WHERE name =?";
    private final String QueryDeleteTagRecordById = "DELETE FROM gifts_to_tags WHERE tag_id=?";

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
    public int getTagIdByTagName(String name) {
        String QuerySelectIdByName = "SELECT id FROM tags WHERE name=?";
        return jdbcTemplate.queryForObject(QuerySelectIdByName, new Object[]{name}, Integer.class);
    }

    @Override
    public Tag getTagByName(String name) {
        return jdbcTemplate.query(QuerySelectTagByName,
                new Object[]{name}, new BeanPropertyRowMapper<>(Tag.class)).stream().findAny().orElse(null);
    }

    @Override
    public void deleteTagById(int id) {
        jdbcTemplate.update(QueryDeleteTagRecordById, id);
        jdbcTemplate.update(QueryDeleteTagById, id);
    }

    @Override
    public int createTag(Tag tag) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement =
                    con.prepareStatement(QueryInsertIntoTag, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, tag.getName());
            return preparedStatement;
        }, keyHolder);

        return (int) Objects.requireNonNull(keyHolder.getKeys()).get("id");
    }

    @Override
    public List<Tag> getAllTags() {
        return jdbcTemplate.query(QuerySelectAllTags, new BeanPropertyRowMapper<>(Tag.class));
    }

}
