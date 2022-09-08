package sit.int202.toabin_quiz3.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import sit.int202.toabin_quiz3.entities.Account;
import sit.int202.toabin_quiz3.entities.Drink;
import sit.int202.toabin_quiz3.entities.Transaction;
import sit.int202.toabin_quiz3.repositories.AccountRepository;
import sit.int202.toabin_quiz3.repositories.DrinkRepository;
import sit.int202.toabin_quiz3.repositories.TransactionRepository;

import java.io.IOException;
import java.util.Date;
import java.util.List;

//63130500055 Tarathep Siripis


@WebServlet(name = "BasketServlet", value = "/basket")
public class BasketServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strId = request.getParameter("id");
        String action = request.getParameter("action");
        Long id = Long.parseLong(strId);
        DrinkRepository drinkRepo = new DrinkRepository();
        HttpSession session = request.getSession(false);
        Account account = (Account) session.getAttribute("account");

        if (action != null) {
            if (action.equals("add")) {
                Drink drink = drinkRepo.find(id);
                Transaction transaction = new Transaction();
                request.setAttribute("tran", transaction);
                request.setAttribute("drink", drink);
                getServletContext().getRequestDispatcher("/basket.jsp").forward(request, response);
            }
            if (action.equals("buy") && account != null) {
                TransactionRepository transactionRepository = new TransactionRepository();
                Drink drink = drinkRepo.find(id);
                Transaction transaction = new Transaction();
                AccountRepository accountRepository = new AccountRepository();
                try {
                    Date tranDate = new Date();
                    transaction.setPrice(drink.getPrice());
                    transaction.setAmount(drink.getPrice());
                    transaction.setDrink(drink);
                    transaction.setQty(1);
                    transaction.setAccountsMobileNo(account);
                    transaction.setTranDate(tranDate);
                    List<Transaction> transactionList = transactionRepository.findAll();
                    transactionList.add(transaction);
                    account.setTransactions(transactionList);
                    account.setLastBuy(tranDate);
                    account.setTotalAmount(account.getTotalAmount() + drink.getPrice());

                    request.setAttribute("account" , account);
                    request.setAttribute("transaction" , transaction);

                    System.out.println("=================================:: "+ transaction);

                    Integer total = account.getTotalAmount() + transaction.getPrice();
                    account.setTotalAmount(total);

                    account = (Account) session.getAttribute("account");
                    Date lastBuy = new Date();
                    account.setLastBuy(lastBuy);
                    accountRepository.update(account);

                    getServletContext().getRequestDispatcher("/account.jsp").forward(request, response);
                    return;

                } catch (NumberFormatException nfe) {
                    System.out.println("Error : " + nfe.getMessage());
                }
                getServletContext().getRequestDispatcher("/account.jsp").forward(request, response);
//                response.sendRedirect("account");
            } else {
                response.sendRedirect("login");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
