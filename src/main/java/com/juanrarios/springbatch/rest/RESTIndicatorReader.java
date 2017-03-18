package com.juanrarios.springbatch.rest;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.juanrarios.springbatch.rest.dto.IndicatorDTO;
import com.juanrarios.springbatch.rest.dto.ResponseIndicatorDTO;

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

		String url = "https://api.esios.ree.es/indicators/1013";		
		ResponseEntity<ResponseIndicatorDTO> response;
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		headers.set("Authorization", "Token token=\"3426e0505e1c3c67a1713f5c5d42a63bf406a37c088734e29bd6596e5af9fe9d\"");

		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		response = restTemplate.exchange(url, HttpMethod.GET, entity, ResponseIndicatorDTO.class);
		IndicatorDTO indicator = response.getBody().getIndicator();

		return Arrays.asList(indicator);
	}
}
