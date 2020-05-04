package mapper;

import domain.Payment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PaymentMapper {
    int deleteByPrimaryKey(Integer paymentid);

    int insert(List<Payment> list);

    int insertSelective(Payment record);

    Payment selectByPrimaryKey(Integer paymentid);

    Payment selectByRoomID(@Param("paymenttableid") Integer paymenttableid, @Param("buildid") Integer buildid, @Param("roomid") Integer roomid);

    List<Payment> selectByTableID(Integer paymenttableid);

    int updateByPrimaryKeySelective(Payment record);

    int updateByPrimaryKey(Payment record);
}