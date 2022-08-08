package com.epam.esm.dao.impl;

import com.epam.esm.dao.TagDAO;
import com.epam.esm.model.Tag;

import java.util.ArrayList;
import java.util.List;


public class TagDaoInMemory implements TagDAO {

    private static int TAG_COUNT;
    private final List<Tag> strings;

    {
        strings = new ArrayList<>();
        strings.add(new Tag(++TAG_COUNT, "birthday"));
        strings.add(new Tag(++TAG_COUNT, "christmas"));
        strings.add(new Tag(++TAG_COUNT, "welcome party"));
        strings.add(new Tag(++TAG_COUNT, "Kaladzis gacileba"));
    }

    @Override
    public Tag getTagById(int id) {
        return strings.stream().filter(tag -> tag.getId() == id).findAny().orElse(null);
    }

    @Override
    public Tag getTagByName(String name) {
        return strings.stream().filter(tag -> tag.getName().equals(name)).findAny().orElse(null);
    }

    @Override
    public void deleteTagById(int id) {
        strings.removeIf(tag -> tag.getId() == id);
    }

    @Override
    public void createTag(Tag string) {
        string.setId(++TAG_COUNT);
        strings.add(string);
    }

    @Override
    public List<Tag> getAllTags() {
        return strings;
    }

}
