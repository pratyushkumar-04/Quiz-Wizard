import { Component } from '@angular/core';
import { AdminService } from '../admin.service';

@Component({
  selector: 'app-top-performers',
  templateUrl: './top-performers.component.html',
  styleUrls: ['./top-performers.component.css']
})
export class TopPerformersComponent {

  constructor(private admservice : AdminService){}
  leaderboard:any[]=[];

  ngOnInit()
  {
    this.gettopperformers();
  }


  gettopperformers()
  {
    this.admservice.getleaderboard().subscribe(
      data=> this.leaderboard=data,
        err => console.error('Error loading leaderboard:', err)
    );
  }
}
