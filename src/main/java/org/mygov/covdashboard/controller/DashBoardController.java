package org.mygov.covdashboard.controller;import org.apache.commons.lang3.StringUtils;import org.mygov.covdashboard.exception.CovDashBoardException;import org.mygov.covdashboard.model.DashboardEntity;import org.mygov.covdashboard.model.DashboardQueryParam;import org.mygov.covdashboard.model.DashboardResponse;import org.mygov.covdashboard.service.DashboardService;import org.slf4j.Logger;import org.slf4j.LoggerFactory;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.http.HttpStatus;import org.springframework.http.ResponseEntity;import org.springframework.web.bind.annotation.GetMapping;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.RequestParam;import org.springframework.web.bind.annotation.RestController;import java.time.LocalDate;import java.time.format.DateTimeFormatter;import java.util.List;@RestController@RequestMapping("/api.mygov.org")public class DashBoardController {	private static final Logger LOGGER  = LoggerFactory.getLogger(DashBoardController.class);	@Autowired	DashboardService service;	@GetMapping("")	public String response() {		return "Welcome to covid data dashboard";	}	@GetMapping("/dashboard")	public ResponseEntity<DashboardResponse> getCovDashboardData(@RequestParam(name = "country") String country,																 @RequestParam(name = "province", required = false) String province,																 @RequestParam(name = "threshold", required = false) Integer threshold,																 @RequestParam(name = "fromDate", required = false) String fromDate,																 @RequestParam(name = "toDate", required = false) String toDate,																 @RequestParam(name = "date", required = false) String date) throws CovDashBoardException {		DashboardQueryParam dashboardQueryParam = buildParam(country, province, threshold, fromDate, toDate, date);		List<DashboardEntity> dashboard = service.findDashboard(dashboardQueryParam);		DashboardResponse response = DashboardResponse.builder().responseCode(HttpStatus.OK.toString()).responseMessage("Query executed succesfully with number of records: " + dashboard.size()).entityList(dashboard).build();		return new ResponseEntity<>(response, HttpStatus.OK);	}	private DashboardQueryParam buildParam(String country, String province, Integer threshold, String from, String to, String queryDate) throws CovDashBoardException {		return DashboardQueryParam.builder()				.date(buildDate(queryDate))				.fromDate(buildDate(from))				.toDate(buildDate(to))				.country(country)				.province(province)				.threshold(threshold)				.build();	}	private LocalDate buildDate(String input) throws CovDashBoardException {		try {			if(StringUtils.isNoneBlank(input)) {				return LocalDate.parse(input, DateTimeFormatter.ofPattern("yyyyMMdd"));			} else {				return null;			}		} catch (Exception e) {			LOGGER.error(e.getMessage());			throw new CovDashBoardException("Wrong date format.  Expected date format is 'yyyyMMdd'");		}	}}