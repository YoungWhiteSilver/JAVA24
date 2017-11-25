package com.kaishengit.crm.service;

import com.kaishengit.crm.entity.Disk;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/16
 */
public interface DiskService {


    /**
     * 新建文件架
     * @param pId
     * @param accountId
     * @param name
     */
    void saveFolder(Integer pId, Integer accountId, String name);


    /**
     * 查询文件和文件夹
     * @param pId
     * @return
     */
    List<Disk> findAll(Integer pId);

    /**
     * 根据ID查询
     * @param Id
     * @return
     */
    Disk findByPid(Integer Id);


    /**
     * 上传文件
     *
     * @param inputStream
     * @param fileSize
     * @param fileName
     * @param pId
     * @param accountId
     */
    void saveFile(InputStream inputStream, Long fileSize, String fileName, Integer pId, Integer accountId);

    /**
     * 下载文件
     * @param id
     * @return
     * @throws IOException
     */
    InputStream downloadFile(Integer id) throws IOException;

    /**
     * 获得七牛云的上传凭证
     * @return
     */
    String getUpToken();

    /**
     * 保存七牛云返回的数据
     * @param name
     * @param key
     * @param accountId
     * @param pId
     * @param fileSize
     */
    void saveQiniu(String name, String key, Integer accountId, Integer pId, Integer fileSize);


}
