import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthserviceService } from 'src/app/shared/authservice.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})

export class HomeComponent {

  loggedin:boolean=false;
  constructor(private router :Router,private auth:AuthserviceService){}

  ngOnInit()
  {
    this.loggedin=this.auth.isLoggedInforquiz()
  }
  
  categories()
  {
    if(this.loggedin)
    {
    this.router.navigate(['/user/categories']);
    }
    else{
      this.router.navigate(['/login']);
    }
  }
  register()
  {
    this.router.navigate(['/register']);
  }
}
