package kz.f12.school.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ServletUtils {

    public static final String JSON_TYPE = "application/json; charset=UTF-8";
    public static final String HTML_TYPE = "text/html; charset=UTF-8";

    public static void printContent(String content, String mediaType, HttpServletResponse resp) throws IOException {
        resp.setContentType(mediaType);
        resp.setCharacterEncoding("UTF-8");

        PrintWriter writer = resp.getWriter();
        writer.write(content);
        writer.flush();
    }
}
