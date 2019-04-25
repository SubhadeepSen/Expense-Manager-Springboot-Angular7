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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@RequestMapping("/expense/api")
@Api
public class ExpenseManagerController {

	@Autowired
	private IExpenseManagerService expenseManagerService;

	@ApiOperation(value = "Add Daily Expense", response = DailyExpense.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Content-Type", value = "application/json", required = true, dataType = "string", paramType = "header") })
	@RequestMapping(value = "/addDailyExpense", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public DailyExpense addDailyExpense(@RequestBody DailyExpense dailyExpense) {
		return expenseManagerService.addDailyExpense(dailyExpense);
	}

	@ApiOperation(value = "Set Monthly Budget Limit", response = Boolean.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "month", value = "JANUARY", required = true, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "budgetLimit", value = "0", required = true, dataType = "double", paramType = "path") })
	@RequestMapping(value = "/setBudgetLimit/{month}/{budgetLimit}", method = RequestMethod.POST)
	public boolean setMonthlyBadgetLimit(@PathVariable String month, @PathVariable double budgetLimit) {
		return expenseManagerService.setMonthlyBadgetLimit(month, budgetLimit);
	}

	@ApiOperation(value = "Set Current Month's Budget Limit", response = Boolean.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "budgetLimit", value = "0", required = true, dataType = "double", paramType = "path") })
	@RequestMapping(value = "/setBudgetLimit/{budgetLimit}", method = RequestMethod.POST)
	public boolean setMonthlyBadgetLimit(@PathVariable double budgetLimit) {
		return expenseManagerService.setMonthlyBadgetLimit("", budgetLimit);
	}

	@ApiOperation(value = "Retrieve Daily Expense", response = List.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "date", value = "YYYY-MM-DD", required = true, dataType = "string", paramType = "path") })
	@RequestMapping(value = "/retrieveExpensesByDate/{date}", method = RequestMethod.GET, produces = "application/json")
	public List<DailyExpense> retrieveExpensesByDate(@PathVariable String date) {
		return expenseManagerService.retrieveExpensesByDate(date);
	}

	@ApiOperation(value = "Retrieve Monthly Expense", response = List.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "month", value = "JANUARY", required = true, dataType = "string", paramType = "path") })
	@RequestMapping(value = "/retrieveExpensesByMonth/{month}", method = RequestMethod.GET, produces = "application/json")
	public List<MonthlyExpense> retrieveExpensesByMonth(@PathVariable String month) {
		return expenseManagerService.retrieveExpensesByMonth(month);
	}

	@ApiOperation(value = "Retrieve Yearly Expense", response = List.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "year", value = "YYYY", required = true, dataType = "integer", paramType = "path") })
	@RequestMapping(value = "/retrieveExpensesByYear/{year}", method = RequestMethod.GET, produces = "application/json")
	public List<Expense> retrieveExpensesByYear(@PathVariable int year) {
		return expenseManagerService.retrieveExpensesByYear(year);
	}

	@ApiOperation(value = "Retrieve All Expenses", response = List.class)
	@RequestMapping(value = "/retrieveAllExpenses", method = RequestMethod.GET, produces = "application/json")
	public List<Expense> retrieveExpenses() {
		return expenseManagerService.retrieveExpenses();
	}
}
