package com.ymm.basic.project.config.beanCopy;

import ma.glasnost.orika.MapperFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yemingming on 2020-11-25.
 */

@Configuration
public class MapperFactoryAutoWire {

	@Bean("MapperFacade")
	public MapperFacade getMapperFacade() {
		return new OrikaBeanMapper();
	}

}
