package edu.cnm.deepdive.codebreaker.service;

import edu.cnm.deepdive.codebreaker.model.dao.UserRepository;
import edu.cnm.deepdive.codebreaker.model.entity.User;
import java.time.Instant;
import java.util.NoSuchElementException;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements AbstractUserService {

  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User getOrCreate(String oauthKey, String displayName) {
    return userRepository
        .save(
            userRepository
                .findByOauthKey(oauthKey)
                .map((user) -> {
                  user.setAccessed(Instant.now());
                  return user;
                })
                .orElseGet(() -> {
                  User user = new User();
                  user.setOauthKey(oauthKey);
                  user.setDisplayName(displayName);
                  user.setAccessed(Instant.now());
                  return user;
                })
        );
  }

  @Override
  public User get(UUID externalKey) throws NoSuchElementException {
    return userRepository
        .getByExternalKey(externalKey)
        .orElseThrow();
  }

  @Override
  public User getCurrentUser() {
    return (User) SecurityContextHolder.getContext()
        .getAuthentication()
        .getPrincipal();
  }

  @Override
  public User update(User received) {
    return userRepository
        .findById(getCurrentUser().getId())
        .map((user) -> {
          String displayName = received.getDisplayName();
          if (displayName != null) {
            user.setDisplayName(displayName);
          }
          return userRepository.save(user);
        })
        .orElseThrow();
  }

}
