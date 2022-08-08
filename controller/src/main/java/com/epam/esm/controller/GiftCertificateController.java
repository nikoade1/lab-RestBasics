package com.epam.esm.controller;

import com.epam.esm.model.GiftCertificate;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("gifts")
public class GiftCertificateController {

    private final GiftCertificateService giftCertificateService;

    public GiftCertificateController(GiftCertificateService giftCertificateService) {
        this.giftCertificateService = giftCertificateService;
    }

    @GetMapping()
    public List<GiftCertificate> getAllGiftCertificates() {
        System.out.println("get all");
        return this.giftCertificateService.getAllGiftCertificates();
    }

    @GetMapping("/{id}")
    public GiftCertificate getGiftCertificate(@PathVariable("id") int id){
        System.out.println("get by id " + id);
        return this.giftCertificateService.getGiftCertificateById(id);
    }

    @PostMapping("/create")
    public ModelAndView create(@Valid @RequestBody GiftCertificate giftCertificate, BindingResult bindingResult) {
        System.out.println("create gift");
        if(!bindingResult.hasErrors())
            this.giftCertificateService.create(giftCertificate);
        return new ModelAndView("redirect:/gifts");
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") int id) {
        System.out.println("delete id " + id);
        this.giftCertificateService.delete(id);
    }

    @PatchMapping("/edit/{id}")
    public ModelAndView update(@Valid @RequestBody GiftCertificate giftCertificate,
                                  BindingResult bindingResult, @PathVariable int id) {

        System.out.println("update gift");
        if(!bindingResult.hasErrors()){
            this.giftCertificateService.update(giftCertificate, id);
        }
        return new ModelAndView("redirect:/gifts/{id}");
    }
}
