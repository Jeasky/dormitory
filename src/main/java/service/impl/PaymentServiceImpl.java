package service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import domain.Paymentfile;
import mapper.PaymentfileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.PaymentService;

import java.util.List;

@Service("PaymentServiceImpl")
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentfileMapper paymentfileMapper;

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


}
