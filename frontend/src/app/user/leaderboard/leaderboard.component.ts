import { Component } from '@angular/core';
import { UserserviceService } from '../shared/userservice.service';

@Component({
  selector: 'app-leaderboard',
  templateUrl: './leaderboard.component.html',
  styleUrls: ['./leaderboard.component.css']
})
export class LeaderboardComponent {

  leaderboard: any[] = [];

  constructor(private uservice : UserserviceService){}
  ngOnInit() {
  this.uservice.getGlobalLeaderboard().subscribe(
    data => this.leaderboard = data,
    err => console.error('Error loading leaderboard:', err)
  );
}
}
