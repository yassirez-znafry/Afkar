package com.example.demo.service;


import com.example.demo.model.Reply;
import com.example.demo.repository.ReplyRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ReplyService {
    private final ReplyRepository replyRepository;


    @Transactional
    public Reply createReply(Reply reply) {

        return replyRepository.save(reply);
    }



    @Transactional
    public Optional<List<Reply>> getAllRepliesOfComment(long comment_id){
        return replyRepository.findRepliesByCommentId(comment_id);
    }


    @Transactional
    public Reply updateReply(Reply reply ){
        return replyRepository.save(reply);
    }


    @Transactional
    public void deleteReply(Long id){
        replyRepository.deleteById(id);
    }


}
