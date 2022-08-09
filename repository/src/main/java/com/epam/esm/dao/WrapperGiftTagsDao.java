package com.epam.esm.dao;

import com.epam.esm.model.GiftCertificate;

import java.util.List;

public interface WrapperGiftTagsDao {
    void createRecord(int giftCertificateId, int tagIds);

    List<GiftCertificate> getGiftCertificatesByTagName(String tagName);

    List<GiftCertificate> getGiftCertificatesByTagNameSubstring(String tagNameSubstring);
}
