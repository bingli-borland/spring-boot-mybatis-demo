package org.hsweb.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@ComponentScan("org.hsweb.demo")
@MapperScan("org.hsweb.demo.dao")
@SpringBootApplication
public class Run extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Run.class, args);
    }

    @RequestMapping({"/", "/index.html"})
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Run.class);
    }
}
