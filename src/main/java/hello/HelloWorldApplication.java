package hello;

/**
 * Created by njucong on 2017/5/24.
 */
import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloWorldApplication {

    public static void main(String[] args) {

        SpringApplication.run(HelloWorldApplication.class, args);
    }

}