package com.rons.bookmarkapi.api;

import com.rons.bookmarkapi.domain.BookmarkService;
import com.rons.bookmarkapi.domain.BookmarksDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @GetMapping
    //@RequestParam for pagination only few data at a page by default page number is 1.
    public BookmarksDTO getBookmarks(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                     @RequestParam(name = "query", defaultValue = "") String query
                                     ){
        if(query == null || query.trim().length() == 0){
            return bookmarkService.getBookmarks(page);
        }
        return bookmarkService.searchBookmarks(query,page);

    }
}
