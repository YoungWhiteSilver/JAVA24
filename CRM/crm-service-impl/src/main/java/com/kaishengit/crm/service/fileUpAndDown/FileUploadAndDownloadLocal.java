package com.kaishengit.crm.service.fileUpAndDown;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sun.misc.Contended;

import java.io.*;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/18
 */
@Component
public class FileUploadAndDownloadLocal implements FileUploadAndDownload{

    @Value("${uploadfile.path}")
    private String saveFilePath;

    /**
     * 文件上传
     *
     * @param inputStream
     * @param fileName
     * @return
     * @throws IOException
     */
    @Override
    public String fileUpload(InputStream inputStream, String fileName) throws IOException {


        String newFileName = UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."));

        FileOutputStream fileOutputStream = new FileOutputStream(new File(saveFilePath, newFileName));
        IOUtils.copy(inputStream, fileOutputStream);

        fileOutputStream.flush();
        fileOutputStream.close();
        inputStream.close();

        return newFileName;

    }

    /**
     * 文件下载
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    @Override
    public byte[] fileDownload(String fileName) throws IOException {

        FileInputStream inputStream = new FileInputStream(new File(saveFilePath, fileName));

        return IOUtils.toByteArray(inputStream);

    }
}
