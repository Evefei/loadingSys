package com.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A Loading.
 */
@Entity
@Table(name = "loading")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Loading implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "loading_no")
    private String loadingNo;

    @Column(name = "volume_utilization")
    private Integer volumeUtilization;

    @Column(name = "weight_utilization")
    private Integer weightUtilization;

    @Column(name = "material_quantity")
    private Integer materialQuantity;

    @Column(name = "material_volume", precision = 10, scale = 2)
    private BigDecimal materialVolume;

    @Column(name = "material_weight", precision = 10, scale = 2)
    private BigDecimal materialWeight;

    @Column(name = "length_remain", precision = 10, scale = 2)
    private BigDecimal lengthRemain;

    @Column(name = "width_remain", precision = 10, scale = 2)
    private BigDecimal widthRemain;

    @Column(name = "height_remain", precision = 10, scale = 2)
    private BigDecimal heightRemain;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoadingNo() {
        return loadingNo;
    }

    public Loading loadingNo(String loadingNo) {
        this.loadingNo = loadingNo;
        return this;
    }

    public void setLoadingNo(String loadingNo) {
        this.loadingNo = loadingNo;
    }

    public Integer getVolumeUtilization() {
        return volumeUtilization;
    }

    public Loading volumeUtilization(Integer volumeUtilization) {
        this.volumeUtilization = volumeUtilization;
        return this;
    }

    public void setVolumeUtilization(Integer volumeUtilization) {
        this.volumeUtilization = volumeUtilization;
    }

    public Integer getWeightUtilization() {
        return weightUtilization;
    }

    public Loading weightUtilization(Integer weightUtilization) {
        this.weightUtilization = weightUtilization;
        return this;
    }

    public void setWeightUtilization(Integer weightUtilization) {
        this.weightUtilization = weightUtilization;
    }

    public Integer getMaterialQuantity() {
        return materialQuantity;
    }

    public Loading materialQuantity(Integer materialQuantity) {
        this.materialQuantity = materialQuantity;
        return this;
    }

    public void setMaterialQuantity(Integer materialQuantity) {
        this.materialQuantity = materialQuantity;
    }

    public BigDecimal getMaterialVolume() {
        return materialVolume;
    }

    public Loading materialVolume(BigDecimal materialVolume) {
        this.materialVolume = materialVolume;
        return this;
    }

    public void setMaterialVolume(BigDecimal materialVolume) {
        this.materialVolume = materialVolume;
    }

    public BigDecimal getMaterialWeight() {
        return materialWeight;
    }

    public Loading materialWeight(BigDecimal materialWeight) {
        this.materialWeight = materialWeight;
        return this;
    }

    public void setMaterialWeight(BigDecimal materialWeight) {
        this.materialWeight = materialWeight;
    }

    public BigDecimal getLengthRemain() {
        return lengthRemain;
    }

    public Loading lengthRemain(BigDecimal lengthRemain) {
        this.lengthRemain = lengthRemain;
        return this;
    }

    public void setLengthRemain(BigDecimal lengthRemain) {
        this.lengthRemain = lengthRemain;
    }

    public BigDecimal getWidthRemain() {
        return widthRemain;
    }

    public Loading widthRemain(BigDecimal widthRemain) {
        this.widthRemain = widthRemain;
        return this;
    }

    public void setWidthRemain(BigDecimal widthRemain) {
        this.widthRemain = widthRemain;
    }

    public BigDecimal getHeightRemain() {
        return heightRemain;
    }

    public Loading heightRemain(BigDecimal heightRemain) {
        this.heightRemain = heightRemain;
        return this;
    }

    public void setHeightRemain(BigDecimal heightRemain) {
        this.heightRemain = heightRemain;
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
        Loading loading = (Loading) o;
        if (loading.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), loading.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Loading{" +
            "id=" + getId() +
            ", loadingNo='" + getLoadingNo() + "'" +
            ", volumeUtilization=" + getVolumeUtilization() +
            ", weightUtilization=" + getWeightUtilization() +
            ", materialQuantity=" + getMaterialQuantity() +
            ", materialVolume=" + getMaterialVolume() +
            ", materialWeight=" + getMaterialWeight() +
            ", lengthRemain=" + getLengthRemain() +
            ", widthRemain=" + getWidthRemain() +
            ", heightRemain=" + getHeightRemain() +
            "}";
    }
}
