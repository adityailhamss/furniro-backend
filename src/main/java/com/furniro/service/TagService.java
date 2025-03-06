package com.furniro.service;

import com.furniro.dto.CreateProductDTO;
import com.furniro.dto.CreateTagDTO;
import com.furniro.dto.ProductDTO;
import com.furniro.dto.TagDTO;
import com.furniro.entity.Product;
import com.furniro.entity.Tag;
import com.furniro.repository.TagRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService {
    private final TagRepository tagRepository;


    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<TagDTO> getAllTag(){
        return tagRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public TagDTO getTagById(Long id) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found"));
        return convertToDTO(tag);
    }

    public TagDTO createTag(CreateTagDTO createTagDTO) {
        if (createTagDTO.getName() == null || createTagDTO.getName().trim().isEmpty()) {
            throw new RuntimeException("Tag name cannot be null or empty");
        }

        Tag tag = new Tag();
        tag.setName(createTagDTO.getName());  // Explicitly set properties

        tag = tagRepository.save(tag); // Save to DB and get an ID

        return convertToDTO(tag);
    }


    public TagDTO updateTag(Long id, CreateTagDTO updateTagDTO) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found"));
        BeanUtils.copyProperties(updateTagDTO, tag);
        return convertToDTO(tagRepository.save(tag));
    }

    public void deleteTag(Long id) {
       tagRepository.deleteById(id);
    }

    private TagDTO convertToDTO(Tag tag) {
        if (tag == null) {
            throw new RuntimeException("Tag entity is null!");
        }

        TagDTO tagDTO = new TagDTO();
        tagDTO.setId(tag.getId());  // Explicitly copy ID
        tagDTO.setName(tag.getName()); // Explicitly copy Name

        System.out.println("Converted to DTO: id=" + tagDTO.getId() + ", name=" + tagDTO.getName()); // Debugging

        return tagDTO;
    }

}
