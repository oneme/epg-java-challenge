package com.tdcs.epg.requests;

import com.tdcs.epg.common.RentingItem;

import java.util.List;

public class RentRequest {

    private Long userId;
    private List<RentingItem> rentingList;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<RentingItem> getRentingList() {
        return rentingList;
    }

    public void setRentingList(List<RentingItem> rentingList) {
        this.rentingList = rentingList;
    }

    @Override
    public String toString() {
        return "RentRequest{" +
                "userId=" + userId +
                ", rentingList=" + rentingList +
                '}';
    }
}
