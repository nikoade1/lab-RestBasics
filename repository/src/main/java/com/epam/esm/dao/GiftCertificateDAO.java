package com.epam.esm.dao;

import com.epam.esm.model.GiftCertificate;

import java.util.List;

public interface GiftCertificateDAO {

    GiftCertificate getGiftCertificateById(int id);

    void deleteGiftCertificateById(int id);

    int createGiftCertificate(GiftCertificate giftCertificate);

    int update(GiftCertificate giftCertificate, int id);

    List<GiftCertificate> getAllGiftCertificates();

}
