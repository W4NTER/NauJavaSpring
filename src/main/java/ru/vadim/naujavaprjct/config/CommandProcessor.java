package ru.vadim.naujavaprjct.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import ru.vadim.naujavaprjct.entity.User;
import ru.vadim.naujavaprjct.service.UserService;

import java.util.List;

@Component
public class CommandProcessor {
    private final static Logger LOGGER = LogManager.getLogger();
    private final UserService userService;

    public CommandProcessor(UserService userService) {
        this.userService = userService;
    }

    public void processCommand(String input) {
        String[] cmd = input.split(" ");
        switch (cmd[0]) {
            case "create" -> {
                userService.addUser(Long.valueOf(cmd[1]), cmd[2]);
                LOGGER.info("Пользователь успешно добавлен");
            }
            case "update" -> {
                userService.updateUsername(Long.valueOf(cmd[1]), cmd[2]);
                LOGGER.info("Username успешно изменен");
            }
            case "delete" -> {
                Long id = Long.valueOf(cmd[1]);
                userService.deleteById(id);
                LOGGER.info(String.format(
                        "Пользователь с id = %d, удален", id));
            }
            case "find" -> {
                User user = userService.findById(Long.valueOf(cmd[1]));
                LOGGER.info(user.toString());

            }
            case "list_all" -> {
                List<User> users = userService.listAll();
                LOGGER.info(users.stream().map(User::toString).toList());
            }
            case "list" -> LOGGER.info("create {user_id} {username}\n" +
                    "update {user_id} {username}\n" +
                    "delete {user_id}\n" +
                    "find {user_id}\n" +
                    "list_all");
            default -> LOGGER.info("Введена неизвестная команда " +
                    "или в команде допущена ошибка");
        }
    }
}
