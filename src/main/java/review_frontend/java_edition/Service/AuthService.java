package review_frontend.java_edition.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import review_frontend.java_edition.Config.AuthUtils;
import review_frontend.java_edition.DTO.AuthResponseDto;
import review_frontend.java_edition.DTO.SignUpRequestDto;
import review_frontend.java_edition.Model.User;
import review_frontend.java_edition.Repository.UserRepository;

import java.util.Optional;

@Slf4j
@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthUtils authUtils;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    AuthService(BCryptPasswordEncoder bCryptPasswordEncoder){
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public AuthResponseDto signUp(SignUpRequestDto signUpRequest) throws Exception {

        try{
                Optional<User> user = userRepository.findByEmail(signUpRequest.email());
                if(user.isPresent()){
                    throw new Exception();
                }
                User created_user = userRepository.save(User.builder()
                                .email(signUpRequest.email())
                                .name(signUpRequest.username())
                                .password(bCryptPasswordEncoder.encode(signUpRequest.password()))
                        .build());

                String jwt_token = authUtils.generateJwt(created_user);

                if(jwt_token.trim().length()<=0 || jwt_token==null || created_user == null){
                    throw new Exception();
                }
                log.info("Token Created ... "+jwt_token);
                return new AuthResponseDto(created_user.getName(),jwt_token);
        }catch (Exception e){
            throw new Exception();
        }

    }
}
