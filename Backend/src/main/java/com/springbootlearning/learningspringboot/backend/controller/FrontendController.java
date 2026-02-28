package com.springbootlearning.learningspringboot.backend.controller;

import com.springbootlearning.learningspringboot.backend.dto.HeroDto;
import com.springbootlearning.learningspringboot.backend.entity.*;
import com.springbootlearning.learningspringboot.backend.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@Controller
public class FrontendController {

    private final HeroService heroService;
    private final VillainService villainService;
    private final CategoryService categoryService;
    private final AntiHeroService antiHeroService;
    private final BlogService blogService;
    private final CommentService commentService;

    public FrontendController(HeroService heroService, VillainService villainService,
                               CategoryService categoryService, AntiHeroService antiHeroService,
                               BlogService blogService, CommentService commentService) {
        this.heroService = heroService;
        this.villainService = villainService;
        this.categoryService = categoryService;
        this.antiHeroService = antiHeroService;
        this.blogService = blogService;
        this.commentService = commentService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<HeroDto> heroes = heroService.findAllHeroes();
        Iterable<Villain> villains = villainService.findAllVillains();
        Iterable<Category> categories = categoryService.findAllCategories();
        Iterable<Blog> blogs = blogService.findAllBlogs();

        model.addAttribute("heroes", heroes);
        model.addAttribute("villains", villains);
        model.addAttribute("categories", categories);
        model.addAttribute("blogs", blogs);

        return "index";
    }

    @GetMapping("/heroes")
    public String listHeroes(Model model) {
        List<HeroDto> heroes = heroService.findAllHeroes();
        model.addAttribute("heroes", heroes);
        return "heroes";
    }

    @GetMapping("/heroes/{id}")
    public String viewHero(@PathVariable UUID id, Model model) {
        HeroDto hero = heroService.findHeroById(id);
        model.addAttribute("hero", hero);
        return "hero";
    }

    @GetMapping("/villains")
    public String listVillains(Model model) {
        Iterable<Villain> villains = villainService.findAllVillains();
        model.addAttribute("villains", villains);
        return "villains";
    }

    @GetMapping("/villains/{id}")
    public String viewVillain(@PathVariable UUID id, Model model) {
        Villain villain = villainService.findVillainById(id);
        model.addAttribute("villain", villain);
        return "villain";
    }

    @GetMapping("/anti-heroes")
    public String listAntiHeroes(Model model) {
        Iterable<AntiHero> antiHeroes = antiHeroService.findAllAntiHeroes();
        model.addAttribute("antiHeroes", antiHeroes);
        return "anti-heroes";
    }

    @GetMapping("/anti-heroes/{id}")
    public String viewAntiHero(@PathVariable UUID id, Model model) {
        AntiHero antiHero = antiHeroService.findAntiHeroById(id);
        model.addAttribute("antiHero", antiHero);
        return "anti-hero";
    }

    @GetMapping("/categories")
    public String listCategories(Model model) {
        Iterable<Category> categories = categoryService.findAllCategories();
        model.addAttribute("categories", categories);
        return "categories";
    }

    @GetMapping("/categories/{id}")
    public String viewCategory(@PathVariable UUID id, Model model) {
        Category category = categoryService.findCategoryById(id);
        model.addAttribute("category", category);
        return "category";
    }

    @GetMapping("/blogs")
    public String listBlogs(Model model) {
        Iterable<Blog> blogs = blogService.findAllBlogs();
        model.addAttribute("blogs", blogs);
        return "blogs";
    }

    @GetMapping("/blogs/{id}")
    public String viewBlog(@PathVariable UUID id, Model model) {
        Blog blog = blogService.findBlogById(id);
        List<Comment> comments = commentService.findCommentsByBlogId(id);
        model.addAttribute("blog", blog);
        model.addAttribute("comments", comments);
        return "blog";
    }
}
