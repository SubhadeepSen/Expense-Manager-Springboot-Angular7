package expense.manager.service;

import java.util.List;

import expense.manager.model.DailyExpense;
import expense.manager.model.Expense;
import expense.manager.model.MonthlyExpense;

public interface IExpenseManagerService {
	public DailyExpense addDailyExpense(DailyExpense dailyExpense);

	public boolean setMonthlyBadgetLimit(String month, double badgetLimit);

	public List<MonthlyExpense> retrieveExpensesByMonth(String month);

	public List<Expense> retrieveExpensesByYear(int year);

	public List<Expense> retrieveExpenses();
	
	public List<DailyExpense> retrieveExpensesByDate(String date);

}
