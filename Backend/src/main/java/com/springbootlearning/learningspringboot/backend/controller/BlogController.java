package com.springbootlearning.learningspringboot.backend.controller;

import com.springbootlearning.learningspringboot.backend.dto.BlogDto;
import com.springbootlearning.learningspringboot.backend.entity.Blog;
import com.springbootlearning.learningspringboot.backend.service.BlogService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/blogs")
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Blog>> getAllBlogs() {
        return ResponseEntity.ok(blogService.findAllBlogs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable UUID id) {
        return ResponseEntity.ok(blogService.findBlogById(id));
    }

    @PostMapping
    public ResponseEntity<Blog> createBlog(@Valid @RequestBody BlogDto blogDto) {
        Blog createdBlog = blogService.createBlog(blogDto);
        return new ResponseEntity<>(createdBlog, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Blog> updateBlog(@PathVariable UUID id, @Valid @RequestBody BlogDto blogDto) {
        Blog updatedBlog = blogService.updateBlog(id, blogDto);
        return ResponseEntity.ok(updatedBlog);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable UUID id) {
        blogService.removeBlogById(id);
        return ResponseEntity.noContent().build();
    }
}