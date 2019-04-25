package expense.manager.dao;

import java.time.LocalDate;
import java.util.List;

import expense.manager.model.DailyExpense;
import expense.manager.model.Expense;
import expense.manager.model.MonthlyExpense;

public interface IExpenseManagerDAO {

	public DailyExpense addDailyExpense(DailyExpense dailyExpense);

	public boolean setMonthlyBadgetLimit(String month, double budget);

	public List<MonthlyExpense> retrieveMonthlyExpense(String month);

	public List<Expense> retrieveYearlyExpenses(int year);

	public List<DailyExpense> retrieveDailyExpenses(LocalDate date);

	public List<Expense> retrieveExpenses();
}
