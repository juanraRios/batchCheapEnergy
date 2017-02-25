package com.juanrarios.springbatch.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.juanrarios.springbatch.rest.dto.IndicatorDTO;

/**
 * This custom {@code ItemProcessor} simply writes the information of the
 * processed indicator to the log and returns the processed object.
 *
 */
public class LoggingIndicatorProcessor implements ItemProcessor<IndicatorDTO, IndicatorDTO> {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingIndicatorProcessor.class);

	@Override
	public IndicatorDTO process(IndicatorDTO item) throws Exception {
		LOGGER.info("Processing indicator information: {}", item);
		return item;
	}
}
