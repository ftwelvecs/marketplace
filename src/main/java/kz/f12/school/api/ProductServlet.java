package kz.f12.school.api;

import kz.f12.school.model.dto.ProductDTO;
import kz.f12.school.service.ProductService;
import kz.f12.school.util.ServletUtils;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    private ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProductDTO> productList = productService.getAll();
        JSONArray jsonArray = new JSONArray(productList);

        ServletUtils.printContent(jsonArray.toString(), ServletUtils.JSON_TYPE, resp);
    }
}
