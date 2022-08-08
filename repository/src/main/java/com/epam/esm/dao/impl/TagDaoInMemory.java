package com.epam.esm.dao.impl;

import com.epam.esm.dao.TagDAO;
import com.epam.esm.model.Tag;

import java.util.ArrayList;
import java.util.List;


public class TagDaoInMemory implements TagDAO {

    private static int TAG_COUNT;
    private final List<Tag> tags;

    {
        tags = new ArrayList<>();
        tags.add(new Tag(++TAG_COUNT, "birthday"));
        tags.add(new Tag(++TAG_COUNT, "christmas"));
        tags.add(new Tag(++TAG_COUNT, "welcome party"));
        tags.add(new Tag(++TAG_COUNT, "Kaladzis gacileba"));
    }

    @Override
    public Tag getTagById(int id) {
        return tags.stream().filter(tag -> tag.getId() == id).findAny().orElse(null);
    }

    @Override
    public Tag getTagByName(String name) {
        return tags.stream().filter(tag -> tag.getName().equals(name)).findAny().orElse(null);
    }

    @Override
    public void deleteTagById(int id) {
        tags.removeIf(tag -> tag.getId() == id);
    }

    @Override
    public void createTag(Tag tag) {
        tag.setId(++TAG_COUNT);
        tags.add(tag);
    }

    @Override
    public List<Tag> getAllTags() {
        return tags;
    }

}
