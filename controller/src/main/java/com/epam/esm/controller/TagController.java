package com.epam.esm.controller;

import com.epam.esm.model.Tag;
import com.epam.esm.service.TagService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("tags")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping()
    public List<Tag> getAllTags() {
        return this.tagService.getAllTags();
    }

    @GetMapping("/{id}")
    public Tag getTag(@PathVariable("id") int id) {
        return this.tagService.getTag(id);
    }

    @PostMapping("/create")
    public ModelAndView create(@Valid @RequestBody Tag tag, BindingResult bindingResult) {
        if(!bindingResult.hasErrors()) this.tagService.create(tag);
        return new ModelAndView("redirect:/tags");
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        this.tagService.delete(id);
    }

}