package mk.ukim.finki.wp.lab.web.servlet;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Order;
import mk.ukim.finki.wp.lab.service.impl.BalloonServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ConfirmationInfoServlet", urlPatterns = "/ConfirmationInfo")
public class ConfirmationInfoServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final BalloonServiceImpl balloonService;

    public ConfirmationInfoServlet(SpringTemplateEngine springTemplateEngine, BalloonServiceImpl balloonService) {
        this.springTemplateEngine = springTemplateEngine;
        this.balloonService = balloonService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        context.setVariable("clientName", req.getParameter("clientName"));
        context.setVariable("clientAddress", req.getParameter("clientAddress"));
        context.setVariable("clientAgent", req.getHeader("User-Agent"));
        context.setVariable("ipAddress", req.getRemoteAddr());
        context.setVariable("balloonType",req.getSession().getAttribute("balloonType"));
        context.setVariable("balloonSize", req.getSession().getAttribute("balloonSize"));

        String balloonColor = (String) req.getSession().getAttribute("balloonType");
        String balloonSize = (String) req.getSession().getAttribute("balloonSize");
        String clientName = req.getParameter("clientName");
        String clientAddress = req.getParameter("clientAddress");
        String orderId =  req.getRemoteAddr(); //ova e ip addresa


        //Order order = new Order(balloonColor, balloonSize,clientName, clientAddress, orderId);
        Order order = new Order(balloonColor, balloonSize);

        req.getSession().setAttribute("order", order);
        DataHolder.orders.add(order);

        springTemplateEngine.process("confirmationInfo.html", context, resp.getWriter());
    }
}
