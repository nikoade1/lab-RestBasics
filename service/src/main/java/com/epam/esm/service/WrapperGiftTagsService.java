package com.epam.esm.service;

import com.epam.esm.dao.WrapperGiftTagsDao;
import com.epam.esm.model.GiftCertificate;
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

    public List<GiftCertificate> getGiftCertificatesByTagName(String tagName) {
        return this.wrapperGiftTagsDao.getGiftCertificatesByTagName(tagName);
    }

    public List<GiftCertificate> getGiftCertificatesByTagNameSubstring(String tagNameSubstring) {
        return this.wrapperGiftTagsDao.getGiftCertificatesByTagNameSubstring(tagNameSubstring);
    }
}
