package com.tdcs.epg.services;


import com.tdcs.epg.common.RentingItem;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RentService {
    ResponseEntity<?> rent(Long id, List<RentingItem> rentingItems);
    ResponseEntity<?> calculateRentPrice(List<RentingItem> rentingItems);
    ResponseEntity<?> getRentedVideoGamesByUser(Long id);
    ResponseEntity<?> giveBack(Long id, List<RentingItem> rentingItems);
}
