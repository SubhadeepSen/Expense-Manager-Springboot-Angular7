import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { MonthlyComponent } from './components/dashboard/monthly/monthly.component';
import { YearlyComponent } from './components/dashboard/yearly/yearly.component';
import { AddExpenseComponent } from './components/expense/add-expense/add-expense.component';
import { SetBudgetComponent } from './components/expense/set-budget/set-budget.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ExpenseComponent } from './components/expense/expense.component';
import { DailyComponent } from './components/dashboard/daily/daily.component';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    MonthlyComponent,
    YearlyComponent,
    AddExpenseComponent,
    SetBudgetComponent,
    DashboardComponent,
    ExpenseComponent,
    DailyComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
