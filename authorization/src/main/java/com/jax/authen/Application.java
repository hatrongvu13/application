package com.jax.authen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

//@SpringBootApplication
//@EnableAspectJAutoProxy
//@EnableCaching
@SpringBootApplication(exclude = {EmbeddedMongoAutoConfiguration.class})
@EnableMongoAuditing
//@EnableSchedulerLock(defaultLockAtMostFor = "10h")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
