package com.swnur.spring.todolist.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.swnur.spring.todolist.proxy")
public class ProjectConfig {
}
