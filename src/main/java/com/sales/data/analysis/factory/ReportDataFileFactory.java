package com.sales.data.analysis.factory;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

import com.sales.data.analysis.Enum.AnalysisDataTypeEnum;
import com.sales.data.analysis.domain.Client;
import com.sales.data.analysis.domain.Sale;
import com.sales.data.analysis.domain.Salesman;
import com.sales.data.analysis.dto.ReportDataDTO;

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

        ReportDataDTO reportDataDTO = new ReportDataDTO();
        
        try (Scanner sc = new Scanner(_filePath, "UTF-8")) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                
                String splitLine = line.split(FILE_SPLIT_LINE)[0];
                
                if (AnalysisDataTypeEnum.CLIENT.getCode().equals(splitLine)) {
					reportDataDTO.getClients().add(new Client(line.split(FILE_SPLIT_LINE)));
                }else
                
            	if (AnalysisDataTypeEnum.SALESMAN.getCode().equals(splitLine)) {
					reportDataDTO.getSalesmen().add(new Salesman(line.split(FILE_SPLIT_LINE)));
            	}else
            		
            	if (AnalysisDataTypeEnum.SALE.getCode().equals(splitLine)) {
					reportDataDTO.getSales().add(new Sale(line.split(FILE_SPLIT_LINE)));
				}
                
            }
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return reportDataDTO;
    }
	
}
