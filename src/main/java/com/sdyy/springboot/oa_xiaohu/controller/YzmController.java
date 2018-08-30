package com.sdyy.springboot.oa_xiaohu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.Random;

/**
 * @author xiaohu
 * @createDate 2018-08-30 19:59
 */
@Controller
public class YzmController {
    /**
     * 字母加数字组合验证码，登陆
     */
    @RequestMapping("/yzm")
    public void Yzm(HttpSession session, PrintWriter out){
        char[] codeSequence = { 'A','B','C','D', 'E', 'F', 'G', 'H', 'I', 'J',
                'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        // 创建一个随机数生成器类
        Random random = new Random();

        // randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
        StringBuffer randomCode = new StringBuffer();

        // 随机产生codeCount数字的验证码。
        for (int i = 0; i < 4; i++) {
            // 得到随机产生的验证码数字。
            randomCode.append(codeSequence[random.nextInt(36)]);
        }
        // 将四位数字的验证码保存到Session中。
        session.setAttribute("validateCode", randomCode.toString());
        out.print(randomCode.toString());
    }
/**
 * 验证判断 ---登陆
 */
    @RequestMapping("/loginyz")
    public void loginyz(HttpServletRequest request,PrintWriter out){
        boolean flag = true;
        String yzm = request.getParameter("yzm");
        System.out.println("yzm================="+yzm);
        HttpSession session = request.getSession();
        String imgyzm = session.getAttribute("validateCode").toString();
        if(!yzm.equalsIgnoreCase(imgyzm)){
             flag = false;
             out.print(false);
        }else{
            flag = true;
            out.print(flag);
        }
    }

    /**
     * 数字计算型验证码
     */
    @RequestMapping("/numyzm")
    public void numyzm(HttpSession session,PrintWriter out){
        // 创建一个随机数生成器类
        Random random = new Random();

        // randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
        StringBuffer randomCode = new StringBuffer();

        // 随机产生两个数字n1、n2
        int n1=(int) (Math.random()*10);
        int n2=(int) (Math.random()*10);
        String h=n1+"+"+n2+"=?";
        String c=n1+"-"+n2+"=?";
        String j=n1+"*"+n2+"=?";
        String s=n1+"/"+n2+"=?";
        String[] codeFh = {h,c,j,s};
        randomCode.append(codeFh[random.nextInt(4)]);
        String num=randomCode.toString();
        int result=0;
        //System.out.println("n1+n2="+(n1+n2));
        //判断随机式子和和、差、积、商、哪个式子相同，并计算出答案存在result中
        if(num.equals(h)){
            result=(n1+n2);
        }else if(num.equals(c)){
            result=(n1-n2);
        }else if(num.equals(j)){
            result=(n1*n2);
        }else if(num.equals(s)){
            result=(n1/n2);
        }else{
            System.out.println("----error------");
        }
        // System.out.println(result+"============"+num);
        // 将随机式子保存到Session中。
        session.setAttribute("validateCode", num);
        //将正确答案也保存在session中
        session.setAttribute("result", result);
        //返回式子
        out.print(num);
    }

    /**
     * 计算验证码 验证------注册
     */
    @RequestMapping("/regyzm")
    public void regyzm(HttpServletRequest request,PrintWriter out){
        boolean flag = true;
        int numyzm =Integer.parseInt(request.getParameter("numyzm"));
        HttpSession session = request.getSession();
        int result = Integer.parseInt(session.getAttribute("result").toString());
        System.out.println(numyzm + "=============="+ result);
        if(result != numyzm){
            flag = false;
            out.print(flag);
        }else{
            out.print(flag);
        }
    }
}
