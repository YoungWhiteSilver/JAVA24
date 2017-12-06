package com.kaishengit.utils;

import com.google.gson.Gson;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/12/6
 */
@Component
public class FileUploadForQiniu implements FileUploadAndDownLoad{

    @Value("${accessKey}")
    private String accessKey;

    @Value("${secretKey}")
    private String secretKey;

    @Value("${bucket}")
    private String bucket;

    @Value("${domainOfBucket}")
    private String domainOfBucket;

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

        Configuration configuration = new Configuration(Zone.zone1());
        UploadManager uploadManager = new UploadManager(configuration);

        Auth auth = Auth.create(accessKey, secretKey);

        String uploadToken = auth.uploadToken(bucket);

        byte[] bytes = IOUtils.toByteArray(inputStream);
        Response response = uploadManager.put(bytes, null, uploadToken);
        DefaultPutRet defaultPutRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        return defaultPutRet.key;
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

        String encodeFileName = URLEncoder.encode(fileName, "utf-8");
        String finalUrl = String .format("%s/%s", domainOfBucket, encodeFileName);

        URL url = new URL(finalUrl);
        URLConnection urlConnection = url.openConnection();

        InputStream inputStream = urlConnection.getInputStream();

        return IOUtils.toByteArray(inputStream);
    }
































}
