package com.planit.planner.serviceendpoint;

import java.util.List;

import com.planit.journey_common.model.PlanCriteria;
import com.planit.journey_common.model.Point;
import com.planit.journey_common.model.TopPlans;

public interface PlannerController {
	
	public List<Point> getPointsByName(String hint);
	
	public TopPlans makePlans(PlanCriteria planCriteria);
}
