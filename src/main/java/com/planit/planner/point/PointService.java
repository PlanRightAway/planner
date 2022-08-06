package com.planit.planner.point;

import java.util.List;

import com.planit.journey_common.model.Point;

public interface PointService {

	List<Point> getPointsByStringMatch(String hint);

	Point getPointInfo(Point point);

	Point getPointByName(String name);

}
