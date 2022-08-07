package com.epam.esm.controller;

import com.epam.esm.model.Tag;
import com.epam.esm.service.TagService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    public List<Tag> readAllTeg() {
        return tagService.getTags();
    }

    @GetMapping("/{id}")
    public Tag show(@PathVariable("id") int id) {
        return tagService.getTag(id);
    }

    @PostMapping("/create")
    public Tag create(@Valid @RequestBody Tag tag, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) return null;
        tagService.create(tag);
        return tag;
    }

    @PatchMapping("/{id}")
    public Tag update(@RequestBody Tag tag, @PathVariable("id") int id) {
        return tagService.update(id, tag);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        tagService.delete(id);
    }

}