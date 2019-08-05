package com.quantconsult.performanceReview.controller;

import com.quantconsult.performanceReview.model.AssetPerformance;
import com.quantconsult.performanceReview.model.StrategyPerformance;
import com.quantconsult.performanceReview.repository.PerformanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;


@Controller
@RestController
@RequestMapping("/strategy")
@RequiredArgsConstructor
public class PerformanceController {

  @Autowired PerformanceRepository repository;

  @GetMapping(value = { "", "/performanceModel" }, produces = MediaType.TEXT_HTML_VALUE)
  public ModelAndView strategyPerformance() {
    ModelAndView mw = new ModelAndView("performancesGraph");
    mw.addObject("performances_string", strategyPerformanceModel());
    return mw;
  }

  @PostMapping("/addAssetPerformance")
  public AssetPerformance addAssetPerformance(@RequestBody @Valid AssetPerformance assetPerformance) {
    return repository.save(assetPerformance);
  }


  @GetMapping(value = { "", "/performance" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseBody
  public String strategyPerformanceModel() {
    List<String> strategyNames = repository.findDistinctStrategyNames();
    HashMap<String, List<StrategyPerformance>> performancesPerStrategy = new HashMap<>();

    for (String strategyName : strategyNames) {
      List<AssetPerformance> assetPerformances = repository.findByStrategyNameOrderByDateAsc(strategyName);
      performancesPerStrategy.put(strategyName, StrategyPerformance.fromListCumulative(assetPerformances));
    }
    return StrategyPerformance.toJson(performancesPerStrategy);
  }
}
