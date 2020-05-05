package service;

import com.github.pagehelper.PageInfo;
import domain.Message;

import java.util.List;

public interface MessageService {

    public int addMessage(Message message);

    public int delMessage(Integer messageid);

    public int updateMessage(Message message);

    public Message selectByID(Integer messageid);

    public PageInfo<Message> display(Integer buildid, Integer roomid, Integer page, Integer pageSize);

    public PageInfo<Message> search(Integer buildid, Integer roomid, String searchKey, Integer page, Integer pageSize);
}
