//package CS544.Helper;
//
//import CS544.Model.User;
//import CS544.Service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class JwtFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private JWTUtil tokenProvider;
//
//    @Autowired
//    private UserService customUserDetailsService;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String jwt = getJwtFromRequest(request);
//
//        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
//            String username = tokenProvider.extractUsername(jwt);
//            User userDetails = customUserDetailsService.findByUserName(username);
//            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }
//
//        filterChain.doFilter(request, response);
//    }
//
//    private String getJwtFromRequest(HttpServletRequest request) {
//        String bearerToken = request.getHeader("Authorization");
//        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
//            return bearerToken.substring(7);
//        }
//        return null;
//    }
//}
//
