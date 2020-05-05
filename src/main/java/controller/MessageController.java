package controller;


import com.github.pagehelper.PageInfo;
import domain.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.MessageService;

import javax.annotation.Resource;
import java.util.Date;

@Controller
@RequestMapping("/message")
public class MessageController {

    //属性
    @Resource
    MessageService messageService;

    @RequestMapping("displayroom")
    public @ResponseBody PageInfo<Message> displayroom(Integer buildid, Integer roomid, Integer page, Integer pageSize) {

        if (page == null) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = 8;
        }

        PageInfo<Message> pageInfo = this.messageService.display(buildid, roomid, page, pageSize);

        return pageInfo;
    }

    @RequestMapping("addmessage")
    public @ResponseBody int addmessage(Integer buildid, Integer roomid, String messagehead, String messagecontent, String messageperson) {

        Message message=new Message();
        message.setBuildid(buildid);
        message.setRoomid(roomid);
        message.setMessagehead(messagehead);
        message.setMessagecontent(messagecontent);
        message.setMessageperson(messageperson);
        message.setMessagedate(new Date());

        int result = this.messageService.addMessage(message);

        return result;
    }

    @RequestMapping("search")
    public @ResponseBody PageInfo<Message> search(Integer buildid, Integer roomid, String searchKey, Integer page, Integer pageSize){

        PageInfo<Message> pageInfo= this.messageService.search(buildid, roomid, searchKey, page, pageSize);

        return pageInfo;
    }

    @RequestMapping("delete")
    public @ResponseBody int delete(Integer messageid){

        int result= this.messageService.delMessage(messageid);

        return result;
    }

    @RequestMapping("update")
    public @ResponseBody int update(Integer messageid, Integer buildid, Integer roomid, String messagehead, String messagecontent, String messageperson){

        Message message = new Message();
        message.setMessageid(messageid);
        message.setBuildid(buildid);
        message.setRoomid(roomid);
        message.setMessagehead(messagehead);
        message.setMessagecontent(messagecontent);
        message.setMessageperson(messageperson);
        message.setMessagedate(new Date());

        int result= this.messageService.updateMessage(message);

        return result;
    }
}
