package com.hrms.api.domain.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 孔超
 * @date 2020/5/11 15:31
 */
@Data
public class FileDocument implements Serializable {
    /**
     * id
     */
    private String id;
    /**
     * 文件名称
     */
    private String name;
    /**
     * 文件大小
     */
    private long size;
    /**
     * 上传时间
     */
    private Date uploadDate;
    /**
     * 文件的md5值
     */
    private String md5;
    /**
     * 文件内容
     */
    private byte[] content;
    /**
     * 文件类型
     */
    private String contentType;
    /**
     * 文件后缀
     */
    private String suffix;
    /**
     * 文件描述
     */
    private String description;
    /**
     * 大文件管理GridFS的ID
     */
    private String gridfsId;
}
