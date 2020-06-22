package com.tdcs.epg.common;

import java.math.BigDecimal;

public class RentingItem {

    private Long videoGameId;
    private Long days;
    private BigDecimal price;

    public RentingItem() {
    }

    public RentingItem(Long videoGameId, Long days, BigDecimal price) {
        this.videoGameId = videoGameId;
        this.days = days;
        this.price = price;
    }

    public Long getVideoGameId() {
        return videoGameId;
    }

    public void setVideoGameId(Long videoGameId) {
        this.videoGameId = videoGameId;
    }

    public Long getDays() {
        return days;
    }

    public void setDays(Long days) {
        this.days = days;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "RentingItem{" +
                "videoGameId=" + videoGameId +
                ", days=" + days +
                ", price=" + price +
                '}';
    }
}
