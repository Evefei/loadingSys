package com.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A LoadingInfo.
 */
@Entity
@Table(name = "loading_info")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class LoadingInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "step")
    private Integer step;

    @Column(name = "material_code")
    private String materialCode;

    @Column(name = "color_code")
    private String colorCode;

    @Column(name = "layout_type")
    private String layoutType;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "jhi_row")
    private Integer row;

    @Column(name = "jhi_column")
    private Integer column;

    @Column(name = "jhi_layer")
    private Integer layer;

    @Column(name = "length_position", precision = 10, scale = 2)
    private BigDecimal lengthPosition;

    @Column(name = "width_position", precision = 10, scale = 2)
    private BigDecimal widthPosition;

    @Column(name = "height_position", precision = 10, scale = 2)
    private BigDecimal heightPosition;

    @Column(name = "length", precision = 10, scale = 2)
    private BigDecimal length;

    @Column(name = "width", precision = 10, scale = 2)
    private BigDecimal width;

    @Column(name = "height", precision = 10, scale = 2)
    private BigDecimal height;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Loading loadingId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStep() {
        return step;
    }

    public LoadingInfo step(Integer step) {
        this.step = step;
        return this;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public LoadingInfo materialCode(String materialCode) {
        this.materialCode = materialCode;
        return this;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getColorCode() {
        return colorCode;
    }

    public LoadingInfo colorCode(String colorCode) {
        this.colorCode = colorCode;
        return this;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getLayoutType() {
        return layoutType;
    }

    public LoadingInfo layoutType(String layoutType) {
        this.layoutType = layoutType;
        return this;
    }

    public void setLayoutType(String layoutType) {
        this.layoutType = layoutType;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public LoadingInfo quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getRow() {
        return row;
    }

    public LoadingInfo row(Integer row) {
        this.row = row;
        return this;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getColumn() {
        return column;
    }

    public LoadingInfo column(Integer column) {
        this.column = column;
        return this;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    public Integer getLayer() {
        return layer;
    }

    public LoadingInfo layer(Integer layer) {
        this.layer = layer;
        return this;
    }

    public void setLayer(Integer layer) {
        this.layer = layer;
    }

    public BigDecimal getLengthPosition() {
        return lengthPosition;
    }

    public LoadingInfo lengthPosition(BigDecimal lengthPosition) {
        this.lengthPosition = lengthPosition;
        return this;
    }

    public void setLengthPosition(BigDecimal lengthPosition) {
        this.lengthPosition = lengthPosition;
    }

    public BigDecimal getWidthPosition() {
        return widthPosition;
    }

    public LoadingInfo widthPosition(BigDecimal widthPosition) {
        this.widthPosition = widthPosition;
        return this;
    }

    public void setWidthPosition(BigDecimal widthPosition) {
        this.widthPosition = widthPosition;
    }

    public BigDecimal getHeightPosition() {
        return heightPosition;
    }

    public LoadingInfo heightPosition(BigDecimal heightPosition) {
        this.heightPosition = heightPosition;
        return this;
    }

    public void setHeightPosition(BigDecimal heightPosition) {
        this.heightPosition = heightPosition;
    }

    public BigDecimal getLength() {
        return length;
    }

    public LoadingInfo length(BigDecimal length) {
        this.length = length;
        return this;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public LoadingInfo width(BigDecimal width) {
        this.width = width;
        return this;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public LoadingInfo height(BigDecimal height) {
        this.height = height;
        return this;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public Loading getLoadingId() {
        return loadingId;
    }

    public LoadingInfo loadingId(Loading loading) {
        this.loadingId = loading;
        return this;
    }

    public void setLoadingId(Loading loading) {
        this.loadingId = loading;
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
        LoadingInfo loadingInfo = (LoadingInfo) o;
        if (loadingInfo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), loadingInfo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LoadingInfo{" +
            "id=" + getId() +
            ", step=" + getStep() +
            ", materialCode='" + getMaterialCode() + "'" +
            ", colorCode='" + getColorCode() + "'" +
            ", layoutType='" + getLayoutType() + "'" +
            ", quantity=" + getQuantity() +
            ", row=" + getRow() +
            ", column=" + getColumn() +
            ", layer=" + getLayer() +
            ", lengthPosition=" + getLengthPosition() +
            ", widthPosition=" + getWidthPosition() +
            ", heightPosition=" + getHeightPosition() +
            ", length=" + getLength() +
            ", width=" + getWidth() +
            ", height=" + getHeight() +
            "}";
    }
}
