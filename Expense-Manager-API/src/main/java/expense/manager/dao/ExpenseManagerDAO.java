package expense.manager.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import expense.manager.model.DailyExpense;
import expense.manager.model.Expense;
import expense.manager.model.MonthlyExpense;
import expense.manager.model.YearlyExpense;

@Component
public class ExpenseManagerDAO implements IExpenseManagerDAO {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public DailyExpense addDailyExpense(DailyExpense dailyExpense) {
		if (!mongoTemplate.collectionExists(Expense.class)) {
			mongoTemplate.createCollection(Expense.class);
		}
		Expense result = null;
		String month = dailyExpense.getDate().getMonth().toString();
		int year = dailyExpense.getDate().getYear();
		List<Expense> expensesByYear = getExpensesByYear(year);
		if (CollectionUtils.isEmpty(expensesByYear)) {
			Expense expense = buildExpense(dailyExpense, month, year);
			result = mongoTemplate.insert(expense);
		} else {
			List<Expense> expensesByMonth = getExpenses(month, year);
			if (CollectionUtils.isEmpty(expensesByMonth)) {
				MonthlyExpense monthlyExpense = buildMonthlyExpense(dailyExpense, month);
				Query query = new Query();
				query.addCriteria(Criteria.where("yearlyExpense.year").is(String.valueOf(year)));
				Update update = new Update();
				update.push("yearlyExpense.monthlyExpenses", monthlyExpense);
				update.set("yearlyExpense.totalExpense",
						expensesByYear.get(0).getYearlyExpense().getTotalExpense() + dailyExpense.getAmount());
				result = mongoTemplate.findAndModify(query, update, Expense.class);
			} else {
				YearlyExpense yearlyExpense = expensesByMonth.get(0).getYearlyExpense();
				int monthIndex = getDBMonthIndex(yearlyExpense, month);
				Query query = buildQuery(month, year);
				Update update = new Update();
				update.push("yearlyExpense.monthlyExpenses." + monthIndex + ".dailyExpenses", dailyExpense);
				update.set("yearlyExpense.monthlyExpenses." + monthIndex + ".totalExpense",
						yearlyExpense.getMonthlyExpenses().get(monthIndex).getTotalExpense()
								+ dailyExpense.getAmount());
				update.set("yearlyExpense.totalExpense", yearlyExpense.getTotalExpense() + dailyExpense.getAmount());
				result = mongoTemplate.findAndModify(query, update, Expense.class);
			}
		}
		if (null != result) {
			return dailyExpense;
		}
		return null;
	}

	@Override
	public boolean setMonthlyBadgetLimit(String month, double monthlyBadgetLimit) {
		boolean isBudgetLimitSet = false;
		Expense result = null;
		int year = LocalDate.now().getYear();
		if (null == month) {
			month = LocalDate.now().getMonth().toString();
		}
		List<Expense> expensesByYear = getExpensesByYear(year);
		if (CollectionUtils.isEmpty(expensesByYear)) {
			DailyExpense dailyExpense = new DailyExpense();
			Expense expense = buildExpense(dailyExpense, month, year);
			expense.getYearlyExpense().getMonthlyExpenses().get(0).setMonthlyBadgetLimit(monthlyBadgetLimit);
			expense.getYearlyExpense().getMonthlyExpenses().get(0).setDailyExpenses(new ArrayList<>());
			result = mongoTemplate.insert(expense);
		} else {
			YearlyExpense yearlyExpense = expensesByYear.get(0).getYearlyExpense();
			int monthIndex = getDBMonthIndex(yearlyExpense, month);
			Query query = buildQuery(month, year);
			Update update = new Update();
			update.set("yearlyExpense.monthlyExpenses." + monthIndex + ".monthlyBadgetLimit", monthlyBadgetLimit);
			result = mongoTemplate.findAndModify(query, update, Expense.class);
		}
		if (null != result) {
			isBudgetLimitSet = true;
		}
		return isBudgetLimitSet;
	}

	@Override
	public List<MonthlyExpense> retrieveMonthlyExpense(String month) {
		List<Expense> expenses = getExpenses(month, LocalDate.now().getYear());
		if (expenses.size() > 0) {
			return expenses.get(0).getYearlyExpense().getMonthlyExpenses().stream()
					.filter(me -> me.getMonth().equalsIgnoreCase(month)).collect(Collectors.toList());
		}
		return new ArrayList<>();
	}

	@Override
	public List<Expense> retrieveYearlyExpenses(int year) {
		return getExpensesByYear(year);
	}

	@Override
	public List<Expense> retrieveExpenses() {
		return mongoTemplate.findAll(Expense.class);

	}

	@Override
	public List<DailyExpense> retrieveDailyExpenses(LocalDate date) {
		List<DailyExpense> dailyExpenses = new ArrayList<>();
		List<MonthlyExpense> monthlyExpenses = retrieveMonthlyExpense(date.getMonth().toString());
		if (monthlyExpenses.size() > 0) {
			for (DailyExpense dailyExpense : monthlyExpenses.get(0).getDailyExpenses()) {
				if (dailyExpense.getDate().compareTo(date) == 0) {
					dailyExpenses.add(dailyExpense);
				}
			}
		}
		return dailyExpenses;
	}

	private int getDBMonthIndex(YearlyExpense yearlyExpense, String month) {
		int monthIndex = 0;
		for (MonthlyExpense expense : yearlyExpense.getMonthlyExpenses()) {
			if (expense.getMonth().equals(month)) {
				break;
			}
			monthIndex++;
		}
		return monthIndex;
	}

	private Expense buildExpense(DailyExpense dailyExpense, String month, int year) {
		Expense expense = new Expense();
		MonthlyExpense monthlyExpense = new MonthlyExpense();
		monthlyExpense.setMonth(month);
		monthlyExpense.setMonthlyBadgetLimit(0);
		monthlyExpense.setDailyExpenses(Arrays.asList(dailyExpense));
		monthlyExpense.setTotalExpense(dailyExpense.getAmount());
		YearlyExpense yearlyExpense = new YearlyExpense();
		yearlyExpense.setMonthlyExpenses(Arrays.asList(buildMonthlyExpense(dailyExpense, month)));
		yearlyExpense.setTotalExpense(dailyExpense.getAmount());
		yearlyExpense.setYear(String.valueOf(year));
		expense.setYearlyExpense(yearlyExpense);
		return expense;
	}

	private MonthlyExpense buildMonthlyExpense(DailyExpense dailyExpense, String month) {
		MonthlyExpense monthlyExpense = new MonthlyExpense();
		monthlyExpense.setMonth(month);
		monthlyExpense.setMonthlyBadgetLimit(0);
		monthlyExpense.setDailyExpenses(Arrays.asList(dailyExpense));
		monthlyExpense.setTotalExpense(dailyExpense.getAmount());
		return monthlyExpense;
	}

	private List<Expense> getExpenses(String month, int year) {
		Query query = buildQuery(month, year);
		return mongoTemplate.find(query, Expense.class);
	}

	private List<Expense> getExpensesByYear(int year) {
		Query query = new Query();
		query.addCriteria(Criteria.where("yearlyExpense.year").is(String.valueOf(year)));
		return mongoTemplate.find(query, Expense.class);
	}

	private Query buildQuery(String month, int year) {
		Query query = new Query();
		query.addCriteria(Criteria.where("yearlyExpense.year").is(String.valueOf(year))
				.andOperator(Criteria.where("yearlyExpense.monthlyExpenses.month").is(month)));
		return query;
	}

}
