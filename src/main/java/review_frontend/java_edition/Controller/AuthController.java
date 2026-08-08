package review_frontend.java_edition.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import review_frontend.java_edition.DTO.AuthResponseDto;
import review_frontend.java_edition.DTO.LoginRequestDto;
import review_frontend.java_edition.DTO.SignUpRequestDto;
import review_frontend.java_edition.Service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponseDto> signUp(@Valid @RequestBody SignUpRequestDto signUpRequest){
        try{
            AuthResponseDto authResponseDto = authService.signUp(signUpRequest);
            return new ResponseEntity<>(authResponseDto,HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<AuthResponseDto>(new AuthResponseDto(e.getMessage(),null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto loginRequestDto){
        try{
            return new ResponseEntity<>(authService.login(loginRequestDto),HttpStatus.OK);

        }catch(Exception e){
            return new ResponseEntity<AuthResponseDto>(new AuthResponseDto(e.getMessage(),null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
