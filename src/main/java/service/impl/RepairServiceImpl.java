package service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import domain.Repair;
import jedis.JedisClient;
import mapper.RepairMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.RepairService;
import util.JsonUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("RepairServiceImpl")
public class RepairServiceImpl implements RepairService {

    @Resource
    RepairMapper repairMapper;

    @Resource
    private JedisClient jedisClient;

    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public PageInfo<Repair> displayAll(Integer page, Integer pageSize){

//        //从缓存中获取json数据
//        String json=jedisClient.get("AllRepairPage:"+page);
//        //判断缓存中是否获取数据成功
//        if(StringUtils.isNotBlank(json)){
//            //成功，讲json数据转为java集合，返回Java集合数据
//            return JsonUtils.jsonToPojo(json,PageInfo.class);
//        }

        //指定分页信息，分页的插件类，PageHelper
        PageHelper.startPage(page,pageSize);

        //定义商品列表
        List<Repair> repairList=this.repairMapper.selectAll();

        PageInfo<Repair> pageInfo=new PageInfo<Repair>(repairList);

        if(page<1){
            pageInfo.setPageNum(1);
        }
        if(page>pageInfo.getPages()){
            pageInfo.setPageNum(pageInfo.getPages());
        }

//        jedisClient.set("AllRepairPage:"+page,JsonUtils.objectToJson(pageInfo));

        return pageInfo;
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public PageInfo<Repair> displayDone(Integer page, Integer pageSize){

//        //从缓存中获取json数据
//        String json=jedisClient.get("DoneRepairPage:"+page);
//        //判断缓存中是否获取数据成功
//        if(StringUtils.isNotBlank(json)){
//            //成功，讲json数据转为java集合，返回Java集合数据
//            return JsonUtils.jsonToPojo(json,PageInfo.class);
//        }

        //指定分页信息，分页的插件类，PageHelper
        PageHelper.startPage(page,pageSize);

        //定义商品列表
        List<Repair> repairList=this.repairMapper.selectDone();

        PageInfo<Repair> pageInfo=new PageInfo<Repair>(repairList);

        if(page<1){
            pageInfo.setPageNum(1);
        }
        if(page>pageInfo.getPages()){
            pageInfo.setPageNum(pageInfo.getPages());
        }

//        jedisClient.set("DoneRepairPage:"+page,JsonUtils.objectToJson(pageInfo));

        return pageInfo;
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public PageInfo<Repair> displayUndo(Integer page, Integer pageSize){

//        //从缓存中获取json数据
//        String json=jedisClient.get("UndoRepairPage:"+page);
//        //判断缓存中是否获取数据成功
//        if(StringUtils.isNotBlank(json)){
//            //成功，讲json数据转为java集合，返回Java集合数据
//            return JsonUtils.jsonToPojo(json,PageInfo.class);
//        }

        //指定分页信息，分页的插件类，PageHelper
        PageHelper.startPage(page,pageSize);

        //定义商品列表
        List<Repair> repairList=this.repairMapper.selectUndo();

        PageInfo<Repair> pageInfo=new PageInfo<Repair>(repairList);

        if(page<1){
            pageInfo.setPageNum(1);
        }
        if(page>pageInfo.getPages()){
            pageInfo.setPageNum(pageInfo.getPages());
        }

//        jedisClient.set("UndoRepairPage:"+page,JsonUtils.objectToJson(pageInfo));

        return pageInfo;
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public PageInfo<Repair> displayRoom(Integer page, Integer pageSize, Integer buildid, Integer roomid){

//        //从缓存中获取json数据
//        String json=jedisClient.get("RoomRepairPage:"+buildid+":"+roomid+":"+page);
//        //判断缓存中是否获取数据成功
//        if(StringUtils.isNotBlank(json)){
//            //成功，讲json数据转为java集合，返回Java集合数据
//            return JsonUtils.jsonToPojo(json,PageInfo.class);
//        }

        //指定分页信息，分页的插件类，PageHelper
        PageHelper.startPage(page,pageSize);

        //定义商品列表
        List<Repair> repairList=this.repairMapper.selectByRoom(buildid, roomid);

        PageInfo<Repair> pageInfo=new PageInfo<Repair>(repairList);

        if(page<1){
            pageInfo.setPageNum(1);
        }
        if(page>pageInfo.getPages()){
            pageInfo.setPageNum(pageInfo.getPages());
        }

//        jedisClient.set("RoomRepairPage:"+buildid+":"+roomid+":"+page,JsonUtils.objectToJson(pageInfo));

        return pageInfo;
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public boolean changeStatus(Integer repairid, Integer repairstatus, String repairperson, Date repairdateend){
        Repair repair=this.repairMapper.selectByPrimaryKey(repairid);
        repair.setRepairstatus(repairstatus);
        repair.setRepairdateend(repairdateend);
        repair.setRepairperson(repairperson);
        int result= this.repairMapper.updateByPrimaryKey(repair);
        if(result >=1)
            return true;
        else
            return false;
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public PageInfo<Repair> RoomAll(Integer buildid, Integer roomid, Integer page, Integer pageSize){

        //指定分页信息，分页的插件类，PageHelper
        PageHelper.startPage(page,pageSize);

        List<Repair> repairList=this.repairMapper.selectByRoom(buildid, roomid);

        PageInfo<Repair> pageInfo=new PageInfo<Repair>(repairList);

        if(page<1){
            pageInfo.setPageNum(1);
        }
        if(page>pageInfo.getPages()){
            pageInfo.setPageNum(pageInfo.getPages());
        }

        return pageInfo;
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public PageInfo<Repair> RoomDone(Integer buildid, Integer roomid, Integer page, Integer pageSize){

        //指定分页信息，分页的插件类，PageHelper
        PageHelper.startPage(page,pageSize);

        List<Repair> repairList=this.repairMapper.selectByRoomDone(buildid, roomid);

        PageInfo<Repair> pageInfo=new PageInfo<Repair>(repairList);

        if(page<1){
            pageInfo.setPageNum(1);
        }
        if(page>pageInfo.getPages()){
            pageInfo.setPageNum(pageInfo.getPages());
        }

        return pageInfo;
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public PageInfo<Repair> RoomUndo(Integer buildid, Integer roomid, Integer page, Integer pageSize){

        //指定分页信息，分页的插件类，PageHelper
        PageHelper.startPage(page,pageSize);

        List<Repair> repairList=this.repairMapper.selectByRoomUndo(buildid, roomid);

        PageInfo<Repair> pageInfo=new PageInfo<Repair>(repairList);

        if(page<1){
            pageInfo.setPageNum(1);
        }
        if(page>pageInfo.getPages()){
            pageInfo.setPageNum(pageInfo.getPages());
        }

        return pageInfo;
    }
    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public Repair Detail(Integer repairid){

        Repair repair = this.repairMapper.selectByPrimaryKey(repairid);

        return repair;
    }
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public int addRepair(Repair repair){

        int result= this.repairMapper.insert(repair);

        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public int Update(Repair repair){

        int result= this.repairMapper.updateByPrimaryKeySelective(repair);

        return result;
    }
}
