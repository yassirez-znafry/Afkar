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

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.status;

@RestController
@AllArgsConstructor
public class ReplyController {
    private final ReplyService replyService;
    private final AuthService authService;

    @RequestMapping(value = "/api/{comment_id}/reply/", method = RequestMethod.POST)
    public ResponseEntity<Reply> createReply(@RequestBody Reply reply) {
        return ResponseEntity.status(HttpStatus.CREATED).body(replyService.createReply(reply));
    }


    @GetMapping("/api/{comment_id}/replies")
    public ResponseEntity<List<Reply>> getAllRepliesOfComment(@PathVariable Long comment_id) {
        Optional<List<Reply>> replyOptional = replyService.getAllRepliesOfComment(comment_id);

        if (replyOptional.isPresent()){
            return new ResponseEntity<>(replyOptional.get(), HttpStatus.OK);
        }else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @RequestMapping(value = "/api/{comment_id}/reply/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Reply> updateReply(@RequestBody Reply reply) {
        return status(HttpStatus.OK).body(replyService.updateReply(reply));
    }


    @RequestMapping(value = "/api/reply/{id}", method = RequestMethod.DELETE)
    public void deleteReply(@PathVariable Long id) {
         replyService.deleteReply(id);
    }

}
