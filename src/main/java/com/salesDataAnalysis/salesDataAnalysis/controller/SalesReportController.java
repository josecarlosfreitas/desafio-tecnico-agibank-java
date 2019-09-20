package com.salesDataAnalysis.salesDataAnalysis.controller;

import com.salesDataAnalysis.salesDataAnalysis.FileProcessor.ReportFileProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Controlador que inicializa a Thread do relatório
 *
 * @author <a href="mailto:jose.freitas@ntconsult.com.br">jose.freitas</a>
 * @since 20/09/2019 09:28:00
 */
@Controller
public class SalesReportController {

    @Autowired
    public SalesReportController(ReportFileProcessor reportFileProcessor) {
        reportFileProcessor.start();
    }
}
