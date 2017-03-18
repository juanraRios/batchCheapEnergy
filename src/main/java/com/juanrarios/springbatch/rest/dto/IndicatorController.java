package com.juanrarios.springbatch.rest.dto;

import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/indicator")
public class IndicatorController {

	private static final Logger LOGGER = LoggerFactory.getLogger(IndicatorController.class);

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public String findIndicators() {
		LOGGER.info("Finding all indicators");

//		List<IndicatorDTO> indicators = createIndicators();

//		LOGGER.info("Found {} indicators", indicators.size());

		return example().toString();
	}

	// private List<IndicatorDTO> createIndicators() {
	// IndicatorDTO indicator1 = new IndicatorDTO();
	// indicator1.setId(new Long(1013));
	// indicator1.setName("Término de facturación de energía activa del PVPC
	// peaje por defecto");
	// List<HourPriceDTO> values = new ArrayList<>();
	// HourPriceDTO h1 = new HourPriceDTO();
	// Calendar cal = Calendar.getInstance();
	// h1.setDateTimeUTC(cal);
	// h1.setValue(103.31);
	// values.add(h1);
	// indicator1.setValues(values);
	//
	// List<IndicatorDTO> result = new ArrayList<>();
	// result.add(indicator1);
	// return result;
	// }
	
	private JsonObject example() {
		 JsonObject value = Json.createObjectBuilder()
			     .add("indicator", Json.createObjectBuilder()
			         .add("name", "Término de facturación de energía activa del PVPC peaje por defecto")
			         .add("id", "1013")
				     .add("values", Json.createArrayBuilder()
					         .add(Json.createObjectBuilder()
					             .add("value", "103.31")
					             .add("datetime_utc", "2015-10-20T22:00:00Z")
					         .add("geo_ids", Json.createArrayBuilder()
					        		 .add(3)))
					         .add(Json.createObjectBuilder()
					        		 .add("value", "105.60")
					        		 .add("datetime_utc", "2015-10-20T22:00:00Z"))
				     )).build();
		 return value;
	}
}
