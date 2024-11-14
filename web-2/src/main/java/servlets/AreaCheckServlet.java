package servlets;

import data.Result;
import data.ResultBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AreaCheckServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String xValueStr = request.getParameter("x_value");
            String yValueStr = request.getParameter("y_value");
            String radiusStr = request.getParameter("radius");



            float x = Float.parseFloat(xValueStr);
            float y = Float.parseFloat(yValueStr);
            double r = Double.parseDouble(radiusStr);

            boolean isInside = checkIfInside(x, y, r);

            Result result = new Result(x, y, r, isInside);

            HttpSession session = request.getSession();

            ResultBean results = (ResultBean) session.getAttribute("results");
            ResultBean points = (ResultBean) session.getAttribute("points");

//            List<Result> results = (List<Result>) session.getAttribute("results");
//            List<Result> points = (List<Result>) session.getAttribute("points");

            if (results == null) {
                results = new ResultBean();
            }

            if (points == null) {
                points = new ResultBean();
            }

            results.addResult(result);
            points.addResult(result);

            session.setAttribute("results", results);
            session.setAttribute("points", points);

            request.setAttribute("currentResult", result);
            request.getRequestDispatcher("/result.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.getWriter().println("Ошибка: " + e.getMessage());
        }
    }

    private boolean checkIfInside(float x, float y, double r) {
        if (x <= 0 && y >= 0 && (x * x + y * y <= r * r)) {
            return true;
        }

        if (x >= -r / 2 && x <= 0 && y <= 0 && y >= -r) {
            return true;
        }

        if (x >= 0 && y <= 0 && y >= (r/2)*x - r/2) {
            return true;
        }

        return false;
    }

}

