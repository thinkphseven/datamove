package com.longtu.datamove.aspect;

import com.longtu.datamove.service.NoteService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Title: OpLogAspect
 * @description:
 * @author: hk
 * @date: 2021-05-18 11:21
 **/
@Aspect
@Component
public class OpLogAspect {


    private final String POINT_CUT = "@annotation(com.longtu.datamove.aspect.OpLog)";

    @Autowired
    NoteService noteService;

    @Pointcut(POINT_CUT)
    public void operationLog() {
    }

    //@描述：方法前处理逻辑
    @Before("operationLog()")
    public void logInfo() {
    }

    //@描述：方法后处理逻辑
    @After("operationLog()")
    public void logAfter(){
    }


    @Around("operationLog()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object thing = joinPoint.proceed();
        try {
            saveLog(joinPoint,"成功");
            // 执行切入方法内容
          //  this.dealWithThing(thing, joinPoint);
            return thing;
        } catch (Throwable e) {
            e.printStackTrace();
            throw e;
        }
    }


    /**
     * 在抛出异常时使用
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(value="operationLog()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Throwable ex) {
        saveLog(joinPoint,"失败");
    }

    private void saveLog(JoinPoint joinPoint, String msg) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        OpLog opLog = method.getAnnotation(OpLog.class);
        noteService.saveNote(opLog.module(),opLog.opMethod(),opLog.opMethod()+msg);
    }
}
