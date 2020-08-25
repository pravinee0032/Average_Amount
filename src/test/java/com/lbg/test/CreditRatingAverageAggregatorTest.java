package com.lbg.test;

import static com.lbg.config.FileConfig.CREDIT_DATA_FILE;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.lbg.config.DelimitedFileLoader;
import com.lbg.model.CreditData;
import com.lbg.model.GroupingKey;
import com.lbg.service.CreditRatingAverageAggregator;
import com.lbg.service.IAggregator;

public class CreditRatingAverageAggregatorTest {
	@Test
	public void testCreditDataAggregator() throws IOException {
		DelimitedFileLoader<CreditData> dataFileLoader = new DelimitedFileLoader<>(CREDIT_DATA_FILE.getFilePath());
		List<CreditData> creditDataList = dataFileLoader.loadData(CreditData.mapToCreditData);

		IAggregator<GroupingKey, Double, CreditData> creditRatingAggregator = new CreditRatingAverageAggregator();

		Map<GroupingKey, Double> result = creditRatingAggregator.compute(creditDataList);

		assertFalse(result.isEmpty());

	}

}
