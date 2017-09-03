package com.juanrarios.springbatch.common;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.juanrarios.springbatch.rest.dto.HourPriceDTO;
import com.juanrarios.springbatch.rest.dto.IndicatorDTO;

/**
 * This custom {@code ItemProcessor} simply writes the information of the
 * processed indicator to the log and returns the processed object.
 *
 */
public class RestIndicatorProcessor implements ItemProcessor<IndicatorDTO, IndicatorDTO> {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestIndicatorProcessor.class);

	@Override
	public IndicatorDTO process(IndicatorDTO item) throws Exception {
		LOGGER.info("Processing indicator information: {}", item);

		List<HourPriceDTO> hourPriceDTOList = item.getValues();
		Collections.sort(hourPriceDTOList);
		item.setValues(hourPriceDTOList);
		return item;
	}
}
