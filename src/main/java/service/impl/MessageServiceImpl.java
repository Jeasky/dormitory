package service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import domain.Message;
import domain.Notifycation;
import mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.MessageService;

import java.util.List;

@Service("MessageServiceImpl")
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageMapper messageMapper;

    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public int addMessage(Message message){

        int result;

        result = this.messageMapper.insert(message);

        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public int delMessage(Integer messageid){
        int result;

        result = this.messageMapper.deleteByPrimaryKey(messageid);

        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public int updateMessage(Message message){
        int result;

        result = this.messageMapper.updateByPrimaryKeySelective(message);

        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public Message selectByID(Integer messageid){
        Message message;

        message = this.messageMapper.selectByPrimaryKey(messageid);

        return message;
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public PageInfo<Message> display(Integer buildid, Integer roomid, Integer page, Integer pageSize){
        //指定分页信息，分页的插件类，PageHelper
        PageHelper.startPage(page,pageSize);

        List<Message> list=messageMapper.selectByRoom(buildid, roomid);

        PageInfo<Message> pageInfo=new PageInfo<>(list);

        if(page<1){
            pageInfo.setPageNum(1);
        }
        if(page>pageInfo.getPages()){
            pageInfo.setPageNum(pageInfo.getPages());
        }

        return pageInfo;
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public PageInfo<Message> search(Integer buildid, Integer roomid, String searchKey, Integer page, Integer pageSize){
        //指定分页信息，分页的插件类，PageHelper
        PageHelper.startPage(page,pageSize);

        List<Message> list=messageMapper.selectByKey(buildid, roomid, searchKey);

        PageInfo<Message> pageInfo=new PageInfo<>(list);

        if(page<1){
            pageInfo.setPageNum(1);
        }
        if(page>pageInfo.getPages()){
            pageInfo.setPageNum(pageInfo.getPages());
        }

        return pageInfo;
    }
}
