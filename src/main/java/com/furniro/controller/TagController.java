package com.furniro.controller;

import com.furniro.dto.CreateTagDTO;
import com.furniro.dto.TagDTO;
import com.furniro.service.TagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tag")
public class TagController {
    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public ResponseEntity<List<TagDTO>> getAllTag() {
        return ResponseEntity.ok(tagService.getAllTag());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagDTO> getTag(@PathVariable Long id) {
        return ResponseEntity.ok(tagService.getTagById(id));
    }

    @PostMapping
    public ResponseEntity<TagDTO> createTag(@RequestBody CreateTagDTO createTagDTO) {
        return ResponseEntity.ok(tagService.createTag(createTagDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TagDTO> updateTag(@PathVariable Long id,
                                                    @RequestBody CreateTagDTO updateTagDTO) {
        return ResponseEntity.ok(tagService.updateTag(id, updateTagDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);
        return ResponseEntity.noContent().build();
    }
}
