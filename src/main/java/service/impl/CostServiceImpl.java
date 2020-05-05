package service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import domain.Cost;
import mapper.CostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.CostService;

import java.util.List;

@Service("CostServiceImpl")
public class CostServiceImpl implements CostService {

    @Autowired
    CostMapper costMapper;

    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public int addCost(Cost cost){

        int result = this.costMapper.insert(cost);

        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public int deleteCost(Integer costid){

        int result = this.costMapper.deleteByPrimaryKey(costid);

        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public int updateCost(Cost cost){

        int result = this.costMapper.updateByPrimaryKey(cost);

        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public Cost seleteByCostID(Integer costid){

        Cost cost = this.costMapper.selectByPrimaryKey(costid);

        return cost;
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public PageInfo<Cost> seleteByRoom(Integer accountid, Integer page, Integer pageSize){

        //指定分页信息，分页的插件类，PageHelper
        PageHelper.startPage(page,pageSize);

        List<Cost> list=this.costMapper.selectByAccountID(accountid);

        PageInfo<Cost> pageInfo=new PageInfo<>(list);

        if(page<1){
            pageInfo.setPageNum(1);
        }
        if(page>pageInfo.getPages()){
            pageInfo.setPageNum(pageInfo.getPages());
        }

        return pageInfo;
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public PageInfo<Cost> seleteByKey(Integer accountid, String searchKey, Integer page, Integer pageSize){
        //指定分页信息，分页的插件类，PageHelper
        PageHelper.startPage(page,pageSize);

        List<Cost> list=this.costMapper.selectByAccountID(accountid);

        PageInfo<Cost> pageInfo=new PageInfo<>(list);

        if(page<1){
            pageInfo.setPageNum(1);
        }
        if(page>pageInfo.getPages()){
            pageInfo.setPageNum(pageInfo.getPages());
        }

        return pageInfo;
    }
}
