package com.quantconsult.performanceReview.repository;

import com.quantconsult.performanceReview.model.AssetPerformance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PerformanceRepository extends JpaRepository<AssetPerformance, Long> {
  List<AssetPerformance> findByStrategyNameOrderByDateAsc(String strategyName);

  @Query("SELECT DISTINCT strategyName from AssetPerformance")
  List<String> findDistinctStrategyNames();

  List<AssetPerformance> findAll();
}
