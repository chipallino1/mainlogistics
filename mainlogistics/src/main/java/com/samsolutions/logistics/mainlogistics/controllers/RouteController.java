package com.samsolutions.logistics.mainlogistics.controllers;

import com.samsolutions.logistics.mainlogistics.dto.PageDTO;
import com.samsolutions.logistics.mainlogistics.dto.RouteDTO;
import com.samsolutions.logistics.mainlogistics.services.routes.RoutesService;
import com.samsolutions.logistics.mainlogistics.services.user.ContactsService;
import com.samsolutions.logistics.mainlogistics.services.utils.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class RouteController {

    private RoutesService routesService;

    @Autowired
    public void setRoutesService(RoutesService routesService) {
        this.routesService = routesService;
    }

    @PostMapping("/routes/create")
    public Boolean createRoute(@RequestBody RouteDTO routeDTO){

        routesService.createRoute(routeDTO);
        return true;
    }

    @GetMapping("/routes/readAll")
    public PageDTO<RouteDTO> getAllRoutes(@PageableDefault(value = 5) Pageable pageable,@RequestParam("email") String email,
                                          @RequestParam("orderBy") String orderBy,@RequestParam("isDesc") boolean isDesc){
        if(email.equals("me"))
            email=SecurityContextHolder.getContext().getAuthentication().getName();
        return routesService.getRoutesByPage(email,orderBy,isDesc,pageable);
    }

    @PostMapping("/routes/order")
    public boolean makeOrder(@RequestBody Map<String,Object> payload){
        return routesService.makeOrder(Long.valueOf((String)payload.get("routeId")),Long.valueOf((String)payload.get("yourCapacity")),Long.valueOf((String)payload.get("yourVolume")));
    }


}
