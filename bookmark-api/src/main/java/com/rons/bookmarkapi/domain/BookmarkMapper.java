package com.rons.bookmarkapi.domain;

import org.springframework.stereotype.Component;

@Component
public class BookmarkMapper {

    public BookmarkDTO toDTO(Bookmark bookmark){

        return BookmarkDTO.builder()
                .id(bookmark.getId())
                .title(bookmark.getTitle())
                .url(bookmark.getUrl())
                .createdAt(bookmark.getCreatedAt())
                .build();
    }
}
