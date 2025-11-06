import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthserviceService } from 'src/app/shared/authservice.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
loginData={

  username:'',
  password:''
};

constructor(private router :Router ,private authservice:AuthserviceService){
}

onLogin() {
  this.authservice.login(this.loginData).subscribe({
    next: (res: any) => {
      const token = res.token;
      localStorage.setItem('token', token);
      const role = this.authservice.getRoleFromToken(token);
      if (role === 'ROLE_ADMIN') {
        this.router.navigate(['/admin/dashboard']);
      } else {
        this.router.navigate(['/user/dashboard']);
      }
    },
    error: (err) => {
      console.error('Login failed:', err);
      alert(`Login failed: ${err.status} - ${err.message}`);
    }
  });
}



}
