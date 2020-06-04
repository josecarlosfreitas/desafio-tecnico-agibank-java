package com.sales.data.analysis.factory;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.StringUtils;

import com.sales.data.analysis.Enum.AnalysisDataTypeEnum;
import com.sales.data.analysis.domain.Client;
import com.sales.data.analysis.domain.Sale;
import com.sales.data.analysis.domain.Salesman;
import com.sales.data.analysis.dto.ReportDataDTO;
import com.sales.data.analysis.util.FileUtil;

/**
 * Classe que faz a montagem da DTO para a análise dos dados
 *
 * @author <a href="mailto:jose.freitas@ntconsult.com.br">jose.freitas</a>
 * @since 30/05/2020 09:29:00
 */
public class ReportDataFileFactory implements IReportDataFileFactory {

	Path _filePath;
	ReportDataDTO _reportDataDTO;
	private final String FILE_SPLIT_LINE = "ç";
	
	public ReportDataFileFactory(Path _filePath) {
		super();
		this._filePath = _filePath;
		this._reportDataDTO = new ReportDataDTO();
	}

	public ReportDataDTO BuildDTO() {

        List<String> fileLines = FileUtil.TransformFilePathInListOfStringLine(_filePath);

        ReportDataDTO reportDataDTO = new ReportDataDTO();
        reportDataDTO.setClients(transformFileLinesInClients(fileLines));
        reportDataDTO.setSalesmen(transformFileLinesInSalesmen(fileLines));
        reportDataDTO.setSales(transformFileLinesInSales(fileLines));

        return reportDataDTO;
    }
	
	private List<Client> transformFileLinesInClients(List<String> fileLines){
        return fileLines
                .stream()
                .filter(line -> !StringUtils.isEmpty(line)
                        && AnalysisDataTypeEnum.CLIENT.getCode().equals(line.split(FILE_SPLIT_LINE)[0]))
                .map(line -> new Client(line.split(FILE_SPLIT_LINE)))
                .collect(Collectors.toList());
    }

    private List<Salesman> transformFileLinesInSalesmen(List<String> fileLine){
        return fileLine
                .stream()
                .filter(line -> !StringUtils.isEmpty(line)
                        && AnalysisDataTypeEnum.SALESMAN.getCode().equals(line.split(FILE_SPLIT_LINE)[0]))
                .map(line -> new Salesman(line.split(FILE_SPLIT_LINE)))
                .collect(Collectors.toList());
    }

    private List<Sale> transformFileLinesInSales(List<String> fileLine){
        return fileLine
                .stream()
                .filter(line -> !StringUtils.isEmpty(line)
                        && AnalysisDataTypeEnum.SALE.getCode().equals(line.split(FILE_SPLIT_LINE)[0]))
                .map(line -> new Sale(line.split(FILE_SPLIT_LINE)))
                .collect(Collectors.toList());
    }
}
