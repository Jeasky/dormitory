package mapper;

import domain.Cost;

import java.util.List;

public interface CostMapper {

    int insert(Cost record);

    int insertSelective(Cost record);

    List<Cost> selectByRoom(Integer accountid);
}