package com.juanrarios.springbatch.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseIndicatorDTO {
	@JsonProperty("indicator")
	IndicatorDTO indicator;

	public IndicatorDTO getIndicator() {
		return indicator;
	}

	public void setIndicator(IndicatorDTO indicator) {
		this.indicator = indicator;
	}
}