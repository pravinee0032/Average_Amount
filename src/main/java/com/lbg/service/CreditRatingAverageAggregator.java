package com.lbg.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.lbg.model.CreditData;
import com.lbg.model.GroupingKey;
@Service
public class CreditRatingAverageAggregator implements IAggregator<GroupingKey, Double, CreditData> {


    @Override
    public Map<GroupingKey, Double> compute(List<CreditData> creditDataList) {
        return creditDataList.stream().collect(Collectors.groupingBy(CreditData.groupCreditDataBy,
                Collectors.averagingDouble(CreditData::getAmountInEuros)));

    }
}
