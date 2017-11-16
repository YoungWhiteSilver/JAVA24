package com.kaishengit.crm.service.impl;

import com.kaishengit.crm.entity.Disk;
import com.kaishengit.crm.example.DiskExample;
import com.kaishengit.crm.mappers.DiskMapper;
import com.kaishengit.crm.service.DiskService;
import com.kaishengit.crm.service.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/16
 */
@Service
public class DiskServiceImpl implements DiskService{

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

        if(StringUtils.isEmpty(name)) {
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


}
