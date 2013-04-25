package com.interzonedev.dataporter.web.exporter;

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

import com.interzonedev.dataporter.service.DataSourceProperties;
import com.interzonedev.dataporter.service.exporter.DataExporter;
import com.interzonedev.dataporter.service.exporter.DataExporterException;
import com.interzonedev.dataporter.web.DataPorterController;

@Controller
@RequestMapping(value = "/export")
public class ExporterController extends DataPorterController {

	public static final String FORM_VIEW = "exporter/exporterForm";

	@Inject
	@Named("dataExporter")
	private DataExporter dataExporter;

	@RequestMapping(method = RequestMethod.GET)
	public String displayExporterForm(Model model) {
		log.debug("displayExporterForm");

		model.addAttribute("exporterForm", new ExporterForm());

		return FORM_VIEW;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String exportData(@Valid ExporterForm exporterForm, BindingResult result, HttpServletResponse response)
			throws IOException, DataExporterException {

		log.debug("exportData: exporterForm - " + exporterForm);

		if (result.hasErrors()) {
			log.debug("Form has errors");
			return FORM_VIEW;
		}

		DataSourceProperties dataSourceProperties = new DataSourceProperties(exporterForm.getDriverClassName().trim(),
				exporterForm.getUrl().trim(), exporterForm.getUsername().trim(), exporterForm.getPassword().trim());

		List<String> tableNames = Arrays.asList(exporterForm.getTableNames().trim().split("\\s*,\\s*"));

		byte[] output = dataExporter.exportData(dataSourceProperties, tableNames);

		String exportFilename = exporterForm.getExportFilename();
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
