package expense.manager.model;

import java.util.List;

public class MonthlyExpense {
	private String month;
	private double monthlyBadgetLimit;
	private double totalExpense;
	private List<DailyExpense> dailyExpenses;

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public double getMonthlyBadgetLimit() {
		return monthlyBadgetLimit;
	}

	public void setMonthlyBadgetLimit(double monthlyBadgetLimit) {
		this.monthlyBadgetLimit = monthlyBadgetLimit;
	}

	public double getTotalExpense() {
		return totalExpense;
	}

	public void setTotalExpense(double totalExpense) {
		this.totalExpense = totalExpense;
	}

	public List<DailyExpense> getDailyExpenses() {
		return dailyExpenses;
	}

	public void setDailyExpenses(List<DailyExpense> dailyExpenses) {
		this.dailyExpenses = dailyExpenses;
	}

}
