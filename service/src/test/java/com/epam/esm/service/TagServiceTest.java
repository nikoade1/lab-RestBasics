package com.epam.esm.service;

import com.epam.esm.dao.TagDAO;
import com.epam.esm.dao.impl.TagDaoJDBC;
import com.epam.esm.model.Tag;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class TagServiceTest {

    private AutoCloseable autoCloseable;
    private TagService underTest;
    @Mock private TagDAO tagDao;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new TagService(tagDao);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllTags() {
        underTest.getAllTags();
        verify(tagDao).getAllTags();
    }

    @Test
    void canCreateTag() {
        Tag tom = new Tag("tom");
        int id = underTest.create(tom);
        verify(tagDao).createTag(tom);
    }

    @Test
    void canGetTag() {
        Tag bob = new Tag("bob");
        int id = underTest.create(bob);
        underTest.getTag(id);
    }

}
