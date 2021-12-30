package com.yzc.myrule.entitly;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Description
 * @Author  Hunter
 * @Date 2020-04-01
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysFileInfo  implements Serializable {

	private static final long serialVersionUID = -7727238808602121527L;
	private String id;

	/**
	 * 关联表主键
	 */
	@TableField("ref_id")
	private String refId;

    /**
     * 关联表名称
     */
	@TableField("ref_table")
    private String refTable;
    /**
     * 关联业务类型
     */
	@TableField("ref_type")
    private String refType;

	/**
	 * 文件名称
	 */
	@TableField("file_name")
	private String fileName;

	/**
	 * 文件路径
	 */
	@TableField("file_location")
    private String fileLocation;

	/**
	 * 文件格式
	 */
	@TableField("file_type")
	private String fileType;

	/**
	 * 上传时间
	 */
	@TableField(value = "upload_time", fill = FieldFill.INSERT_UPDATE)
	private Date uploadTime;

	/**
	 * 上传人
	 */
	@TableField("upload_person")
	private String uploadPerson;

	/**
	 * 上传人主键
	 */
	@TableField("upload_personId")
	private String uploadPersonId;

	/**
	 * 图片url
	 */
	@TableField(exist = false)
	private String url;

	/**
	 * 是否可以下载
	 */
	@TableField("can_down_load")
	private Integer canDownLoad;
}
