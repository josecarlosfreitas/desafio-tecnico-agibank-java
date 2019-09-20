package com.salesDataAnalysis.salesDataAnalysis.FileProcessor;

import com.salesDataAnalysis.salesDataAnalysis.config.FileConfig;
import com.salesDataAnalysis.salesDataAnalysis.dto.ReportDataDTO;
import com.salesDataAnalysis.salesDataAnalysis.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

/**
 * Classe que faz o processamento do arquivo de entrada para o do relatório na saída
 *
 * @author <a href="mailto:jose.freitas@ntconsult.com.br">jose.freitas</a>
 * @since 20/09/2019 09:28:00
 */
@Component
public class ReportFileProcessor extends Thread {

    private final InputFileProcessor inputFileProcessor;
    private final OutputFileProcessor outputFileProcessor;

    @Autowired
    public ReportFileProcessor(InputFileProcessor inputFileProcessor, OutputFileProcessor outputFileProcessor) {
        this.inputFileProcessor = inputFileProcessor;
        this.outputFileProcessor = outputFileProcessor;
    }

    private static final Path folder =  FileConfig.PATH_IN;

    @Override
    public void run() {

        WatchService watchService;
        try {
            createDirectories();
            watchService = FileSystems.getDefault().newWatchService();

            folder.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        while (true) {
            WatchKey key;
            try {
                //return signaled key, meaning events occurred on the object
                key = watchService.take();
            } catch (InterruptedException ex) {
                return;
            }

            //retrieve all the accumulated events
            for (WatchEvent<?> event : key.pollEvents()) {
                Path path = (Path)event.context();
                Path filePath = Paths.get(String.valueOf(folder), String.valueOf(path.toString()));

                ReportDataDTO reportDataDTO = null;
                try {
                    reportDataDTO = inputFileProcessor.fileProcess(filePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                outputFileProcessor.process(reportDataDTO, path);
            }
            boolean valid = key.reset();
            if (!valid) {
                break;
            }
        }
    }

    private void createDirectories() throws IOException {
        FileUtil.createDirectory(FileConfig.PATH_DATA);
        FileUtil.createDirectory(FileConfig.PATH_IN);
        FileUtil.createDirectory(FileConfig.PATH_OUT);
    }

}
