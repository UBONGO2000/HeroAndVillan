package com.springbootlearning.learningspringboot.backend.service;

import com.springbootlearning.learningspringboot.backend.dto.BlogDto;
import com.springbootlearning.learningspringboot.backend.entity.Blog;
import com.springbootlearning.learningspringboot.backend.exception.NotFoundException;
import com.springbootlearning.learningspringboot.backend.repository.BlogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class BlogService {

    private static final Logger logger = LoggerFactory.getLogger(BlogService.class);
    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public Iterable<Blog> findAllBlogs() {
        return blogRepository.findAll();
    }

    public Blog findBlogById(UUID id) {
        return blogRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Blog by id " + id + " was not found")
        );
    }

    @Transactional
    public void removeBlogById(UUID id) {
        blogRepository.deleteById(id);
    }

    @Transactional
    public Blog createBlog(BlogDto blogDto) {
        Blog blog = new Blog();
        blog.setTitle(blogDto.getTitle());
        blog.setBody(blogDto.getBody());
        blog.setAuthor(blogDto.getAuthor());

        Blog savedBlog = blogRepository.save(blog);
        logger.info("Blog created successfully with id: {}", savedBlog.getId());
        return savedBlog;
    }

    @Transactional
    public Blog updateBlog(UUID id, BlogDto blogDto) {
        Blog blog = findBlogById(id);
        blog.setTitle(blogDto.getTitle());
        blog.setBody(blogDto.getBody());
        blog.setAuthor(blogDto.getAuthor());

        Blog updatedBlog = blogRepository.save(blog);
        logger.info("Blog updated successfully with id: {}", updatedBlog.getId());
        return updatedBlog;
    }
}