package com.kaishengit.crm.service.impl;

import com.kaishengit.crm.entity.Disk;
import com.kaishengit.crm.example.DiskExample;
import com.kaishengit.crm.mappers.DiskMapper;
import com.kaishengit.crm.service.DiskService;
import com.kaishengit.crm.service.exception.ServiceException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/16
 */
@Service
public class DiskServiceImpl implements DiskService {

    @Value("${uploadfile.path}")
    private String saveFilePath;

    @Autowired
    private DiskMapper diskMapper;

    /**
     * 新建文件架
     *
     * @param pId
     * @param accountId
     * @param name
     */
    @Override
    public void saveFolder(Integer pId, Integer accountId, String name) {

        Disk disk = new Disk();

        if (StringUtils.isEmpty(name)) {
            throw new ServiceException("文件夹名不能为空");
        }

        disk.setpId(pId);
        disk.setType(Disk.DIR);
        disk.setName(name);
        disk.setAccountId(accountId);
        disk.setUpdateTime(new Date(System.currentTimeMillis()));

        diskMapper.insertSelective(disk);

    }

    /**
     * 查询文件和文件夹
     *
     * @param pId
     * @return
     */
    @Override
    public List<Disk> findAll(Integer pId) {

        DiskExample diskExample = new DiskExample();
        diskExample.createCriteria().andPIdEqualTo(pId);

        return diskMapper.selectByExample(diskExample);

    }

    /**
     * 根据ID查询
     *
     * @param Id
     * @return
     */
    @Override
    public Disk findByPid(Integer Id) {
        return diskMapper.selectByPrimaryKey(Id);
    }

    /**
     * 上传文件
     *
     * @param inputStream
     * @param fileSize
     * @param fileName
     * @param pId
     * @param accountId
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveFile(InputStream inputStream, Long fileSize, String fileName, Integer pId, Integer accountId) {

        Disk disk = new Disk();

        disk.setType(Disk.FILE);
        disk.setAccountId(accountId);
        disk.setpId(pId);
        disk.setUpdateTime(new Date(System.currentTimeMillis()));
        disk.setName(fileName);

        disk.setFileSize(FileUtils.byteCountToDisplaySize(fileSize));

        String newFileName = UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."));

        disk.setSaveName(newFileName);

        try {

            FileOutputStream outputStream = new FileOutputStream(new File(saveFilePath, newFileName));
            IOUtils.copy(inputStream, outputStream);

        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("文件上传异常");
        }

        diskMapper.insertSelective(disk);

    }

    /**
     * 下载文件
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public InputStream downloadFile(Integer id) throws FileNotFoundException {

        Disk disk = diskMapper.selectByPrimaryKey(id);

        if(disk == null || disk.getType().equals(Disk.DIR)) {

            throw new ServiceException("文件不存在");

        }

        disk.setDownloadCount(disk.getDownloadCount() + 1);
        diskMapper.updateByPrimaryKeySelective(disk);

        FileInputStream inputStream = new FileInputStream(new File(saveFilePath, disk.getSaveName()));

        return inputStream;

    }


}
