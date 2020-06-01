package com.salesDataAnalysis.service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

import com.salesDataAnalysis.config.FileConfig;
import com.salesDataAnalysis.dto.ReportDataDTO;
import com.salesDataAnalysis.factory.ReportDataFileFactory;
import com.salesDataAnalysis.util.FileUtil;

/**
 * Classe responsável por mediar entre a montagem do DTO e a geração do relatório de venda
 *
 * @author <a href="mailto:jose.freitas@ntconsult.com.br">jose.freitas</a>
 * @since 30/05/2020 09:38:00
 * 
 */
@Component
public class SalesDataAnalysis {
	
	public void generateSaleReport(Path filePath) throws IOException {
		
		ReportDataDTO reportDataDTO = new ReportDataFileFactory(filePath).BuildDTO();
		
		createAndWriteToOutputFileReport(filePath, reportDataDTO);
	}
	
	
	private void createAndWriteToOutputFileReport(Path filePath, ReportDataDTO reportDataDTO) {
		String pathName = filePath.getFileName().toString();
        Path pathOut = Paths.get(FileConfig.PATH_OUT.toString(),
                String.valueOf(pathName).replaceAll(FileConfig.FILE_EXTENSION,
                FileConfig.FILE_OUT_EXTENSION));

        List<String> lines = transformDTOInReportString(reportDataDTO);

        FileUtil.writeToFile(pathOut, lines);
    }

    private List<String> transformDTOInReportString(ReportDataDTO reportDataDTO) {
        List<String> lines = new ArrayList<>();
        if(Objects.nonNull(reportDataDTO)){

            lines.add(getLineNumberOfClients(reportDataDTO));
            lines.add(getLineNumberOfSalesmen(reportDataDTO));
            lines.add(getLineIdOfBiggerSale(reportDataDTO));
            lines.add(getLineWorstSalesman(reportDataDTO));
        }
        return lines;
    }

    private String getLineNumberOfClients(ReportDataDTO reportDataDTO) {
        return "Quantidade de clientes: " + reportDataDTO.getClients().size();
    }

    private String getLineNumberOfSalesmen(ReportDataDTO reportDataDTO) {
        return "Quantidade de vendedores: " + reportDataDTO.getSalesmen().size();
    }

    private String getLineIdOfBiggerSale(ReportDataDTO reportDataDTO) {
        return "ID da venda mais cara: " + reportDataDTO.getIIdMostExpensiveSale();
    }

    private String getLineWorstSalesman(ReportDataDTO reportDataDTO) {
        return "Pior vendedor: " + reportDataDTO.getWorstSalesman();
    }
}
