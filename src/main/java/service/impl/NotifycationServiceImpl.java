package service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import domain.Notifycation;
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

    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public int addNotifycation(Notifycation notifycation){
        int result;

        result = notifycationMapper.insert(notifycation);

        return result;
    }
}
