package com.salesDataAnalysis.salesDataAnalysis.config;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Classe de configuração dos arquivos
 *
 * @author <a href="mailto:jose.freitas@ntconsult.com.br">jose.freitas</a>
 * @since 20/09/2019 09:43:00
 */
public class FileConfig {

    public static final Path PATH_DATA = Paths.get(System.getProperty("user.home") + "\\data\\");
    public static final Path PATH_IN = Paths.get(System.getProperty("user.home") + "\\data\\in\\");
    public static final Path PATH_OUT = Paths.get(System.getProperty("user.home") + "\\data\\out\\");
    public static final String FILE_EXTENSION = ".dat";
    public static final String FILE_OUT_EXTENSION = ".out.dat";

}
