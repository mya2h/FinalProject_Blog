package CS544;

//import CS544.Helper.FilterConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
//    @Bean
//    public FilterRegistrationBean<FilterConfiguration> jwtFilter() {
//        FilterRegistrationBean<FilterConfiguration> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new FilterConfiguration());
//        registrationBean.addUrlPatterns("/user/add"); // Apply the filter to all URLs
//        return registrationBean;
//    }
}