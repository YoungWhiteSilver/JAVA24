package com.kaishengit.crm.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class Disk implements Serializable {

    /**
     * 文件夹
     */
    public static final String DIR = "dir";

    /**
     * 文件
     */
    public static final String FILE = "file";

    private Integer id;

    /**
     * 上传文件名 
     */
    private String name;

    /**
     * 存到磁盘或云盘的名字（UUID）
     */
    private String saveName;

    /**
     * 文件大小
     */
    private String fileSize;

    private Integer pId;

    /**
     * 文件类型 dir 文件夹 file文件
     */
    private String type;

    /**
     * 上传时间
     */
    private Date updateTime;

    private Integer accountId;

    /**
     * 下载次数
     */
    private Integer downloadCount;

    /**
     * 文件密码 文件锁
     */
    private String password;

    /**
     * 文件的MD5名
     */
    private String md5;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSaveName() {
        return saveName;
    }

    public void setSaveName(String saveName) {
        this.saveName = saveName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }
}