package com.juanrarios.springbatch.common;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import com.juanrarios.springbatch.rest.dto.IndicatorDTO;

/**
 * This custom {@code ItemWriter} writes the information of the indicator to the
 * log.
 *
 */
public class LoggingIndicatorWriter implements ItemWriter<IndicatorDTO> {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingIndicatorWriter.class);

	@Override
	public void write(List<? extends IndicatorDTO> items) throws Exception {
		LOGGER.info("Received the information of {} indicators", items.size());

		LOGGER.info("Name: {}", items.get(0).getName());

		items.forEach(i -> LOGGER.debug("Received the information of a indicator: {}", i));
	}
}
