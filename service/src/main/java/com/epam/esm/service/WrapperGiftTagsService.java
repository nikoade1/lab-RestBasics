package com.epam.esm.service;

import com.epam.esm.dao.TagDAO;
import com.epam.esm.dao.WrapperGiftTagsDao;
import com.epam.esm.model.GiftCertificate;
import com.epam.esm.model.Tag;
import com.epam.esm.model.WrapperGiftTags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WrapperGiftTagsService {


    private final WrapperGiftTagsDao wrapperGiftTagsDao;

    @Autowired
    public WrapperGiftTagsService(WrapperGiftTagsDao wrapperGiftTagsDao) {
        this.wrapperGiftTagsDao = wrapperGiftTagsDao;
    }

    public void createRecord(int giftCertificateId, List<Integer> tagIds) {
        tagIds.forEach(tag -> wrapperGiftTagsDao.createRecord(giftCertificateId, tag));
    }

}
