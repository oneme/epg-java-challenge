package com.tdcs.epg.controllers;


import com.tdcs.epg.common.Utils;
import com.tdcs.epg.requests.RentRequest;
import com.tdcs.epg.services.RentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class RentRestController {

    private Logger logger = LoggerFactory.getLogger(RentRestController.class);
    private String className = getClass().getSimpleName();

    private final RentService rentService;

    public RentRestController(RentService rentService) {
        this.rentService = rentService;
    }

    @PostMapping("/rent")
    public ResponseEntity<?> rent(@RequestBody RentRequest rentRequest){
        ResponseEntity<?> response;
        String method = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info(Utils.prettyLog(className, method, "request :: {}"), rentRequest);
        try {
            response = rentService.rent(rentRequest.getUserId(), rentRequest.getRentingList());
        } catch (Exception e){
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Renting failed!");
            logger.error(Utils.prettyLog(className, method, "error :: {}"), e.getMessage());
        }
        logger.info(Utils.prettyLog(className, method, "response :: {}"), response);
        return response;
    }

    @PostMapping("/giveBack")
    public ResponseEntity<?> giveBack(@RequestBody RentRequest rentRequest){
        ResponseEntity<?> response;
        String method = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info(Utils.prettyLog(className, method, "request :: {}"), rentRequest);
        try {
            response = rentService.giveBack(rentRequest.getUserId(), rentRequest.getRentingList());
        } catch (Exception e){
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Returning failed!");
            logger.error(Utils.prettyLog(className, method, "error :: {}"), e.getMessage());
        }
        logger.info(Utils.prettyLog(className, method, "response :: {}"), response);
        return response;
    }

    @GetMapping("/rented/{id}")
    public ResponseEntity<?> getRentedVideoGamesByUser(@PathVariable Long id){
        ResponseEntity<?> response;
        String method = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info(Utils.prettyLog(className, method, "request :: {}"), id);
        try{
            response = rentService.getRentedVideoGamesByUser(id);
        } catch (Exception e){
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            logger.error(Utils.prettyLog(className, method, "error :: {}"), e.getMessage());
        }
        logger.info(Utils.prettyLog(className, method, "response :: {}"), response);
        return response;
    }

    @PostMapping("/rent/price")
    public ResponseEntity<?> getPrice(@RequestBody RentRequest rentRequest){
        ResponseEntity<?> response;
        String method = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info(Utils.prettyLog(className, method, "request :: {}"), rentRequest);
        try{
            response = rentService.calculateRentPrice(rentRequest.getRentingList());
        } catch (Exception e){
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            logger.error(Utils.prettyLog(className, method, "error :: {}"), e.getMessage());
        }
        logger.info(Utils.prettyLog(className, method, "response :: {}"), response);
        return response;
    }
}
