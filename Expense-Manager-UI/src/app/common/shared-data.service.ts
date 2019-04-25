import { Injectable } from "@angular/core";

@Injectable({
  providedIn: "root"
})
export class SharedDataService {
  months: String[] = [
    "JANUARY",
    "FEBRUARY",
    "MARCH",
    "APRIL",
    "MAY",
    "JUNE",
    "JULY",
    "AUGUST",
    "SEPTEMBER",
    "OCTOBER",
    "NOVEMBER",
    "DECEMBER"
  ];

  dailyMonthlyYearly: string = "Monthly";

  constructor() {}
}
