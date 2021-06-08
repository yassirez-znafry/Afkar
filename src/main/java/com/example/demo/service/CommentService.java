package com.example.demo.service;


import com.example.demo.model.Comment;
import com.example.demo.repository.CommentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class CommentService {

    private final CommentRepository commentRepository;
    private final StoryService storyService;

    @Transactional
    public Comment save(Comment comment) {

        return commentRepository.save(comment);
    }


    @Transactional
    public Optional<Comment> getComment(Long id){
        return commentRepository.findById(id);
    }

    @Transactional
    public void delete(Long id){
        commentRepository.deleteById(id);
    }
}
