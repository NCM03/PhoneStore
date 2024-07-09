package fa.training.phonestore.Sercurity;

import fa.training.phonestore.Exception.EntityNotFoundException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


public class ExceptionHandlerFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);

        }catch (EntityNotFoundException e){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("Username does not exist");
            response.getWriter().flush();
        }
        catch (RuntimeException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("BADREQUEST");
            response.getWriter().flush();
        }
    }
}
