package com.quantconsult.performanceReview.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Value;
import org.springframework.lang.NonNullApi;
import com.google.gson.annotations.SerializedName;


import java.time.ZonedDateTime;
import java.util.*;

@Value public class StrategyPerformance {

    public StrategyPerformance(AssetPerformance assetPerformance) {
        this.date = assetPerformance.getDate();
        this.strategyName = assetPerformance.getStrategyName();
        this.pnl = assetPerformance.getPnl();
    }

    public StrategyPerformance(ZonedDateTime date, String strategyName, double pnl) {
        this.date = date;
        this.strategyName = strategyName;
        this.pnl = pnl;
    }

    @SerializedName("x")
    ZonedDateTime date;

    transient String strategyName;

    @SerializedName("y")
    double pnl;

    public static List<StrategyPerformance> fromList(List<AssetPerformance> assetPerformance) {
        List<StrategyPerformance> strategyPerformances = new ArrayList<>();
        assetPerformance.forEach(x -> strategyPerformances.add(new StrategyPerformance(x)));
        return strategyPerformances;
    }

    public static List<StrategyPerformance> fromListCumulative(List<AssetPerformance> assetPerformance) {
        List<StrategyPerformance> strategyPerformances = fromList(assetPerformance);
        double cumPnl = 0;
        List<StrategyPerformance> strategyPerformancesCumPnl = new ArrayList<>();
        for (StrategyPerformance strategyPerformance: strategyPerformances) {
            cumPnl = cumPnl + strategyPerformance.getPnl();
            StrategyPerformance cumPerformance = new StrategyPerformance(strategyPerformance.getDate(), strategyPerformance.getStrategyName(), cumPnl);
            strategyPerformancesCumPnl.add(cumPerformance);
        }
        return strategyPerformancesCumPnl;
    }

    public static String toJson(HashMap<String, List<StrategyPerformance>> performancesPerStrategy) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(ZonedDateTime.class, new DateSerializer())
                .disableHtmlEscaping()
                .create();

        JsonArray array = new JsonArray();
        JsonObject jsonElement;
        for (Map.Entry<String, List<StrategyPerformance>> kvp: performancesPerStrategy.entrySet()) {
            jsonElement = new JsonObject();
            jsonElement.add("values", gson.toJsonTree(kvp.getValue()));
            jsonElement.addProperty("key", kvp.getKey());
            array.add(jsonElement);
        }
        return gson.toJson(array);
    }
}
