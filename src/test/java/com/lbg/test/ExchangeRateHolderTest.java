package com.lbg.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.lbg.constants.ExchangeRatesHolder;
import com.lbg.model.ExchangeRate;

public class ExchangeRateHolderTest {
	@Test
	public void testExchangeRate() {
		ExchangeRate exchangeRate=new ExchangeRate();
		String fromCurrency = exchangeRate.getFromCurrency();
		String toCurrency = exchangeRate.getToCurrency();
		Double rate = exchangeRate.getRate();
		Double convertedRate = ExchangeRatesHolder.INSTANCE.convert(fromCurrency, toCurrency, rate);
		assertNotNull(convertedRate);
		
	}

}
