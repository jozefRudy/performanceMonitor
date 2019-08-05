package com.quantconsult.performanceReview;

import com.quantconsult.performanceReview.repository.PerformanceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.text.TableView;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PerformanceMonitorApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private PerformanceRepository repository;


}
