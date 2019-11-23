package com.gmail.silverleaf.annn.servlets;

import com.gmail.silverleaf.annn.dbobjects.Currency;
import com.gmail.silverleaf.annn.dbobjects.User;
import com.gmail.silverleaf.annn.dbutilities.*;
import com.gmail.silverleaf.annn.exceptions.NotEnoughMoneyException;

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

@WebServlet(name = "ConvertServlet", urlPatterns = "/convert")
public class ConvertServlet extends HttpServlet {
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
        String uidStr = req.getParameter("user_id");
        String currencyFromIdStr = req.getParameter("from_currency_id");
        String currencyToIdStr = req.getParameter("to_currency_id");
        String sumStr = req.getParameter("sum");
        if ((uidStr == null) || (currencyFromIdStr == null) || (currencyToIdStr == null) || (sumStr == null)) {
            setupErrorMsg(req, "All fields must be filled!");
            dispatch(req, resp);
        }
        try {
            int userId = Integer.parseInt(uidStr);
            int currencyFromId = Integer.parseInt(currencyFromIdStr);
            int currencyToId = Integer.parseInt(currencyToIdStr);
            Double sum = Double.parseDouble(sumStr);
            convertMoney(userId, currencyFromId, currencyToId, sum);

        } catch (NumberFormatException | NotEnoughMoneyException e) {
            setupErrorMsg(req, e.getMessage());
        }
        fillPage(req);
        dispatch(req, resp);
    }

    private void fillPage(HttpServletRequest req) throws NoResultException, NonUniqueResultException {
        setupUsers(req);
        setupCurrencies(req);
    }

    private void dispatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/convert.jsp");
        rd.forward(req, resp);
    }

    private void setupUsers(HttpServletRequest req) throws NoResultException, NonUniqueResultException {
        UserController controller = new UserController(new UserDAOJpaImplementation());
        List<User> users = controller.getAllUsers();
        req.setAttribute("users", users);
    }

    private void setupCurrencies(HttpServletRequest req) throws NoResultException, NonUniqueResultException {
        CurrencyController controller = new CurrencyController(new CurrencyDAOJpaImplementation());
        List<Currency> currencies = controller.getAllCurrencies();
        req.setAttribute("currencies", currencies);
    }

    private void convertMoney(int userId, int currencyFromId, int currencyToId, Double sum) throws NotEnoughMoneyException {
        if (currencyFromId == currencyToId) {
            return;
        }
        UserController userController = new UserController(new UserDAOJpaImplementation());
        User user = userController.getUserById(userId);

        CurrencyController currencyController = new CurrencyController(new CurrencyDAOJpaImplementation());
        Currency currencyFrom = currencyController.getCurrencyById(currencyFromId);
        Currency currencyTo = currencyController.getCurrencyById(currencyToId);

        RateController rateController = new RateController(new RateDAOJpaImplementation());
        Double destinationSum = sum * rateController.getCurrencyRate(currencyFromId, currencyToId);

        BillingController billingController = new BillingController(new BillingDAOJpaImplementation());
        TransactionController transactionController = new TransactionController(new TransactionDAOJpaImplementation());
        try {
            billingController.addMoney((-sum), user, currencyFrom);
            transactionController.addTransaction(user, billingController.getUserCurrencyBilling(user, currencyFrom), (- sum));
            billingController.addMoney(destinationSum, user, currencyTo);
            transactionController.addTransaction(user, billingController.getUserCurrencyBilling(user, currencyTo), destinationSum);
        } catch (NotEnoughMoneyException e) {
            throw e;
        }
    }

    private void setupErrorMsg(HttpServletRequest req, String msg) {
        req.setAttribute("error", true);
        req.setAttribute("error_msg", msg);
    }
}
