package com.task.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.task.model.Tag;
import com.task.repository.TagRepository;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public List<String> getAllTagsString() {
        return tagRepository.findAll().stream().map(tag -> tag.getValue()).collect(Collectors.toList());
    }

    public List<Tag> saveTags(Set<Tag> tags) {

        if (!tags.isEmpty()) {
            List<Tag> tagsFromDB = tagRepository.findAll();
            List<Tag> addTags = tags.stream().map(tag -> tag).collect(Collectors.toList());
            for (int i = 0; i < addTags.size(); i++) {
                for (int j = 0; j < tagsFromDB.size(); j++) {
                    if (addTags.get(i).getValue().equals(tagsFromDB.get(j).getValue())) {
                        addTags.set(i, tagsFromDB.get(j));
                        break;
                    }
                }
            }
            return addTags;
        }
        return null;
    }
}