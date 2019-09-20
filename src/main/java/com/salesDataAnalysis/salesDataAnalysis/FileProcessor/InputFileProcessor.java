package com.salesDataAnalysis.salesDataAnalysis.FileProcessor;

import com.salesDataAnalysis.salesDataAnalysis.domain.Client;
import com.salesDataAnalysis.salesDataAnalysis.domain.Enum.AnalysisDataTypeEnum;
import com.salesDataAnalysis.salesDataAnalysis.domain.Sale;
import com.salesDataAnalysis.salesDataAnalysis.domain.Salesman;
import com.salesDataAnalysis.salesDataAnalysis.dto.ReportDataDTO;
import com.salesDataAnalysis.salesDataAnalysis.parse.ClientParse;
import com.salesDataAnalysis.salesDataAnalysis.parse.SaleParse;
import com.salesDataAnalysis.salesDataAnalysis.parse.SalesmanParse;
import com.salesDataAnalysis.salesDataAnalysis.util.FileUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe que faz o processamento do arquivo de entrada
 * transforma os dados pegos em cada linha e joga para o dto
 *
 * @author <a href="mailto:jose.freitas@ntconsult.com.br">jose.freitas</a>
 * @since 20/09/2019 09:29:00
 */
@Component
public class InputFileProcessor {

    private final String FILE_SPLIT_LINE = "ç";

    public ReportDataDTO fileProcess(Path filePath) throws IOException {

        List<String> fileLines = FileUtil.readFileLines(filePath);

        ReportDataDTO reportDataDTO = new ReportDataDTO();
        reportDataDTO.setClients(transformFileLinesInClients(fileLines));
        reportDataDTO.setSalesmen(transformFileLinesInSalesmen(fileLines));
        reportDataDTO.setSales(transformFileLinesInSales(fileLines, reportDataDTO.getSalesmen()));

        return reportDataDTO;
    }

    private List<Client> transformFileLinesInClients(List<String> fileLines){
        return fileLines
                .stream()
                .filter(line -> !StringUtils.isEmpty(line)
                        && AnalysisDataTypeEnum.CLIENT.getCode().equals(line.split(FILE_SPLIT_LINE)[0]))
                .map(line -> ClientParse.parse(line.split(FILE_SPLIT_LINE)))
                .collect(Collectors.toList());
    }

    private List<Salesman> transformFileLinesInSalesmen(List<String> fileLine){
        return fileLine
                .stream()
                .filter(line -> !StringUtils.isEmpty(line)
                        && AnalysisDataTypeEnum.SALESMAN.getCode().equals(line.split(FILE_SPLIT_LINE)[0]))
                .map(line -> SalesmanParse.parse(line.split(FILE_SPLIT_LINE)))
                .collect(Collectors.toList());
    }

    private List<Sale> transformFileLinesInSales(List<String> fileLine, List<Salesman> salesmen){
        return fileLine
                .stream()
                .filter(line -> !StringUtils.isEmpty(line)
                        && AnalysisDataTypeEnum.SALE.getCode().equals(line.split(FILE_SPLIT_LINE)[0]))
                .map(line -> SaleParse.parse(line.split(FILE_SPLIT_LINE), salesmen))
                .collect(Collectors.toList());
    }

}
