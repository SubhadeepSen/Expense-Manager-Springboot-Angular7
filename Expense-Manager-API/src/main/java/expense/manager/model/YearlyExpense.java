package expense.manager.model;

import java.util.List;

public class YearlyExpense {
	private String year;
	private double totalExpense;
	private List<MonthlyExpense> monthlyExpenses;

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public double getTotalExpense() {
		return totalExpense;
	}

	public void setTotalExpense(double totalExpense) {
		this.totalExpense = totalExpense;
	}

	public List<MonthlyExpense> getMonthlyExpenses() {
		return monthlyExpenses;
	}

	public void setMonthlyExpenses(List<MonthlyExpense> monthlyExpenses) {
		this.monthlyExpenses = monthlyExpenses;
	}

}
