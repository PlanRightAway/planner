package com.planit.planner.travelplanner;

import com.planit.journey_common.model.PlanCriteria;
import com.planit.journey_common.model.TopPlans;

public interface TravelPlannerService {

	TopPlans makePlans(int pageNumber, int pageSize, PlanCriteria planCriteria);


}
