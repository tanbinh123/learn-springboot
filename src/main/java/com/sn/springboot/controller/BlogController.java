package com.sn.springboot.controller;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.sn.springboot.mongodb.BlogService;
import com.sn.springboot.pojo.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @PostMapping("/save")
    public Blog saveBlog(@RequestBody Blog blog) {
        blogService.saveBlog(blog);
        return blog;
    }

    @GetMapping("/findById")
    public Blog findBlog(Long id) {
        Blog blog = blogService.findBlog(id);
        return blog;
    }

    @GetMapping("/find")
    public List<Blog> findBlogs(String title, String content, int skip, int limit) {
        List<Blog> blogs = blogService.findBlogs(title, content, skip, limit);
        return blogs;
    }

    @PostMapping("/update")
    public UpdateResult updateBlog(Blog blog) {
        UpdateResult updateResult = blogService.updateBlog(blog);
        return updateResult;
    }

    @GetMapping("/delete")
    public DeleteResult deleteBlog(Long id) {
        DeleteResult deleteResult = blogService.deleteBlog(id);
        return deleteResult;
    }
}
