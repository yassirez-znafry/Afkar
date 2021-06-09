package com.example.demo.controller;


import com.example.demo.model.Story;
import com.example.demo.model.User;
import com.example.demo.service.AuthService;
import com.example.demo.service.StoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.status;

@RestController
@AllArgsConstructor
public class StoryController {
    private final StoryService storyService;
    private final AuthService authService;

    @RequestMapping(value = "/api/story", method = RequestMethod.POST)
    public ResponseEntity<Story> createStory(@RequestBody Story story) {
        User user = authService.getCurrentUser();
        story.setUserId(user.getUserId());
        return ResponseEntity.status(HttpStatus.CREATED).body(storyService.createStory(story));
    }

    @RequestMapping(value = "/api/story/{id}", method = RequestMethod.GET)
    public Optional<Story> getStory(@PathVariable Long id){
        return storyService.getStory(id);
    }


    @RequestMapping(value = "/api/story/{id}", method = RequestMethod.DELETE)
    public void deletStory(@PathVariable Long id) {
        User user = authService.getCurrentUser();
        assert (user.getUserId()==storyService.getStory(id).get().getUserId());
        storyService.deletStory(id);
    }


    @RequestMapping(value = "/api/story/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Story> updateStory(@PathVariable long story_id, @RequestBody Story story) {
        User user = authService.getCurrentUser();
        assert (user.getUserId()==storyService.getStory(story_id).get().getUserId());
        return status(HttpStatus.OK).body(storyService.updateStory(story));
    }


    @GetMapping("/api/story")
    public ResponseEntity<List<Story>> getAllStories() {
        return status(HttpStatus.OK).body(storyService.getAllStories());
    }


    @GetMapping("/api/stories/by-user/{id}")
    public ResponseEntity<List<Story>> getStoriesByUser(@PathVariable Long id) {
        Optional<List<Story>> storyOptional = storyService.getStoriesByUser(id);
        if (storyOptional.isPresent()){
            return new ResponseEntity<>(storyOptional.get(), HttpStatus.OK);
        }else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/story/myStories")
    public ResponseEntity<List<Story>> getMyStories() {
        User user = authService.getCurrentUser();
        Optional<List<Story>> storyOptional = storyService.getStoriesByUser(user.getUserId());
        if (storyOptional.isPresent()){
            return new ResponseEntity<>(storyOptional.get(), HttpStatus.OK);
        }else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @RequestMapping(value = "/api/like/story/{story_id}", method = RequestMethod.POST)
    public void createStory(@PathVariable long story_id) {
        Optional<Story> story = storyService.getStory(story_id);
        story.get().setTotalLikes(story.get().getTotalLikes()+1);
    }










}
