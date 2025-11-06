import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthserviceService } from 'src/app/shared/authservice.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  registerData = {
    username: '',
    password: '',
    email:''
  };

  constructor(private authservice: AuthserviceService, private router: Router) {}


  onRegister() {
    this.authservice.register(this.registerData).subscribe({
      next: (res: { message: string }) => {
        if (res.message === 'User registered successfully') {
          alert(res.message);
          this.router.navigate(['/login']);
        } else {
          console.error('Unexpected success message:', res.message);
          alert('Registration failed due to an unexpected response');
        }
      },
      error: (err) => {
        const errorMessage = err.error?.message || err.message || 'Unknown error';
        console.error('Registration failed:', err);
        alert(` ${errorMessage}`);
        
      }
    });
  }
}