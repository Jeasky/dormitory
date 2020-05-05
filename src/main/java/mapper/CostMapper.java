package mapper;

import domain.Cost;

import java.util.List;

public interface CostMapper {
    int deleteByPrimaryKey(Integer costid);

    int insert(Cost record);

    int insertSelective(Cost record);

    Cost selectByPrimaryKey(Integer costid);

    List<Cost> selectByAccountID(Integer accountid);

    int updateByPrimaryKeySelective(Cost record);

    int updateByPrimaryKey(Cost record);
}