package cw.sprboot.dpiqb.feature.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
  private final UserRepository userRepository;

  public List<User> findAll(){
      return userRepository.findAll();
  }

  public void save(User user){
    userRepository.save(user);
  }

  public boolean exists(String email){
    if(email == null){
      return false;
    }
    return userRepository.existsById(email);
  }

  public void deleteById(String email){
    userRepository.deleteById(email);
  }

  public List<User> search(String query){
//    userRepository.search("%"+query+"%");
//    userRepository.searchByNativeSql("%"+query+"%");
//    userRepository.searchEmails("%"+query+"%");
    return userRepository.findAllById(
      userRepository.searchEmails("%"+query+"%")
    );
  }

  public int countPeopleOlderThan(int age){
    return userRepository.countPeopleOlderThan(LocalDate.now().minusYears(age));
  }
}
