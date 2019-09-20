package com.salesDataAnalysis.salesDataAnalysis.service;

import com.salesDataAnalysis.salesDataAnalysis.domain.Sale;
import com.salesDataAnalysis.salesDataAnalysis.domain.Salesman;
import com.salesDataAnalysis.salesDataAnalysis.dto.ReportDataDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Classe onde é feito a análise dos dados de vendas
 *
 * @author <a href="mailto:jose.freitas@ntconsult.com.br">jose.freitas</a>
 * @since 20/09/2019 09:38:00
 */
@Component
public class SalesDataAnalysis {

    private ReportDataDTO reportDataDTO;

    public ReportDataDTO getReportDataDTO() {
        return reportDataDTO;
    }

    public void setReportDataDTO(ReportDataDTO reportDataDTO) {
        this.reportDataDTO = reportDataDTO;
    }

    /**
     *  Pega a quantidade de clientes conforme os dados do DTO
     *
     * @return quantidade de clientes
     */
    public Integer numberOfClients(){
        if(Objects.nonNull(reportDataDTO)){
            return reportDataDTO.getClients().size();
        }
        return 0;
    }

    /**
     *  Pega a quantidade de vendedores conforme os dados do DTO
     *
     * @return quantidade de vendedores
     */
    public Integer numberOfSalesmen(){
        if(Objects.nonNull(reportDataDTO)){
            return reportDataDTO.getSalesmen().size();
        }
        return 0;
    }

    /**
     *  Pega o id da venda mais cara conforme os dados do DTO
     *
     * @return id da venda mais cara
     */
    public Integer idMostExpensiveSale(){
        if(isThereAnySale()){

            Sale sale =  reportDataDTO.getSales().stream()
                    .max(Comparator.comparing(Sale::getTotal)).orElse(null);

            if (Objects.nonNull(sale)){
                return sale.getId();
            }
        }
        return null;
    }

    /**
     *  Pega o pior vendedor conforme os dados do DTO
     *
     * @return pior vendedor
     */
    public String worstSalesman(){
        if(isThereAnySale()){

            Map<Salesman, BigDecimal> salesmanGroupMap =
                    reportDataDTO.getSales().stream().collect(Collectors.groupingBy(Sale::getSalesman
                            , Collectors.reducing(BigDecimal.ZERO, Sale::getTotal, BigDecimal::add)));

            Map.Entry<Salesman, BigDecimal> salesmanWithWorstSale = Collections.min(salesmanGroupMap.entrySet(),
                    Comparator.comparing(Map.Entry::getValue));

            if (Objects.nonNull(salesmanWithWorstSale) && Objects.nonNull(salesmanWithWorstSale.getKey())){
                return salesmanWithWorstSale.getKey().getName();
            }
        }
        return null;
    }

    private boolean isThereAnySale(){
        return Objects.nonNull(reportDataDTO)
                && Objects.nonNull(reportDataDTO.getSales())
                && reportDataDTO.getSales().size() > 0;
    }
}
