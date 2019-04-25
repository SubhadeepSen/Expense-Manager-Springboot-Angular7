import { Component, OnInit, Injectable } from "@angular/core";
import { MonthlyComponent } from "./monthly/monthly.component";
import { DailyComponent } from "./daily/daily.component";
import { YearlyComponent } from "./yearly/yearly.component";
import { SharedDataService } from "src/app/common/shared-data.service";

@Component({
  selector: "app-dashboard",
  templateUrl: "./dashboard.component.html",
  styleUrls: ["./dashboard.component.css"]
})
export class DashboardComponent implements OnInit {
  private dailyMonthlyYearly: string = "Monthly";
  constructor(
    private monthlyComponent: MonthlyComponent,
    private dailyComponent: DailyComponent,
    private yearlyComponent: YearlyComponent,
    private sharedData: SharedDataService
  ) {}

  ngOnInit() {}

  onClickDailyExpense() {
    this.dailyMonthlyYearly = "Daily";
    this.sharedData.dailyMonthlyYearly = 'Daily';
    this.dailyComponent.onChangeDay();
  }

  onClickMonthlyExpense() {
    this.dailyMonthlyYearly = "Monthly";
    this.sharedData.dailyMonthlyYearly = 'Monthly';
    this.monthlyComponent.onChangeMonth();
  }

  onClickYearlyExpense() {
    this.dailyMonthlyYearly = "Yearly";
    this.sharedData.dailyMonthlyYearly = 'Yearly';
    this.yearlyComponent.onChangeYear();
  }
}
