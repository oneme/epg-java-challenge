package com.tdcs.epg.controllers;

import com.tdcs.epg.common.Utils;
import com.tdcs.epg.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
@Api(value = "UserRestController")
public class UserRestController {

    private Logger logger = LoggerFactory.getLogger(UserRestController.class);
    private String className = getClass().getSimpleName();

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    @ApiOperation(value = "Get users")
    public ResponseEntity<?> users(){
        ResponseEntity<?> response;
        String method = new Object(){}.getClass().getEnclosingMethod().getName();
        logger.info(Utils.prettyLog(className, method, "no request"));
        try{
            response = ResponseEntity.ok(userService.getUsers());
        }catch (Exception e){
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            logger.error(Utils.prettyLog(className, method, "error :: {}"), e.getMessage());
        }
        logger.info(Utils.prettyLog(className, method, "response :: {}"), response);
        return response;
    }
}
