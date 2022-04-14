package com.waltersun.waltersunspringbootstarter.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.waltersun.waltersunspringbootstarter.aspect.StandardLogAspect;

/**
 * @author 道禹 Walter
 * @date 2022-04-14
 */
@Configuration
// 只有当StandardLogAspect存在于classpath中，才会进行相应的实力话
@ConditionalOnClass(StandardLogAspect.class)
public class AutoConfig {

    @Bean
    // 当容器中不存在StandardLogAspect的对象时，才进行实例化操作
    @ConditionalOnMissingBean(StandardLogAspect.class)
    public StandardLogAspect standardLogAspect(){
        return new StandardLogAspect();
    }
}
