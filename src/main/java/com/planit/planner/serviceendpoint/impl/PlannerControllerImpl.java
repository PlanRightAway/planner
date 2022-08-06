package com.planit.planner.serviceendpoint.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.planit.journey_common.model.PlanCriteria;
import com.planit.journey_common.model.Plans;
import com.planit.journey_common.model.Point;
import com.planit.journey_common.model.TopPlans;
import com.planit.planner.point.PointService;
import com.planit.planner.serviceendpoint.PlannerController;
import com.planit.planner.travelplanner.TravelPlannerService;

@RestController
public class PlannerControllerImpl implements PlannerController {

	@Autowired
	private TravelPlannerService travelPlannerService;

	@Autowired
	private PointService pointService;

	@Override
	@GetMapping("/getPointsByhint/{hint}")
	public List<Point> getPointsByName(@PathVariable String hint) {
		return pointService.getPointsByStringMatch(hint);
	}

	@Override
	@PostMapping("/makePlans")
	public TopPlans makePlans(@RequestBody PlanCriteria planCriteria) {
		planCriteria.setSourcePoint(pointService.getPointByName(planCriteria.getSourcePoint().getName()));
		planCriteria.setDestinationPoint(pointService.getPointByName(planCriteria.getDestinationPoint().getName()));
		return addPointDetails(travelPlannerService.makePlans(1, 1, planCriteria));
	}

	private TopPlans addPointDetails(TopPlans plans) {
		plans.getPlans().getPlans().stream().forEach(p -> {
			p.getJournies().stream().forEach(j -> {
				j.setDestinationPoint(pointService.getPointInfo(j.getDestinationPoint()));
				j.setSourcePoint(pointService.getPointInfo(j.getSourcePoint()));
			});
		});
		return plans;
	}

}
