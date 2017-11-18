package com.kaishengit.crm.service.fileUpAndDown;

import com.kaishengit.crm.service.exception.ServiceException;
import org.apache.commons.io.IOUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/18
 */
@Component
public class FileUploadAndDownloadFastDFS implements FileUploadAndDownload{

    @Value("${trackerServer}")
    private String trackerServers ;
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

        String suffix = "";
        String separators = "#";
        String newFileName = "";

        if(fileName.indexOf(".") != -1) {

            suffix = fileName.substring(fileName.lastIndexOf(".") + 1);

        }

        try {

            StorageClient client = getstorageClient();
            byte[] bytes = IOUtils.toByteArray(inputStream);

            String[] result = client.upload_file(bytes, suffix, null);

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(result[0])
                    .append(separators)
                    .append(result[1]);
            newFileName = stringBuilder.toString();

        } catch (MyException e) {
            e.printStackTrace();
            throw new ServiceException("文件上传异常");
        }

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

        String[] fileNames = fileName.split("#");

        try {

            StorageClient client = getstorageClient();
            byte[] bytes = client.download_file(fileNames[0], fileNames[1]);
            return bytes;

        } catch (MyException e) {
            e.printStackTrace();
            throw new ServiceException("文件下载异常");
        }

    }


    private StorageClient getstorageClient() throws IOException, MyException {

        Properties properties = new Properties();
        properties.setProperty(ClientGlobal.PROP_KEY_TRACKER_SERVERS, trackerServers);

        //初始化配置
        ClientGlobal.initByProperties(properties);

        TrackerClient client = new TrackerClient();
        TrackerServer trackerServer = client.getConnection();

        StorageClient storageClient = new StorageClient(trackerServer, null);

        return  storageClient;
    }

}
