package com.example.jpa.main;

import com.example.jpa.domain.User;
import com.example.jpa.helper.EMF;
import com.example.jpa.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

public class UserServiceMain {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceMain.class);
    public static void main(String[] args) throws IOException {
        UserService userService = new UserService();
        EMF.init();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = "";
            while(!line.equals("exit")) {
                System.out.print("명령어를 입력하세요:");
                line = reader.readLine();
                if (line == null) {
                    break;
                }
                if (line.startsWith("new ")) {
                    handleNew(line, userService);
                } else if (line.startsWith("get ")) {
                    handleGet(line, userService);
                } else if (line.startsWith("change name")) {
                    handleChangeName(line, userService);
                } else if (line.startsWith("remove ")) {
                    handleRemove(line, userService);
                }
            }
        } finally {
            EMF.close();
        }
    }

    private static void handleRemove(String line, UserService userService) {
        String email = line.substring(7);
        try {
            userService.removeUser(email);
        } catch (Exception ex) {
            logger.error("{}: {}", ex.getMessage(), email);
        }
    }

    private static void handleChangeName(String line, UserService userService) {
        String[] v= line.substring(12).split(" ");
        String email = v[0];
        String newName = v[1];
        try {
            userService.changeName(email, newName);
            logger.info("사용자 이름 변경: {}, {}", email, newName);
        } catch (Exception ex) {
            logger.error("{}: {}", ex.getMessage(), email);
        }
    }

    private static void handleGet(String line, UserService userService) {
        String email = line.substring(4);
        try {
            User user = userService.getUser(email);
            logger.info("사용자 정보: {}",user);
        } catch (Exception ex) {
            logger.error("{}: {}", ex.getMessage(), email);
        }
    }

    private static void handleNew(String line, UserService userService) {
        String[] v = line.substring(4).split(" ");
        User user = new User(v[0], v[1], LocalDateTime.now());
        try {
            userService.saveNewUser(user);
            logger.info("새 사용자 저장: {}", user);
        } catch (Exception ex) {
            logger.error("{}: {}", ex.getMessage(), user.getEmail());
        }
    }
}
