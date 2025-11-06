import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';
import { AuthserviceService } from '../authservice.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit{

  userRole : string='';
  constructor(private router:Router,private authservice:AuthserviceService){}

  ngOnInit(): void {
      if(this.authservice.isLoggedIn())
      {
        this.userRole=this.authservice.getRoleFromToken(this.authservice.getToken()!);
      }
  }
    isLoggedIn(): boolean {
    return this.authservice.isLoggedIn();
  }

  logout():void{
    this.authservice.logout();
    this.userRole='no';
    this.router.navigate(['']);
  }
}
