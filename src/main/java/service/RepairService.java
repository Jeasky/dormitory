package service;

import com.github.pagehelper.PageInfo;
import domain.Repair;

import java.util.Date;
import java.util.List;

public interface RepairService {

    public PageInfo<Repair> displayAll(Integer page, Integer pageSize);

    public PageInfo<Repair> displayDone(Integer page, Integer pageSize);

    public PageInfo<Repair> displayUndo(Integer page, Integer pageSize);

    public PageInfo<Repair> displayRoom(Integer page, Integer pageSize, Integer buildid, Integer roomid);

    public boolean changeStatus(Integer repairid, Integer repairstatus, String repairperson, Date repairdateend);
}
