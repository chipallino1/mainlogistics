package com.samsolutions.logistics.mainlogistics.services.maps.impl;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.*;
import com.samsolutions.logistics.mainlogistics.services.maps.GeoCoderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeoCoderServiceImpl implements GeoCoderService{
    private final Geocoder geocoder = new Geocoder();
    @Override
    public GeocoderGeometry locationToCoordinate(String location) {
        final Geocoder geocoder = new Geocoder();
        GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress("Paris, France").setLanguage("en").getGeocoderRequest();
        GeocodeResponse geocoderResponse = geocoder.geocode(geocoderRequest);
        GeocoderGeometry coordinate = null;

        if (location != null && !location.isEmpty()) {
            GeocoderRequest request = new GeocoderRequest();
            request.setAddress(location);

            GeocodeResponse response = geocoder.geocode(request);
            if (response.getStatus() == GeocoderStatus.OK) {
                List<GeocoderResult> results = response.getResults();

                for (GeocoderResult result : results) {
                    GeocoderGeometry geometry = result.getGeometry();
                    coordinate = geometry;
                    break;
                }
            }
        }

        return coordinate;
    }

    @Override
    public String coordinateToLocation(GeocoderGeometry coordinate) {
        String location = null;

        if (coordinate != null) {
            GeocoderRequest request = new GeocoderRequest();
            request.setLocation(coordinate.getLocation());
            request.setBounds(coordinate.getBounds());

            GeocodeResponse response = geocoder.geocode(request);
            if (response.getStatus() == GeocoderStatus.OK) {
                List<GeocoderResult> results = response.getResults();
                for (GeocoderResult result : results) {
                    location = result.getFormattedAddress();
                    break;
                }
            }
        }

        return location;
    }
}
