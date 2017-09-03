package com.juanrarios.springbatch.rest;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import com.juanrarios.springbatch.common.RestIndicatorProcessor;
import com.juanrarios.springbatch.common.LoggingIndicatorWriter;
import com.juanrarios.springbatch.rest.dto.IndicatorDTO;

@Configuration
public class RESTIndicatorJobConfig {

	private static final String PROPERTY_REST_API_URL = "rest.api.to.database.job.api.url";

	@Bean
	ItemReader<IndicatorDTO> restIndicatorReader(Environment environment, RestTemplate restTemplate) {
		return new RESTIndicatorReader(environment.getRequiredProperty(PROPERTY_REST_API_URL), restTemplate);
	}

	@Bean
	ItemProcessor<IndicatorDTO, IndicatorDTO> restIndicatorProcessor() {
		return new RestIndicatorProcessor();
	}

	@Bean
	ItemWriter<IndicatorDTO> restIndicatorWriter() {
		return new LoggingIndicatorWriter();
	}

	@Bean
	Step restIndicatorStep(ItemReader<IndicatorDTO> restIndicatorReader,
			ItemProcessor<IndicatorDTO, IndicatorDTO> restIndicatorProcessor,
			ItemWriter<IndicatorDTO> restIndicatorWriter, StepBuilderFactory stepBuilderFactory) {
		return stepBuilderFactory.get("restIndicatorStep").<IndicatorDTO, IndicatorDTO>chunk(1)
				.reader(restIndicatorReader).processor(restIndicatorProcessor).writer(restIndicatorWriter).build();
	}

	@Bean
	Job restIndicatorJob(JobBuilderFactory jobBuilderFactory, @Qualifier("restIndicatorStep") Step restIndicatorStep) {
		return jobBuilderFactory.get("restIndicatorJob").incrementer(new RunIdIncrementer()).flow(restIndicatorStep)
				.end().build();
	}
}
