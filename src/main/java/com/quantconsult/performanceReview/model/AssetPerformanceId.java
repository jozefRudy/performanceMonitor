package com.quantconsult.performanceReview.model;

import java.io.Serializable;
import java.util.Date;

public class AssetPerformanceId implements Serializable {
    private Date date;
    private String strategyName;
    private String assetName;
    private int conId;
}
