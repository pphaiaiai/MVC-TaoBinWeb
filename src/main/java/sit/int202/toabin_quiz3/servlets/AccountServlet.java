package sit.int202.toabin_quiz3.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import sit.int202.toabin_quiz3.entities.Account;
import sit.int202.toabin_quiz3.entities.Transaction;
import sit.int202.toabin_quiz3.repositories.AccountRepository;
import sit.int202.toabin_quiz3.repositories.TransactionRepository;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;

//63130500055 Tarathep Siripis

@WebServlet(name = "AccountServlet", value = "/account")
public class AccountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null && action.equals("logout")) {
            HttpSession session = request.getSession(false);

            if (session != null) {
                session.invalidate();
            }
        } else {
            HttpSession session = request.getSession(false);
            Account account = (Account) session.getAttribute("account");
            TransactionRepository transactionRepo = new TransactionRepository();
            if (account != null) {
                request.setAttribute("account", account);
                getServletContext().getRequestDispatcher("/account.jsp").forward(request, response);
            }
//            response.sendRedirect("beverage-list");
        }
        response.sendRedirect("beverage-list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mobileno = request.getParameter("mobileno");
        String pincode = request.getParameter("pincode");


        if (mobileno != null && mobileno.trim().length() > 0 && pincode != null && pincode.trim().length() > 0) {
            AccountRepository accountRepo = new AccountRepository();
            Account account = accountRepo.find(mobileno);

            if (account != null) {
                if (sha256(pincode).equals(account.getPinCode())) {
                    HttpSession session = request.getSession(true);
                    if (session.getAttribute("account") == null) {
                        session.setAttribute("account", account);
                    }
                    response.sendRedirect("beverage-list");
//                    getServletContext().getRequestDispatcher("/toabin_beverage.jsp").forward(request, response);
                    return;
                }
            }
        }
        response.sendRedirect("login");
    }


    private String sha256(final String base) {
        try {
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = digest.digest(base.getBytes("UTF-8"));
            final StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                final String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
