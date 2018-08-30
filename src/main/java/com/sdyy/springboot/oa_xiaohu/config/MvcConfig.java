package com.sdyy.springboot.oa_xiaohu.config;

        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
        import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
        import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author xiaohu
 * @createDate 2018-08-27 11:12
 */
@Configuration
public class MvcConfig  {

    //设置组件，见映射关系加入
    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("index");
                registry.addViewController("/index.html").setViewName("index");
                registry.addViewController("/main.html").setViewName("main");
            }

            //注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                System.out.println(123);
              //  registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
              //          .excludePathPatterns("/oa/login","/oa","/oa/index","/oa/email","/oa/register","/oa/service");
            }
        };
        return  adapter;
    }



}
