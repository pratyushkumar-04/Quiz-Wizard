import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthserviceService {
  private baseUrl = 'http://localhost:8081/auth';

  constructor(private http: HttpClient) {}

  login(user: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/login`, user, {
      headers: { 'Content-Type': 'application/json' }

    });
  }


  register(data: { username: string; password: string }): Observable<any> {
    return this.http.post(`${this.baseUrl}/register`, data, {
      headers: { 'Content-Type': 'application/json' }
    });
  }

  getRoleFromToken(token: string): string {
    try {
      const payload = JSON.parse(atob(token.split('.')[1]));
      return payload.role || 'ROLE_USER';
    } catch (e) {
      console.error('Invalid token:', e);
      return '';
    }
  }

  getToken() {
    return localStorage.getItem('token');
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }
   isLoggedInforquiz(): boolean {
    return !!localStorage.getItem('token');
  }

  logout() {
    localStorage.removeItem('token');
  }
  
}