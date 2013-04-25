package com.interzonedev.dataporter.web.importer;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import com.interzonedev.dataporter.web.ImporterExporterForm;

public class ImporterForm extends ImporterExporterForm {

	@NotEmpty
	private String importFilePath;

	private MultipartFile importFile;

	public String getImportFilePath() {
		return importFilePath;
	}

	public void setImportFilePath(String importFilePath) {
		this.importFilePath = importFilePath;
	}

	public MultipartFile getImportFile() {
		return importFile;
	}

	public void setImportFile(MultipartFile importFile) {
		this.importFile = importFile;
		if (!this.importFile.isEmpty()) {
			setImportFilePath(this.importFile.getOriginalFilename());
		}
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append(super.toString()).append("importFilePath", getImportFilePath())
				.toString();
	}

}
