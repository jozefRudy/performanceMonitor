package com.quantconsult.performanceReview.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name="assetPerformance")
@IdClass(AssetPerformanceId.class)
public class AssetPerformance implements Serializable {

    public enum Currency {
        USD,
        EUR,
        JPY
    }

    public AssetPerformance(Date date, String strategyName, String assetName, int conId, double pnl, Currency currency) {
        this.date = date;
        this.strategyName = strategyName;
        this.assetName = assetName;
        this.conId = conId;
        this.pnl = pnl;
        this.currency = currency;
    }

    @Id @Temporal(TemporalType.TIMESTAMP) @NotNull
    private Date date;

    @Id @NotBlank
    private String strategyName;

    @Id @NotBlank
    private String assetName;

    @Id
    private int conId;

    @Column(columnDefinition = "smallint") @NonNull @Enumerated
    private  Currency currency;

    private double pnl;


}
