import { Component, OnInit, Injectable } from "@angular/core";
import { HttpErrorResponse } from "@angular/common/http";

import { ExpenseManagerService } from "src/app/services/expense-manager.service";
import { YearlyExpense, Expense } from "src/app/models/expense";

@Component({
  selector: "app-yearly",
  templateUrl: "./yearly.component.html",
  styleUrls: ["./yearly.component.css"]
})
@Injectable({
  providedIn: "root"
})
export class YearlyComponent implements OnInit {
  private year: number = 0;
  private years:number[] = [];
  private expense: Expense[] = [];
  private yearlyExpense: YearlyExpense;

  constructor(private expenseManagerService: ExpenseManagerService) {
    let thisYear = new Date().getFullYear();
    this.year = thisYear;
    thisYear = thisYear - 10;
    for (let yr = thisYear; yr <= thisYear + 10; yr++) {
      this.years.push(yr);
    }
    this.onChangeYear();
  }

  ngOnInit() {}

  onChangeYear() {
    this.expenseManagerService.retrieveExpensesByYear(this.year).subscribe((res: Expense[]) => {
      this.expense = res;
      if(res.length != 0){
        this.yearlyExpense = res[0].yearlyExpense;
      }
    },
    (httpErr: HttpErrorResponse) => {
      console.log(httpErr.status + ': ' + httpErr.message);
    });
  }
}
