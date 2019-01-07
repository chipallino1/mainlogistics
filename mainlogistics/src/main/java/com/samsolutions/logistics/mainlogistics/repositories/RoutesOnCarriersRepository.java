package com.samsolutions.logistics.mainlogistics.repositories;

import com.samsolutions.logistics.mainlogistics.entities.Carriers;
import com.samsolutions.logistics.mainlogistics.entities.RoutesOnCarriers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface RoutesOnCarriersRepository extends JpaRepository<RoutesOnCarriers,Long> {
    @Query(value = "SELECT PF.COUNTRY COUNTRY_FROM,PF.REGION REGION_FROM,PF.CITY CITY_FROM," +
            "PT.COUNTRY COUNTRY_TO,PT.REGION REGION_TO,PT.CITY CITY_TO, CA.CARRIER_NAME,CA.CAR_NAME,CA.VOLUME,CA.CAPACITY,CA.COST,RI.DATE_START,RI.DATE_FINISH,RI.LENGTH,RI.DURATION FROM ROUTES_ON_CARRIERS ROC" +
            " INNER JOIN ROUTES R INNER JOIN POINTS PF ON R.POINT_FROM_ID=PF.ID" +
            " INNER JOIN POINTS PT ON R.POINT_TO_ID=PT.ID" +
            " INNER JOIN ROUTES_INFO RI ON R.ID=RI.ROUTE_ID  ON ROC.ROUTES_ID=R.ID" +
            " INNER JOIN CARRIERS CA ON ROC.CARRIERS_ID=CA.ID ORDER BY PF.CITY",
            countQuery = "SELECT count(*) FROM ROUTES_ON_CARRIERS ROC" +
                    " INNER JOIN ROUTES R INNER JOIN POINTS PF ON R.POINT_FROM_ID=PF.ID" +
                    " INNER JOIN POINTS PT ON R.POINT_TO_ID=PT.ID" +
                    " INNER JOIN ROUTES_INFO RI ON R.ID=RI.ROUTE_ID  ON ROC.ROUTES_ID=R.ID" +
                    " INNER JOIN CARRIERS CA ON ROC.CARRIERS_ID=CA.ID",
            nativeQuery = true)
    Page<Map<String,Object>> findAllRoutesOrderByCityFromAsc(Pageable pageable);
    @Query(value = "SELECT PF.COUNTRY COUNTRY_FROM,PF.REGION REGION_FROM,PF.CITY CITY_FROM," +
            "PT.COUNTRY COUNTRY_TO,PT.REGION REGION_TO,PT.CITY CITY_TO, CA.CARRIER_NAME,CA.CAR_NAME,CA.VOLUME,CA.CAPACITY,CA.COST,RI.DATE_START,RI.DATE_FINISH,RI.LENGTH,RI.DURATION FROM ROUTES_ON_CARRIERS ROC" +
            " INNER JOIN ROUTES R INNER JOIN POINTS PF ON R.POINT_FROM_ID=PF.ID" +
            " INNER JOIN POINTS PT ON R.POINT_TO_ID=PT.ID" +
            " INNER JOIN ROUTES_INFO RI ON R.ID=RI.ROUTE_ID  ON ROC.ROUTES_ID=R.ID" +
            " INNER JOIN CARRIERS CA ON ROC.CARRIERS_ID=CA.ID ORDER BY PF.COUNTRY",
            countQuery = "SELECT count(*) FROM ROUTES_ON_CARRIERS ROC" +
                    " INNER JOIN ROUTES R INNER JOIN POINTS PF ON R.POINT_FROM_ID=PF.ID" +
                    " INNER JOIN POINTS PT ON R.POINT_TO_ID=PT.ID" +
                    " INNER JOIN ROUTES_INFO RI ON R.ID=RI.ROUTE_ID  ON ROC.ROUTES_ID=R.ID" +
                    " INNER JOIN CARRIERS CA ON ROC.CARRIERS_ID=CA.ID",
            nativeQuery = true)
    Page<Map<String,Object>> findAllRoutesOrderByCountryFromAsc(Pageable pageable);
    @Query(value = "SELECT PF.COUNTRY COUNTRY_FROM,PF.REGION REGION_FROM,PF.CITY CITY_FROM," +
            "PT.COUNTRY COUNTRY_TO,PT.REGION REGION_TO,PT.CITY CITY_TO, CA.CARRIER_NAME,CA.CAR_NAME,CA.VOLUME,CA.CAPACITY,CA.COST,RI.DATE_START,RI.DATE_FINISH,RI.LENGTH,RI.DURATION FROM ROUTES_ON_CARRIERS ROC" +
            " INNER JOIN ROUTES R INNER JOIN POINTS PF ON R.POINT_FROM_ID=PF.ID" +
            " INNER JOIN POINTS PT ON R.POINT_TO_ID=PT.ID" +
            " INNER JOIN ROUTES_INFO RI ON R.ID=RI.ROUTE_ID  ON ROC.ROUTES_ID=R.ID" +
            " INNER JOIN CARRIERS CA ON ROC.CARRIERS_ID=CA.ID ORDER BY PF.CITY DESC",
            countQuery = "SELECT count(*) FROM ROUTES_ON_CARRIERS ROC" +
                    " INNER JOIN ROUTES R INNER JOIN POINTS PF ON R.POINT_FROM_ID=PF.ID" +
                    " INNER JOIN POINTS PT ON R.POINT_TO_ID=PT.ID" +
                    " INNER JOIN ROUTES_INFO RI ON R.ID=RI.ROUTE_ID  ON ROC.ROUTES_ID=R.ID" +
                    " INNER JOIN CARRIERS CA ON ROC.CARRIERS_ID=CA.ID",
            nativeQuery = true)
    Page<Map<String,Object>> findAllRoutesOrderByCityFromDesc(Pageable pageable);
    @Query(value = "SELECT PF.COUNTRY COUNTRY_FROM,PF.REGION REGION_FROM,PF.CITY CITY_FROM," +
            "PT.COUNTRY COUNTRY_TO,PT.REGION REGION_TO,PT.CITY CITY_TO, CA.CARRIER_NAME,CA.CAR_NAME,CA.VOLUME,CA.CAPACITY,CA.COST,RI.DATE_START,RI.DATE_FINISH,RI.LENGTH,RI.DURATION FROM ROUTES_ON_CARRIERS ROC" +
            " INNER JOIN ROUTES R INNER JOIN POINTS PF ON R.POINT_FROM_ID=PF.ID" +
            " INNER JOIN POINTS PT ON R.POINT_TO_ID=PT.ID" +
            " INNER JOIN ROUTES_INFO RI ON R.ID=RI.ROUTE_ID  ON ROC.ROUTES_ID=R.ID" +
            " INNER JOIN CARRIERS CA ON ROC.CARRIERS_ID=CA.ID ORDER BY PF.COUNTRY DESC",
            countQuery = "SELECT count(*) FROM ROUTES_ON_CARRIERS ROC" +
                    " INNER JOIN ROUTES R INNER JOIN POINTS PF ON R.POINT_FROM_ID=PF.ID" +
                    " INNER JOIN POINTS PT ON R.POINT_TO_ID=PT.ID" +
                    " INNER JOIN ROUTES_INFO RI ON R.ID=RI.ROUTE_ID  ON ROC.ROUTES_ID=R.ID" +
                    " INNER JOIN CARRIERS CA ON ROC.CARRIERS_ID=CA.ID",
            nativeQuery = true)
    Page<Map<String,Object>> findAllRoutesOrderByCountryFromDesc(Pageable pageable);

    @Query(value = "SELECT PF.COUNTRY COUNTRY_FROM,PF.REGION REGION_FROM,PF.CITY CITY_FROM," +
            "PT.COUNTRY COUNTRY_TO,PT.REGION REGION_TO,PT.CITY CITY_TO, CA.CARRIER_NAME,CA.CAR_NAME,CA.VOLUME,CA.CAPACITY,CA.COST,RI.DATE_START,RI.DATE_FINISH,RI.LENGTH,RI.DURATION FROM ROUTES_ON_CARRIERS ROC" +
            " INNER JOIN ROUTES R INNER JOIN POINTS PF ON R.POINT_FROM_ID=PF.ID" +
            " INNER JOIN POINTS PT ON R.POINT_TO_ID=PT.ID" +
            " INNER JOIN ROUTES_INFO RI ON R.ID=RI.ROUTE_ID  ON ROC.ROUTES_ID=R.ID" +
            " INNER JOIN CARRIERS CA ON ROC.CARRIERS_ID=CA.ID ORDER BY RI.DATE_START",
            countQuery = "SELECT count(*) FROM ROUTES_ON_CARRIERS ROC" +
                    " INNER JOIN ROUTES R INNER JOIN POINTS PF ON R.POINT_FROM_ID=PF.ID" +
                    " INNER JOIN POINTS PT ON R.POINT_TO_ID=PT.ID" +
                    " INNER JOIN ROUTES_INFO RI ON R.ID=RI.ROUTE_ID  ON ROC.ROUTES_ID=R.ID" +
                    " INNER JOIN CARRIERS CA ON ROC.CARRIERS_ID=CA.ID",
            nativeQuery = true)
    Page<Map<String,Object>> findAllRoutesOrderByDateStartAsc(Pageable pageable);
    @Query(value = "SELECT PF.COUNTRY COUNTRY_FROM,PF.REGION REGION_FROM,PF.CITY CITY_FROM," +
            "PT.COUNTRY COUNTRY_TO,PT.REGION REGION_TO,PT.CITY CITY_TO, CA.CARRIER_NAME,CA.CAR_NAME,CA.VOLUME,CA.CAPACITY,CA.COST,RI.DATE_START,RI.DATE_FINISH,RI.LENGTH,RI.DURATION FROM ROUTES_ON_CARRIERS ROC" +
            " INNER JOIN ROUTES R INNER JOIN POINTS PF ON R.POINT_FROM_ID=PF.ID" +
            " INNER JOIN POINTS PT ON R.POINT_TO_ID=PT.ID" +
            " INNER JOIN ROUTES_INFO RI ON R.ID=RI.ROUTE_ID  ON ROC.ROUTES_ID=R.ID" +
            " INNER JOIN CARRIERS CA ON ROC.CARRIERS_ID=CA.ID ORDER BY RI.DATE_START DESC",
            countQuery = "SELECT count(*) FROM ROUTES_ON_CARRIERS ROC" +
                    " INNER JOIN ROUTES R INNER JOIN POINTS PF ON R.POINT_FROM_ID=PF.ID" +
                    " INNER JOIN POINTS PT ON R.POINT_TO_ID=PT.ID" +
                    " INNER JOIN ROUTES_INFO RI ON R.ID=RI.ROUTE_ID  ON ROC.ROUTES_ID=R.ID" +
                    " INNER JOIN CARRIERS CA ON ROC.CARRIERS_ID=CA.ID",
            nativeQuery = true)
    Page<Map<String,Object>> findAllRoutesOrderByDateStartDesc(Pageable pageable);



    @Query(value = "SELECT PF.COUNTRY COUNTRY_FROM,PF.REGION REGION_FROM,PF.CITY CITY_FROM," +
            "PT.COUNTRY COUNTRY_TO,PT.REGION REGION_TO,PT.CITY CITY_TO, CA.CARRIER_NAME,CA.CAR_NAME,CA.VOLUME,CA.CAPACITY,CA.COST,RI.DATE_START,RI.DATE_FINISH,RI.LENGTH,RI.DURATION FROM ROUTES_ON_CARRIERS ROC" +
            " INNER JOIN ROUTES R INNER JOIN CONTACTS CON ON R.CONTACTS_ID=CON.ID INNER JOIN POINTS PF ON R.POINT_FROM_ID=PF.ID" +
            " INNER JOIN POINTS PT ON R.POINT_TO_ID=PT.ID" +
            " INNER JOIN ROUTES_INFO RI ON R.ID=RI.ROUTE_ID  ON ROC.ROUTES_ID=R.ID" +
            " INNER JOIN CARRIERS CA ON ROC.CARRIERS_ID=CA.ID WHERE CON.EMAIL=?1 ORDER BY PF.CITY",
            countQuery = "SELECT count(*) FROM ROUTES_ON_CARRIERS ROC" +
                    " INNER JOIN ROUTES R INNER JOIN POINTS PF ON R.POINT_FROM_ID=PF.ID" +
                    " INNER JOIN POINTS PT ON R.POINT_TO_ID=PT.ID" +
                    " INNER JOIN ROUTES_INFO RI ON R.ID=RI.ROUTE_ID  ON ROC.ROUTES_ID=R.ID" +
                    " INNER JOIN CARRIERS CA ON ROC.CARRIERS_ID=CA.ID",
            nativeQuery = true)
    Page<Map<String,Object>> findAllRoutesByEmailCreatorOrderByCityFromAsc(String  email,Pageable pageable);
    @Query(value = "SELECT PF.COUNTRY COUNTRY_FROM,PF.REGION REGION_FROM,PF.CITY CITY_FROM," +
            "PT.COUNTRY COUNTRY_TO,PT.REGION REGION_TO,PT.CITY CITY_TO, CA.CARRIER_NAME,CA.CAR_NAME,CA.VOLUME,CA.CAPACITY,CA.COST,RI.DATE_START,RI.DATE_FINISH,RI.LENGTH,RI.DURATION FROM ROUTES_ON_CARRIERS ROC" +
            " INNER JOIN ROUTES R INNER JOIN CONTACTS CON ON R.CONTACTS_ID=CON.ID INNER JOIN POINTS PF ON R.POINT_FROM_ID=PF.ID" +
            " INNER JOIN POINTS PT ON R.POINT_TO_ID=PT.ID" +
            " INNER JOIN ROUTES_INFO RI ON R.ID=RI.ROUTE_ID  ON ROC.ROUTES_ID=R.ID" +
            " INNER JOIN CARRIERS CA ON ROC.CARRIERS_ID=CA.ID WHERE CON.EMAIL=?1 ORDER BY PF.COUNTRY",
            countQuery = "SELECT count(*) FROM ROUTES_ON_CARRIERS ROC" +
                    " INNER JOIN ROUTES R INNER JOIN POINTS PF ON R.POINT_FROM_ID=PF.ID" +
                    " INNER JOIN POINTS PT ON R.POINT_TO_ID=PT.ID" +
                    " INNER JOIN ROUTES_INFO RI ON R.ID=RI.ROUTE_ID  ON ROC.ROUTES_ID=R.ID" +
                    " INNER JOIN CARRIERS CA ON ROC.CARRIERS_ID=CA.ID",
            nativeQuery = true)
    Page<Map<String,Object>> findAllRoutesByEmailCreatorOrderByCountryFromAsc(String  email, Pageable pageable);
    @Query(value = "SELECT PF.COUNTRY COUNTRY_FROM,PF.REGION REGION_FROM,PF.CITY CITY_FROM," +
            "PT.COUNTRY COUNTRY_TO,PT.REGION REGION_TO,PT.CITY CITY_TO, CA.CARRIER_NAME,CA.CAR_NAME,CA.VOLUME,CA.CAPACITY,CA.COST,RI.DATE_START,RI.DATE_FINISH,RI.LENGTH,RI.DURATION FROM ROUTES_ON_CARRIERS ROC" +
            " INNER JOIN ROUTES R INNER JOIN CONTACTS CON ON R.CONTACTS_ID=CON.ID INNER JOIN POINTS PF ON R.POINT_FROM_ID=PF.ID" +
            " INNER JOIN POINTS PT ON R.POINT_TO_ID=PT.ID" +
            " INNER JOIN ROUTES_INFO RI ON R.ID=RI.ROUTE_ID  ON ROC.ROUTES_ID=R.ID" +
            " INNER JOIN CARRIERS CA ON ROC.CARRIERS_ID=CA.ID WHERE CON.EMAIL=?1 ORDER BY PF.CITY DESC",
            countQuery = "SELECT count(*) FROM ROUTES_ON_CARRIERS ROC" +
                    " INNER JOIN ROUTES R INNER JOIN POINTS PF ON R.POINT_FROM_ID=PF.ID" +
                    " INNER JOIN POINTS PT ON R.POINT_TO_ID=PT.ID" +
                    " INNER JOIN ROUTES_INFO RI ON R.ID=RI.ROUTE_ID  ON ROC.ROUTES_ID=R.ID" +
                    " INNER JOIN CARRIERS CA ON ROC.CARRIERS_ID=CA.ID",
            nativeQuery = true)
    Page<Map<String,Object>> findAllRoutesByEmailCreatorOrderByCityFromDesc(String  email,Pageable pageable);
    @Query(value = "SELECT PF.COUNTRY COUNTRY_FROM,PF.REGION REGION_FROM,PF.CITY CITY_FROM," +
            "PT.COUNTRY COUNTRY_TO,PT.REGION REGION_TO,PT.CITY CITY_TO, CA.CARRIER_NAME,CA.CAR_NAME,CA.VOLUME,CA.CAPACITY,CA.COST,RI.DATE_START,RI.DATE_FINISH,RI.LENGTH,RI.DURATION FROM ROUTES_ON_CARRIERS ROC" +
            " INNER JOIN ROUTES R INNER JOIN CONTACTS CON ON R.CONTACTS_ID=CON.ID INNER JOIN POINTS PF ON R.POINT_FROM_ID=PF.ID" +
            " INNER JOIN POINTS PT ON R.POINT_TO_ID=PT.ID" +
            " INNER JOIN ROUTES_INFO RI ON R.ID=RI.ROUTE_ID  ON ROC.ROUTES_ID=R.ID" +
            " INNER JOIN CARRIERS CA ON ROC.CARRIERS_ID=CA.ID WHERE CON.EMAIL=?1 ORDER BY PF.COUNTRY DESC",
            countQuery = "SELECT count(*) FROM ROUTES_ON_CARRIERS ROC" +
                    " INNER JOIN ROUTES R INNER JOIN POINTS PF ON R.POINT_FROM_ID=PF.ID" +
                    " INNER JOIN POINTS PT ON R.POINT_TO_ID=PT.ID" +
                    " INNER JOIN ROUTES_INFO RI ON R.ID=RI.ROUTE_ID  ON ROC.ROUTES_ID=R.ID" +
                    " INNER JOIN CARRIERS CA ON ROC.CARRIERS_ID=CA.ID",
            nativeQuery = true)
    Page<Map<String,Object>> findAllRoutesByEmailCreatorOrderByCountryFromDesc(String  email,Pageable pageable);

    @Query(value = "SELECT PF.COUNTRY COUNTRY_FROM,PF.REGION REGION_FROM,PF.CITY CITY_FROM," +
            "PT.COUNTRY COUNTRY_TO,PT.REGION REGION_TO,PT.CITY CITY_TO, CA.CARRIER_NAME,CA.CAR_NAME,CA.VOLUME,CA.CAPACITY,CA.COST,RI.DATE_START,RI.DATE_FINISH,RI.LENGTH,RI.DURATION FROM ROUTES_ON_CARRIERS ROC" +
            " INNER JOIN ROUTES R INNER JOIN CONTACTS CON ON R.CONTACTS_ID=CON.ID INNER JOIN POINTS PF ON R.POINT_FROM_ID=PF.ID" +
            " INNER JOIN POINTS PT ON R.POINT_TO_ID=PT.ID" +
            " INNER JOIN ROUTES_INFO RI ON R.ID=RI.ROUTE_ID  ON ROC.ROUTES_ID=R.ID" +
            " INNER JOIN CARRIERS CA ON ROC.CARRIERS_ID=CA.ID WHERE CON.EMAIL=?1 ORDER BY RI.DATE_START",
            countQuery = "SELECT count(*) FROM ROUTES_ON_CARRIERS ROC" +
                    " INNER JOIN ROUTES R INNER JOIN POINTS PF ON R.POINT_FROM_ID=PF.ID" +
                    " INNER JOIN POINTS PT ON R.POINT_TO_ID=PT.ID" +
                    " INNER JOIN ROUTES_INFO RI ON R.ID=RI.ROUTE_ID  ON ROC.ROUTES_ID=R.ID" +
                    " INNER JOIN CARRIERS CA ON ROC.CARRIERS_ID=CA.ID",
            nativeQuery = true)
    Page<Map<String,Object>> findAllRoutesByEmailCreatorOrderByDateStartAsc(String  email,Pageable pageable);
    @Query(value = "SELECT PF.COUNTRY COUNTRY_FROM,PF.REGION REGION_FROM,PF.CITY CITY_FROM," +
            "PT.COUNTRY COUNTRY_TO,PT.REGION REGION_TO,PT.CITY CITY_TO, CA.CARRIER_NAME,CA.CAR_NAME,CA.VOLUME,CA.CAPACITY,CA.COST,RI.DATE_START,RI.DATE_FINISH,RI.LENGTH,RI.DURATION FROM ROUTES_ON_CARRIERS ROC" +
            " INNER JOIN ROUTES R INNER JOIN CONTACTS CON ON R.CONTACTS_ID=CON.ID INNER JOIN POINTS PF ON R.POINT_FROM_ID=PF.ID" +
            " INNER JOIN POINTS PT ON R.POINT_TO_ID=PT.ID" +
            " INNER JOIN ROUTES_INFO RI ON R.ID=RI.ROUTE_ID  ON ROC.ROUTES_ID=R.ID" +
            " INNER JOIN CARRIERS CA ON ROC.CARRIERS_ID=CA.ID WHERE CON.EMAIL=?1 ORDER BY RI.DATE_START DESC",
            countQuery = "SELECT count(*) FROM ROUTES_ON_CARRIERS ROC" +
                    " INNER JOIN ROUTES R INNER JOIN POINTS PF ON R.POINT_FROM_ID=PF.ID" +
                    " INNER JOIN POINTS PT ON R.POINT_TO_ID=PT.ID" +
                    " INNER JOIN ROUTES_INFO RI ON R.ID=RI.ROUTE_ID  ON ROC.ROUTES_ID=R.ID" +
                    " INNER JOIN CARRIERS CA ON ROC.CARRIERS_ID=CA.ID",
            nativeQuery = true)
    Page<Map<String,Object>> findAllRoutesByEmailCreatorOrderByDateStartDesc(String  email,Pageable pageable);
}
