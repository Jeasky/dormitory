package service;

import com.github.pagehelper.PageInfo;
import domain.Cost;

public interface CostService {

    public int addCost(Cost cost);

    public int deleteCost(Integer costid);

    public int updateCost(Cost cost);

    public Cost seleteByCostID(Integer costid);

    public PageInfo<Cost> seleteByRoom(Integer accountid, Integer page, Integer pageSize);

    public PageInfo<Cost> seleteByKey(Integer accountid, String searchKey, Integer page, Integer pageSize);
}
