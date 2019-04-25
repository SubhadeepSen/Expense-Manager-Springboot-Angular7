import { Component, OnInit } from "@angular/core";
import { HttpErrorResponse } from "@angular/common/http";

import { ExpenseManagerService } from "src/app/services/expense-manager.service";
import { SharedDataService } from "src/app/common/shared-data.service";

@Component({
  selector: "app-set-budget",
  templateUrl: "./set-budget.component.html",
  styleUrls: ["./set-budget.component.css"]
})
export class SetBudgetComponent implements OnInit {
  private month: String = "";
  private budgetLimit: number = 2000;
  private months: String[];

  private isAdded: boolean = false;
  private isError: boolean = false;
  private errorMsg: string = "";

  constructor(
    private expenseManagerService: ExpenseManagerService,
    private sharedData: SharedDataService
  ) {
    this.months = this.sharedData.months;
    this.setCurrentMonth();
  }

  ngOnInit() {}

  onClickSetBudget() {
    if (this.budgetLimit > 0) {
      this.expenseManagerService
        .setBudgetLimitByMonth(this.month, this.budgetLimit)
        .subscribe(
          res => {
            console.log(res);
            this.isAdded = true;
          },
          (httpErr: HttpErrorResponse) => {
            console.log(httpErr.status + ': ' + httpErr.message);
            this.isError = true;
            this.errorMsg = "Something went wrong... Please try later.";
          }
        );
    } else {
      this.isError = true;
      this.errorMsg = "Please enter a valid amount.";
    }
  }

  setCurrentMonth() {
    let monthIndex = new Date().getMonth();
    this.months.forEach(m => {
      if (m === this.months[monthIndex]) {
        this.month = m;
      }
    });
  }

  hideNotification() {
    this.isAdded = false;
    this.isError = false;
  }
}
