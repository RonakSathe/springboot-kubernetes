package com.rons.bookmarkapi.domain;

import lombok.*;
import org.springframework.data.domain.Page;

import java.time.Instant;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class BookmarkDTO {
    private Long id;
    private String title;
    private String url;
    private Instant createdAt;

}
