package com.epam.esm.service;

import com.epam.esm.dao.TagDAO;
import com.epam.esm.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TagService {

    private final TagDAO tagDAO;

    @Autowired
    public TagService(TagDAO tagDao) {
        this.tagDAO = tagDao;
    }

    public List<Tag> getTags() {
        return tagDAO.getAllTags();
    }

    public Tag getTag(int id) {
        return tagDAO.getTagById(id);
    }

    public void create(Tag tag) {
        tagDAO.createTag(tag);
    }

    public Tag update(int id, Tag tag) {
        return tagDAO.update(id, tag);
    }

    public void delete(int id) {
        tagDAO.deleteTagById(id);
    }
}
