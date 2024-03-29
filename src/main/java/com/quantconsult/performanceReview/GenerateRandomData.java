package com.quantconsult.performanceReview;

import com.quantconsult.performanceReview.model.AssetPerformance;
import com.quantconsult.performanceReview.repository.PerformanceRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@Slf4j
@Profile({"dev"})
public class GenerateRandomData {

  @Autowired private PerformanceRepository performanceRepository;

  @Bean
  CommandLineRunner initDatabase() {
    LocalDate startDate = LocalDate.of(2019, 1, 1);
    LocalDate endDate = LocalDate.now();
    List<LocalDate> dateRange = startDate.datesUntil(endDate).collect(Collectors.toList());

    List<AssetPerformance> assetPerformances = new ArrayList<>();
    var generator = new RandomDataGenerator();
    List<String> strategies = List.of("overnight", "vix", "intraday");

    dateRange.forEach(
        x -> {
          for (String strategy : strategies) {
            assetPerformances.add(
                    new AssetPerformance(
                    ZonedDateTime.of(x, LocalTime.of(16, 0), ZoneId.of("America/Chicago")),
                    strategy,
                    "AAPL",
                    1,
                    generator.nextUniform(-100, 100),
                    AssetPerformance.Currency.USD,
                            1000d, 1));
          }
        });
    return args -> {
      performanceRepository.saveAll(assetPerformances);
      log.info("filled in database with random data");
    };
  }
}
