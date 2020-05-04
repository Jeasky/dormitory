package service;

import com.github.pagehelper.PageInfo;
import domain.Payment;
import domain.Paymentfile;

import java.util.Date;
import java.util.List;

public interface PaymentService {

    public PageInfo<Paymentfile> displayAll(Integer buildid, Integer page, Integer pageSize);

    public PageInfo<Paymentfile> search(Integer buildid,String searchKey, Integer page, Integer pageSize);

    public Payment searchByRoom(Integer paymenttableid, Integer buildid, Integer roomid);

    public int insertPaymentFile(Paymentfile paymentfile);

    public int insertPayment(List<Payment> payments);

    public Paymentfile selectTableID(Date date);

    public Paymentfile selectByTableID(Integer paymenttableid);

    public int deletePaymenFile(Integer paymenttableid);

    public PageInfo<Payment> PaymentDetail(Integer paymenttableid, Integer page, Integer pageSize);
}
