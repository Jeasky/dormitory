package controller;

import com.github.pagehelper.PageInfo;
import domain.Paymentfile;
import domain.User;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import service.PaymentService;
import service.RedisService;
import util.InsertByExcel;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
            pageSize = 3;
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

    @RequestMapping(value = "add",headers = "content-type=multipart/*", method = RequestMethod.POST)
    public ModelAndView add(@RequestParam(value = "paymentfile", required = false) MultipartFile paymentfile, HttpServletRequest request) throws Exception {

        System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
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
        } else {
            String originalFilename = paymentfile.getOriginalFilename();
            String fileBaseName = FilenameUtils.getBaseName(originalFilename);
            Date now = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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

                //遍历表格，从第二行开始遍历
                for(int i = 0 ;i < result.size() ;i++){
                    System.out.printf("\n");
                    for(int j = 0;j<result.get(i).size(); j++){
                        System.out.printf(result.get(i).get(j).toString());
                        System.out.printf("\t");
                    }
                }

                map.put("result", "success");

            } catch (Exception e) {
                map.put("result", "error");
                map.put("msg", e.getMessage());

            }
        }

        //调用业务方法
        PageInfo<Paymentfile> pageInfo = this.paymentService.displayAll(4, 1, 4);
        modelAndView.addObject("map", map);
        modelAndView.addObject("user", user);
        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.setViewName("payment/list");

        return modelAndView;
    }

}
