package mapper;

import domain.Sweep;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SweepMapper {
    int deleteByPrimaryKey(Integer sweepid);

    int insert(Sweep record);

    int insertSelective(Sweep record);

    Sweep selectByPrimaryKey(Integer sweepid);

    List<Sweep> selectByRoom(@Param("buildid") Integer buildid, @Param("roomid")Integer roomid);

    List<Sweep> selectByType(@Param("buildid") Integer buildid, @Param("roomid")Integer roomid, @Param("sweeptype") Integer sweeptype);

    int updateByPrimaryKeySelective(Sweep record);

    int updateByPrimaryKey(Sweep record);
}