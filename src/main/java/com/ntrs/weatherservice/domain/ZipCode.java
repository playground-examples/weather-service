package com.ntrs.weatherservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@Table
@Builder
@NoArgsConstructor
public class ZipCode {

    @Id
    private String zipCode;
    private String zipLocation;

    @OneToMany(targetEntity = Weather.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "zip_code",referencedColumnName = "zipCode")
    private List<Weather> weathers;
}