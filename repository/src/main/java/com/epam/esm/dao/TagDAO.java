package com.epam.esm.dao;

import com.epam.esm.model.Tag;

import java.util.List;

public interface TagDAO {

    Tag getTagById(int id);

    Tag getTagByName(String name);

    void deleteTagById(int id);

    void createTag(Tag tag);

    List<Tag> getAllTags();

    Tag update(int id, Tag tag);
}
