package com.juanrarios.springbatch.rest.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/indicator")
public class IndicatorController {

	private static final Logger LOGGER = LoggerFactory.getLogger(IndicatorController.class);

	@RequestMapping(method = RequestMethod.GET)
	public List<IndicatorDTO> findIndicators() {
		LOGGER.info("Finding all indicators");

		List<IndicatorDTO> indicators = createIndicators();

		LOGGER.info("Found {} indicators", indicators.size());

		return indicators;
	}

	private List<IndicatorDTO> createIndicators() {
		IndicatorDTO indicator1 = new IndicatorDTO();
		indicator1.setId(new Long(1013));
		indicator1.setName("Término de facturación de energía activa del PVPC peaje por defecto");
		List<HourPriceDTO> values = new ArrayList<>();
		HourPriceDTO h1 = new HourPriceDTO();
		Calendar cal = Calendar.getInstance();
		h1.setDateTimeUTC(cal);
		h1.setValue(103.31);
		values.add(h1);
		indicator1.setValues(values);

		return Arrays.asList(indicator1);
	}
}
