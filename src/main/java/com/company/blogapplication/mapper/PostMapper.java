package com.company.blogapplication.mapper;

import com.company.blogapplication.dto.PostDto;
import com.company.blogapplication.entity.Post;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.Objects;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), imports = {Objects.class})
public interface PostMapper {
    PostDto map(Post post);

    Post map(PostDto postDto);
}
