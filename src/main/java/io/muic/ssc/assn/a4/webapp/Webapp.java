package io.muic.ssc.assn.a4.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import javax.servlet.ServletException;

import io.muic.ssc.assn.a4.webapp.service.SecurityService;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

@SpringBootApplication
public class Webapp {
    public static void main(String[] args) {
        SpringApplication.run(Webapp.class, args);

        File docBase = new File("src/main/webapp/");
        docBase.mkdirs();
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8082);

        SecurityService securityService = new SecurityService();
        ServletRouter servletRouter = new ServletRouter();
        servletRouter.setSecurityService(securityService);

        Context ctx;
        try {
            ctx = tomcat.addWebapp("", docBase.getAbsolutePath());
            servletRouter.init(ctx);

            tomcat.start();
            tomcat.getServer().await();
        } catch (ServletException | LifecycleException ex) {
            ex.printStackTrace();
        }

    }
}
