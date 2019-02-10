package com.louisun.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.louisun.dao.PostDao;
import com.louisun.model.Post;
import com.louisun.service.PostService;
import com.louisun.util.JsonResult;
import com.louisun.util.MarkdownRenderer;
import com.louisun.util.constant.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
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
     * @return JSONObject
     * @author YeJianan
     * @date 2019/1/27 23:40
     */
    @Override
    public JSONObject insertPost(Post post) {
        // 渲染 markdwon 为 html，保存到 content 字段中
        post.setContent(MarkdownRenderer.renderMarkdown(post.getContentMd()));
        if(postDao.insertPost(post) == 0){
            return JsonResult.errorResult(ErrorEnum.E_4001);
        }
        else{
            return JsonResult.successResult("保存帖子成功");
        }
    }

    /**
     * 根据 tagId 获取帖子基本信息列表: 无 comment 列表
     * @param tagId 版块 ID
     * @return JSONObject
     * @author YeJianan
     * @date 2019/1/27 23:40
     */
    @Override
    public JSONObject getPostsByTag(int tagId) {
        List<Post> postList = postDao.selectByTagId(tagId);
        if(postList == null){
            return JsonResult.errorResult(ErrorEnum.E_4002);
        }
        else{
            return JsonResult.successResult(postList);
        }
    }

    /**
     * 根据 userId 获取某用户所有帖子基本信息列表
     * @param userId 用户 ID
     * @return JSONObject
     * @author YeJianan
     * @date 2019/1/27 23:41
     */
    @Override
    public JSONObject getPostsByUserId(int userId) {
        List<Post> postList = postDao.selectByUserId(userId);
        if(postList == null){
            return JsonResult.errorResult(ErrorEnum.E_4002);
        }
        else{
            return JsonResult.successResult(postList);
        }
    }

    /**
     * 根据 postId 获取帖子详细内容（包含评论列表）
     * @param postId 帖子 ID
     * @return JSONObject
     * @author YeJianan
     * @date 2019/1/27 23:42
     */
    @Override
    public JSONObject getPostById(int postId) {
        // 注意 Mapper 中如果用 selectByPostId 无法获得评论列表
        Post post = postDao.selectPostWithCommentsByPostId(postId);
        if(post == null){
            return JsonResult.errorResult(ErrorEnum.E_4002);
        }
        else{
            return JsonResult.successResult(post);
        }
    }

    /**
     * 根据 postId 删除帖子：暂不实现
     * @param postId 帖子 ID
     * @return JSONObject
     * @author YeJianan
     * @date 2019/1/27 23:43
     */
    @Override
    public JSONObject deletePostById(int postId) {
        return null;
    }

    /**
     * 根据 userId 删除某用户所有帖子：暂不实现
     * @param userId 用户 ID
     * @return JSONObject
     * @author YeJianan
     * @date 2019/1/27 23:43
     */
    @Override
    public JSONObject deletePostsByUserId(int userId) {
        return null;
    }
}
