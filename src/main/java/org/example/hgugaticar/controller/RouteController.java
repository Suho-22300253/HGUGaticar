package org.example.hgugaticar.controller;

import lombok.RequiredArgsConstructor;
import org.example.hgugaticar.dto.RoutePreviewRequest;
import org.example.hgugaticar.dto.RoutePreviewResponse;
import org.example.hgugaticar.service.RouteService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
public class RouteController {

    private final RouteService routeService;

    @PostMapping("/preview")
    public RoutePreviewResponse previewRoute(@RequestBody RoutePreviewRequest request) {
        return routeService.previewRoute(request);
    }
}