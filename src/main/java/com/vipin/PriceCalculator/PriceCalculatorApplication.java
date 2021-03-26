package com.vipin.PriceCalculator;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.vipin.PriceCalculator.processor.CycleConsumer;
import com.vipin.PriceCalculator.processor.CycleProducer;
import com.vipin.PriceCalculator.repository.BicycleComponentRepository;

@SpringBootApplication
@EnableJpaRepositories
public class PriceCalculatorApplication {

    private static final long INTERVAL = 30000;

    private static int BOUND = 100;

    private static BlockingQueue<Cycle> queue = new LinkedBlockingQueue<>(BOUND);

    private static Executor producerExecutor;

    private static Executor consumerExecutor;

    public static void main(String[] args) {
        final ConfigurableApplicationContext applicationContext = SpringApplication.run(PriceCalculatorApplication.class, args);
        BicycleComponentRepository repositoryBean = applicationContext.getBean(BicycleComponentRepository.class);
        String inputFolderPath = applicationContext.getEnvironment().getProperty("priceCalulator.inputLocation");
        System.out.println(inputFolderPath);
        System.out.println("/home/vipin/atest/");
        File inputFolder = new File(inputFolderPath);
        Long dateOfPricing = applicationContext.getEnvironment().getProperty("priceCalulator.dateOfPricing", Long.class, System.currentTimeMillis());
        producerExecutor = Executors.newFixedThreadPool(5);
        consumerExecutor = Executors.newFixedThreadPool(5);
        startConsumers(repositoryBean, dateOfPricing);
        try {
            while (true) {
                crawlDirectoryAndProcessFiles(inputFolder, producerExecutor);
                Thread.sleep(INTERVAL);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void crawlDirectoryAndProcessFiles(File directory, Executor executor) {
        if (!directory.isDirectory()) {
            return;
        }
        for (File file : directory.listFiles()) {
            if (file.isDirectory()) {
                crawlDirectoryAndProcessFiles(file, executor);
            } else {
                try {
                    String jsonString = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
                    executor.execute(new CycleProducer(queue, jsonString));
                } catch (IOException e) {
                    continue;
                } finally {
                    if (file.isFile()) {
                        file.delete();
                    }
                }

            }
        }
    }

    private static void startConsumers(BicycleComponentRepository repositoryBean, Long dateOfPricing) {
        consumerExecutor.execute(new CycleConsumer(queue, repositoryBean, dateOfPricing));
        consumerExecutor.execute(new CycleConsumer(queue, repositoryBean, dateOfPricing));
        consumerExecutor.execute(new CycleConsumer(queue, repositoryBean, dateOfPricing));
        consumerExecutor.execute(new CycleConsumer(queue, repositoryBean, dateOfPricing));
        consumerExecutor.execute(new CycleConsumer(queue, repositoryBean, dateOfPricing));
    }
}
