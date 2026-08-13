package review_frontend.java_edition.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import review_frontend.java_edition.Config.AuthUtils;
import review_frontend.java_edition.Config.KafkaProducer;
import review_frontend.java_edition.DTO.AuthResponseDto;
import review_frontend.java_edition.DTO.LoginRequestDto;
import review_frontend.java_edition.DTO.SignUpRequestDto;
import review_frontend.java_edition.Model.CustomUserDetails;
import review_frontend.java_edition.Model.User;
import review_frontend.java_edition.Repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.Optional;

@Slf4j
@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthUtils authUtils;

    @Autowired
    private KafkaProducer kafkaProducer;

    private final AuthenticationManager authenticationManager;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    AuthService(BCryptPasswordEncoder bCryptPasswordEncoder, AuthenticationManager authenticationManager){
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationManager = authenticationManager;
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

                String jwt_token = authUtils.generateJwt(new CustomUserDetails(created_user));

                if(jwt_token==null || jwt_token.trim().length()<=0 ||  created_user == null){
                    throw new Exception();
                }

                kafkaProducer.topicProducer("email-topic","Dummy Body! "+created_user.getName());

                return new AuthResponseDto(created_user.getName(),jwt_token);
        }catch (Exception e){
            throw e;
        }

    }

    public AuthResponseDto login(LoginRequestDto loginRequestDto) throws Exception{
        try{
            log.info("********* Login Started **********");

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDto.email(),
                            loginRequestDto.password()
                    )
            );
            CustomUserDetails userPrincipal = (CustomUserDetails) authentication.getPrincipal();
            log.info("User Principal -->"+userPrincipal.toString());

            String jwt_token = authUtils.generateJwt(userPrincipal);

            return new AuthResponseDto(userPrincipal.getUsername(), jwt_token);

        }catch(Exception e){
            throw e;
        }
    }
}
