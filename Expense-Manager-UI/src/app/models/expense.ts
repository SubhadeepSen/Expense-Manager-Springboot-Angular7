export class Expense {
  yearlyExpense: YearlyExpense;
  constructor() {}
}

export class YearlyExpense {
  year: String;
  totalExpense: number;
  monthlyExpenses: MonthlyExpense[];
  constructor() {}
}

export class MonthlyExpense {
  month: String;
  monthlyBadgetLimit: number;
  totalExpense: number;
  dailyExpenses: DailyExpense[];
  constructor() {}
}

export class DailyExpense {
  amount: number;
  category: String;
  date: Date;
  remark: String;
  constructor() {}
}
