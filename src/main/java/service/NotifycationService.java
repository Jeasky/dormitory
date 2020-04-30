package service;

import com.github.pagehelper.PageInfo;
import domain.Notifycation;
import exception.AddException;
import exception.DelException;
import exception.UpdateException;

import java.util.List;

public interface NotifycationService {

    public Notifycation searchById(Integer notifycationId);

    public List<Notifycation> displayByBuild(Integer buildId);

    public PageInfo<Notifycation> displayAll(Integer buildid, Integer page, Integer pageSize);

    public PageInfo<Notifycation> search(Integer buildid,String searchKey, Integer page, Integer pageSize);

    public int addNotifycation(Notifycation notifycation) throws AddException;

    public int delNotifycation(int notifycationid) throws DelException;

    public int updateNotifycation(Notifycation notifycation) throws UpdateException;
}
