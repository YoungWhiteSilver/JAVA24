package com.kaishengit.utils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/12/6
 */
public interface FileUploadAndDownLoad {

    /**
     * 文件上传
     * @param inputStream
     * @param fileName
     * @return
     * @throws IOException
     */
    String fileUpload(InputStream inputStream, String fileName) throws IOException;


    /**
     * 文件下载
     * @param fileName
     * @return
     * @throws IOException
     */
    byte[] fileDownload(String fileName) throws IOException;



}
