package com.salesDataAnalysis.fileWatcher;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.salesDataAnalysis.config.FileConfig;
import com.salesDataAnalysis.service.SalesDataAnalysis;
import com.salesDataAnalysis.util.FileUtil;

/**
 * Classe que faz o monitoramento dos arquivos na pasta de entrada
 *
 * @author <a href="mailto:jose.freitas@ntconsult.com.br">jose.freitas</a>
 * @since 30/05/2020 09:28:00
 */
@Component
public class FileWatcher extends Thread {

    private final SalesDataAnalysis salesDataAnalysis;

    @Autowired
    public FileWatcher(SalesDataAnalysis salesDataAnalysis) {
        this.salesDataAnalysis = salesDataAnalysis;
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

                try {
                	salesDataAnalysis.generateSaleReport(filePath);
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
