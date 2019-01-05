package com.samsolutions.logistics.mainlogistics.controllers;

import com.samsolutions.logistics.mainlogistics.dto.RouteDTO;
import com.samsolutions.logistics.mainlogistics.services.routes.RoutesService;
import com.samsolutions.logistics.mainlogistics.services.user.ContactsService;
import com.samsolutions.logistics.mainlogistics.services.utils.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public List<RouteDTO> getAllRoutes(){
        return
    }

}
