package com.salesDataAnalysis.Enum;

import java.util.stream.Stream;

public enum AnalysisDataTypeEnum {
    SALESMAN("001"),
    CLIENT("002"),
    SALE("003");

    private final String code;

    AnalysisDataTypeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    /**
     * Converte o parâmetro em {@link AnalysisDataTypeEnum} baseado no seu code.
     * Se for passado um código inexistente é disparada {@link IllegalArgumentException}.
     *
     * @param code código do tipo de domínio
     * @return {@link AnalysisDataTypeEnum}
     */
    public static AnalysisDataTypeEnum getValue(String code) {
        return Stream.of(values()).filter(e -> e.code.equals(code)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Código não existe no AnalysisDataTypeEnum: código=" + code));
    }
}
