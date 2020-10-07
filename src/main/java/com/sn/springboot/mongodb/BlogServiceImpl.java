package com.sn.springboot.mongodb;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.sn.springboot.pojo.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveBlog(Blog blog) {
        // 保存对象
        // 使用类名为blog的文档保存数据，如果文档类名首字母小写则可以省略第二个参数
        // 如果已存在id相同的对象，则更新对象
        mongoTemplate.save(blog, "blog");
    }

    @Override
    public Blog findBlog(Long id) {
        // 直接使用主键查询
        Blog blog = mongoTemplate.findById(id, Blog.class);

        // 构建查询准则
//        Criteria criteria = Criteria.where("id").is(id);
        // 构建查询条件
//        Query query = Query.query(criteria);
//        mongoTemplate.findOne(query, Blog.class);
        return blog;
    }

    @Override
    public List<Blog> findBlogs(String title, String content, int skip, int limit) {
        // 构建title、content的模糊查询准则
        Criteria criteria = Criteria.where("title").regex(title).and("content").regex(content);
        // 构建查询条件，同时设置跳过skip条记录，最多返回limit条记录
        Query query = Query.query(criteria).skip(skip).limit(limit);
        List<Blog> blogs = mongoTemplate.find(query, Blog.class);
        return null;
    }

    @Override
    public UpdateResult updateBlog(Blog blog) {
        Criteria criteria = Criteria.where("id").is(blog.getId());
        Query query = Query.query(criteria);
        // 只更新title、content两个属性
        Update update = Update.update("title", blog.getTitle()).set("content", blog.getContent());
        // 只更新第一个满足条件的文档
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, Blog.class);
        // 更新多个文档
//        UpdateResult updateResult = mongoTemplate.updateMulti(query, update, Blog.class);
        return updateResult;
    }

    @Override
    public DeleteResult deleteBlog(Long id) {
        Criteria criteria = Criteria.where("id").is(id);
        Query query = Query.query(criteria);
        DeleteResult deleteResult = mongoTemplate.remove(query, Blog.class);
        return deleteResult;
    }
}
