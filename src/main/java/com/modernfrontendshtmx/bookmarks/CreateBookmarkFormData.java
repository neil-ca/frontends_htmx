package com.modernfrontendshtmx.bookmarks;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBookmarkFormData {
    private String name;
    private String url;

    public Bookmark toBookmark(int id) {
        return new Bookmark(id, name, url);
    }
}
