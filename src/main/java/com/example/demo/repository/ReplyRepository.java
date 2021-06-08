package com.example.demo.repository;


import com.example.demo.model.Reply;
import com.example.demo.model.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {
    Optional<List<Reply>> findRepliesByCommentId(long commentID);
}
