package com.company.blogapplication.service.impl;

import com.company.blogapplication.dto.CommentDto;
import com.company.blogapplication.entity.Comment;
import com.company.blogapplication.entity.Post;
import com.company.blogapplication.entity.User;
import com.company.blogapplication.mapper.CommentMapper;
import com.company.blogapplication.repository.CommentRepository;
import com.company.blogapplication.repository.PostRepository;
import com.company.blogapplication.repository.UserRepository;
import com.company.blogapplication.service.CommentService;
import com.company.blogapplication.util.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public record CommentServiceImpl(CommentRepository commentRepository,
                                 PostRepository postRepository,
                                 UserRepository userRepository, CommentMapper commentMapper) implements CommentService {

    @Override
    public void createComment(String postUrl, CommentDto commentDto) {

        Post post = postRepository.findByUrl(postUrl).get();
        Comment comment = commentMapper.map(commentDto);
        comment.setPost(post);
        commentRepository.save(comment);
    }

    @Override
    public List<CommentDto> findAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream()
                .map(commentMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public List<CommentDto> findCommentsByPost() {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User createdBy = userRepository.findByEmail(email);
        Long userId = createdBy.getId();
        List<Comment> comments = commentRepository.findCommentsByPost(userId);
        return comments.stream()
                .map(commentMapper::map)
                .collect(Collectors.toList());
    }
}
