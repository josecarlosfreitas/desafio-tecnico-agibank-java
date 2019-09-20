package com.salesDataAnalysis.salesDataAnalysis.FileProcessor;

import com.salesDataAnalysis.salesDataAnalysis.config.FileConfig;
import com.salesDataAnalysis.salesDataAnalysis.dto.ReportDataDTO;
import com.salesDataAnalysis.salesDataAnalysis.service.SalesDataAnalysis;
import com.salesDataAnalysis.salesDataAnalysis.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe que faz o processamento do arquivo de saída
 *
 *
 * @author <a href="mailto:jose.freitas@ntconsult.com.br">jose.freitas</a>
 * @since 18/09/2019 13:52:00
 */
@Component
public class OutputFileProcessor {

    private final SalesDataAnalysis salesDataAnalysis;

    @Autowired
    public OutputFileProcessor(SalesDataAnalysis salesDataAnalysis) {
        this.salesDataAnalysis = salesDataAnalysis;
    }

    public void process(ReportDataDTO reportDataDTO, Path filePath){

        salesDataAnalysis.setReportDataDTO(reportDataDTO);
        createAndWriteToOutputFile(filePath);
    }

    private void createAndWriteToOutputFile(Path filePath) {
        Path pathOut = Paths.get(FileConfig.PATH_OUT.toString(),
                String.valueOf(filePath).replaceAll(FileConfig.FILE_EXTENSION,
                FileConfig.FILE_OUT_EXTENSION));

        List<String> lines = transformDTOInReportString();

        FileUtil.writeToFile(pathOut, lines);
    }

    private List<String> transformDTOInReportString() {
        List<String> lines = new ArrayList<>();
        if(Objects.nonNull(salesDataAnalysis.getReportDataDTO())){

            lines.add(getLineNumberOfClients());
            lines.add(getLineNumberOfSalesmen());
            lines.add(getLineIdOfBiggerSale());
            lines.add(getLineWorstSalesman());
        }

        return lines;
    }

    private String getLineNumberOfClients() {
        return "Quantidade de clientes no arquivo de entrada: " + salesDataAnalysis.numberOfClients();
    }

    private String getLineNumberOfSalesmen() {
        return "Quantidade de vendedores no arquivo de entrada: " + salesDataAnalysis.numberOfSalesmen();
    }

    private String getLineIdOfBiggerSale() {
        return "ID da venda mais cara: " + salesDataAnalysis.idMostExpensiveSale();
    }

    private String getLineWorstSalesman() {
        return "O pior vendedor: " + salesDataAnalysis.worstSalesman();
    }

}
