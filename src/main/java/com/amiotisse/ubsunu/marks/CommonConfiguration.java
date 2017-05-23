package com.amiotisse.ubsunu.marks;

import com.amiotisse.ubsunu.marks.service.ArgsChecker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author himna
 * @since 5/23/2017.
 */
@Configuration
public class CommonConfiguration {

    @Bean
    public ArgsChecker argsChecker(){
        return new ArgsChecker();
    }
}
