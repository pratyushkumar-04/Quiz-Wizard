import { Component, OnInit } from '@angular/core';
import { AuthserviceService } from 'src/app/shared/authservice.service';
import { UserserviceService } from '../shared/userservice.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  user: any = {};
  currentQuote: string = '';
  currentFact: string = '';

  quotes: string[] = [
    "Knowledge is power. — Francis Bacon",
    "Learn something new every day.",
    "Small progress is still progress.",
    "Be curious, not judgmental. – Ted Lasso"
  ];

  facts: string[] = [
    "The Eiffel Tower can grow over 6 inches during summer.",
    "Octopuses have three hearts and nine brains.",
    "Honey never spoils. Archaeologists found 3000-year-old honey in Egyptian tombs!",
    "Bananas are berries, but strawberries aren't."
  ];

  constructor(private uservice: UserserviceService, private authservice: AuthserviceService) { }

  ngOnInit() {
    this.loadUserDetails();
    this.initRotations();
  }

 loadUserDetails() {
  this.uservice.getProfile().subscribe(
    (res: any) => {
      this.user = res;
    },
    err => {
      console.error('Failed to load profile:', err);
    }
  );
}

  initRotations() {
    this.currentQuote = this.quotes[0];
    this.currentFact = this.facts[0];

    setInterval(() => {
      const qIdx = Math.floor(Math.random() * this.quotes.length);
      this.currentQuote = this.quotes[qIdx];
    }, 5000);

    setInterval(() => {
      const fIdx = Math.floor(Math.random() * this.facts.length);
      this.currentFact = this.facts[fIdx];
    }, 8000);
  }
}
