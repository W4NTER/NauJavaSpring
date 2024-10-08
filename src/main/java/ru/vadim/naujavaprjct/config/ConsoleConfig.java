package ru.vadim.naujavaprjct.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class ConsoleConfig {
    private final static Logger LOGGER = LogManager.getLogger();
    private final String COMMAND_TO_EXIT = "exit";
    private final CommandProcessor commandProcessor;

    public ConsoleConfig(CommandProcessor commandProcessor) {
        this.commandProcessor = commandProcessor;
    }

    @Bean
    public CommandLineRunner commandScanner() {
        return args -> {
            try (Scanner scanner = new Scanner(System.in)) {
                LOGGER.info("Start program");
                LOGGER.info("Введите команду \"exit\" для выхода или команду \"list\" чтобы увидеть список команд");
                while (true) {
                    System.out.print("> ");
                    String input = scanner.nextLine();
                    if (COMMAND_TO_EXIT.equalsIgnoreCase(input.trim())) {
                        LOGGER.info("End program");
                        break;
                    }
                    commandProcessor.processCommand(input);
                }
            }
        };
    }
}
