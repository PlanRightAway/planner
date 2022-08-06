package com.planit.planner.travelplanner.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.planit.journey_common.model.PlanCriteria;
import com.planit.journey_common.model.TopPlans;
import com.planit.planner.travelplanner.TravelPlannerService;

@Service
public class TravelPlannerServiceImpl implements TravelPlannerService {
	
	@Autowired
	protected WebClient.Builder webClientBuilder;

	@Override
	public TopPlans makePlans(int pageNumber, int pageSize, PlanCriteria planCriteria) {
		return webClientBuilder.baseUrl(String.format("http://%s", "TRAVEL-PLANNER-SERVICE")).build().post()
				.uri(String.format("/makePlan/?pageSize=%d&pageNumber=%d", pageSize, pageNumber)).bodyValue(planCriteria).retrieve()
				.bodyToMono(TopPlans.class).block();
	}

}
