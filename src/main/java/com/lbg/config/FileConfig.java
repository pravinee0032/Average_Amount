package com.lbg.config;

import com.lbg.constants.Constants;

public enum FileConfig {
    CREDIT_DATA_FILE("FILE.DAT", Constants.TAB),
    EXCHANGE_RATES_FILE("exchangeRates.csv", Constants.COMMA);

    private final String filePath;
    private final String delimiter;


    FileConfig(String filePath, String delimiter) {
        this.filePath = filePath;
        this.delimiter = delimiter;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public String getDelimiter() {
        return this.delimiter;
    }


}
