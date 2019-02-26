package com.louisun.service.Impl;

import com.louisun.dao.PostDao;
import com.louisun.model.Post;
import com.louisun.service.PostService;
import com.louisun.util.MarkdownRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl  implements PostService {

    private final PostDao postDao;

    @Autowired
    public PostServiceImpl(PostDao postDao) {
        this.postDao = postDao;
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
    @Cacheable(value="post", key="'postlist_tag_'+#tagId", unless = "#result == null")
    public List<Post> getPostsByTag(int tagId) {
        return postDao.selectByTagId(tagId);
    }

    /**
     * 根据 userId 获取某用户所有帖子基本信息列表
     * @param userId 用户 ID
     * @return List<Post> 帖子基本信息列表
     * @author YeJianan
     * @date 2019/1/27 23:41
     */
    @Override
    @Cacheable(value="post", key="'postlist_user_'+#userId", unless = "#result == null")
    public List<Post> getPostsByUserId(int userId) {
        return postDao.selectByUserId(userId);

    }

    /**
     * 根据 postId 获取帖子详细内容（包含评论列表）
     * @param postId 帖子 ID
     * @return JSONObject
     * @author YeJianan
     * @date 2019/1/27 23:42
     */
    @Override
    @Cacheable(value="post", key="'post_'+#postId", unless = "#result == null")
    public Post getPostById(int postId) {
        // 注意 Mapper 中如果用 selectByPostId 无法获得评论列表
        return postDao.selectPostWithCommentsByPostId(postId);
    }
}
