package com.example.ProjectCC.repository;

import com.example.ProjectCC.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Integer> {

    List<Tag> findByUserId(String id);
    List<Tag> findByTagContaining(String tag);
    List<Tag> findAllByTag(String tag);
}
