package com.hanghae.study.domain.comment.repository;

import com.hanghae.study.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
