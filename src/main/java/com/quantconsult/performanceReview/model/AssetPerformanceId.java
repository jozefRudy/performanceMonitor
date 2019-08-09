package com.quantconsult.performanceReview.model;

import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Date;

@EqualsAndHashCode
public class AssetPerformanceId implements Serializable {
    private ZonedDateTime date;
    private String strategyName;
    private String assetName;
    private int conId;
}
