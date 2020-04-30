package mapper;

import domain.Paymentfile;

import java.util.List;

public interface PaymentfileMapper {
    int deleteByPrimaryKey(Integer paymenttableid);

    int insert(Paymentfile record);

    int insertSelective(Paymentfile record);

    Paymentfile selectByPrimaryKey(Integer paymenttableid);

    List<Paymentfile> selectByBuildid(Integer buildid);

    int updateByPrimaryKeySelective(Paymentfile record);

    int updateByPrimaryKey(Paymentfile record);
}