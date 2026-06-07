package org.example.hgugaticar.service;

import lombok.RequiredArgsConstructor;
import org.example.hgugaticar.model.Place;
import org.example.hgugaticar.repository.PlaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    public List<Place> getAllPlaces() {
        return placeRepository.findAll();
    }

    public Place createPlace(Place place) {
        return placeRepository.save(place);
    }
}