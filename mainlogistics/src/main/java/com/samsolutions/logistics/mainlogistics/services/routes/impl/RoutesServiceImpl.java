package com.samsolutions.logistics.mainlogistics.services.routes.impl;

import com.samsolutions.logistics.mainlogistics.dto.RouteDTO;
import com.samsolutions.logistics.mainlogistics.entities.*;
import com.samsolutions.logistics.mainlogistics.repositories.*;
import com.samsolutions.logistics.mainlogistics.services.routes.RoutesService;
import com.samsolutions.logistics.mainlogistics.services.utils.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private ContactsRepository contactsRepository;

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
    @Autowired
    public void setContactsRepository(ContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository;
    }

    @Override
    @Transactional
    public void createRoute(RouteDTO routeDto) {
        Long pointFromId = createPointsFrom(routeDto).getId();
        Long pointToId = createPointsTo(routeDto).getId();
        Long routeId = createRoutes(pointFromId,pointToId).getId();
        createRoutesInfo(routeDto,routeId);
        Long carrierId = createCarriers(routeDto).getId();
        createRoutesOnCarriers(routeId,carrierId);
    }

    private RoutesInfo createRoutesInfo(RouteDTO routeDto,Long routeId){
        Date dateStart = dateConverter.getDateFromString(routeDto.getDateA());
        Date dateFinish = dateConverter.getDateFromString(routeDto.getDateB());
        RoutesInfo routesInfo = new RoutesInfo();
        routesInfo.setDateStart(dateStart);
        routesInfo.setDateFinish(dateFinish);
        map(routeDto,routesInfo);
        routesInfo.setRouteId(routeId);
        return routesInfoRepository.save(routesInfo);
    }
    private Carriers createCarriers(RouteDTO routeDto){
        Carriers carriers = new Carriers();
        map(routeDto,carriers);
        Carriers carriersStored = carriersRepository.findByCarrierName(carriers.getCarrierName());
        if(carriersStored!=null)
            return carriersStored;
        return carriersRepository.save(carriers);
    }
    private Points createPointsFrom(RouteDTO routeDto){
        Points points=new Points();
        points.setCity(routeDto.getCityFrom());
        points.setRegion(routeDto.getRegionFrom());
        points.setCountry(routeDto.getCountryFrom());
        Points pointsStored=pointsRepository.findByCountryAndCityAndRegion(points.getCountry(),points.getCity(),points.getRegion());
        if(pointsStored!=null)
            return pointsStored;
        return  pointsRepository.save(points);
    }
    private Points createPointsTo(RouteDTO routeDto){
        Points points=new Points();
        points.setCity(routeDto.getCityTo());
        points.setRegion(routeDto.getRegionTo());
        points.setCountry(routeDto.getCountryTo());
        Points pointsStored=pointsRepository.findByCountryAndCityAndRegion(points.getCountry(),points.getCity(),points.getRegion());
        if(pointsStored!=null)
            return pointsStored;
        return  pointsRepository.save(points);
    }
    private Routes createRoutes(Long pointFromId,Long pointToId){
        Routes routes=new Routes();
        routes.setPointFromId(pointFromId);
        routes.setPointToId(pointToId);
        routes.setContactsId(contactsRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getId());
        return routesRepository.save(routes);
    }
    private RoutesOnCarriers createRoutesOnCarriers(Long routeId,Long carrierId){
        RoutesOnCarriers routesOnCarriers = new RoutesOnCarriers();
        routesOnCarriers.setRoutesId(routeId);
        routesOnCarriers.setCarriersId(carrierId);
        return routesOnCarriersRepository.save(routesOnCarriers);
    }
}
