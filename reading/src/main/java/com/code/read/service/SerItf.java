package com.code.read.service;

import com.code.read.PageUtil;
import com.code.read.bean.LunBo;
import com.code.read.bean.Record;
import com.code.read.bean.Wengzhang;
import com.code.read.bean.Yonghu;

import java.util.List;
import java.util.Map;

public interface SerItf {


    Map<String, Object> dl(Yonghu y);

    List<Object> getS(Integer codeId);

    List<Object> getSs(Integer codeId);

    boolean zc(Yonghu y);

    boolean wy(Yonghu y);

    List<Wengzhang> wengzhang(Record r);

    void add(Wengzhang wz);

    List<Object> fl();

    List<Object> getJx();

    List<Object> lh();

    boolean sc(String jid);

    boolean sc2(String wid);

    void add2(LunBo lb);

    List<Object> alls(PageUtil p, String name);

    Integer count();

    Object huxian(int wid);

    void xg(Wengzhang w);

}
