package com.quantconsult.performanceReview.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;
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

    public AssetPerformance(ZonedDateTime date, String strategyName, String assetName, int conId, double pnl, Currency currency, double price, int quantity) {
        this.date = date;
        this.strategyName = strategyName;
        this.assetName = assetName;
        this.conId = conId;
        this.pnl = pnl;
        this.price = price;
        this.quantity = quantity;
        this.currency = currency;
    }

    @Id @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS z")
    @Column(columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime date;

    @Id @NotBlank
    private String strategyName;

    @Id @NotBlank
    private String assetName;

    @Id
    private int conId;

    @Column(columnDefinition = "INT") @NonNull @Enumerated
    private  Currency currency;

    private double pnl;
    private double price;
    private int quantity;
}
