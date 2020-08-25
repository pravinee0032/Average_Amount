package com.lbg;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.lbg.config.DelimitedFileLoader;
import com.lbg.config.FileConfig;
import com.lbg.exception.IncorrectFileNameException;
import com.lbg.exception.InvalidArgumentException;
import com.lbg.model.CreditData;
import com.lbg.model.CreditRatingGroupingKey;
import com.lbg.model.GroupingKey;
import com.lbg.service.CreditRatingAverageAggregator;

@SpringBootApplication
public class AverageAmountApplication {

	public static void main(String[] args) throws InvalidArgumentException {
		ConfigurableApplicationContext run = SpringApplication.run(AverageAmountApplication.class, args);
		AverageAmountApplication bean = run.getBean(AverageAmountApplication.class);
		bean.printData();

	}

	public void printData() throws InvalidArgumentException {
		List<CreditData> loadData = null;
		Map<GroupingKey, Double> map = null;
		DelimitedFileLoader<CreditData> loader=null;
		CreditRatingAverageAggregator creditRating=null;

		loader = new DelimitedFileLoader<CreditData>(
				FileConfig.CREDIT_DATA_FILE.getFilePath());
		try {
			if (loader != null) {
				loadData = loader.loadData(CreditData.mapToCreditData);
			}
			 creditRating = new CreditRatingAverageAggregator();
			if (creditRating != null) {
				map = creditRating.compute(loadData);
			}
			if(map!=null) {
			map.entrySet().forEach(data -> {
				CreditRatingGroupingKey creditRatingGroupingKey = (CreditRatingGroupingKey) data.getKey();
				System.out.println("Key:" + creditRatingGroupingKey + "Value:" + data.getValue());
			});
			}

		} catch (Exception e) {
			throw new InvalidArgumentException("Argument type is Invalid");
		}

	}

}
