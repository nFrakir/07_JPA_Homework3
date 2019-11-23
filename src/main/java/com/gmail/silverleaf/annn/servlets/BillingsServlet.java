package com.gmail.silverleaf.annn.servlets;

import com.gmail.silverleaf.annn.dbobjects.Billing;
import com.gmail.silverleaf.annn.dbobjects.Currency;
import com.gmail.silverleaf.annn.dbobjects.User;
import com.gmail.silverleaf.annn.dbutilities.*;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BillingsServlet", urlPatterns = "/all_billings")
public class BillingsServlet extends HttpServlet {
    private int currentUserId = -1;
    private User currentUser = null;
    private Currency baseCurrency = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            fillPage(req);
        } catch (NoResultException | NonUniqueResultException e) {
            setupErrorMsg(req, e.getMessage());
        }
        dispatch(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uid = req.getParameter("user_id");
        if (uid == null) {
            setupErrorMsg(req, "Empty user value");
            dispatch(req, resp);
        }
        try {
            currentUserId = Integer.parseInt(uid);
            fillPage(req);
        } catch (NumberFormatException e) {
            setupErrorMsg(req, e.getMessage());
        }
        dispatch(req, resp);
    }

    private void fillPage(HttpServletRequest req) throws NoResultException, NonUniqueResultException {
        setupUsers(req);
        setupBaseCurrency();
        setupBillings(req);
    }

    private void dispatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/all_billings.jsp");
        rd.forward(req, resp);
    }

    private void setupUsers(HttpServletRequest req) throws NoResultException, NonUniqueResultException {
        UserController controller = new UserController(new UserDAOJpaImplementation());
        List<User> users = controller.getAllUsers();
        if (currentUserId != -1) {
            currentUser = controller.getUserById(currentUserId);
        } else {
            currentUser = users.get(0);
        }
        req.setAttribute("currentUser", currentUser.getLogin());
        req.setAttribute("users", users);
    }

    private void setupBaseCurrency() throws NoResultException, NonUniqueResultException {
        CurrencyController controller = new CurrencyController(new CurrencyDAOJpaImplementation());
        baseCurrency = controller.getBaseCurrency("UAH");
    }

    private void setupBillings(HttpServletRequest req) throws NoResultException, NonUniqueResultException {
        BillingController controller = new BillingController(new BillingDAOJpaImplementation());
        List<Billing> billings = controller.getUserBillings(currentUser);

        Double totalUAH = 0.0;
        for (Billing el : billings) {
            totalUAH += getUAHSum(el.getCurrency(), el.getTotal());
        }
        req.setAttribute("billings", billings);
        req.setAttribute("totalUAH", totalUAH);
    }

    private Double getUAHSum(Currency currency, Double sum) throws NoResultException, NonUniqueResultException {
        if (currency.getId() == baseCurrency.getId()) {
            return sum;
        }

        RateController controller = new RateController(new RateDAOJpaImplementation());
        Double rateValue = controller.getCurrencyRate(currency, baseCurrency);
        return sum*rateValue;
    }

    private void setupErrorMsg(HttpServletRequest req, String msg) {
        req.setAttribute("error", true);
        req.setAttribute("error_msg", msg);
    }
}
