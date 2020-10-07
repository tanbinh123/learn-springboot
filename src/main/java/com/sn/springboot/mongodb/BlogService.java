package com.sn.springboot.mongodb;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.sn.springboot.pojo.Blog;

import java.util.List;

public interface BlogService {
    void saveBlog(Blog blog);

    Blog findBlog(Long id);

    List<Blog> findBlogs(String title, String content, int skip, int limit);

    UpdateResult updateBlog(Blog blog);

    DeleteResult deleteBlog(Long id);
}
