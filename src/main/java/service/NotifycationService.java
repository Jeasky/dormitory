package service;

import com.github.pagehelper.PageInfo;
import domain.Notifycation;

import java.util.List;

public interface NotifycationService {

    public Notifycation searchById(Integer notifycationId);

    public List<Notifycation> displayByBuild(Integer buildId);

    public PageInfo<Notifycation> displayAll(Integer buildid, Integer page, Integer pageSize);

    public int addNotifycation(Notifycation notifycation);
}
