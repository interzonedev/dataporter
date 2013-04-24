package com.interzonedev.dataporter.web.export;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.interzonedev.dataporter.service.DataExporter;
import com.interzonedev.dataporter.service.DataSourceProperties;
import com.interzonedev.dataporter.web.DataPorterController;

@Controller
@RequestMapping(value = "/export")
public class ExportController extends DataPorterController {

	public static final String FORM_VIEW = "export/exportForm";

	@Inject
	@Named("dataExporter")
	private DataExporter dataExporter;

	@RequestMapping(method = RequestMethod.GET)
	public String displayExportForm(Model model) {
		log.debug("displayExportForm");

		model.addAttribute("exportForm", new ExportForm());

		return FORM_VIEW;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String exportData(@Valid ExportForm exportForm, BindingResult result, HttpServletResponse response)
			throws IOException {

		log.debug("exportData");

		if (result.hasErrors()) {
			log.debug("Form has errors");
			return FORM_VIEW;
		}

		DataSourceProperties dataSourceProperties = new DataSourceProperties(exportForm.getDriverClassName().trim(),
				exportForm.getUrl().trim(), exportForm.getUsername().trim(), exportForm.getPassword().trim());

		List<String> tableNames = null;
		if (!exportForm.isAllTables() && StringUtils.isNotBlank(exportForm.getTableNames())) {
			tableNames = Arrays.asList(exportForm.getTableNames().trim().split(","));
		}

		byte[] output = dataExporter.export(dataSourceProperties, tableNames);

		String exportFilename = exportForm.getExportFilename();
		if (StringUtils.isBlank(exportFilename)) {
			exportFilename = generateExportFilename();
		}

		response.setContentType("applicaton/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=" + exportFilename);

		OutputStream out = response.getOutputStream();
		out.write(output);
		out.flush();

		return null;

	}

	private String generateExportFilename() {

		StringBuilder filename = new StringBuilder("data_export_");

		DateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_SSS");
		String currentDateStamp = formatter.format(new Date());

		filename.append(currentDateStamp);
		filename.append(".xml");

		return filename.toString();

	}

}
