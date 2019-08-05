package com.quantconsult.performanceReview.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name="assetPerformance")
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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date date;

    @Column(name = "strategy_name")
    @NotBlank
    private String strategyName;

    @Column(name = "asset_name")
    @NotBlank
    private String assetName;

    @Column(name= "conId")
    private int conId;

    @Column(name = "pnl")
    private double pnl;

    @Column(name = "currency")
    private @NonNull Currency currency;
}
