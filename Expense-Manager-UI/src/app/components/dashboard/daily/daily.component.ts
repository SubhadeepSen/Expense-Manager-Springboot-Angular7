import { Component, OnInit, Injectable } from "@angular/core";
import {HttpErrorResponse} from "@angular/common/http";

import { DailyExpense } from "src/app/models/expense";
import { ExpenseManagerService } from "src/app/services/expense-manager.service";

@Component({
  selector: "app-daily",
  templateUrl: "./daily.component.html",
  styleUrls: ["./daily.component.css"]
})
@Injectable({
  providedIn: "root"
})
export class DailyComponent implements OnInit {
  private date: string = "";
  private dailyExpenses: DailyExpense[] = [];

  constructor(private expenseManagerService: ExpenseManagerService) {
    this.setToday();
    this.onChangeDay();
  }

  ngOnInit() {}

  setToday() {
    let date = new Date();
    let year = date.getFullYear();
    let month: any = date.getMonth() + 1;
    let day = date.getDate();
    month = month < 10 ? "0" + month : month;
    let today = year + "-" + month + "-" + day;
    this.date = today;
  }

  onChangeDay() {
    this.expenseManagerService
      .retrieveExpensesByDate(this.date)
      .subscribe((res: DailyExpense[]) => {
        this.dailyExpenses = res;
      },(httpErr: HttpErrorResponse) => {
        console.log(httpErr.status + ': ' + httpErr.message);
      });
  }
}
