package com.epam.esm.service;

import com.epam.esm.dao.GiftCertificateDAO;
import com.epam.esm.model.GiftCertificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiftCertificateService {
    private GiftCertificateDAO giftCertificateDAO;

    @Autowired
    public void GiftCertificateDAO(GiftCertificateDAO giftCertificateDAO) {
        this.giftCertificateDAO = giftCertificateDAO;
    }

    public List<GiftCertificate> getAllGiftCertificates() {
        return this.giftCertificateDAO.getAllGiftCertificates();
    }

    public GiftCertificate getGiftCertificateById(int id) {
        return this.giftCertificateDAO.getGiftCertificateById(id);
    }

    public int create(GiftCertificate giftCertificate) {
        return this.giftCertificateDAO.createGiftCertificate(giftCertificate);
    }

    public void delete(int id) {
        this.giftCertificateDAO.deleteGiftCertificateById(id);
    }

    public int update(GiftCertificate giftCertificate, int id) {
        return this.giftCertificateDAO.update(giftCertificate, id);
    }
}
