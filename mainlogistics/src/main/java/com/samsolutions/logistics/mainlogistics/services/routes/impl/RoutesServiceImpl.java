package com.samsolutions.logistics.mainlogistics.services.routes.impl;

import com.samsolutions.logistics.mainlogistics.dto.PageDTO;
import com.samsolutions.logistics.mainlogistics.dto.RouteDTO;
import com.samsolutions.logistics.mainlogistics.entities.*;
import com.samsolutions.logistics.mainlogistics.repositories.*;
import com.samsolutions.logistics.mainlogistics.services.routes.RoutesService;
import com.samsolutions.logistics.mainlogistics.services.security.Role;
import com.samsolutions.logistics.mainlogistics.services.user.UserService;
import com.samsolutions.logistics.mainlogistics.services.utils.DateConverter;
import com.samsolutions.logistics.mainlogistics.services.utils.MailSender;
import com.samsolutions.logistics.mainlogistics.services.utils.PackageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.*;

@Service
public class RoutesServiceImpl implements RoutesService{

    private DateConverter dateConverter;
    private RoutesRepository routesRepository;
    private RoutesInfoRepository routesInfoRepository;
    private PointsRepository pointsRepository;
    private CarriersRepository carriersRepository;
    private RoutesOnCarriersRepository routesOnCarriersRepository;
    private ContactsRepository contactsRepository;
    private ApplicationContext applicationContext;
    private UserService userService;
    private FirmsRepository firmsRepository;
    private OrdersRepository ordersRepository;
    private MailSender mailSender;

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
    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setFirmsRepository(FirmsRepository firmsRepository) {
        this.firmsRepository = firmsRepository;
    }
    @Autowired
    public void setOrdersRepository(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }
    @Autowired
    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
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

    @Override
    public PageDTO<RouteDTO> getRoutesByPage(String email, String orderBy, boolean desc, Pageable pageable) {
        Contacts contacts = null;
        Map<String,Object> map=new LinkedHashMap<>();
        if(!email.equals("ALL")){
            map.put("EmailCreator",email);
        }
        Page routesPage=getOrderPage(map,orderBy,desc,pageable,applicationContext);
        List<Object> objectList=routesPage.getContent();
        List<RouteDTO> routeDTOList=new ArrayList<>();
        map(objectList,routeDTOList);
        return getPage(routeDTOList,routesPage);
    }

    @Override
    public RouteDTO getRouteById(Long routeId) {
        List<Object> objectList = new ArrayList<>();
        List<RouteDTO> routeDTOList = new ArrayList<>();
        objectList.add(routesOnCarriersRepository.findRoute(routeId));
        map(objectList,routeDTOList);
        return routeDTOList.get(0);
    }

    @Override
    public boolean isRouteCreator(Long routeId) {
        if(routesOnCarriersRepository.findIfCreator(SecurityContextHolder.getContext().getAuthentication().getName(),routeId).size()>0)
            return true;
        return false;
    }

    @Override
    public void deleteRoute(Long routeId) {
        Optional<RoutesOnCarriers> routesOnCarriers = routesOnCarriersRepository.findById(routeId);
        if(routesOnCarriers.isPresent())
            routesOnCarriersRepository.delete(routesOnCarriers.get());
    }

    @Override
    public boolean makeOrder(Long routeId,Long yourCapacity,Long yourVolume) {
        Orders orders = new Orders();
        orders.setOrderDate(new Date());
        orders.setRoutesOnCarriersId(routeId);
        String emailConsumer = SecurityContextHolder.getContext().getAuthentication().getName();
        Role role = userService.getRoleByEmail(emailConsumer);
        if(role==Role.ROLE_CONTACT_USER || role==Role.ROLE_CONTACT_SIMPLE_FIRM_USER)
            orders.setConsumerContactId(contactsRepository.findByEmail(emailConsumer).getId());
        else
            //orders.setConsumerContactId(contactsRepository.findByEmail(emailConsumer).getId());
            orders.setConsumerFirmId(firmsRepository.findAllByEmail(emailConsumer).get(0).getId());
        orders.setProducerId(routesOnCarriersRepository.findFirmCreatorId(routeId));
        if(!checkParams(routeId,yourCapacity,yourVolume))
            return false;
        ordersRepository.save(orders);
        String orderText=emailConsumer+" http://localhost:8080/routes/read?routeId="+routeId+" m="+yourCapacity+" v="+yourVolume;
        mailSender.sendMail(routesOnCarriersRepository.findCreatorEmail(routeId),"",orderText);
        return true;
    }

    @Override
    public PageDTO<RouteDTO> searchRoutesByParams(String countryFrom, String cityFrom, String countryTo, String cityTo,Pageable pageable) {
        Page routesPage = routesOnCarriersRepository
                .findAllRoutesByCountryFromAndCityFromAndCountryToAndCityToOrderByDateStartAsc(countryFrom,cityFrom,countryTo,cityTo,pageable);
        List<Object> objectList=routesPage.getContent();
        List<RouteDTO> routeDTOList=new ArrayList<>();
        map(objectList,routeDTOList);
        return getPage(routeDTOList,routesPage);
    }

    private boolean checkParams(Long routeId,Long yourCapacity,Long yourVolume){
        Map<String,Object> map = routesOnCarriersRepository.findRoute(routeId);
        Carriers carriers = carriersRepository.findById(((BigInteger)map.get("CARRIER_ID")).longValue()).get();
        if(carriers.getCapacity()<yourCapacity || carriers.getVolume()<yourVolume){
            return false;
        }
        else{
            carriers.setCapacity(carriers.getCapacity()-yourCapacity);
            carriers.setVolume(carriers.getVolume()-yourVolume);
            carriersRepository.save(carriers);
        }
        return true;
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
    private void map(List routeList,List<RouteDTO> routeDTOList){
        RouteDTO routeDTO;
        for(int i=0;i<routeList.size();i++){
            routeDTO=new RouteDTO();
            routeDTO.setRouteId(((BigInteger)((Map) routeList.get(i)).get("ID")).longValue());
            routeDTO.setCountryFrom((String) ((Map) routeList.get(i)).get("COUNTRY_FROM"));
            routeDTO.setRegionFrom((String) ((Map) routeList.get(i)).get("REGION_FROM"));
            routeDTO.setCityFrom((String) ((Map) routeList.get(i)).get("CITY_FROM"));
            routeDTO.setCountryTo((String) ((Map) routeList.get(i)).get("COUNTRY_TO"));
            routeDTO.setRegionTo((String) ((Map) routeList.get(i)).get("REGION_TO"));
            routeDTO.setCityTo((String) ((Map) routeList.get(i)).get("CITY_TO"));
            routeDTO.setCarName((String) ((Map) routeList.get(i)).get("CAR_NAME"));
            routeDTO.setCarrierName((String) ((Map) routeList.get(i)).get("CARRIER_NAME"));
            routeDTO.setVolume(((BigInteger)((Map) routeList.get(i)).get("VOLUME")).longValue());
            routeDTO.setCapacity(((BigInteger)((Map) routeList.get(i)).get("CAPACITY")).longValue());
            routeDTO.setInitialVolume(((BigInteger)((Map) routeList.get(i)).get("INITIAL_VOLUME")).longValue());
            routeDTO.setInitialCapacity(((BigInteger)((Map) routeList.get(i)).get("INITIAL_CAPACITY")).longValue());
            routeDTO.setLength(((BigInteger)((Map) routeList.get(i)).get("LENGTH")).longValue());
            routeDTO.setDuration(((BigInteger)((Map) routeList.get(i)).get("DURATION")).longValue());
            routeDTO.setCost(((BigInteger)((Map) routeList.get(i)).get("COST")).longValue());
            routeDTO.setDateA(dateConverter.getStringFromDate((Date) ((Map) routeList.get(i)).get("DATE_START")));
            routeDTO.setDateB(dateConverter.getStringFromDate((Date) ((Map) routeList.get(i)).get("DATE_FINISH")));
            routeDTOList.add(routeDTO);
        }
    }
    @Override
    public Page<Object> getOrderPage(Map samples, String orderBy, boolean desc, Pageable pageable, ApplicationContext applicationContext) {
        Page<Object> routesPage=null;
        Set<String> stringSetKeys = samples.keySet();
        try {
            Method method = this.getClass().getMethod("getOrderPage",Map.class,String.class,boolean.class,Pageable.class,ApplicationContext.class);
            String genericReturnType = method.getGenericReturnType().getTypeName();
            genericReturnType = genericReturnType.substring(genericReturnType.lastIndexOf('<')+1,genericReturnType.lastIndexOf('>'));
            if(genericReturnType.contains(".")){
                genericReturnType=genericReturnType.substring(genericReturnType.lastIndexOf('.')+1);
            }
            String beanName = genericReturnType.toLowerCase();
            beanName="routesOnCarriersRepository";
            genericReturnType = "RoutesOnCarriersRepository";
            String methodName = "findAllRoutes";
            String[] stringKeys=new String[stringSetKeys.size()];
            stringSetKeys.toArray(stringKeys);
            if(stringKeys.length>0)
                methodName=methodName+"By";
            for (int i=0;i<stringKeys.length;i++){
                if(i==stringKeys.length-1){
                    methodName=methodName+stringKeys[i];
                    break;
                }
                methodName=methodName+stringKeys[i]+"And";
            }
            if(orderBy!=null) {
                if (desc)
                    methodName = methodName + "OrderBy" + orderBy + "Desc";
                else
                    methodName = methodName + "OrderBy" + orderBy + "Asc";
            }
            String packagePath = getPackagePath(PackageType.REPOSITORIES_PACKAGE);
            Method methodFind = null;
            if(samples.size()>0){
                methodFind = Class.forName(packagePath+"."+genericReturnType).getMethod(methodName,String.class, Pageable.class);
            }
            else {
                methodFind = Class.forName(packagePath+"."+genericReturnType).getMethod(methodName,Pageable.class);
            }
            samples.put("Pageable",pageable);
            routesPage = (Page)methodFind.invoke(applicationContext.getBean(beanName),samples.values().toArray());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return routesPage;
    }
}
