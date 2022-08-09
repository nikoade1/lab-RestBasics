package com.epam.esm.service;

import com.epam.esm.dao.TagDAO;
import com.epam.esm.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService {

    private final TagDAO tagDAO;

    @Autowired
    public TagService(TagDAO tagDao) {
        this.tagDAO = tagDao;
    }

    public List<Tag> getAllTags() {
        return tagDAO.getAllTags();
    }

    public Tag getTag(int id) {
        return tagDAO.getTagById(id);
    }

    public int create(Tag tag) {
        return tagDAO.createTag(tag);
    }

    public void delete(int id) {
        tagDAO.deleteTagById(id);
    }

    public List<Integer> createTagsIfNotExists(List<Tag> tags) {
        return tags.stream().map(tag -> tagDAO.getTagByName(tag.getName())
                        == null ? tagDAO.createTag(tag) : tagDAO.getTagIdByTagName(tag.getName()))
                .collect(Collectors.toList());
    }
}
