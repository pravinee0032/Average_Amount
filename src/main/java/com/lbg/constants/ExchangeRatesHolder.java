package com.lbg.constants;



import static com.lbg.config.FileConfig.EXCHANGE_RATES_FILE;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.lbg.config.DelimitedFileLoader;
import com.lbg.config.FileConfig;
import com.lbg.model.ExchangeRate;

public enum ExchangeRatesHolder {
	INSTANCE(EXCHANGE_RATES_FILE);

	private Map<String, ExchangeRate> exchangeRatesMap = new HashMap<>();

	ExchangeRatesHolder(FileConfig exchangeRatesFileConfig) {
		DelimitedFileLoader<ExchangeRate> exchangeRatesLoader = new DelimitedFileLoader<>(
				EXCHANGE_RATES_FILE.getFilePath());
		try {
			exchangeRatesLoader.loadData((line) -> {
				String[] row = line.split(EXCHANGE_RATES_FILE.getDelimiter());
				return new ExchangeRate(row);

			}).forEach(exchangeRate -> exchangeRatesMap.put(exchangeRate.getFromCurrency(), exchangeRate));

		} catch (IOException ioe) {
			ioe.printStackTrace();

		}

	}

	public Double convert(String fromCurrency, String toCurrency, Double amount) {
		ExchangeRate toUSDRate = this.exchangeRatesMap.computeIfAbsent(fromCurrency, (from) -> new ExchangeRate());
		Double amountInUSD = amount * toUSDRate.getRate();
		ExchangeRate toCurrencyRate = this.exchangeRatesMap.computeIfAbsent(toCurrency, (to) -> new ExchangeRate());
		return amountInUSD / toCurrencyRate.getRate();

	}

}
