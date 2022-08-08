package com.epam.esm.dao.impl;

import com.epam.esm.model.Tag;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TagMapper implements RowMapper<Tag> {
    @Override
    public Tag mapRow(ResultSet rs, int rowNum) throws SQLException {
        Tag string = new Tag();
        string.setId(rs.getInt("id"));
        string.setName(rs.getString("name"));

        return string;
    }
}
