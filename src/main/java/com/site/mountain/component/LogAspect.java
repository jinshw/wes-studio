package com.site.mountain.component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.site.mountain.annotate.Log;
import com.site.mountain.entity.SysLog;
import com.site.mountain.entity.SysUser;
import com.site.mountain.service.SysLogService;
import com.site.mountain.utils.UUIDUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Arrays;

@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Autowired
    private SysLogService sysLogService;

    /**
     * 定义@Before增强，拦截带有@Log注解的方法，并记录操作日志
     */
    @Before("@annotation(com.site.mountain.annotate.Log)")
    public void before(JoinPoint joinPoint) throws Exception {
        // 获取目标方法名
        String methodName = joinPoint.getSignature().getName();
        // 获取目标方法参数
        Object[] args = joinPoint.getArgs();
        // 获取目标方法所在类
        String className = joinPoint.getTarget().getClass().getName();

        // 获取Log注解信息
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Log logAnnotation = method.getAnnotation(Log.class);

        // 记录操作日志 实际开发中这里可以改为插入数据库
        logger.info("方法:{}.{}, 参数:{}, 操作:{}", className, methodName, Arrays.toString(args), logAnnotation.operation());

        Subject currentUser = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) currentUser.getPrincipal();

        SysLog sysLog = new SysLog();
        sysLog.setLogId(UUIDUtil.create64UUID());
        if (sysUser != null) {
            sysLog.setOptUserId(sysUser.getUserId().longValue());
            sysLog.setOptUserName(sysUser.getUsername());
        } else {
            if (args.length >= 3 && args[0] instanceof SysUser) {
                sysUser = (SysUser) args[0];
            } else {
                sysUser = new SysUser();
            }
            sysLog.setOptUserId(sysUser.getUserId() == null ? -1 : sysUser.getUserId().longValue());
            sysLog.setOptUserName(sysUser.getUsername());
        }

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String classMethod = className + "." + methodName;
        sysLog.setMethodName(classMethod);
        sysLog.setOptContent(logAnnotation.operation());

        sysLog.setArgs(logAnnotation.operModul());
        sysLog.setOptTime(timestamp);
        sysLogService.insert(sysLog);

    }
}
