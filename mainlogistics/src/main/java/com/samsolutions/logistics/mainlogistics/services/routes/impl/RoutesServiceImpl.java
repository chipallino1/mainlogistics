package com.samsolutions.logistics.mainlogistics.services.routes.impl;

import com.samsolutions.logistics.mainlogistics.dto.RouteDTO;
import com.samsolutions.logistics.mainlogistics.entities.*;
import com.samsolutions.logistics.mainlogistics.repositories.*;
import com.samsolutions.logistics.mainlogistics.services.routes.RoutesService;
import com.samsolutions.logistics.mainlogistics.services.utils.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class RoutesServiceImpl implements RoutesService{

    private DateConverter dateConverter;
    private RoutesRepository routesRepository;
    private RoutesInfoRepository routesInfoRepository;
    private PointsRepository pointsRepository;
    private CarriersRepository carriersRepository;
    private RoutesOnCarriersRepository routesOnCarriersRepository;

    @Autowired
    public void setDateConverter(DateConverter dateConverter) {
        this.dateConverter = dateConverter;
    }
    @Autowired
    public void setRoutesRepository(RoutesRepository routesRepository) {
        this.routesRepository = routesRepository;
    }
    @Autowired
    public void setRoutesInfoRepository(RoutesInfoRepository routesInfoRepository) {
        this.routesInfoRepository = routesInfoRepository;
    }
    @Autowired
    public void setPointsRepository(PointsRepository pointsRepository) {
        this.pointsRepository = pointsRepository;
    }
    @Autowired
    public void setCarriersRepository(CarriersRepository carriersRepository) {
        this.carriersRepository = carriersRepository;
    }
    @Autowired
    public void setRoutesOnCarriersRepository(RoutesOnCarriersRepository routesOnCarriersRepository) {
        this.routesOnCarriersRepository = routesOnCarriersRepository;
    }

    @Override
    @Transactional
    public void createRoute(RouteDTO routeDto) {
        Date dateStart = dateConverter.getDateFromString(routeDto.getDateA());
        Date dateFinish = dateConverter.getDateFromString(routeDto.getDateB());
        RoutesInfo routesInfo = new RoutesInfo();
        routesInfo.setDateStart(dateStart);
        routesInfo.setDateFinish(dateFinish);
        map(routeDto,routesInfo);
        Points pointsFrom=new Points();
        pointsFrom.setCity(routeDto.getCityFrom());
        pointsFrom.setRegion(routeDto.getRegionFrom());
        pointsFrom.setCountry(routeDto.getCountryFrom());
        Points pointsTo=new Points();
        pointsTo.setCity(routeDto.getCityTo());
        pointsTo.setRegion(routeDto.getRegionTo());
        pointsTo.setCountry(routeDto.getCountryTo());
        Long pointFromId = pointsRepository.save(pointsFrom).getId();
        Long pointToId = pointsRepository.save(pointsTo).getId();
        Routes routes=new Routes();
        routes.setPointFromId(pointFromId);
        routes.setPointToId(pointToId);
        Long routeId = routesRepository.save(routes).getId();
        routesInfo.setRouteId(routeId);
        routesInfoRepository.save(routesInfo);
        Carriers carriers = new Carriers();
        map(routeDto,carriers);
        Long carrierId = carriersRepository.save(carriers).getId();
        RoutesOnCarriers routesOnCarriers = new RoutesOnCarriers();
        routesOnCarriers.setRoutesId(routeId);
        routesOnCarriers.setCarriersId(carrierId);
        routesOnCarriersRepository.save(routesOnCarriers);
    }
}
