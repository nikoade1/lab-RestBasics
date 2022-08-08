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
    public void createGiftCertificate(GiftCertificate giftCertificate) {
        this.giftCertificates.add(giftCertificate);
    }

    @Override
    public void update(GiftCertificate giftCertificate, int id) {
    }

    @Override
    public List<GiftCertificate> getAllGiftCertificates() {
        return this.giftCertificates;
    }
}
