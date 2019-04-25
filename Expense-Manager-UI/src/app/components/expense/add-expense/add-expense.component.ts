import { Component, OnInit } from "@angular/core";
import { ExpenseManagerService } from "src/app/services/expense-manager.service";
import { DailyExpense } from "src/app/models/expense";

import { HttpErrorResponse } from "@angular/common/http";

@Component({
  selector: "app-add-expense",
  templateUrl: "./add-expense.component.html",
  styleUrls: ["./add-expense.component.css"]
})
export class AddExpenseComponent implements OnInit {
  private category: String = "Food";
  private otherCategory: String = "";
  private amount: number = 0.0;
  private date: any = "";
  private remark: String = "";
  private dailyExpense: DailyExpense;

  private isAdded: boolean = false;
  private isError: boolean = false;
  private errorMsg: string = "";

  constructor(private expenseManagerService: ExpenseManagerService) {
    this.setToday();
  }

  ngOnInit() {}

  onClickAddExpense() {
    this.dailyExpense = new DailyExpense();
    this.dailyExpense.amount = this.amount;
    if (this.category === "Other") {
      this.dailyExpense.category = this.otherCategory;
    } else {
      this.dailyExpense.category = this.category;
    }
    this.dailyExpense.date = this.date;
    this.dailyExpense.remark = this.remark;

    this.expenseManagerService
      .addDailyExpense(JSON.stringify(this.dailyExpense))
      .subscribe(
        (res: DailyExpense) => {
          this.isAdded = true;
        },
        (httpErr: HttpErrorResponse) => {
          console.log(httpErr.status + ': ' + httpErr.message);
          this.errorMsg = 'Something went wrong...Please try later.';
          this.isError = true;
        }
      );
  }

  setToday() {
    let date = new Date();
    let year = date.getFullYear();
    let month: any = date.getMonth() + 1;
    let day = date.getDate();
    month = month < 10 ? "0" + month : month;
    let today = year + "-" + month + "-" + day;
    this.date = today;
  }

  hideNotification() {
    this.isAdded = false;
    this.isError = false;
  }
}
