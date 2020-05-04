package mapper;

import domain.Payment;
import domain.Paymentfile;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface PaymentfileMapper {
    int deleteByPrimaryKey(Integer paymenttableid);

    int insert(Paymentfile record);

    int insertSelective(Paymentfile record);

    Paymentfile selectByPrimaryKey(Integer paymenttableid);

    List<Paymentfile> selectByBuildid(Integer buildid);

    List<Paymentfile> selectByKey(@Param(value = "buildid") Integer buildid, @Param("searchKey") String searchKey);

    int updateByPrimaryKeySelective(Paymentfile record);

    int updateByPrimaryKey(Paymentfile record);

    Paymentfile selectByDate(Date date);
}