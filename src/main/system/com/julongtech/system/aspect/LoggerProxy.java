package com.julongtech.system.aspect;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 日志代理类
 * @author julong
 * @date 2018-5-15 上午11:10:08
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME)  
@Documented  
public @interface LoggerProxy {

    /**
     * 执行方法的类型 添加 修改 删除 查询 导入 导出 
     * @return
     * @author julong
     * @date 2018-5-15 上午11:28:52
     */
    public LoggerMethod[] method() default {};  
    
 
	/**
     * 执行的模块名称
     * @return
     * @author julong
     * @date 2018-5-15 下午1:22:08
     */
    public LoggerModule[] module() default {};  

    
    /**
     * 日志类型
     * @return
     * @author julong
     * @date 2018-5-15 上午11:28:52
     */
    public LoggerType[] type() default {};  
	
    
    /**
	 * 信息描述
	 * @return
     * @author julong
     * @date 2018-5-15 上午11:28:52
     * @desc
     */
    public String description() default "";
     
    
}
