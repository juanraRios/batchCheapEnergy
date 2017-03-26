package com.juanrarios.springbatch.common;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juanrarios.springbatch.rest.dto.CheapestHourNotificationRequestDTO;
import com.juanrarios.springbatch.rest.dto.IndicatorDTO;
import com.juanrarios.springbatch.rest.dto.NotificationDTO;

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

		LOGGER.info("Value: {}", items.get(0).getValues().get(0).getValue());
		
		CheapestHourNotificationRequestDTO createCheapestHourNotificationRequestDTO = createCheapestHourNotificationRequestDTO(items.get(0));
		
		
		// Send post request to firebase cloud messaging
		
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://fcm.googleapis.com/fcm/send";
		ObjectMapper mapper = new ObjectMapper();
		String requestJson = mapper.writeValueAsString(createCheapestHourNotificationRequestDTO);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "key=AAAAQ3hqbgc:APA91bGa3SpbCJLDMkgllcW6RzGD2warBVKcgMy47k3OvpwEM246juIS_e4lwgl2y46MjEKEMwnktAnSx8pUiGGMIhssaGvEwlJxQ8-GKwQ_zQWwX-azF2lGwJZAbt85tvrSs1wSC04J");

		HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
		restTemplate.postForObject(url, entity, String.class);
		
	}
	
	private CheapestHourNotificationRequestDTO createCheapestHourNotificationRequestDTO(IndicatorDTO indicatorDTO){
		
		CheapestHourNotificationRequestDTO result;
		
		Calendar calendar = indicatorDTO.getValues().get(0).getDateTimeUTC();
		String cheapHour = new SimpleDateFormat("HH:mm").format(calendar.getTime());

		String title = MessageFormat.format("La hora mas barata de hoy es a las {0}", cheapHour);
		String body = "Momento idoneo para poner la lavadora :)";
		
		NotificationDTO notificationDTO = new NotificationDTO(title, body);
		result = new CheapestHourNotificationRequestDTO("/topics/news", notificationDTO);

		
		return result;
	}
	
	
}
