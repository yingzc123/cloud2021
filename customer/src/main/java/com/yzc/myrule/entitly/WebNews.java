package com.yzc.myrule.entitly;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lzr
 * @description 新闻资讯实体类
 * @created 2021/11/5 14:23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "web_news")
public class WebNews implements Serializable, Cloneable {
    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String newsId;
    /**
     * 标题
     */
    private String newsTitle;
    /**
     * 图片
     */
    private String newsPicture;
    /**
     * 描述
     */
    private String newsDescribe;
    /**
     * 登记日期
     */
    private Date newsTime;
    /**
     * 作者
     */
    private String newsAuthor;
    /**
     * 阅读人数
     */
    private Integer newsNumber;
    /**
     * 内容
     */
    private String newsContent;
    /**
     * 原文地址
     */
    private String newsLink;
    /**
     * 附件（id)
     */
    private String newsFileId;
    /**
     * 文件名称
     */
    @TableField(exist = false)
    private String newsFileName;
    /**
     * 文件路径
     */
    @TableField(exist = false)
    private String newsFileURL;
    /**
     * 类型;新闻资讯/通知公告/行业要闻
     */
    private String newsType;
    /**
     * 是否置顶;0表示不置顶、1表示置顶（默认为0）
     */
    private int newsTop;
    /**
     * 逻辑删除;0未删除1已删除
     */
    @TableLogic
    private Integer invalid;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;
    /**
     * 修改人
     */
    private String updatedBy;
    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;


}