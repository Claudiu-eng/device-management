package devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.configuration;

import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.model.entity.User;
import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.repository.UserRepository;
import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final UUID userId;
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }
        jwt = authHeader.substring(7);
        userId = jwtService.extractId(jwt);
        if(userId !=null && SecurityContextHolder.getContext().getAuthentication()==null){
            User user = this.userRepository.findById(userId).orElse(null);
            if(user == null){
                filterChain.doFilter(request,response);
                return;
            }
            if(jwtService.isTokenValid(jwt,user)){
                List<String> roles = jwtService.extractRole(jwt);
                Collection<GrantedAuthority> authorities = new ArrayList<>();
                for (String authority : roles) {
                    authorities.add(new SimpleGrantedAuthority(authority));
                }
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user.getId(),null, authorities);
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
                //what is security context holder?
            }
        }

        filterChain.doFilter(request,response);
    }
}
