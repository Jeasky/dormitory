package controller;

import com.github.pagehelper.PageInfo;
import domain.Account;
import domain.Cost;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.AccountService;
import service.CostService;

import javax.annotation.Resource;
import java.util.Date;

@Controller
@RequestMapping("/cost")
public class CostController {

    //属性
    @Resource
    AccountService accountService;

    @Resource
    CostService costService;

    @RequestMapping("display")
    public @ResponseBody PageInfo<Cost> display(Integer buildid, Integer roomid, Integer page, Integer pageSize) {

        if (page == null) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = 8;
        }

        Integer accountid = buildid * 10000 +  roomid;

        PageInfo<Cost> pageInfo = this.costService.seleteByRoom(accountid, page, pageSize);

        return pageInfo;
    }

    @RequestMapping("getcost")
    public @ResponseBody Cost getcost(Integer costid) {

        Cost cost = this.costService.seleteByCostID(costid);

        return cost;
    }

    @RequestMapping("displayaccount")
    public @ResponseBody Account displayaccount(Integer buildid, Integer roomid) {

        Integer accountid = buildid * 10000 +  roomid;

        Account account = this.accountService.display(accountid);

        return account;
    }

    @RequestMapping("add")
    public @ResponseBody int add(Integer buildid, Integer roomid, Integer costtype, Float amount, String costdes){

        Integer accountid = buildid * 10000 +  roomid;

        Cost cost = new Cost();
        cost.setAccountid(accountid);
        cost.setCosttype(costtype);
        cost.setCostdes(costdes);
        cost.setCostdate(new Date());
        cost.setAmount(amount);

        //从账户中取出账户信息
        Account account =null;
        account= this.accountService.display(accountid);
        //判断账户是否存在，不存在则创建
        if(account == null){
            account.setAccountid(accountid);
            int creat_account = this.accountService.addAccount(account);
        }
        //判断收支类型，0则为支出
        if(costtype == 0){
            account.setBalance(account.getBalance() - amount);
        }else{
            account.setBalance(account.getBalance() + amount);
        }
        //修改账户中的余额
        int update_account = this.accountService.updateAccount(account);

        int result = this.costService.addCost(cost);

        return result;
    }

    @RequestMapping("update")
    public @ResponseBody int update(Integer costid, Integer buildid, Integer roomid, Integer costtype, Float amount, String costdes){

        Integer accountid = buildid * 10000 +  roomid;

        Cost cost = new Cost();
        cost.setCostid(costid);
        cost.setAccountid(accountid);
        cost.setCosttype(costtype);
        cost.setCostdes(costdes);
        cost.setCostdate(new Date());
        cost.setAmount(amount);

        //从账户中取出账户信息
        Account account =null;
        account= this.accountService.display(accountid);

        //取出修改之前的收支记录
        Cost cost_before = this.costService.seleteByCostID(costid);

        //判断之前的收支类型,并处理账户中的余额成这笔收支之前的余额
        if(cost_before.getCosttype() == 0){
            account.setBalance(account.getBalance() + cost_before.getAmount());
        }else {
            account.setBalance(account.getBalance() - cost_before.getAmount());
        }
        //判断收支类型，0则为支出 修改账户中的余额成本应该的样子
        if(costtype == 0){
            account.setBalance(account.getBalance() - amount);
        }else{
            account.setBalance(account.getBalance() + amount);
        }
        //修改账户中的余额
        int update_account = this.accountService.updateAccount(account);

        int result = this.costService.updateCost(cost);

        return result;
    }

    @RequestMapping("delete")
    public @ResponseBody int delete(Integer costid){

        Cost cost = this.costService.seleteByCostID(costid);

        //从账户中取出账户信息
        Account account =null;
        account= this.accountService.display(cost.getAccountid());

        //判断之前的收支类型,并处理账户中的余额成这笔收支之前的余额
        if(cost.getCosttype() == 0){
            account.setBalance(account.getBalance() + cost.getAmount());
        }else {
            account.setBalance(account.getBalance() - cost.getAmount());
        }

        //修改账户中的余额
        int update_account = this.accountService.updateAccount(account);

        int result = this.costService.deleteCost(costid);

        return result;
    }

}
