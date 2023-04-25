package com.company.blogapplication.mapper;

import com.company.blogapplication.dto.CommentDto;
import com.company.blogapplication.entity.Comment;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.Objects;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), imports = {Objects.class})
public interface CommentMapper {
    CommentDto map(Comment comment);

    Comment map(CommentDto commentDto);

}
