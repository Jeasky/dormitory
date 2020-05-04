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

    public PageInfo<Repair> RoomAll(Integer buildid, Integer roomid, Integer page, Integer pageSize);

    public PageInfo<Repair> RoomDone(Integer buildid, Integer roomid, Integer page, Integer pageSize);

    public PageInfo<Repair> RoomUndo(Integer buildid, Integer roomid, Integer page, Integer pageSize);

    public boolean changeStatus(Integer repairid, Integer repairstatus, String repairperson, Date repairdateend);

    public Repair Detail(Integer repairid);

    public int addRepair(Repair repair);

    public int Update(Repair repair);

}
