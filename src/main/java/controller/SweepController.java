package controller;


import com.github.pagehelper.PageInfo;
import domain.Sweep;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.SweepService;

import javax.annotation.Resource;

@Controller
@RequestMapping("/sweep")
public class SweepController {

    //属性
    @Resource
    SweepService sweepService;

    @RequestMapping("display")
    public @ResponseBody PageInfo display(Integer buildid, Integer roomid, Integer sweeptype,Integer page, Integer pageSize){
        if (page == null) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = 10;
        }

        PageInfo<Sweep> pageInfo = this.sweepService.selectByType(buildid, roomid, sweeptype, page, pageSize);

        return pageInfo;
    }

    @RequestMapping("add")
    public @ResponseBody int add(Integer sweeptype, String sweepperson, Integer sweeprank, Integer buildid, Integer roomid){

        Sweep sweep = new Sweep();
        sweep.setBuildid(buildid);
        sweep.setRoomid(roomid);
        sweep.setSweepperson(sweepperson);
        sweep.setSweeptype(sweeptype);
        sweep.setSweeprank(sweeprank);

        int result = this.sweepService.addSweep(sweep);

        return result;
    }

    @RequestMapping("delete")
    public @ResponseBody int delete(Integer sweepid){

        int result = this.sweepService.deleteSweep(sweepid);

        return result;
    }

    @RequestMapping("update")
    public @ResponseBody int update(Integer sweepid, Integer sweeptype, String sweepperson, Integer sweeprank, Integer buildid, Integer roomid){

        Sweep sweep = new Sweep();
        sweep.setSweepid(sweepid);
        sweep.setBuildid(buildid);
        sweep.setRoomid(roomid);
        sweep.setSweepperson(sweepperson);
        sweep.setSweeptype(sweeptype);
        sweep.setSweeprank(sweeprank);

        int result = this.sweepService.updateSweep(sweep);

        return result;
    }

}
