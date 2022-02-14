package org.mygov.covdashboard.service;import org.mygov.covdashboard.business.FindDashboard;import org.mygov.covdashboard.exception.CovDashBoardException;import org.mygov.covdashboard.model.DashboardApiModel;import org.mygov.covdashboard.model.DashboardEntity;import org.mygov.covdashboard.model.DashboardQueryParam;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;import java.util.List;@Servicepublic class DashboardService {	@Autowired	FindDashboard findDashboard;	public List<DashboardEntity> findDashboard(DashboardQueryParam queryParams) throws CovDashBoardException {		return findDashboard.findDashboard(queryParams);	}}