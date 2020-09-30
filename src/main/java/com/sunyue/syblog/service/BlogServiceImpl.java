package com.sunyue.syblog.service;

import com.sunyue.syblog.NotFoundException;
import com.sunyue.syblog.Utils.MarkdownUtils;
import com.sunyue.syblog.dao.BlogRepository;
import com.sunyue.syblog.po.Blog;
import com.sunyue.syblog.po.Type;
import com.sunyue.syblog.vo.BlogQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.*;

@Service
public class BlogServiceImpl implements  BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Blog getBlog(Long id) {
        return blogRepository.getOne(id);
    }
@Override
public Page<Blog> listBlog(Pageable pageable, BlogQuery blog) {
    return blogRepository.findAll(new Specification<Blog>() {
        @Override
        public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
            List<Predicate> predicates = new ArrayList<>();
            //构造like查询，第一个是名字，第二个是值
            //predicates条件集合，用于组合条件判断
            if (!"".equals(blog.getTitle()) && blog.getTitle() != null) {
                predicates.add(cb.like(root.<String>get("title"), "%"+blog.getTitle()+"%"));
            }
            if (blog.getTypeId() != null) {
                predicates.add(cb.equal(root.<Type>get("type").get("id"), blog.getTypeId()));
            }
            if (blog.isRecommend()) {
                predicates.add(cb.equal(root.<Boolean>get("recommend"), blog.isRecommend()));
            }
            cq.where(predicates.toArray(new Predicate[predicates.size()]));
            return null;
        }
    },pageable);
}
    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        //如果为空blog新增，否则只是更新
        if(blog==null){
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
        }else {
            blog.setUpdateTime(new Date());
        }

        return blogRepository.save(blog);
    }

    @Transactional
    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Blog b = blogRepository.getOne(id);
        if(b ==null){
            throw new NotFoundException("想要更新的博客不存在");
        }
        //如果博客存在就把新的博客overwrite到b，最后返回一个blog对象就可以了
        BeanUtils.copyProperties(blog,b);
        return blogRepository.save(b);
    }

    @Transactional
    @Override
    public void deleteBlog(Long id) {
         blogRepository.deleteById(id);
    }

    @Override
    public List<Blog> listRecommendBlogTop(Integer size) {
        Sort sort =Sort.by(Sort.Direction.DESC,"updateTime");
        Pageable pageable = PageRequest.of(0,size,sort);
        return blogRepository.findTop(pageable);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    //搜索

    @Override
    public Page<Blog> listBolg(String query, Pageable pageable) {
        return blogRepository.findbyQuery(query,pageable);
    }

    @Override
    public Blog getAndConvert(Long id) {
        Blog blog=blogRepository.getOne(id);
        if(blog==null){
           throw new NotFoundException("该博客不存在");
        }
        //新建一个b防止数据库的数据被篡改
        Blog b =new Blog();
        BeanUtils.copyProperties(blog,b);
        String content = b.getContent();
        b.setContent( MarkdownUtils.markdownToHtml(content));
        blogRepository.updateViews(id);
        return b;
    }

    @Override
    public Page<Blog> listBlog(Long tagId, Pageable pageable) {
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                Join join = root.join("tags");
                return cb.equal(join.get("id"),tagId);
            }
        },pageable);
    }

    //归档
    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = blogRepository.findGroupYear();
        Map<String, List<Blog>> map = new HashMap<>();
        for (String year : years) {
            map.put(year, blogRepository.findByYear(year));
        }
        return map;
    }

    @Override
    public Long count() {
        return blogRepository.count();
    }
}
