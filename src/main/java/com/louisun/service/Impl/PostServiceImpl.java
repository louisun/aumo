package com.louisun.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.louisun.dao.CommentDao;
import com.louisun.dao.PostDao;
import com.louisun.dto.PostRankInfo;
import com.louisun.model.Comment;
import com.louisun.model.Post;
import com.louisun.service.PostService;
import com.louisun.util.MarkdownRenderer;
import com.louisun.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PostServiceImpl  implements PostService {

    private final PostDao postDao;


    private final CommentDao commentDao;

    private final PostServiceHelper postServiceHelper;



    @Autowired
    public PostServiceImpl(PostDao postDao, CommentDao commentDao, PostServiceHelper postServiceHelper) {
        this.postDao = postDao;
        this.commentDao = commentDao;
        this.postServiceHelper = postServiceHelper;
    }

    @Override
    public List<PostRankInfo> getPostRankInfoList() {
        return postDao.getPostRankInfoList();
    }

    /**
     * 保存帖子信息
     * @param post 帖子：包含 postId, tagId, userId, title, content, contentMd
     * @return int 插入成功的条数
     * @author YeJianan
     * @date 2019/1/27 23:40
     */
    @Override
    public int insertPost(Post post) {
        // 渲染 markdwon 为 html，保存到 content 字段中
        post.setContent(MarkdownRenderer.renderMarkdown(post.getContentMd()));
        return postDao.insertPost(post);
    }

    /**
     * 根据 tagId 获取帖子基本信息列表
     * @param tagId 版块 ID
     * @return List<Post> 帖子基本信息列表
     * @author YeJianan
     * @date 2019/1/27 23:40
     */
    @Override
    public PageBean<Post> getPostsByTag(int tagId, int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        // 拦截器机制，会拦截 SQL
        Page<Post> allPosts = (Page<Post>) postDao.selectByTagId(tagId);
        PageBean<Post> pageData = new PageBean<>(currentPage, pageSize, (int)allPosts.getTotal());
        pageData.setItems(allPosts.getResult());
        return pageData;
    }

    /**
     * 根据 userId 获取某用户所有帖子基本信息列表
     * @param userId 用户 ID
     * @return List<Post> 帖子基本信息列表
     * @author YeJianan
     * @date 2019/1/27 23:41
     */
    @Override
    public PageBean<Post> getPostsByUserId(int userId, int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        // 拦截器机制，会拦截 SQL
        Page<Post> allPosts = (Page<Post>) postDao.selectByUserId(userId);
        PageBean<Post> pageData = new PageBean<>(currentPage, pageSize, (int)allPosts.getTotal());
        pageData.setItems(allPosts.getResult());
        return pageData;
    }

    /**
     * 根据 postId 获取帖子详细内容（包含评论列表）
     * @param postId 帖子 ID
     * @return JSONObject
     * @author YeJianan
     * @date 2019/1/27 23:42
     */
    @Override
    public Post getPostById(int postId) {
        // 注意 Mapper 中如果用 selectByPostId 无法获得评论列表
        // 本来是用 selectPostWithCommentsByPostId 获取完整的 Post，但是这样缓存就和 comment 耦合
        Post post = postServiceHelper.getBasicPostById(postId);
        if(post == null) return null;
        List<Comment> commentList = commentDao.selectByPostId(postId);
        post.setCommentList(commentList);
        return post;
    }

    /**
     * 根据 postId 获取帖子基本内容（不含评论列表）
     *
     * @param postId 帖子 ID
     * @return JSONObject
     * @author YeJianan
     * @date 2019/1/27 23:43
     */
    @Override
    public Post getBasicPostById(int postId) {
        // 内部调用这个，缓存失效，要放在不同类中
        return postDao.selectByPostId(postId);
    }



    @Override
    @CacheEvict(value="post", key="'post_'+#postId")
    public int deletePost(int postId) {
        return postDao.deleteByPostId(postId);
    }
}


@Service
class PostServiceHelper{
    @Autowired
    private final PostDao postDao;

    PostServiceHelper(PostDao postDao) {
        this.postDao = postDao;
    }

    /**
     * 根据 postId 获取帖子基本内容（不含评论列表）
     *
     * @param postId 帖子 ID
     * @return JSONObject
     * @author YeJianan
     * @date 2019/1/27 23:43
     */
    @Cacheable(value="post", key="'post_'+#postId", unless = "#result == null")
    public Post getBasicPostById(int postId) {
        return postDao.selectByPostId(postId);
    }

}