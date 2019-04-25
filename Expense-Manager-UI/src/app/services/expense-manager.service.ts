import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";
import { Expense, MonthlyExpense, DailyExpense } from "../models/expense";

@Injectable({
  providedIn: "root"
})
export class ExpenseManagerService {
  private apiUrl: String = "http://localhost:8089/ExpenseManager/expense/api";
  private emptyBody: String = "";

  constructor(private http: HttpClient) {}

  setCurrentMonthBudgetLimit(budgetLimit: Number): Observable<Boolean> {
    return this.http.post<Boolean>(
      this.apiUrl + "/setBudgetLimit/" + budgetLimit,
      this.emptyBody
    );
  }

  setBudgetLimitByMonth(
    month: String,
    budgetLimit: Number
  ): Observable<Boolean> {
    return this.http.post<Boolean>(
      this.apiUrl + "/setBudgetLimit/" + month + "/" + budgetLimit,
      this.emptyBody
    );
  }

  retrieveExpensesByYear(year: Number): Observable<Expense[]> {
    return this.http.get<Expense[]>(
      this.apiUrl + "/retrieveExpensesByYear/" + year
    );
  }

  retrieveExpensesByMonth(month: String): Observable<MonthlyExpense[]> {
    let headers = new HttpHeaders()
      .set("Content-Type", "application/json")
      .set("Accept", "application/json");
    return this.http.get<MonthlyExpense[]>(
      this.apiUrl + "/retrieveExpensesByMonth/" + month,
      { headers }
    );
  }

  retrieveExpensesByDate(date: String): Observable<DailyExpense[]> {
    let headers = new HttpHeaders()
      .set("Content-Type", "application/json")
      .set("Accept", "application/json");
    return this.http.get<DailyExpense[]>(
      this.apiUrl + "/retrieveExpensesByDate/" + date,
      { headers }
    );
  }

  addDailyExpense(expense: String): Observable<Object> {
    let headers = new HttpHeaders()
      .set("Content-Type", "application/json")
      .set("Accept", "application/json");
    return this.http.post(this.apiUrl + "/addDailyExpense", expense, {
      headers
    });
  }
}
