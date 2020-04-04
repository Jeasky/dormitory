package mapper;

import domain.Repair;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RepairMapper {

    int deleteByPrimaryKey(Integer repairid);

    int insert(Repair record);

    int insertSelective(Repair record);

    Repair selectByPrimaryKey(Integer repairid);

    List<Repair> selectByRoom(@Param("buildid")Integer buildid, @Param("roomid")Integer roomid);

    List<Repair> selectAll();

    List<Repair> selectDone();

    List<Repair> selectUndo();

    int updateByPrimaryKeySelective(Repair record);

    int updateByPrimaryKey(Repair record);
}