package com.gmail.silverleaf.annn.servlets;

import com.gmail.silverleaf.annn.dbobjects.Transaction;
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

@WebServlet(name = "TransactionsServlet", urlPatterns = "/all_transactions")
public class TransactionsServlet extends HttpServlet {
    private int currentUserId = -1;
    private User currentUser = null;

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
        setupTransactions(req);
    }

    private void dispatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/all_transactions.jsp");
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

    private void setupTransactions(HttpServletRequest req) throws NoResultException, NonUniqueResultException {
        TransactionController controller = new TransactionController(new TransactionDAOJpaImplementation());
        List<Transaction> transactions = controller.getUserTransactions(currentUser);

        req.setAttribute("transactions", transactions);
    }

    private void setupErrorMsg(HttpServletRequest req, String msg) {
        req.setAttribute("error", true);
        req.setAttribute("error_msg", msg);
    }
}
