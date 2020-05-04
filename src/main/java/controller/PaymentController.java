package controller;

import com.github.pagehelper.PageInfo;
import domain.Payment;
import domain.Paymentfile;
import domain.User;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import service.PaymentService;
import service.RedisService;
import util.InsertByExcel;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;
import java.util.List;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    @Resource
    PaymentService paymentService;

    @Resource
    RedisService redisService;

    //宿管页面展示水电账单
    @RequestMapping("list")
    public ModelAndView search(Integer buildid, Integer page, Integer pageSize, HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        User user = new User();
        //获取Cookie
        Cookie[] cookies = request.getCookies();

        //对cookies判断
        if (cookies != null) {

            //遍历
            for (Cookie cookie : cookies) {

                //获取cookie的键
                String name = cookie.getName();

                //对name进行判断，name是否就是我们需要的token
                if ("token".equals(name)) {

                    //从把cookies中name对应的值取出来
                    //这个值就是token
                    String token = cookie.getValue();     //这个tokne还是redis的key

                    //根据这个token获取到user
                    //从缓存中取出这个数据
                    user = this.redisService.getUser(token);
                }
            }
        }

        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = 8;
        }

        //调用业务方法
        PageInfo<Paymentfile> pageInfo = this.paymentService.displayAll(buildid, page, pageSize);

        //把数据放入到容器中，以后能够从jsp页面中从这个容器中取出
        modelAndView.addObject("user", user);
        modelAndView.addObject("pageInfo", pageInfo);

        //view 设置返回地址
        modelAndView.setViewName("payment/list");

        return modelAndView;

    }

    @RequestMapping("preadd")
    public ModelAndView preadd(HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        User user = new User();
        //获取Cookie
        Cookie[] cookies = request.getCookies();

        //对cookies判断
        if (cookies != null) {

            //遍历
            for (Cookie cookie : cookies) {

                //获取cookie的键
                String name = cookie.getName();

                //对name进行判断，name是否就是我们需要的token
                if ("token".equals(name)) {

                    //从把cookies中name对应的值取出来
                    //这个值就是token
                    String token = cookie.getValue();     //这个tokne还是redis的key

                    //根据这个token获取到user
                    //从缓存中取出这个数据
                    user = this.redisService.getUser(token);
                }
            }
        }

        modelAndView.addObject("user", user);
        modelAndView.setViewName("payment/add");

        return modelAndView;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView add(@RequestParam("paymentfile") MultipartFile paymentfile,String paymenthead, Integer buildid, HttpServletRequest request) throws Exception {

        ModelAndView modelAndView = new ModelAndView();

        User user = new User();
        //获取Cookie
        Cookie[] cookies = request.getCookies();

        //对cookies判断
        if (cookies != null) {

            //遍历
            for (Cookie cookie : cookies) {

                //获取cookie的键
                String name = cookie.getName();

                //对name进行判断，name是否就是我们需要的token
                if ("token".equals(name)) {

                    //从把cookies中name对应的值取出来
                    //这个值就是token
                    String token = cookie.getValue();     //这个tokne还是redis的key

                    //根据这个token获取到user
                    //从缓存中取出这个数据
                    user = this.redisService.getUser(token);
                }
            }
        }

        Map<String, Object> map = new HashMap<String, Object>();
        if (paymentfile.isEmpty()) {
            map.put("result", "error");
            map.put("msg", "上传文件不能为空");
            System.out.println("=-=-=-=-=-=上传文件不能为空=-=-=-=-=-");
        } else {

            //构造账单文件对象，并插入到水电账单文件表中
            Date date=new Date();
            Paymentfile paymentfile1=new Paymentfile();
            paymentfile1.setBuildid(buildid);
            paymentfile1.setPaymentdate(date);
            paymentfile1.setPaymenthead(paymenthead);

            //调用水电账单文件插入表格业务方法
            int file_result=this.paymentService.insertPaymentFile(paymentfile1);

            //打印是否成功
            System.out.println("插入水电账单文件是否成功："+file_result+"-------------");

            String originalFilename = paymentfile.getOriginalFilename();
            try {
                //创建要上传的路径
                File fdir = new File("./file");
                if (!fdir.exists()) {
                    fdir.mkdirs();
                }

                File file=new File(fdir, originalFilename);

                //文件上传到路径下
                FileUtils.copyInputStreamToFile(paymentfile.getInputStream(), file);

                //表格的各项
                ArrayList<ArrayList<Object>> result = InsertByExcel.readExcel(file);

                //创建水电账单数组
                List<Payment> paymentList=new ArrayList<Payment>();

                //取出当前水电账单文件的项
                Paymentfile paymentfile2=this.paymentService.selectTableID(date);
                Integer pamentfileID=paymentfile2.getPaymenttableid();

                //遍历表格，从第二行开始遍历
                for(int i = 1 ;i < result.size() ;i++){
                    //创建水电账单项
                    Payment payment=new Payment();
                    //赋值
                    payment.setPaymenttableid(pamentfileID);
                    payment.setBuildid(Integer.valueOf(result.get(i).get(0).toString()));
                    payment.setRoomid(Integer.valueOf(result.get(i).get(1).toString()));
                    payment.setElectricityfrom(Double.valueOf(result.get(i).get(2).toString()));
                    payment.setElectricityend(Double.valueOf(result.get(i).get(3).toString()));
                    payment.setElectricityprice(Double.valueOf(result.get(i).get(4).toString()));
                    payment.setElectricitycost(Double.valueOf(result.get(i).get(5).toString()));
                    payment.setWaterfrom(Double.valueOf(result.get(i).get(6).toString()));
                    payment.setWaterend(Double.valueOf(result.get(i).get(7).toString()));
                    payment.setWaterprice(Double.valueOf(result.get(i).get(8).toString()));
                    payment.setWatercost(Double.valueOf(result.get(i).get(9).toString()));
                    payment.setTotal(Double.valueOf(result.get(i).get(10).toString()));

                    //加入List
                    paymentList.add(payment);
                    System.out.printf("\n");
                    for(int j = 0;j<result.get(i).size(); j++){
                        System.out.printf(result.get(i).get(j).toString());
                        System.out.printf("\t");
                    }
                }

                //调用插入水电账单List的业务方法
                int payment_result=this.paymentService.insertPayment(paymentList);

                //打印插入水电账单结果
                System.out.println("水电账单List插入结果："+payment_result+"===========");

                map.put("result", "success");

            } catch (Exception e) {
                map.put("result", "error");
                map.put("msg", e.getMessage());

            }
        }

        //调用业务方法
        PageInfo<Paymentfile> pageInfo = this.paymentService.displayAll(user.getBuildid(), 1, 4);
        modelAndView.addObject("map", map);
        modelAndView.addObject("user", user);
        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.setViewName("payment/list");

        return modelAndView;
    }

    //删除指定水电账单文件
    @RequestMapping("delete")
    public ModelAndView delete(Integer paymenttableid,Integer page, Integer pageSize, HttpServletRequest request) throws Exception{
        ModelAndView modelAndView = new ModelAndView();

        User user = new User();
        //获取Cookie
        Cookie[] cookies = request.getCookies();

        //对cookies判断
        if (cookies != null) {

            //遍历
            for (Cookie cookie : cookies) {

                //获取cookie的键
                String name = cookie.getName();

                //对name进行判断，name是否就是我们需要的token
                if ("token".equals(name)) {

                    //从把cookies中name对应的值取出来
                    //这个值就是token
                    String token = cookie.getValue();     //这个tokne还是redis的key

                    //根据这个token获取到user
                    //从缓存中取出这个数据
                    user = this.redisService.getUser(token);
                }
            }
        }

        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = 8;
        }

        //调用业务方法
        int result=this.paymentService.deletePaymenFile(paymenttableid);

        PageInfo<Paymentfile> pageInfo=this.paymentService.displayAll(user.getBuildid(), page, pageSize);

        //把数据放入到容器中，以后能够从jsp页面中从这个容器中取出
        modelAndView.addObject("user", user);
        modelAndView.addObject("pageInfo", pageInfo);

        //view 设置返回地址
        modelAndView.setViewName("payment/list");

        return modelAndView;
    }

    //宿管页面展示详细的水单账单
    @RequestMapping("detail")
    public ModelAndView detail(Integer paymenttableid, Integer page, Integer pageSize, HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        User user = new User();
        //获取Cookie
        Cookie[] cookies = request.getCookies();

        //对cookies判断
        if (cookies != null) {

            //遍历
            for (Cookie cookie : cookies) {

                //获取cookie的键
                String name = cookie.getName();

                //对name进行判断，name是否就是我们需要的token
                if ("token".equals(name)) {

                    //从把cookies中name对应的值取出来
                    //这个值就是token
                    String token = cookie.getValue();     //这个tokne还是redis的key

                    //根据这个token获取到user
                    //从缓存中取出这个数据
                    user = this.redisService.getUser(token);
                }
            }
        }

        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }

        //调用业务方法
        PageInfo<Payment> pageInfo = this.paymentService.PaymentDetail(paymenttableid, page, pageSize);
        Paymentfile paymentfile=this.paymentService.selectByTableID(paymenttableid);

        //把数据放入到容器中，以后能够从jsp页面中从这个容器中取出
        modelAndView.addObject("user", user);
        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.addObject("paymentfile", paymentfile);

        //view 设置返回地址
        modelAndView.setViewName("payment/detail");

        return modelAndView;

    }

    //宿管搜索通知公告
    @RequestMapping("find")
    public ModelAndView find(Integer buildid, String searchKey, Integer page, Integer pageSize, HttpServletRequest request) throws Exception {

        ModelAndView modelAndView = new ModelAndView();

        User user = new User();
        //获取Cookie
        Cookie[] cookies = request.getCookies();

        //对cookies判断
        if (cookies != null) {

            //遍历
            for (Cookie cookie : cookies) {

                //获取cookie的键
                String name = cookie.getName();

                //对name进行判断，name是否就是我们需要的token
                if ("token".equals(name)) {

                    //从把cookies中name对应的值取出来
                    //这个值就是token
                    String token = cookie.getValue();     //这个tokne还是redis的key

                    //根据这个token获取到user
                    //从缓存中取出这个数据
                    user = this.redisService.getUser(token);
                }
            }
        }

        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = 8;
        }

        PageInfo<Paymentfile> pageInfo = null;

        //调用业务方法
        if (searchKey == "") {
            pageInfo = this.paymentService.displayAll(buildid, page, pageSize);
        } else {
            pageInfo = this.paymentService.search(buildid, searchKey, page, pageSize);
        }

        //把数据放入到容器中，以后能够从jsp页面中从这个容器中取出
        modelAndView.addObject("user", user);
        modelAndView.addObject("pageInfo", pageInfo);

        //view 设置返回地址
        modelAndView.setViewName("payment/list");

        return modelAndView;
    }

    //展示该学生本宿舍的水单账单的详细信息
    @RequestMapping("details")
    public @ResponseBody Payment details(Integer paymenttableid,Integer buildid, Integer roomid, HttpServletRequest request) throws Exception {

        //调用业务方法
        Payment payment = this.paymentService.searchByRoom(paymenttableid, buildid, roomid);

        return payment;

    }

    //在宿学生展示水电账单
    @RequestMapping("display")
    public @ResponseBody PageInfo<Paymentfile> display(Integer buildid, Integer page, Integer pageSize, HttpServletRequest request) throws Exception {

        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = 8;
        }

        //调用业务方法
        PageInfo<Paymentfile> pageInfo = this.paymentService.displayAll(buildid, page, pageSize);

        return pageInfo;

    }

    //在宿学生搜索水电账单
    @RequestMapping("search")
    public @ResponseBody PageInfo<Paymentfile> search(Integer buildid, String searchKey, Integer page, Integer pageSize, HttpServletRequest request) throws Exception {

        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = 8;
        }

        PageInfo<Paymentfile> pageInfo=null;

        //调用业务方法
        if(searchKey == "") {
            pageInfo = this.paymentService.displayAll(buildid, page, pageSize);
        }else{
            pageInfo = this.paymentService.search(buildid,searchKey,page,pageSize);
        }

        return pageInfo;

    }

}
