package com.juanrarios.springbatch.rest.dto;

import java.io.Serializable;
import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HourPriceDTO implements Serializable, Comparable<HourPriceDTO> {

	private static final long serialVersionUID = 1L;

	@JsonProperty("value")
	private Double value;

	@JsonProperty("datetime_utc")
	private Calendar dateTimeUTC;

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Calendar getDateTimeUTC() {
		return dateTimeUTC;
	}

	public void setDateTimeUTC(Calendar dateTimeUTC) {
		this.dateTimeUTC = dateTimeUTC;
	}

	@Override
	public int compareTo(HourPriceDTO hourPriceDTO) {
		return getValue().compareTo(hourPriceDTO.getValue());
	}
}
