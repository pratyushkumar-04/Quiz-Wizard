import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent {

  constructor(private router : Router){}
  
  viewcategories()
  {
    this.router.navigate(['/user/categories']);
  }
  viewresults()
  {
    this.router.navigate(['user/results']);
  }
  viewleaderboard()
  {
    this.router.navigate(['/user/leaderboard']);
  }
  update()
  {
    this.router.navigate(['/user/update']);
  }
  password()
  {
    this.router.navigate(['/user/changepassword']);
  }
}
