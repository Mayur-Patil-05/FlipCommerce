package com.mayur.FlipCommerce.Loader;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;

public class EnvLoader implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .load();
        dotenv.entries()
                .forEach(dotenvEntry -> environment
                        .getSystemProperties()
                        .put(dotenvEntry.getKey(), dotenvEntry.getValue()));
    }
}
