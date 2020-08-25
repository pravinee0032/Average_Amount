package com.lbg.test;

import static com.lbg.config.FileConfig.CREDIT_DATA_FILE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.lbg.config.DelimitedFileLoader;
import com.lbg.model.CreditData;

public class DelimitedFileLoaderTest {

	@Test
	public void testFileSize() throws IOException {
		DelimitedFileLoader<CreditData> dataFileLoader = new DelimitedFileLoader<>(CREDIT_DATA_FILE.getFilePath());
		List<CreditData> creditDataList = dataFileLoader.loadData(CreditData.mapToCreditData);
		assertEquals(19, creditDataList.size());

	}

}
