package com.code.read.controller;

import com.code.read.PageUtil;
import com.code.read.bean.LunBo;
import com.code.read.bean.Record;
import com.code.read.bean.Wengzhang;
import com.code.read.bean.Yonghu;
import com.code.read.service.SerItf;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class MyController {
    @Autowired
    SerItf ser;

    @RequestMapping("xg")
    @ResponseBody
    public boolean xg(Wengzhang w) {

        ser.xg(w);

        return true;
    }

    @RequestMapping("huxian")
    @ResponseBody
    public Object huixian(int wid) {
        return ser.huxian(wid);
    }

    @RequestMapping("lunbo")
    public String lunbo(HttpServletRequest request,
                        HttpServletResponse response,
                        @RequestParam("myFile") MultipartFile myFile, LunBo lb) {
        Map<String, Object> json = new HashMap<String, Object>();
        try {
            DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String name = df.format(new Date());

            Random r = new Random();
            for (int i = 0; i < 3; i++) {
                name += r.nextInt(10);
            }
            //
            String ext = FilenameUtils.getExtension(myFile
                    .getOriginalFilename());
            String url = request.getSession().getServletContext()
                    .getRealPath("img");
            String path = "/" + name + "." + ext;
            File file = new File(url);
            if (!file.exists()) {
                file.mkdirs();
            }
            String pic = name + "." + ext;
            ;
            lb.setJpic(pic);
            ser.add2(lb);
            myFile.transferTo(new File(url + "." + path));
            json.put("success", "img" + path);
            json.put("msg", "ok");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:ht.do?name=";
    }

    @RequestMapping("ht")
    public String ht(HttpServletRequest request, String page, String name)
            throws UnsupportedEncodingException {
        Record r = new Record();
        PageUtil p = new PageUtil(page, 4, ser.count());
        List<Object> lh = ser.lh();
        String name2 = "";
        name2 = URLDecoder.decode(name, "UTF-8");
        List<Object> all = ser.alls(p, name2);
        request.setAttribute("all", all);
        request.setAttribute("lh", lh);
        request.setAttribute("p", p);
        return "WEB-INF/ht-jsp/ht";

    }

    @RequestMapping("tisp")
    public String tisp() {
        return "pic";
    }

    @RequestMapping("scs")
    @ResponseBody
    public boolean sc2(String wid) {
        return ser.sc2(wid);
    }

    @RequestMapping("sc")
    @ResponseBody
    public boolean sc(String jid) {
        return ser.sc(jid);
    }

    @RequestMapping("lh")
    public String lh(HttpServletRequest request) {
        List<Object> lh = ser.lh();
        request.setAttribute("lh", lh);
        return "WEB-INF/ht-jsp/lh";
    }

    @RequestMapping("fl")
    @ResponseBody
    public List<Object> fl() {

        return ser.fl();
    }

    @RequestMapping("all")
    public String all(HttpServletRequest request) {
        Record r = new Record();
        request.setAttribute("all", ser.wengzhang(r));
        return "WEB-INF/ht-jsp/all";
    }

    @RequestMapping("tj")
    public String updatePhoto(HttpServletRequest request,
                              HttpServletResponse response,
                              @RequestParam("myFile") MultipartFile myFile, Wengzhang wz) {
        Map<String, Object> json = new HashMap<String, Object>();
        try {
            DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String name = df.format(new Date());

            Random r = new Random();
            for (int i = 0; i < 3; i++) {
                name += r.nextInt(10);
            }
            //
            String ext = FilenameUtils.getExtension(myFile
                    .getOriginalFilename());
            String url = request.getSession().getServletContext()
                    .getRealPath("img");
            String path = "/" + name + "." + ext;
            File file = new File(url);
            if (!file.exists()) {
                file.mkdirs();
            }
            String pic = name + "." + ext;
            ;
            wz.setPic(pic);
            ser.add(wz);
            myFile.transferTo(new File(url + "." + path));
            json.put("success", "img" + path);
            json.put("msg", "ok");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:go.do";
    }

    @RequestMapping("jx")
    public String jx(HttpServletRequest request, int fid) {
        Record r = new Record();
        r.setFid(fid);
        request.setAttribute("wengzhang", ser.wengzhang(r));
        return "jx";
    }

    @RequestMapping("go")
    public String go(HttpSession session, HttpServletRequest request, Record r,
                     String yname) throws UnsupportedEncodingException {
        List<Wengzhang> wengzhang = ser.wengzhang(r);
        List<Object> jx = ser.getJx();
        request.setAttribute("wengzhang", wengzhang);
        request.setAttribute("biaoti", r.getBiaoti());
        request.setAttribute("jx", jx);
        if (yname != null) {
            session.setAttribute("name", URLDecoder.decode(yname, "UTF-8"));
        }

        if (wengzhang.size() == 0) {
            request.setAttribute("msg", "抱歉没有此类书!");
        }
        return "shouye";
    }

    @RequestMapping("fenlei")
    @ResponseBody
    public List<Object> fenglei() {
        return ser.fl();
    }

    @RequestMapping("ck")
    public String ck(HttpServletRequest request, Record r, int wid) {

        request.setAttribute("wengzhang", ser.wengzhang(r));
        request.setAttribute("id", wid);
        return "ck";
    }

    @RequestMapping("dl")
    @ResponseBody
    public Map<String, Object> dl(HttpSession session, Yonghu y) {
        session.setAttribute("name", y.getYname());
        return ser.dl(y);
    }

    @RequestMapping("getS")
    @ResponseBody
    public List<Object> getS(Integer codeId) {
        return ser.getS(codeId);
    }

    @RequestMapping("gs")
    @ResponseBody
    public List<Object> getSs(Integer codeId) {
        return ser.getSs(codeId);
    }

    @RequestMapping("zc")
    @ResponseBody
    public boolean zc(Yonghu y) {
        return ser.zc(y);
    }

    @RequestMapping("wy")
    @ResponseBody
    public boolean wy(Yonghu y) {
        return ser.wy(y);
    }
}
