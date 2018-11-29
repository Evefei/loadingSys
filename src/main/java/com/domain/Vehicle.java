package com.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A Vehicle.
 */
@Entity
@Table(name = "vehicle")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Vehicle implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "car_license_no")
    private String carLicenseNo;

    @Column(name = "length", precision = 10, scale = 2)
    private BigDecimal length;

    @Column(name = "width", precision = 10, scale = 2)
    private BigDecimal width;

    @Column(name = "height", precision = 10, scale = 2)
    private BigDecimal height;

    @Column(name = "vehicle_volume", precision = 10, scale = 2)
    private BigDecimal vehicleVolume;

    @Column(name = "vehicle_weight", precision = 10, scale = 2)
    private BigDecimal vehicleWeight;

    @Column(name = "underpan_height", precision = 10, scale = 2)
    private BigDecimal underpanHeight;

    @Column(name = "crank_height", precision = 10, scale = 2)
    private BigDecimal crankHeight;

    @Column(name = "crank_width", precision = 10, scale = 2)
    private BigDecimal crankWidth;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarLicenseNo() {
        return carLicenseNo;
    }

    public Vehicle carLicenseNo(String carLicenseNo) {
        this.carLicenseNo = carLicenseNo;
        return this;
    }

    public void setCarLicenseNo(String carLicenseNo) {
        this.carLicenseNo = carLicenseNo;
    }

    public BigDecimal getLength() {
        return length;
    }

    public Vehicle length(BigDecimal length) {
        this.length = length;
        return this;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public Vehicle width(BigDecimal width) {
        this.width = width;
        return this;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public Vehicle height(BigDecimal height) {
        this.height = height;
        return this;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public BigDecimal getVehicleVolume() {
        return vehicleVolume;
    }

    public Vehicle vehicleVolume(BigDecimal vehicleVolume) {
        this.vehicleVolume = vehicleVolume;
        return this;
    }

    public void setVehicleVolume(BigDecimal vehicleVolume) {
        this.vehicleVolume = vehicleVolume;
    }

    public BigDecimal getVehicleWeight() {
        return vehicleWeight;
    }

    public Vehicle vehicleWeight(BigDecimal vehicleWeight) {
        this.vehicleWeight = vehicleWeight;
        return this;
    }

    public void setVehicleWeight(BigDecimal vehicleWeight) {
        this.vehicleWeight = vehicleWeight;
    }

    public BigDecimal getUnderpanHeight() {
        return underpanHeight;
    }

    public Vehicle underpanHeight(BigDecimal underpanHeight) {
        this.underpanHeight = underpanHeight;
        return this;
    }

    public void setUnderpanHeight(BigDecimal underpanHeight) {
        this.underpanHeight = underpanHeight;
    }

    public BigDecimal getCrankHeight() {
        return crankHeight;
    }

    public Vehicle crankHeight(BigDecimal crankHeight) {
        this.crankHeight = crankHeight;
        return this;
    }

    public void setCrankHeight(BigDecimal crankHeight) {
        this.crankHeight = crankHeight;
    }

    public BigDecimal getCrankWidth() {
        return crankWidth;
    }

    public Vehicle crankWidth(BigDecimal crankWidth) {
        this.crankWidth = crankWidth;
        return this;
    }

    public void setCrankWidth(BigDecimal crankWidth) {
        this.crankWidth = crankWidth;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vehicle vehicle = (Vehicle) o;
        if (vehicle.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vehicle.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Vehicle{" +
            "id=" + getId() +
            ", carLicenseNo='" + getCarLicenseNo() + "'" +
            ", length=" + getLength() +
            ", width=" + getWidth() +
            ", height=" + getHeight() +
            ", vehicleVolume=" + getVehicleVolume() +
            ", vehicleWeight=" + getVehicleWeight() +
            ", underpanHeight=" + getUnderpanHeight() +
            ", crankHeight=" + getCrankHeight() +
            ", crankWidth=" + getCrankWidth() +
            "}";
    }
}
