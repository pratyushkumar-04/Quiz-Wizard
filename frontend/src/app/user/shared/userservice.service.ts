import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';

interface QuestionAnswerDTO {
  questionId: number;
  selectedAnswer: string;
  correctAnswer: string;
  isCorrect: boolean;
}

interface ResultDTO {
  categoryId: number;
  score: number;
  questions: number;
  correctAnswers: number;
  questionResults: QuestionAnswerDTO[];
}

@Injectable({
  providedIn: 'root'
})
export class UserserviceService {
  private base_url = 'http://localhost:8081/user'; // Verify port (8081 vs. 8080)

  constructor(private http: HttpClient) { }

  getcategories(): Observable<any[]> {
    return this.makeGetRequest(`${this.base_url}/categories`);
  }

  getquestions(categoryID: number): Observable<any[]> {
    return this.makeGetRequest(`${this.base_url}/quiz/start/${categoryID}`);
  }

  getPastResults(): Observable<ResultDTO[]> {
    return this.makeGetRequest(`${this.base_url}/result/previousattempts`);
  }

  submitResult(result: ResultDTO): Observable<ResultDTO> {
    const token = localStorage.getItem('token');
    if (!token) {
      return throwError(() => new Error('No authentication token found'));
    }

    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });

    return this.http.post<ResultDTO>(`${this.base_url}/result/save`, result, { headers });
  }

  getCategory(id: number): Observable<any> {
    return this.makeGetRequest(`${this.base_url}/category/${id}`);
  }

  getResultById(resultId: number) {
    return this.makeGetRequest(`${this.base_url}/result/${resultId}`);
  }
  getGlobalLeaderboard(): Observable<any[]> {
    return this.makeGetRequest(`${this.base_url}/result/leaderboard`);
  }

  updateProfile(data: any) {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.put(`${this.base_url}/update-profile`, data,{headers});
  }
   changepassword(data: any) {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.put(`${this.base_url}/updatepassword`, data,{headers,responseType: 'text'});
  }

  getProfile() {
  const token = localStorage.getItem('token');
  const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
  return this.http.get(`${this.base_url}/profile`, { headers });
}

  private makeGetRequest<T>(url: string): Observable<T> {
    const token = localStorage.getItem('token');
    if (!token) {
      return throwError(() => new Error('No authentication token found'));
    }
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<T>(url, { headers });
  }
}