package com.planit.planner.point.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.planit.journey_common.model.Point;
import com.planit.planner.point.PointService;

@Service
public class PointServiceImpl implements PointService {

	private static final String POINT_OPS_SERVICE = "POINT-OPS-SERVICE";

	@Autowired
	protected WebClient.Builder webClientBuilder;

	public List<Point> getPointsByStringMatch(String hint) {
		return webClientBuilder.baseUrl(String.format("http://%s", POINT_OPS_SERVICE)).build().get()
				.uri(String.format("/getPointsByStringMatch/%s", hint)).retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<Point>>() {
				}).block();
	}

	@Override
	public Point getPointInfo(Point point) {
		return webClientBuilder.baseUrl(String.format("http://%s", POINT_OPS_SERVICE)).build().post()
				.uri("/getPointInfo").bodyValue(point).retrieve().bodyToMono(Point.class).block();
	}

	@Override
	public Point getPointByName(String name) {
		return webClientBuilder.baseUrl(String.format("http://%s", POINT_OPS_SERVICE)).build().get()
				.uri(String.format("/getPointByName/%s", name)).retrieve().bodyToMono(Point.class).block();
	}

}
