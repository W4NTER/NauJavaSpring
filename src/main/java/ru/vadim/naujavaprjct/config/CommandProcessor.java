package ru.vadim.naujavaprjct.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import ru.vadim.naujavaprjct.exception.CustomErrorException;
import ru.vadim.naujavaprjct.entity.User;
import ru.vadim.naujavaprjct.exception.NotValidIdError;
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
        try {
            long id;
            switch (cmd[0]) {
                case "create" -> {
                    userService.addUser(cmd[1]);
                    LOGGER.info("Пользователь успешно добавлен");
                }
                case "update" -> {
                    id = parseId(cmd[1]);
                    userService.updateUsername(id, cmd[2]);
                    LOGGER.info("Username успешно изменен");
                }
                case "delete" -> {
                    id = parseId(cmd[1]);
                    userService.deleteById(id);
                    LOGGER.info(String.format(
                            "Пользователь с id = %d, удален", id));
                }
                case "find" -> {
                    id = parseId(cmd[1]);
                    User user = userService.findById(id);
                    LOGGER.info(user.toString());

                }
                case "list_all" -> {
                    List<User> users = userService.listAll();
                    LOGGER.info(users.stream().map(User::toString).toList());
                }
                case "list" -> LOGGER.info("create {username}\n" +
                        "update {user_id} {username}\n" +
                        "delete {user_id}\n" +
                        "find {user_id}\n" +
                        "list_all");
                default -> LOGGER.info("Введена неизвестная команда " +
                        "или в команде допущена ошибка");
            }
        } catch (CustomErrorException e) {
            LOGGER.info(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            LOGGER.info("Комманда введена неправильно");
        }
    }

    private Long parseId(String userId) throws NotValidIdError {
        try {
            return Long.valueOf(userId);
        } catch (NumberFormatException e) {
            throw new NotValidIdError();
        }
    }
}
