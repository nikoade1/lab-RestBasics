package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificateDAO;
import com.epam.esm.model.GiftCertificate;

import java.util.ArrayList;
import java.util.List;

public class GiftCertificateDaoInMemory implements GiftCertificateDAO {

    private final List<GiftCertificate> giftCertificates;

    {
        this.giftCertificates = new ArrayList<>();
        this.giftCertificates.add(new GiftCertificate());
        this.giftCertificates.add(new GiftCertificate());
        this.giftCertificates.add(new GiftCertificate());
        this.giftCertificates.add(new GiftCertificate());
    }

    @Override
    public GiftCertificate getGiftCertificateById(int id) {
        return null;
    }

    @Override
    public void deleteGiftCertificateById(int id) {
    }

    @Override
    public int createGiftCertificate(GiftCertificate giftCertificate) {
        this.giftCertificates.add(giftCertificate);
        return giftCertificate.getId();
    }

    @Override
    public int update(GiftCertificate giftCertificate, int id) {
        return 0;
    }

    @Override
    public List<GiftCertificate> getAllGiftCertificates() {
        return this.giftCertificates;
    }
}
