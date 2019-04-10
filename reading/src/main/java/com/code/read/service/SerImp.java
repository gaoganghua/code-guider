package com.code.read.service;

import com.code.read.PageUtil;
import com.code.read.bean.LunBo;
import com.code.read.bean.Record;
import com.code.read.bean.Wengzhang;
import com.code.read.bean.Yonghu;
import com.code.read.mappers.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SerImp implements SerItf {
    @Autowired
    Mappers mp;

    @Override
    public Map<String, Object> dl(Yonghu y) {
        Map<String, Object> map = new HashMap<String, Object>();
        // TODO Auto-generated method stub
        if (mp.dl(y) != null) {
            map.put("msg", true);
            map.put("yh", mp.dl(y));
        }

        return map;
    }

    @Override
    public List<Object> getS(Integer codeId) {
        if (codeId == null) {
            codeId = 0;
        }

        return mp.getS(codeId);
    }

    @Override
    public List<Object> getSs(Integer codeId) {
        if (codeId == null) {
            codeId = 0;
        }

        return mp.getSs(codeId);
    }

    @Override
    public boolean zc(Yonghu y) {
        // TODO Auto-generated method stub
        if (mp.zc(y) != 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean wy(Yonghu y) {
        // TODO Auto-generated method stub
        if (mp.wy(y) != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Wengzhang> wengzhang(Record r) {
        // TODO Auto-generated method stub
        return mp.wengzhang(r);
    }

    @Override
    public void add(Wengzhang wz) {
        // TODO Auto-generated method stub
        mp.add(wz);
    }

    @Override
    public List<Object> fl() {
        // TODO Auto-generated method stub
        return mp.fl();
    }

    @Override
    public List<Object> getJx() {
        // TODO Auto-generated method stub
        return mp.getJx();
    }

    @Override
    public List<Object> lh() {
        // TODO Auto-generated method stub
        return mp.getJx();
    }

    @Override
    public boolean sc(String jid) {
        // TODO Auto-generated method stub

        mp.sc(jid);

        return true;
    }

    @Override
    public boolean sc2(String wid) {
        // TODO Auto-generated method stub
        mp.sc2(wid);

        return true;
    }

    @Override
    public void add2(LunBo lb) {
        // TODO Auto-generated method stub
        mp.add2(lb);
    }

    @Override
    public List<Object> alls(PageUtil p, String name) {
        // TODO Auto-generated method stub
        return mp.alls(p, name);
    }

    @Override
    public Integer count() {
        // TODO Auto-generated method stub
        return mp.count();
    }

    @Override
    public Object huxian(int wid) {
        // TODO Auto-generated method stub
        return mp.huxian(wid);
    }

    @Override
    public void xg(Wengzhang w) {
        // TODO Auto-generated method stub
        mp.xg(w);
    }


}
