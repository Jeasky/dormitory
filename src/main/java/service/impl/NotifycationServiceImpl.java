package service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import domain.Notifycation;
import exception.AddException;
import exception.DelException;
import exception.UpdateException;
import mapper.NotifycationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.NotifycationService;

import java.util.ArrayList;
import java.util.List;

@Service("NotifycationServiceImpl")
public class NotifycationServiceImpl implements NotifycationService {

    @Autowired
    NotifycationMapper notifycationMapper;

    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public Notifycation searchById(Integer notifycationId) {

        Notifycation notifycation=new Notifycation();

        notifycation=notifycationMapper.selectByPrimaryKey(notifycationId);

        return notifycation;
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public List<Notifycation> displayByBuild(Integer buildId){

        List<Notifycation> list=new ArrayList<Notifycation>();

        list=notifycationMapper.selectByBuildId(buildId);

        return list;
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public PageInfo<Notifycation> displayAll(Integer buildid, Integer page, Integer pageSize){
        //指定分页信息，分页的插件类，PageHelper
        PageHelper.startPage(page,pageSize);

        List<Notifycation> list=notifycationMapper.selectByBuildId(buildid);

        PageInfo<Notifycation> pageInfo=new PageInfo<>(list);

        if(page<1){
            pageInfo.setPageNum(1);
        }
        if(page>pageInfo.getPages()){
            pageInfo.setPageNum(pageInfo.getPages());
        }

        return pageInfo;
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public PageInfo<Notifycation> search(Integer buildid,String searchKey, Integer page, Integer pageSize){
        //指定分页信息，分页的插件类，PageHelper
        PageHelper.startPage(page,pageSize);

        List<Notifycation> list=notifycationMapper.selectByKey(buildid,searchKey);

        PageInfo<Notifycation> pageInfo=new PageInfo<>(list);

        if(page<1){
            pageInfo.setPageNum(1);
        }
        if(page>pageInfo.getPages()){
            pageInfo.setPageNum(pageInfo.getPages());
        }

        return pageInfo;
    }

    //添加通知公告实现方法
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public int addNotifycation(Notifycation notifycation) throws AddException {
        int result;

        result = notifycationMapper.insert(notifycation);

        if(result == 1)
            return result;
        throw new AddException("添加操作失败！");
    }

    //删除通知公告实现方法
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public int delNotifycation(int notifycationid) throws DelException {
        int result;

        result = notifycationMapper.deleteByPrimaryKey(notifycationid);

        if(result == 1)
            return result;
        throw new DelException("添加操作失败！");
    }

    //添加通知公告实现方法
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public int updateNotifycation(Notifycation notifycation) throws UpdateException {
        int result;

        result = notifycationMapper.updateByPrimaryKey(notifycation);

        if(result == 1)
            return result;
        throw new UpdateException("修改操作失败！");
    }
}
