package expense.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import expense.manager.model.DailyExpense;
import expense.manager.model.Expense;
import expense.manager.model.MonthlyExpense;
import expense.manager.service.IExpenseManagerService;

@RestController
@CrossOrigin
@RequestMapping("/expense/api")
public class ExpenseManagerController {

	@Autowired
	private IExpenseManagerService expenseManagerService;

	@RequestMapping(value = "/addDailyExpense", method = RequestMethod.POST)
	public DailyExpense addDailyExpense(@RequestBody DailyExpense dailyExpense) {
		return expenseManagerService.addDailyExpense(dailyExpense);
	}

	@RequestMapping(value = "/setBudgetLimit/{month}/{budgetLimit}", method = RequestMethod.POST)
	public boolean setMonthlyBadgetLimit(@PathVariable String month, @PathVariable double budgetLimit) {
		return expenseManagerService.setMonthlyBadgetLimit(month, budgetLimit);
	}

	@RequestMapping(value = "/setBudgetLimit/{budgetLimit}", method = RequestMethod.POST)
	public boolean setMonthlyBadgetLimit(@PathVariable double budgetLimit) {
		return expenseManagerService.setMonthlyBadgetLimit("", budgetLimit);
	}

	@RequestMapping(value = "/retrieveExpensesByDate/{date}", method = RequestMethod.GET)
	public List<DailyExpense> retrieveExpensesByDate(@PathVariable String date) {
		return expenseManagerService.retrieveExpensesByDate(date);
	}
	
	@RequestMapping(value = "/retrieveExpensesByMonth/{month}", method = RequestMethod.GET)
	public List<MonthlyExpense> retrieveExpensesByMonth(@PathVariable String month) {
		return expenseManagerService.retrieveExpensesByMonth(month);
	}

	@RequestMapping(value = "/retrieveExpensesByYear/{year}", method = RequestMethod.GET)
	public List<Expense> retrieveExpensesByYear(@PathVariable int year) {
		return expenseManagerService.retrieveExpensesByYear(year);
	}

	@RequestMapping(value = "/retrieveAllExpenses", method = RequestMethod.GET)
	public List<Expense> retrieveExpenses() {
		return expenseManagerService.retrieveExpenses();
	}
}
