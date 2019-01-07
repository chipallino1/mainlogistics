package com.samsolutions.logistics.mainlogistics.services.routes;

import com.samsolutions.logistics.mainlogistics.dto.PageDTO;
import com.samsolutions.logistics.mainlogistics.dto.RouteDTO;
import com.samsolutions.logistics.mainlogistics.entities.Routes;
import com.samsolutions.logistics.mainlogistics.services.utils.Converter;
import com.samsolutions.logistics.mainlogistics.services.utils.Packagable;
import com.samsolutions.logistics.mainlogistics.services.utils.Pagination;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RoutesService extends Converter,Pagination<RouteDTO,Routes>,Packagable {

    void createRoute(RouteDTO routeDto);

    PageDTO<RouteDTO> getRoutesByPage(String email, String orderBy, boolean desc, Pageable pageable);
}
