package com.rons.bookmarkapi.domain;

import java.time.Instant;

public interface BookmarkVM {

    Long getId();
    String getTitle();
    String getUrl();
    Instant getCreatedAt();
}
