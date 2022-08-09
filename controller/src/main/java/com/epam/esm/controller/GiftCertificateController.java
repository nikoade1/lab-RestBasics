package com.epam.esm.controller;

import com.epam.esm.model.GiftCertificate;
import com.epam.esm.model.Tag;
import com.epam.esm.model.WrapperGiftTags;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.TagService;
import com.epam.esm.service.WrapperGiftTagsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("gifts")
public class GiftCertificateController {

    private final GiftCertificateService giftCertificateService;
    private final TagService tagService;
    private final WrapperGiftTagsService wrapperService;

    public GiftCertificateController(GiftCertificateService giftCertificateService,
                                     TagService tagService, WrapperGiftTagsService wrapperService) {
        this.giftCertificateService = giftCertificateService;
        this.tagService = tagService;
        this.wrapperService = wrapperService;
    }

    @GetMapping()
    public List<GiftCertificate> getAllGiftCertificates() {
        return this.giftCertificateService.getAllGiftCertificates();
    }

    @GetMapping("/{id}")
    public GiftCertificate getGiftCertificate(@PathVariable("id") int id) {
        return this.giftCertificateService.getGiftCertificateById(id);
    }

    @PostMapping("/create")
    public ModelAndView create(@Valid @RequestBody WrapperGiftTags requestWrapper, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            int giftCertificateId = this.giftCertificateService.create(requestWrapper.getGiftCertificate());
            List<Tag> tags = requestWrapper.getTags();
            if (tags != null) {
                List<Integer> tagIds = this.tagService.createTagsIfNotExists(tags);
                this.wrapperService.createRecord(giftCertificateId, tagIds);
            }
        }
        return new ModelAndView("redirect:/gifts");
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") int id) {
        this.giftCertificateService.delete(id);
    }

    @PatchMapping("/edit/{id}")
    public ModelAndView update(@Valid @RequestBody WrapperGiftTags requestWrapper,
                               BindingResult bindingResult, @PathVariable int id) {

        if (!bindingResult.hasErrors()) {
            this.tagService.createTagsIfNotExists(requestWrapper.getTags());
            this.giftCertificateService.update(requestWrapper.getGiftCertificate(), id);
        }
        return new ModelAndView("redirect:/gifts/{id}");
    }
}
