package com.sdyy.springboot.oa_xiaohu.controller;
import com.sdyy.springboot.oa_xiaohu.beans.Users;
import com.sdyy.springboot.oa_xiaohu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author xiaohu
 * @createDate 2018-08-24 15:53
 */
@Controller
public class IndexController {
    @Autowired
    public UserService userService;

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String serviceName;

//    @GetMapping(value = "/login")
//    public String login(){
//        return "login";
//    }

    @PostMapping("/login")
    @ResponseBody
     public String index(@RequestParam String username,@RequestParam String password,@RequestParam boolean isRememberme,
                         @RequestParam String logintime, Model model,HttpSession session){
        Integer count = 0;
        Map<String,String> paraMap = new HashMap<String,String>();
        paraMap.put("username",username);
        paraMap.put("password",password);
        System.out.println(paraMap);
           Users user = userService.findUseByUsername(paraMap);
        System.out.println(user);
           if(user == null || StringUtils.isEmpty(user)){
               System.out.println("用户不存在"+Integer.toString(count));
               return Integer.toString(count);
           }else{
               System.out.println("用户存在"+Integer.toString(count));
               paraMap.clear();
               paraMap.put("logintime",logintime);
               paraMap.put("userId",user.getUserId());
                count = userService.updateByUser(paraMap);
               session.setAttribute("user",user);
               return Integer.toString(count);
           }
    }

    @PostMapping("/register")
    @ResponseBody
    public String register(@RequestParam String username, @RequestParam String password, @RequestParam String email,
                            @RequestParam String phone, @RequestParam String loginTime, @RequestParam String registTime,
                           @RequestParam  Boolean isChecked, Model model){
        System.out.println(username+password);
        Integer count =0;
     if(isChecked){
        Users user = new Users(username,password,email,phone,loginTime,registTime);
        user.setUserId(UUID.randomUUID().toString());
        System.out.println(user);
         count = userService.addUser(user);
        return  Integer.toString(count);

     }else{
         model.addAttribute("msg","注册失败，请重新注册");
          return Integer.toString(count);
     }
   }

    @PostMapping("/email")
    public String email(@RequestParam String email, @RequestParam String phone,Model model){
         Map<String,String> paraMap = new HashMap<String,String>();
         paraMap.put("email",email);
         paraMap.put("phone",phone);
         Users user = userService.findUseByUsername(paraMap);
   if(user != null || !StringUtils.isEmpty(user)){
        MimeMessage message = null;
        try {

            message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(serviceName);
            helper.setTo(email);
            helper.setSubject("标题：XX平台密码");

            StringBuffer sb = new StringBuffer();

            sb.append("<h1>密码：</h1>")
                    .append("<p style='color:#F00'>")
                    .append(user.getPassword().trim()+"</p>");
          //          .append("<p style='text-align:right'>右对齐</p>")
          //          .append(user.getPassword().trim());
            helper.setText(sb.toString(), true);
            FileSystemResource fileSystemResource=new FileSystemResource(new File("C:\\Users\\Lenovo\\Desktop\\《出差审批单》.xlsx"));
            helper.addAttachment("电子文档",fileSystemResource);
            javaMailSender.send(message);
            model.addAttribute("msg","邮件发送成功,请登入邮箱查看密码");
        } catch (MessagingException e) {
            e.printStackTrace();
            model.addAttribute("msg","邮件发送失败,请检查邮箱的是否正确");
        }
   }else{
       model.addAttribute("msg","用户不存在，请重新注册");
   }

        return "index";
    }

    @GetMapping("/service")
    public String service(){
        return "service";
    }

    @PostMapping("/check")
    public String check(HttpServletRequest request){
        String email = request.getParameter("email");
        System.out.println(email);
        return "index";
    }
}
