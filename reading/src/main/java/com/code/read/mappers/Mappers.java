package com.code.read.mappers;

import com.code.read.PageUtil;
import com.code.read.bean.LunBo;
import com.code.read.bean.Record;
import com.code.read.bean.Wengzhang;
import com.code.read.bean.Yonghu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

public interface Mappers {

    Yonghu dl(Yonghu y);

    List<Object> getS(@Param("codeid") Integer codeId);

    List<Object> getSs(@Param("codeid") Integer codeId);

    int zc(Yonghu y);

    Object wy(Yonghu y);

    List<Wengzhang> wengzhang(Record r);

    List<Object> fenlei();

    void add(Wengzhang wz);

    List<Object> fl();

    List<Object> getJx();

    void sc(@Param("jid") String jid);

    void sc2(@Param("wid") String wid);

    void add2(LunBo lb);

    List<Object> alls(@Param("p") PageUtil p, @Param("name") String name);

    Integer count();

    Object huxian(@Param("wid") int wid);

    void xg(Wengzhang w);

}
