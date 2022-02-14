package kz.f12.school.api;

import kz.f12.school.util.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

import static javax.servlet.RequestDispatcher.*;

public class ErrorHandler extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder responseBuilder = new StringBuilder();
        responseBuilder.append("<ul>");
        Arrays.asList(ERROR_STATUS_CODE, ERROR_EXCEPTION_TYPE, ERROR_MESSAGE)
                .forEach(e -> responseBuilder.append("<li>")
                                             .append(e)
                                             .append(":")
                                             .append(req.getAttribute(e))
                                             .append("</li>"));
        responseBuilder.append("</ul>");
        ServletUtils.printContent(responseBuilder.toString(), ServletUtils.HTML_TYPE, resp);
    }
}
