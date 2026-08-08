package review_frontend.java_edition.Config;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import review_frontend.java_edition.Model.CustomUserDetails;
import review_frontend.java_edition.Model.User;
import review_frontend.java_edition.Repository.UserRepository;

import java.io.IOException;

@Slf4j
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private AuthUtils authUtils;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.info("**** JWT Filter ****");
        final String token_header = request.getHeader("Authorization");

        if(token_header == null){
            filterChain.doFilter(request,response);
            return;
        }

        String jwt_token = token_header.split("Bearer ")[1];

        if(jwt_token == null || jwt_token.trim().length()<=0){
            filterChain.doFilter(request,response);
            return;
        }

        String email = authUtils.getJwtUserName(jwt_token);
        if(email != null || email.trim().length()>0 || SecurityContextHolder.getContext().getAuthentication() == null){
            User user = userRepository.findByEmail(email).orElseThrow();
            CustomUserDetails userDetails = new CustomUserDetails(user);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    userDetails, null , userDetails.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(token);
        }

        filterChain.doFilter(request,response);

    }
}
