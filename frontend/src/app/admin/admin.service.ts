import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }

  private base_url = 'http://localhost:8081/admin';


  hellow() {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    this.http.get(`${this.base_url}/pannel`, { headers, responseType: 'text' })
      .subscribe({
        next: (res) => {
          alert(res);
        }
        , error: (err) => {
          alert('Error calling secured API');
        }


      })
  }
  createcategory(category: any) {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.post(`${this.base_url}/category/create`, category, { headers, responseType: 'text' as const });

  }
  getAllCategories() {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<any[]>(`${this.base_url}/category/all`, { headers });
  }
  createQuestion(question: any) {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.post(`${this.base_url}/question/create`, question, { headers, responseType: 'text' as const });
  }

  getQuestionsByCategory(categoryId: any) {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get(`${this.base_url}/question/by-category/${categoryId}`, { headers });
  }

  getallusers(): Observable<any> {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<any[]>(`${this.base_url}/users`, { headers });
  }
  deleteuser(userId: number) {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.delete(`${this.base_url}/users/${userId}`, { headers, responseType: 'text' as const });
  }

  getleaderboard(): Observable<any[]> {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<any[]>(`${this.base_url}/leaderboard`, { headers });
  }
}
