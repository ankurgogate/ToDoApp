package com.todoapp.security.web;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.todoapp.config.RootConfig;
import com.todoapp.security.config.SecurityConfig;


public class ApplicationInitializer extends
AbstractAnnotationConfigDispatcherServletInitializer {

@Override
protected Class<?>[] getRootConfigClasses() {
return new Class<?>[] { RootConfig.class, SecurityConfig.class };
}

@Override
protected Class<?>[] getServletConfigClasses() {
return new Class<?>[] { WebMvcConfig.class };
}

@Override
protected String[] getServletMappings() {
return new String[] {"/"};
}

}