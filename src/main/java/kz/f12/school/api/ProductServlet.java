package kz.f12.school.api;

import kz.f12.school.model.dto.ProductDTO;
import kz.f12.school.service.ProductService;
import kz.f12.school.util.Mapper;
import kz.f12.school.util.ServletUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static kz.f12.school.util.ServletUtils.getJsonBody;

@WebServlet("/products/*")
public class ProductServlet extends HttpServlet {

    private ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String methodName = req.getPathInfo().split("/")[1];
        if ("getById".equals(methodName)) {
            int id = Integer.parseInt(req.getPathInfo().split("/")[2]);
            ProductDTO productDTO = productService.getById(id);
            JSONObject jsonObject = new JSONObject(productDTO);

            ServletUtils.printContent(jsonObject.toString(), ServletUtils.JSON_TYPE, resp);
        } else {
            showDefaultContent(resp);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String methodName = req.getPathInfo().split("/")[1];
        if ("create".equals(methodName)) {
            ProductDTO productDTO = Mapper.toProductDTO(getJsonBody(req));
            productService.save(productDTO);
        } else {
            showDefaultContent(resp);
        }
    }

    private void showDefaultContent(HttpServletResponse resp) throws IOException {
        List<ProductDTO> productList = productService.getAll();
        JSONArray jsonArray = new JSONArray(productList);

        ServletUtils.printContent(jsonArray.toString(), ServletUtils.JSON_TYPE, resp);
    }
}
