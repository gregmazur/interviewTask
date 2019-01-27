package hello.world.interviewTask.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Set;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    private static final String TEMPLATES = "/templates/";
    private static final String RESOURCES_URL = "/static/**";
    private static final String RESOURCES_LOCATION = "/static/";;

    @Autowired
    private Environment env;

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("helloworld");
        registry.addViewController("/login").setViewName("login");
    }

    @Bean(name = "webTemplateResolvers")
    public ITemplateResolver webTemplateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix(TEMPLATES);
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCacheable(env.getProperty("thymeleaf.cache.templates", Boolean.class, Boolean.FALSE));
        templateResolver.setCheckExistence(true);
        templateResolver.setOrder(1);
        return templateResolver;
    }

    @Bean(name = "classpathTemplateResolver")
    public ITemplateResolver classpathTemplateResolver() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCacheable(env.getProperty("thymeleaf.cache.templates", Boolean.class, Boolean.FALSE));
        templateResolver.setOrder(2);
        return templateResolver;
    }

    @Bean
    @Autowired
    public SpringTemplateEngine templateEngine(Set<ITemplateResolver> templateResolvers) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolvers(templateResolvers);
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Bean
    @Autowired
    public ViewResolver viewResolver(SpringTemplateEngine templateEngine) {
        ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
        thymeleafViewResolver.setTemplateEngine(templateEngine);
        thymeleafViewResolver.setCharacterEncoding("UTF-8");
        return thymeleafViewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler(RESOURCES_URL)
                .addResourceLocations(RESOURCES_LOCATION)
                .setCachePeriod((int) Duration.ofMinutes(10).get(ChronoUnit.SECONDS));
    }
}
