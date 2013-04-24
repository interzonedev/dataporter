package com.interzonedev.dataporter.web.export;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.interzonedev.dataporter.service.DataSourceFinder;
import com.interzonedev.dataporter.web.DataPorterController;

@Controller
@RequestMapping(value = "/export")
public class ExportController extends DataPorterController {

	@Inject
	@Named("dataSourceFinder")
	private DataSourceFinder dataSourceFinder;

	@RequestMapping(method = RequestMethod.GET)
	public String getDataSourcesInContext(Model model) {
		log.debug("getAllUsers");

		List<DataSource> dataSources = dataSourceFinder.getDataSourcesInContext();
		model.addAttribute("dataSources", dataSources);

		return "users/viewAllDataSources";
	}

}
