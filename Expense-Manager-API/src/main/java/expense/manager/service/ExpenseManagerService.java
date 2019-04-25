package expense.manager.service;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import expense.manager.dao.IExpenseManagerDAO;
import expense.manager.model.DailyExpense;
import expense.manager.model.Expense;
import expense.manager.model.MonthlyExpense;

@Service
public class ExpenseManagerService implements IExpenseManagerService {

	@Autowired
	private IExpenseManagerDAO expenseManagerDao;

	@Override
	public DailyExpense addDailyExpense(DailyExpense dailyExpense) {
		return expenseManagerDao.addDailyExpense(dailyExpense);
	}

	@Override
	public boolean setMonthlyBadgetLimit(String month, double badgetLimit) {
		month = month.toUpperCase();
		if (StringUtils.isEmpty(month)) {
			return expenseManagerDao.setMonthlyBadgetLimit(null, badgetLimit); // setting for current month
		} else if (isValidMonth(month) && isNotPastMonth(month)) {
			return expenseManagerDao.setMonthlyBadgetLimit(month, badgetLimit);
		} else {
			return false;
		}

	}

	@Override
	public List<MonthlyExpense> retrieveExpensesByMonth(String month) {
		month = month.toUpperCase();
		if (StringUtils.isEmpty(month) || !isValidMonth(month)) {
			return null;
		}
		return expenseManagerDao.retrieveMonthlyExpense(month);
	}

	@Override
	public List<Expense> retrieveExpensesByYear(int year) {
		return expenseManagerDao.retrieveYearlyExpenses(year);
	}

	@Override
	public List<Expense> retrieveExpenses() {
		return expenseManagerDao.retrieveExpenses();
	}

	@Override
	public List<DailyExpense> retrieveExpensesByDate(String date) {
		if(StringUtils.isEmpty(date)) {
			return new ArrayList<>();
		}
		return expenseManagerDao.retrieveDailyExpenses(LocalDate.parse(date));
	}

	private boolean isValidMonth(String month) {
		boolean isValid = false;
		for (Month m : Month.values()) {
			if (m.toString().equals(month)) {
				isValid = true;
			}
		}
		return isValid;
	}

	private boolean isNotPastMonth(String month) {
		return LocalDate.now().getMonth().compareTo(Month.valueOf(month)) <= 0 ? true : false;
	}

}
