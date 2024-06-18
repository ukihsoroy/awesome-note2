package org.ko.multiapi.conf;

import org.ko.multiapi.condi.ApiHandlerMapping;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

@Configuration
public class WebConf extends WebMvcConfigurationSupport {

    @Override
    public RequestMappingHandlerMapping requestMappingHandlerMapping(
            ContentNegotiationManager mvcContentNegotiationManager,
            FormattingConversionService mvcConversionService,
            ResourceUrlProvider mvcResourceUrlProvider) {

        RequestMappingHandlerMapping handlerMapping = new ApiHandlerMapping();

        handlerMapping.setOrder(0);
        handlerMapping.setInterceptors(getInterceptors(mvcConversionService, mvcResourceUrlProvider));
        return handlerMapping;
    }
}
