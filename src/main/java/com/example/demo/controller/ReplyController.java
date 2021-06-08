package com.example.demo.controller;


import com.example.demo.model.Reply;
import com.example.demo.model.Story;
import com.example.demo.service.AuthService;
import com.example.demo.service.ReplyService;
import com.example.demo.service.StoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.status;

@RestController
@AllArgsConstructor
public class ReplyController {
    private final ReplyService replyService;
    private final AuthService authService;

    @RequestMapping(value = "/api/reply/{comment_id}/", method = RequestMethod.POST)
    public ResponseEntity<Reply> createReply(@PathVariable long comment_id, @RequestBody Reply reply) {

        reply.setUserId(authService.getCurrentUser().getUserId());
        System.out.println(authService.getCurrentUser().getUserId());
        reply.setCommentId(comment_id);
        Date date= new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        reply.setCreatedAt(ts);
        return ResponseEntity.status(HttpStatus.CREATED).body(replyService.createReply(reply));
    }


    @GetMapping("/api/replies/{comment_id}/")
    public ResponseEntity<List<Reply>> getAllRepliesOfComment(@PathVariable Long comment_id) {
        Optional<List<Reply>> replyOptional = replyService.getAllRepliesOfComment(comment_id);

        if (replyOptional.isPresent()){
            return new ResponseEntity<>(replyOptional.get(), HttpStatus.OK);
        }else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @RequestMapping(value = "/api/reply/{reply_id}/", method = RequestMethod.PUT)
    public ResponseEntity<Reply> updateReply(@PathVariable long reply_id, @RequestBody Reply reply) {
        return status(HttpStatus.OK).body(replyService.updateReply(reply));
    }


    @RequestMapping(value = "/api/reply/{id}", method = RequestMethod.DELETE)
    public void deleteReply(@PathVariable Long id) {
         replyService.deleteReply(id);
    }

}
