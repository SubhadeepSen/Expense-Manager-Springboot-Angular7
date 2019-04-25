import { Component, OnInit, Injectable } from "@angular/core";
import { HttpErrorResponse } from "@angular/common/http";

import { ExpenseManagerService } from "src/app/services/expense-manager.service";
import { MonthlyExpense } from "src/app/models/expense";
import { SharedDataService } from "src/app/common/shared-data.service";

@Component({
  selector: "app-monthly",
  templateUrl: "./monthly.component.html",
  styleUrls: ["./monthly.component.css"]
})
@Injectable({
  providedIn: "root"
})
export class MonthlyComponent implements OnInit {
  private month: String = "";
  private months: String[];

  private monthlyExpenses: MonthlyExpense[] = [];
  private isLimitCrossed: boolean = false;
  
  constructor(private expenseManagerService: ExpenseManagerService, private sharedData: SharedDataService) {
    this.months = this.sharedData.months;
    this.setCurrentMonth();
    this.onChangeMonth();
  }

  ngOnInit() {}
  setCurrentMonth() {
    let monthIndex = new Date().getMonth();
    this.months.forEach(m => {
      if (m === this.months[monthIndex]) {
        this.month = m;
      }
    });
  }

  onChangeMonth() {
    this.expenseManagerService.retrieveExpensesByMonth(this.month).subscribe(
      (res: MonthlyExpense[]) => {
        this.monthlyExpenses = res;
        if (res.length != 0) {
          this.isLimitCrossed =
            res[0].totalExpense > res[0].monthlyBadgetLimit ? true : false;
        }
      },
      (httpErr: HttpErrorResponse) => {
        console.log(httpErr.status + ': ' + httpErr.message);
      }
    );
  }
}
