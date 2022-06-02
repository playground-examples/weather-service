package com.ntrs.weatherservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@Entity
@Table
@Builder
@NoArgsConstructor
public class Weather {
    @Id
    private Long id;
    private BigDecimal minTemperature;
    private BigDecimal maxTemperature;
    @Temporal(TemporalType.DATE)
    private Date date;
}