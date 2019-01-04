package com.samsolutions.logistics.mainlogistics.services.routes;

import com.samsolutions.logistics.mainlogistics.dto.RouteDTO;
import com.samsolutions.logistics.mainlogistics.services.utils.Converter;
import org.springframework.stereotype.Component;

@Component
public interface RoutesService extends Converter {

    void createRoute(RouteDTO routeDto);

}
