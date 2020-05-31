package com.salesDataAnalysis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.salesDataAnalysis.fileWatcher.FileWatcher;

/**
 * Controlador que inicializa a Thread do relatório
 *
 * @author <a href="mailto:jose.freitas@ntconsult.com.br">jose.freitas</a>
 * @since 30/05/2020 09:28:00
 */
@Controller
public class SalesReportController {

    @Autowired
    public SalesReportController(FileWatcher fileWatcher) {
        fileWatcher.start();
    }
}
