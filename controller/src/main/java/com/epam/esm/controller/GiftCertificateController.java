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
    public ModelAndView create(@RequestBody WrapperGiftTags requestWrapper, BindingResult bindingResult) {
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
    public ModelAndView update(@RequestBody WrapperGiftTags requestWrapper,
                               BindingResult bindingResult, @PathVariable int id) {

        if (!bindingResult.hasErrors()) {
            List<Tag> tags = requestWrapper.getTags();
            int giftCertificateId = this.giftCertificateService.update(requestWrapper.getGiftCertificate(), id);
            if (tags != null) {
                List<Integer> tagIds = this.tagService.createTagsIfNotExists(tags);
                this.wrapperService.createRecord(giftCertificateId, tagIds);
            }
        }
        return new ModelAndView("redirect:/gifts/{id}");
    }

    @GetMapping("/getByTagName/{tagName}")
    public List<GiftCertificate> getGiftCertificatesByTagName(@PathVariable("tagName") String tagName) {
        return this.wrapperService.getGiftCertificatesByTagName(tagName);
    }

    @GetMapping("/getByTagNameSubstring/{tagNameSubstring}")
    public List<GiftCertificate> getGiftCertificatesByTagNameSubstring(@PathVariable("tagNameSubstring") String tagNameSubstring) {
        return this.wrapperService.getGiftCertificatesByTagNameSubstring(tagNameSubstring);
    }
}
