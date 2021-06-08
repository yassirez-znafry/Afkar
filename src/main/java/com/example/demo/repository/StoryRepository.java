package com.example.demo.repository;

import com.example.demo.model.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {
    Optional<List<Story>>  findStoriesByUserId(Long userId);
}
