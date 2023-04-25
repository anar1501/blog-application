package com.company.blogapplication.controller;

import com.company.blogapplication.dto.CommentDto;
import com.company.blogapplication.dto.PostDto;
import com.company.blogapplication.enums.ROLE;
import com.company.blogapplication.service.CommentService;
import com.company.blogapplication.service.PostService;
import com.company.blogapplication.util.SecurityUtils;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public record CommentController(CommentService commentService, PostService postService) {
    @PostMapping("/{postUrl}/comments")
    public String createComment(@PathVariable("postUrl") String postUrl,
                                @Valid @ModelAttribute("comment") CommentDto commentDto,
                                BindingResult result,
                                Model model) {

        PostDto postDto = postService.findPostByUrl(postUrl);
        if (result.hasErrors()) {
            model.addAttribute("post", postDto);
            model.addAttribute("comment", commentDto);
            return "blog/blog_post";
        }
        commentService.createComment(postUrl, commentDto);
        return "redirect:/post/" + postUrl;
    }

}
