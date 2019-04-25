import { Component, OnInit } from "@angular/core";

@Component({
  selector: "app-expense",
  templateUrl: "./expense.component.html",
  styleUrls: ["./expense.component.css"]
})
export class ExpenseComponent implements OnInit {
  private addExpenseOrSetBudget: String = "Add Expense";
  constructor() {}

  ngOnInit() {}

  onClickaddExpense(){
    this.addExpenseOrSetBudget = "Add Expense";
  }

  onClickSetBudget(){
    this.addExpenseOrSetBudget = "Set Budget";
  }
}
