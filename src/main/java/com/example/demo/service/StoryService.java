package com.example.demo.service;


import com.example.demo.model.Story;
import com.example.demo.repository.StoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class StoryService {
    private final StoryRepository storyRepository;

    @Transactional
    public Story createStory(Story story) {
        return storyRepository.save(story);
    }


    @Transactional
    public Optional<Story> getStory(Long id){
        return storyRepository.findById(id);
    }


    @Transactional
    public void deletStory(Long id){
         storyRepository.deleteById(id);
    }


    @Transactional
    public Story updateStory(Story story ){
        return storyRepository.save(story);
    }


    @Transactional
    public List<Story> getAllStories(){
        return storyRepository.findAll();
    }


    @Transactional
    public Optional<List<Story>>  getStoriesByUser(long id){
        return storyRepository.findStoriesByUserId(id);
    }

    @Transactional
    public Optional<Story> getLastStory(){
        return storyRepository.findTopByOrderByIdDesc();
    }
}
