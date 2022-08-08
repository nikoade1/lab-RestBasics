package com.epam.esm.dao;

import com.epam.esm.model.GiftCertificate;

import java.util.List;

public interface GiftCertificateDAO {

    GiftCertificate getGiftCertificateById(int id);

    void deleteGiftCertificateById(int id);

    void createGiftCertificate(GiftCertificate giftCertificate);

    void update(GiftCertificate giftCertificate, int id);

    List<GiftCertificate> getAllGiftCertificates();

}
