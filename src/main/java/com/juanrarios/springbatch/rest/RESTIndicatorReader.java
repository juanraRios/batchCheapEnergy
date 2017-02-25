package com.juanrarios.springbatch.rest;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.juanrarios.springbatch.rest.dto.IndicatorDTO;

/**
 * This class demonstrates how we can read the input of our batch job from an
 * external REST API.
 *
 */
class RESTIndicatorReader implements ItemReader<IndicatorDTO> {

	private static final Logger LOGGER = LoggerFactory.getLogger(RESTIndicatorReader.class);

	private final String apiUrl;
	private final RestTemplate restTemplate;

	private int nextIndicatorIndex;
	private List<IndicatorDTO> indicatortData;

	RESTIndicatorReader(String apiUrl, RestTemplate restTemplate) {
		this.apiUrl = apiUrl;
		this.restTemplate = restTemplate;
		nextIndicatorIndex = 0;
	}

	@Override
	public IndicatorDTO read() throws Exception {
		LOGGER.info("Reading the information of the next indicator");

		if (indicatorDataIsNotInitialized()) {
			indicatortData = fetchIndicatorDataFromAPI();
		}

		IndicatorDTO nextIndicator = null;

		if (nextIndicatorIndex < indicatortData.size()) {
			nextIndicator = indicatortData.get(nextIndicatorIndex);
			nextIndicatorIndex++;
		}

		LOGGER.info("Found indicator: {}", nextIndicator);

		return nextIndicator;
	}

	private boolean indicatorDataIsNotInitialized() {
		return this.indicatortData == null;
	}

	private List<IndicatorDTO> fetchIndicatorDataFromAPI() {
		LOGGER.debug("Fetching indicator data from an external API by using the url: {}", apiUrl);

		ResponseEntity<IndicatorDTO[]> response = restTemplate.getForEntity(apiUrl, IndicatorDTO[].class);
		IndicatorDTO[] indicatorData = response.getBody();
		LOGGER.debug("Found {} indicators", indicatorData.length);

		return Arrays.asList(indicatorData);
	}
}
