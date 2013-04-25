package com.interzonedev.dataporter.web.importer;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.interzonedev.dataporter.service.DataSourceProperties;
import com.interzonedev.dataporter.service.importer.DataImporter;
import com.interzonedev.dataporter.service.importer.DataImporterException;
import com.interzonedev.dataporter.web.DataPorterController;

@Controller
@RequestMapping(value = "/importer")
public class ImporterController extends DataPorterController {

	public static final String FORM_VIEW = "importer/importerForm";
	public static final String RESULTS_VIEW = "importer/importerResults";

	@Inject
	@Named("dataImporter")
	private DataImporter dataImporter;

	@RequestMapping(method = RequestMethod.GET)
	public String displayImporterForm(Model model) {
		log.debug("displayImporterForm");

		model.addAttribute("importerForm", new ImporterForm());

		return FORM_VIEW;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String importData(ModelMap model, @Valid ImporterForm importerForm, BindingResult result)
			throws IOException, DataImporterException {

		log.debug("importData: importerForm - " + importerForm);

		if (result.hasErrors()) {
			log.debug("Form has errors");
			return FORM_VIEW;
		}

		DataSourceProperties dataSourceProperties = new DataSourceProperties(importerForm.getDriverClassName().trim(),
				importerForm.getUrl().trim(), importerForm.getUsername().trim(), importerForm.getPassword().trim());

		MultipartFile importFile = importerForm.getImportFile();

		byte[] importFileContents = importFile.getBytes();

		dataImporter.importData(dataSourceProperties, importFileContents);

		return RESULTS_VIEW;

	}

}
