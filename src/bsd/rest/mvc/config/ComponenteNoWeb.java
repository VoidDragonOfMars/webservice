package bsd.rest.mvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration

@EnableWebMvc

@ComponentScan(basePackages=("bsd.rest"),excludeFilters={@Filter(type=FilterType.ANNOTATION,value=EnableWebMvc.class)})
public class ComponenteNoWeb {

}
