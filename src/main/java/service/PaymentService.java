package service;

import com.github.pagehelper.PageInfo;
import domain.Paymentfile;

public interface PaymentService {

    public PageInfo<Paymentfile> displayAll(Integer buildid, Integer page, Integer pageSize);
}
