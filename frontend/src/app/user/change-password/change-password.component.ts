import { Component } from '@angular/core';
import { UserserviceService } from '../shared/userservice.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent {


    passwordData = {
    oldpwd: '',
    newpwd: '',
    confirmPassword: ''
  };

  successMessage = '';
  errorMessage = '';

  constructor(private uservice: UserserviceService) {}

  onUpdatePassword() {
  this.uservice.changepassword(this.passwordData).subscribe(
    res => {
      this.successMessage = res; // should be "Password updated successfully"
      this.errorMessage = '';
    },
    err => {
      this.errorMessage = err.error || 'Error updating password.';
      this.successMessage = '';
    }
  );
}
}
