package org.example.hgugaticar.service;

import lombok.RequiredArgsConstructor;
import org.example.hgugaticar.dto.RoutePreviewRequest;
import org.example.hgugaticar.dto.RoutePreviewResponse;
import org.example.hgugaticar.model.Place;
import org.example.hgugaticar.model.RouteType;
import org.example.hgugaticar.repository.PlaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteService {

    private final PlaceRepository placeRepository;

    public RoutePreviewResponse previewRoute(RoutePreviewRequest request) {
        String departure = request.getDeparture();
        String arrival = request.getArrival();

        RouteType routeType = determineRouteType(departure, arrival);
        String message = createMessage(routeType, departure, arrival);

        return new RoutePreviewResponse(
                departure,
                arrival,
                request.getDepartureDate(),
                routeType,
                message
        );
    }

    private RouteType determineRouteType(String departure, String arrival) {
        if (isPohangStationAndHgu(departure, arrival)) {
            return RouteType.TRAIN_BASED;
        }

        if (isHguAndMainPlace(departure, arrival)) {
            return RouteType.HOURLY_BASED;
        }

        return RouteType.CUSTOM_PLACE;
    }

    private boolean isPohangStationAndHgu(String departure, String arrival) {
        return (departure.equals("포항역") && arrival.equals("한동대"))
                || (departure.equals("한동대") && arrival.equals("포항역"));
    }

    private boolean isHguAndMainPlace(String departure, String arrival) {
        if (departure.equals("한동대")) {
            return isMainPlace(arrival);
        }

        if (arrival.equals("한동대")) {
            return isMainPlace(departure);
        }

        return false;
    }

    private boolean isMainPlace(String placeName) {
        List<Place> places = placeRepository.findAll();

        return places.stream()
                .filter(Place::isMainPlace)
                .anyMatch(place -> place.getName().equals(placeName));
    }

    private String createMessage(RouteType routeType, String departure, String arrival) {
        if (routeType == RouteType.TRAIN_BASED) {
            if (departure.equals("포항역") && arrival.equals("한동대")) {
                return "기차가 포항역에 도착하는 시간 기준으로 리스트를 보여줍니다.";
            }

            if (departure.equals("한동대") && arrival.equals("포항역")) {
                return "기차가 포항역에서 출발하는 시간 기준으로 리스트를 보여줍니다.";
            }
        }

        if (routeType == RouteType.HOURLY_BASED) {
            return "00시부터 23시까지 1시간 단위 리스트를 보여줍니다.";
        }

        return "생성된 방 목록을 장소별, 시간별로 정렬해서 보여줍니다.";
    }
}