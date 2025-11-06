import { Component } from '@angular/core';
import { UserserviceService } from '../shared/userservice.service';
import { AuthserviceService } from 'src/app/shared/authservice.service';

@Component({
  selector: 'app-update-profile',
  templateUrl: './update-profile.component.html',
  styleUrls: ['./update-profile.component.css']
})
export class UpdateProfileComponent {

  constructor(private uservice: UserserviceService, private authservice: AuthserviceService) { }
  user = {
    email: '',
    username: ''
  };

  successMessage = '';
  errorMessage = '';

  ngOnInit(): void {
    const token = this.authservice.getToken();
    if (token) {
      const payload = JSON.parse(atob(token.split('.')[1]));
      this.user.email = payload.email;
      this.user.username = payload.sub;
    }
  }


onUpdateProfile() {
  this.uservice.updateProfile(this.user).subscribe(
    res => {
      console.log('Response:', res);
      this.successMessage = 'Profile updated successfully!';
      this.errorMessage = '';
    },
    err => {
      console.error('Error:', err);
      this.errorMessage = err.error?.message || 'Error updating profile.';
      this.successMessage = '';
    }
  );
}
}
