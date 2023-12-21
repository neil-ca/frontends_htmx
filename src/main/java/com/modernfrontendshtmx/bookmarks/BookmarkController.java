package com.modernfrontendshtmx.bookmarks;

import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/bookmarks")
public class BookmarkController {
    private final AtomicInteger autoIncrementCounter = new AtomicInteger();
    private final Map<Integer, Bookmark> bookmarkMap = new HashMap<>();

    @GetMapping
    public String index(Model model) {
        model.addAttribute("bookmarks", bookmarkMap.values());
        return "bookmark";
    }

    @HxRequest
    @PostMapping("/create")
    public String createBookmark(@ModelAttribute("formData") CreateBookmarkFormData formData, Model model) {
        Bookmark bookmark = addBookmark(formData);
        model.addAttribute("bookmark", bookmark);
        return "bookmark :: bookmark";
    }

    @HxRequest
    @DeleteMapping("/{id}")
    @ResponseBody
    public String deleteBookmark(@PathVariable int id) {
        bookmarkMap.remove(id);
        return "";
    }

    private Bookmark addBookmark(CreateBookmarkFormData formData) {
        Bookmark bookmark = formData.toBookmark(autoIncrementCounter.incrementAndGet());
        bookmarkMap.put(bookmark.id(), bookmark);
        return bookmark;
    }
}
