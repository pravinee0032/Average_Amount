package com.lbg.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.lbg.exception.IncorrectFileNameException;

/**
 * Read the File and load
 * 
 * @author Pravin
 *
 */
@Component
public class DelimitedFileLoader<T> {

	private String inputFile = null;

	DelimitedFileLoader() {

	}

	public DelimitedFileLoader(String inputFile) {
		this.inputFile = inputFile;
	}

	public List<T> loadData(final Function<String, T> transform) throws IOException {
		Resource resource=null;
		InputStream inputStream=null;
		ResourceLoader resourceLoader = new DefaultResourceLoader();
		if(resourceLoader!=null) {

	 resource = resourceLoader.getResource("classpath:" + inputFile);
		}
		if(resource!=null) {
			try {
	 inputStream = resource.getInputStream();
		}
			catch(IOException io) {
				if(inputFile.isEmpty()) {
					throw new IncorrectFileNameException("Incorrect File Name:" +inputFile);
				}
			}
		}
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

			return br.lines().skip(1).map(transform).collect(Collectors.toList());

		}

	}

}
