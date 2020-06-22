package com.tdcs.epg.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tdcs.epg.requests.RentRequest;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static BigDecimal calculateRentPrice(int type, int days){
        BigDecimal result = null;
        int overdue;
        switch (type){
            case 1:
                result = new BigDecimal(Constants.PREMIUM_PRICE * days);
                break;
            case 2:
                overdue = days - Constants.DAYS_OVER_STANDARD_GAME;
                result = overdue < 0 ? new BigDecimal(Constants.BASIC_PRICE)
                        : new BigDecimal((Constants.BASIC_PRICE * overdue) + Constants.BASIC_PRICE);
                break;
            case 3:
                overdue = days - Constants.DAYS_OVER_CLASSIC_GAME;
                result = overdue < 0 ? new BigDecimal(Constants.BASIC_PRICE)
                        : new BigDecimal((Constants.BASIC_PRICE * overdue) + Constants.BASIC_PRICE);
                break;
        }
        return result;
    }

    public static String prettyLog(String clz, String method, String message) {
        return String.format("%s :: %s :: %s", clz, method, message);
    }

    public static ResponseEntity<?> getResponse(int code){
        ResponseEntity<?> response = null;
        switch (code){
            case Constants.CODE_USER_NOT_FOUND:
                response = ResponseEntity.badRequest().body(Constants.USER_NOT_FOUND);
                break;
            case Constants.CODE_VIDEO_GAME_NOT_FOUND:
                response = ResponseEntity.badRequest().body(Constants.VIDEO_GAME_NOT_FOUND);
                break;
            case Constants.CODE_VIDEO_GAME_NOT_AVAILABLE:
                response = ResponseEntity.badRequest().body(Constants.VIDEO_GAME_NOT_AVAILABLE);
                break;
            case Constants.CODE_NO_RENTS_FOUND_FOR_THIS_USER:
                response = ResponseEntity.badRequest().body(Constants.NO_RENTS_FOUND_FOR_THIS_USER);
                break;
            case Constants.CODE_NO_VALID_DAYS_AMOUNT:
                response = ResponseEntity.badRequest().body(Constants.NO_VALID_DAYS_AMOUNT);
                break;
            case Constants.CODE_RETURN_SUCCESS:
                response = ResponseEntity.ok().body(Constants.RETURN_SUCCESS);
                break;
            case Constants.CODE_RENT_SUCCESS:
                response = ResponseEntity.ok().body(Constants.RENT_SUCCESS);
                break;
        }
        return response;
    }

    public static String prepareRequestForTest(Long userId, Long videoGameId, Long days) throws JsonProcessingException {
        RentRequest rentRequest = new RentRequest();
        rentRequest.setUserId(userId);
        rentRequest.setRentingList(prepareListItemForTest(videoGameId, days));
        return new ObjectMapper().writeValueAsString(rentRequest);
    }

    public static List<RentingItem> prepareListItemForTest(Long videGameId, Long days){
        List<RentingItem> rentingItemList = new ArrayList<>();
        RentingItem rentingItem = new RentingItem();
        rentingItem.setVideoGameId(videGameId);
        rentingItem.setDays(days);
        rentingItemList.add(rentingItem);
        return rentingItemList;
    }
}
