package com.example.demo.controller;

import com.example.demo.model.Comment;
import com.example.demo.service.AuthService;
import com.example.demo.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController

@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final AuthService authService;


    @RequestMapping(value = "/api/{story_id}/comment", method = RequestMethod.POST)
    public ResponseEntity<Comment> createComment(@PathVariable long story_id, @RequestBody Comment comment) {

        comment.setUserId(authService.getCurrentUser().getUserId());
        comment.setStoryId(story_id);
        Date date= new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        comment.setCreated_at(ts);

        System.out.println();
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.save(comment));
    }


    @RequestMapping(value = "/api/comment/{id}", method = RequestMethod.GET)
    public Optional<Comment> getComment(@PathVariable Long id) {
        return commentService.getComment(id);
    }


    @RequestMapping(value = "/api/{story_id}/comment", method = RequestMethod.GET)
    public Optional<List<Comment>> getAllCommentsOfAStory(@PathVariable Long story_id) {
        return commentService.getAllCommentsOfAStory(story_id);
    }


    @RequestMapping(value = "/api/comment/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteComment(@PathVariable Long id) {
        commentService.delete(id);
    }




}