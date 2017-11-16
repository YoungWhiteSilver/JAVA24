import org.apache.commons.io.IOUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/16
 */
public class FastDFSTestCase {

    @Test
    public void UpLoadTest() throws IOException, MyException {

        Properties properties = new Properties();

        properties.setProperty(ClientGlobal.PROP_KEY_TRACKER_SERVERS, "192.168.253.89:22122");
        //初始化配置
        ClientGlobal.initByProperties(properties);

        TrackerClient client = new TrackerClient();
        TrackerServer trackerServer = client.getConnection();

        //存储服务器客户端
        StorageClient storageClient = new StorageClient(trackerServer, null);

        InputStream inputStream = new FileInputStream("G:/upload/down/2.txt");

        byte[] bytes = IOUtils.toByteArray(inputStream);
        String[] results =  storageClient.upload_appender_file(bytes, "jpg", null);

        for(String str: results) {
            System.out.println(str);
        }

        inputStream.close();

    }

    @Test
    public void downloadFile() throws IOException, MyException {

        Properties properties = new Properties();
        properties.setProperty(ClientGlobal.PROP_KEY_TRACKER_SERVERS, "192.168.253.89:22122");

        ClientGlobal.initByProperties(properties);

        TrackerClient client = new TrackerClient();
        TrackerServer trackerServer = client.getConnection();

        StorageClient storageClient = new StorageClient(trackerServer, null);

        byte[] bytes = storageClient.download_file("group1", "M00/00/00/wKj9WVoNg6mELbKbAAAAAPfuiM8660.jpg");

        FileOutputStream outputStream = new FileOutputStream("G:/new.txt");
        outputStream.write(bytes, 0, bytes.length);

        outputStream.flush();
        outputStream.close();


    }




}
