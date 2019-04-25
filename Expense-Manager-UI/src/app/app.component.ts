import { Component } from "@angular/core";
import { HostListener } from "@angular/core";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.css"]
})
export class AppComponent {
  paddingLeft = "60px";

  @HostListener("window:resize", ["$event"])
  onResize(event: Event) {
    let width = window.innerWidth;
    if (width < 768) {
      this.paddingLeft = '';
    } else {
      this.paddingLeft = '60px';
    }
  }
}
