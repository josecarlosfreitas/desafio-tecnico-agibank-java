package com.salesDataAnalysis.builder;

import java.io.IOException;

import com.salesDataAnalysis.dto.ReportDataDTO;

public interface IReportDataFileBuilder {
	
	public ReportDataDTO BuildDTO() throws IOException;
	
}
