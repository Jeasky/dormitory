package service;

import com.github.pagehelper.PageInfo;
import domain.Sweep;


public interface SweepService {
    public int addSweep(Sweep sweep);

    public int deleteSweep(Integer sweepid);

    public int updateSweep(Sweep sweep);

    public Sweep selectByID(Integer sweepid);

    public PageInfo<Sweep> selectByRoom(Integer buildid, Integer roomid, Integer page, Integer pageSize);
}
