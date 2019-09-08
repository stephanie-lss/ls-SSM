package com.cqupt.ssm.controller;

import com.cqupt.ssm.domain.SysLog;
import com.cqupt.ssm.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author LiSheng
 * @date 2019/9/6 22:06
 */
@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ISysLogService sysLogService;
    private Date visitTime;//开始时间
    private Class clazz;//访问的类
    private Method method;//访问的方法

    /**
     * 前置通知，主要获取开始时间，执行的类是哪一个，执行的是哪一个方法
     *
     * @param jp
     */
    @Before("execution(* com.cqupt.ssm.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws Exception {
        visitTime = new Date();//当前时间就是开始访问的时间
        clazz = jp.getTarget().getClass();//具体要访问的类
        String methodName = jp.getSignature().getName();//获取访问的方法的名字
        Object[] args = jp.getArgs();
        //获取具体执行的方法的Method对象
        if (args == null || args.length == 0) {
            method = clazz.getMethod(methodName);//只能获取无参数的方法
        } else {
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            method = clazz.getMethod(methodName, classArgs);
        }
    }

    /**
     * 后置通知
     *
     * @param jp
     */
    @After("execution(* com.cqupt.ssm.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception{
        long time = new Date().getTime() - visitTime.getTime();//获取访问的时长

        String url = "";
        //获取url
        if (clazz != null && method != null && clazz != LogAop.class) {
            //1.获取类上的@RequestMapping("/orders")
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (classAnnotation != null) {
                String[] classValue = classAnnotation.value();
                //2.获取方法上的@RequestMapping(XXX)
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    String[] methodValue = methodAnnotation.value();
                    url = classValue[0] + methodValue[0];

                    //获取访问的IP地址
                    String ip = request.getRemoteAddr();

                    //如何获取当前操作的用户
                    SecurityContext context = SecurityContextHolder.getContext();//从上下文中获取了当前登陆的用户
                    User user = (User) context.getAuthentication().getPrincipal();
                    String username = user.getUsername();

                    //将日志相关信息封装到SysLog对象中
                    SysLog sysLog = new SysLog();
                    sysLog.setIp(ip);
                    sysLog.setUrl(url);
                    sysLog.setUsername(username);
                    sysLog.setVisitTime(visitTime);
                    sysLog.setExecutionTime(time);
                    sysLog.setMethod("[类名] " + clazz.getName() + "[方法名] " + method.getName());

                    //调用Service完成日志的记录操作
                    if (!sysLog.getUrl().equals("/sysLog/findAll")){
                        sysLogService.saveSysLog(sysLog);
                    }
                }
            }
        }

    }
}
