package com.sales.data.analysis.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Classe utilitária de arquivo
 *
 * @author <a href="mailto:jose.freitas@ntconsult.com.br">jose.freitas</a>
 * @since 30/05/2020 09:38:00
 * 
 */
public class FileUtil {

    public static void createDirectory(Path path) throws IOException {
        if (Files.notExists(path)) {
            Files.createDirectory(path.toAbsolutePath());
        }
    }

    public static List<String> TransformFilePathInListOfStringLine(Path filePath){
        List<String> lines = new ArrayList<>();
        
        try {
			lines = Files.lines(filePath, StandardCharsets.UTF_8).collect(Collectors.toList());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        return lines;
    }

    public static void writeToFile(Path pathOut, List<String> lines) {
        try {
            Files.write(Paths.get(pathOut.toString()),
                    lines,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Erro ao criar o arquivo: " + pathOut);
        }
    }

}
