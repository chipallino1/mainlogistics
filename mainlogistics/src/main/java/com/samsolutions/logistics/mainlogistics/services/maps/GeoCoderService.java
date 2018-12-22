package com.samsolutions.logistics.mainlogistics.services.maps;

import com.google.code.geocoder.model.GeocoderGeometry;
import org.springframework.stereotype.Component;

@Component
public interface GeoCoderService {
    GeocoderGeometry locationToCoordinate(String location);
    String coordinateToLocation(GeocoderGeometry geometry);
}
