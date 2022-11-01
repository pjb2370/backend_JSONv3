package com.investmentsite.isc.configurable;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

@Configurable
public class RestTemplateClient {
    @Bean
    public ModelMapper modelMapper() { return new ModelMapper(); }
}
