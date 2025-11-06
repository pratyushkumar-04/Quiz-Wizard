import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent {

  constructor(private router :  Router){}
    createcat()
  {
    this.router.navigate(['/admin/createcategory']);
  }

  addquestion()
  {
    this.router.navigate(['/admin/createquestion']);
  }

  
userlist()
{
  this.router.navigate(['/admin/userlist']);
}

viewcategories()
{
  this.router.navigate(['/admin/categories']);
}
leaderboard()
{
  this.router.navigate(['admin/topperformers']);
}
}
