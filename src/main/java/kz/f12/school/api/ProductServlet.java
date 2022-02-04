package kz.f12.school.api;

import kz.f12.school.model.dto.CategoryDTO;
import kz.f12.school.model.dto.ProductDTO;
import kz.f12.school.service.ProductService;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    private ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(1);
        categoryDTO.setName("Техника");
        categoryDTO.setName("Домашняя техника");

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1);
        productDTO.setName("Телевизор");
        productDTO.setDescription("Smart TV");
        productDTO.setPrice(2450000);
        productDTO.setQuantity(5);
        productDTO.setWeight(10);
        productDTO.setCategoryDTO(categoryDTO);

        List<ProductDTO> productList = Collections.singletonList(productDTO);
        JSONArray jsonArray = new JSONArray(productList);

        resp.setContentType("application/json; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter writer = resp.getWriter();
        writer.write(jsonArray.toString());
        writer.flush();
    }
}
