package service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import domain.Sweep;
import mapper.SweepMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.SweepService;

import java.util.List;

@Service("SweepServiceImpl")
public class SweepServiceImpl implements SweepService {

    @Autowired
    SweepMapper sweepMapper;

    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public int addSweep(Sweep sweep){
        int result = this.sweepMapper.insert(sweep);

        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public int deleteSweep(Integer sweepid){
        int result = this.sweepMapper.deleteByPrimaryKey(sweepid);

        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public int updateSweep(Sweep sweep){
        int result = this.sweepMapper.updateByPrimaryKey(sweep);

        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public Sweep selectByID(Integer sweepid){
        Sweep sweep = new Sweep();

        sweep = this.sweepMapper.selectByPrimaryKey(sweepid);

        return sweep;
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public PageInfo<Sweep> selectByRoom(Integer buildid, Integer roomid, Integer page, Integer pageSize){
        //指定分页信息，分页的插件类，PageHelper
        PageHelper.startPage(page,pageSize);

        List<Sweep> list=this.sweepMapper.selectByRoom(buildid, roomid);

        PageInfo<Sweep> pageInfo=new PageInfo<>(list);

        if(page<1){
            pageInfo.setPageNum(1);
        }
        if(page>pageInfo.getPages()){
            pageInfo.setPageNum(pageInfo.getPages());
        }

        return pageInfo;
    }
}
