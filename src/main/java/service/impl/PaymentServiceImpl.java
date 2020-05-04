package service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import domain.Payment;
import domain.Paymentfile;
import mapper.PaymentMapper;
import mapper.PaymentfileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.PaymentService;

import java.util.Date;
import java.util.List;

@Service("PaymentServiceImpl")
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentfileMapper paymentfileMapper;
    @Autowired
    PaymentMapper paymentMapper;

    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public PageInfo<Paymentfile> displayAll(Integer buildid, Integer page, Integer pageSize){
        //指定分页信息，分页的插件类，PageHelper
        PageHelper.startPage(page,pageSize);

        List<Paymentfile> list=paymentfileMapper.selectByBuildid(buildid);

        PageInfo<Paymentfile> pageInfo=new PageInfo<>(list);

        if(page<1){
            pageInfo.setPageNum(1);
        }
        if(page>pageInfo.getPages()){
            pageInfo.setPageNum(pageInfo.getPages());
        }

        return pageInfo;
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public PageInfo<Paymentfile> search(Integer buildid, String searchKey, Integer page, Integer pageSize){
        //指定分页信息，分页的插件类，PageHelper
        PageHelper.startPage(page,pageSize);

        List<Paymentfile> list=paymentfileMapper.selectByKey(buildid,searchKey);

        PageInfo<Paymentfile> pageInfo=new PageInfo<>(list);

        if(page<1){
            pageInfo.setPageNum(1);
        }
        if(page>pageInfo.getPages()){
            pageInfo.setPageNum(pageInfo.getPages());
        }

        return pageInfo;
    }


    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public int insertPaymentFile(Paymentfile paymentfile){

        int result=paymentfileMapper.insert(paymentfile);

        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public int insertPayment(List<Payment> payments){

        int result=paymentMapper.insert(payments);

        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public Paymentfile selectTableID(Date date){
        Paymentfile paymentfile=paymentfileMapper.selectByDate(date);

        return paymentfile;
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public Paymentfile selectByTableID(Integer pamenttableid){
        Paymentfile paymentfile=paymentfileMapper.selectByPrimaryKey(pamenttableid);

        return paymentfile;
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public int deletePaymenFile(Integer paymenttableid){
        int result = paymentfileMapper.deleteByPrimaryKey(paymenttableid);

        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public PageInfo<Payment> PaymentDetail(Integer paymenttableid, Integer page, Integer pageSize){
        //指定分页信息，分页的插件类，PageHelper
        PageHelper.startPage(page,pageSize);

        List<Payment> list=paymentMapper.selectByTableID(paymenttableid);

        PageInfo<Payment> pageInfo=new PageInfo<>(list);

        if(page<1){
            pageInfo.setPageNum(1);
        }
        if(page>pageInfo.getPages()){
            pageInfo.setPageNum(pageInfo.getPages());
        }

        return pageInfo;
    }
    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public Payment searchByRoom(Integer paymenttableid, Integer buildid, Integer roomid){

        Payment payment=paymentMapper.selectByRoomID(paymenttableid, buildid, roomid);

        return payment;
    }

}
