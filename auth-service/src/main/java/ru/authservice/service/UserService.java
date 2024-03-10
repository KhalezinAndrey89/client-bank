package ru.authservice.service;

import org.springframework.stereotype.Service;
import ru.authservice.client.AccountClient;
import ru.authservice.dto.Account;
import ru.authservice.entity.User;
import ru.authservice.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AccountClient accountClient;

    public UserService(UserRepository userRepository, AccountClient accountClient) {
        this.userRepository = userRepository;
        this.accountClient = accountClient;
    }

    public Optional<Account> getAccountClient(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return accountClient.getAccountById(user.get().getAccountId());
        } else {
            return Optional.empty();
        }
    }

    public User createUser(String username, String password, String email) {
        return userRepository.save(User.builder()
                .username(username)
                .password(password)
                .email(email)
                .accountId(crateAccountFromUser())
                .build());
    }

    public String deleteUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            deleteAccountById(user.getAccountId());
            userRepository.deleteById(id);
            return "User is deleted";
        } else {
            return "User not found";
        }
    }

    private Long crateAccountFromUser() {
        return accountClient.createAccount();
    }

    private void deleteAccountById(Long id) {
        accountClient.deleteAccountById(id);
    }

}
